let map = new Map();
$(document).ready(function () {
    $('.btn.btn-outline-success').click(function () {
        $('input[name$="username"]').val($('.btn.btn-outline-secondary span').text());
        $('input[name$="placeFrom"]').val($('input[placeholder$="Откуда"]').val());
        $('input[name$="placeTo"]').val($('input[placeholder$="Куда"]').val());
    });

});


ymaps.ready(geoFindMe);

function addFirstPriceToModal() {
    $('input[name$="price"]').val(map.get(1));
    $('input[name$="nameTaxi"]').val('Aleksey');

}

function addSecondPriceToModal() {
    $('input[name$="price"]').val(map.get(2));
    $('input[name$="nameTaxi"]').val('Vitaliy');
}

function addThirdPriceToModal() {
    $('input[name$="price"]').val(map.get(3));
    $('input[name$="nameTaxi"]').val('Andrey');
}

function change() {
    $('#map').css({"opacity": '1'});
    $('div.cssload-spin-box').toggleClass('newForAnimation');
    $('div.cssload-spin-box').toggleClass('cssload-spin-box');
    changeTextOfInput();
}

function cl() {
    $('.ymaps-2-1-73-route-panel__clear').click();
}

function addInfoToInputFirst() {
    $('.ymaps-2-1-73-route-panel-input__input').val();
}

function changeTextOfInput() {

    $('input[placeholder$="Откуда"]').setAttribute("onchange", "foo()");
    $('input[placeholder$="Куда"]').setAttribute("onchange", "foo()");
}


function foo() {

    if ($('input[placeholder$="Откуда"]').val() != '' && $('input[placeholder$="Куда"]').val() != '') {
        init($('input[placeholder$="Откуда"]').val(), '');
    }
}

let num = [];
let myPlacemarkFirst;
let myPlacemarkSecond;
let myPlacemarkThird;
let myMap;

function init(latitude, longitude) {
    if (longitude == '') {

        countDistanceWithoutMyPlace();
        return;
    }
    // Стоимость за километр.

    var DELIVERY_TARIFF = 20,
        // Минимальная стоимость.
        MINIMUM_COST = 500,
        myMap = new ymaps.Map('map', {
            center: [53.9, 27.55659],
            zoom: 9,
            controls: []
        }),
        // Создадим панель маршрутизации.
        routePanelControl = new ymaps.control.RoutePanel({
            options: {
                // Добавим заголовок панели.
                showHeader: true

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

    myMap.controls.add(routePanelControl).add(zoomControl);

    // Получим ссылку на маршрут.
    routePanelControl.routePanel.getRouteAsync().then(function (route) {

        // Зададим максимально допустимое число маршрутов, возвращаемых мультимаршрутизатором.
        route.model.setParams({results: 1}, true);

        // Повесим обработчик на событие построения маршрута.
        route.model.events.add('requestsuccess', function () {

            var activeRoute = route.getActiveRoute();
            if (activeRoute) {
                // Получим протяженность маршрута.
                var length = route.getActiveRoute().properties.get("distance"),
                    // Вычислим стоимость доставки.
                    price = calculate(Math.round(length.value / 1000)),
                    // Создадим макет содержимого балуна маршрута.
                    balloonContentLayout = ymaps.templateLayoutFactory.createClass(
                        '<span>Расстояние: ' + length.text + '.</span><br/>' +
                        '<span style="font-weight: bold; font-style: italic">Стоимость доставки: ' + price + ' р.</span>');
                // Зададим этот макет для содержимого балуна.
                route.options.set('routeBalloonContentLayout', balloonContentLayout);
                // Откроем балун.
                activeRoute.balloon.open();
                $('input[name$="price"]').val(price);
            }
        });

    });
    setTimeout(change(),1000);
    // change();
    changeTextOfInput();

    function countDistanceWithoutMyPlace() {

        $('#map').css({"opacity": '0'});
        $('div.newForAnimation').toggleClass('cssload-spin-box');
        $('div.newForAnimation').toggleClass('newForAnimation');
        if ($('input[placeholder$="Откуда"]').val() == '' && $('input[placeholder$="Куда"]').val() == '') {
        }
        else {
            setTimeout(change(),1000);
            changeTextOfInput();
        }


    }

    // Функция, вычисляющая стоимость доставки.
    function calculate(routeLength) {
        return routeLength * 0.5;
    }

}


function geoFindMe() {

    function success(position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;
        init(latitude, longitude);
    };
    navigator.geolocation.getCurrentPosition(success);
}

function get_coords(address) {
    // Поиск координат
    ymaps.geocode(address, {results: 1}).then(function (res) {
            // Выбираем первый результат геокодирования
            var firstGeoObject = res.geoObjects.get(0);
            var cords = firstGeoObject.geometry.getCoordinates();

            alert(cords[0] + ' ' + cords[1])


        },
        function (err) {
            // Если геокодирование не удалось,
            // сообщаем об ошибке
            alert(err.message);
        })

}