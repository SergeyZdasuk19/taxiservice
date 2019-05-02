ymaps.ready(init);

function init() {

    // Стоимость за километр.
    var DELIVERY_TARIFF = 20,
        // Минимальная стоимость.
        MINIMUM_COST = 500,
        myMap = new ymaps.Map('map', {
            center: [53.9000000, 27.5666700],
            zoom: 9,
            controls: []
        }, {
            searchControlProvider: 'yandex#search'
        }),
        // Создадим панель маршрутизации.
        routePanelControl = new ymaps.control.RoutePanel({
            options: {
                position: {}
            }
        }),
        zoomControl = new ymaps.control.ZoomControl({
            options: {
                size: 'small',
                float: 'none',
                position: {
                    bottom: 145,
                    right: 10
                }
            }
        });
    // Пользователь сможет построить только автомобильный маршрут.
    routePanelControl.routePanel.options.set({
        types: {auto: true}
    });

    // Если вы хотите задать неизменяемую точку "откуда", раскомментируйте код ниже.
    /*routePanelControl.routePanel.state.set({
        fromEnabled: false,
        from: 'Москва, Льва Толстого 16'
     });*/
    var myPlacemark = new ymaps.Placemark([53.937722, 27.462818], {
            balloonContentBody: [

            ].join('')
        },
        {
            preset: 'islands#redDotIcon'
        });
    myMap.geoObjects.add(myPlacemark);


    routePanelControl.routePanel.state.set({
        // Зададим тип маршрутизации - такси.
        type: "taxi",
        // Зададим адрес пункта назначения.
        to: myPlacemark.geometry.getCoordinates(),
        from: routePanelControl.routePanel.geolocate('from'),
        // Отключим возможность задавать пункт отправления в поле ввода.
        toEnabled: true
    });
    myMap.controls.add(routePanelControl).add(zoomControl);
    // $('input[placeholder$="Откуда"]').val(routePanelControl.routePanel.geolocate('from'));
    routePanelControl.routePanel.getRouteAsync().then(function (route) {
        route.model.setParams({results: 1}, true);
        route.model.events.add('requestsuccess', function () {
            var activeRoute = route.getActiveRoute();
            if (activeRoute) {
                var length = route.getActiveRoute().properties.get("distance"),
                    price = calculate(Math.round(length.value / 1000));
                balloonContentLayout = ymaps.templateLayoutFactory.createClass(
                    '<span>Расстояние: ' + length.text + '.</span><br/>' +
                    '<span style="font-weight: bold; font-style: italic">Стоимость доставки: ' + Math.round(price) + ' р.</span>');
                myPlacemark.properties.set('balloonContentBody', '<button type="button" class="btn btn-warning">Aleksey</button><br></br>' + length.text);
                route.options.set('routeBalloonContentLayout', balloonContentLayout);
                activeRoute.balloon.open();
                if ($('input[placeholder$="Откуда"]').val() == '' && $('input[placeholder$="Куда"]').val() == '') {

                }
                else {
                    $('.ymaps-2-1-73-route-panel__clear').click();
                }
            }
        });

    });


    function calculate(routeLength) {
        return Math.max(routeLength * DELIVERY_TARIFF, MINIMUM_COST) * 0.0326;
    }


    // routePanelControl.routePanel.state.set({
    //     // Зададим тип маршрутизации - такси.
    //     type: "taxi",
    //     // Зададим адрес пункта назначения.
    //     from:geolocation,
    //     to: 'Павелецкий вокзал',
    //     // Отключим возможность задавать пункт отправления в поле ввода.
    //     toEnabled: false
    // });

}

function test() {
    alert($('.ymaps-2-1-73-route-panel-input__input').val());
}

function change() {
    // $("[id='newForMap']").each(function (){
    //     $(this).attr("id", "map");
    // });
    $('#map').css({"opacity":'1'});
    $('div.cssload-spin-box').toggleClass('newForAnimation');
    $('div.cssload-spin-box').toggleClass('cssload-spin-box');
    $('div.newForCars').toggleClass('newForCar');
    $('div.newForCars').toggleClass('newForCars');
}
setTimeout(change, 10000);