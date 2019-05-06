ymaps.ready(geoFindMe);

function change() {
    $('#map').css({"opacity": '1'});
    $('div.cssload-spin-box').toggleClass('newForAnimation');
    $('div.cssload-spin-box').toggleClass('cssload-spin-box');
    changeTextOfInput();
}

function cl() {
    $('.ymaps-2-1-73-route-panel__clear').click();
}

function test() {
    alert($('.ymaps-2-1-73-route-panel-input__input').val());
}

function changeTextOfInput() {
    $(".ymaps-2-1-73-route-panel-input__input")[0].setAttribute("onchange", "foo()");
    $(".ymaps-2-1-73-route-panel-input__input")[1].setAttribute("onchange", "foo()");
}

function foo() {
    if($('input[placeholder$="Откуда"]').val()=='' && $('input[placeholder$="Куда"]').val()==''){
        alert('вышло');
    }
}

let num = [];

function init(latitude, longitude) {
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
    myMap.controls.add(routePanelControl).add(zoomControl);
    num = [
        {
            coordinates: [53.937722, 27.462818]
        },
        {
            coordinates: [53.909938, 27.458761]
        },
        {
            coordinates: [53.883729, 27.498733]
        }
    ];


    var myPlacemarkFirst = new ymaps.Placemark(num[0].coordinates, {
        balloonContentBody: [].join('')
    });
    var myPlacemarkSecond = new ymaps.Placemark(num[1].coordinates, {
        balloonContentBody: [].join('')
    });
    var myPlacemarkThird = new ymaps.Placemark(num[2].coordinates, {
        balloonContentBody: [].join('')
    });
    myMap.geoObjects.add(myPlacemarkFirst);
    myMap.geoObjects.add(myPlacemarkSecond);
    myMap.geoObjects.add(myPlacemarkThird);

    for (var i = 0; i < num.length; i++) {
        if (i == 0) {
            ymaps.route([latitude + " " + longitude, myPlacemarkFirst.geometry.getCoordinates()], {mapStateAutoApply: true}).then(
                function (route) {
                    myMap.geoObjects.add(route);
                    myPlacemarkFirst.properties.set('balloonContentBody', '<button type="button" class="btn btn-warning">Aleksey</button><br></br>' + route.getHumanLength());
                    myMap.geoObjects.remove(route);
                }
            );
            if ($('input[placeholder$="Откуда"]').val() == '' && $('input[placeholder$="Куда"]').val() == '') {

            }
            else {
                setTimeout(cl, 2000);
            }


        }


        if (i == 1) {

            ymaps.route([latitude + " " + longitude, myPlacemarkSecond.geometry.getCoordinates()], {mapStateAutoApply: true}).then(
                function (route) {
                    myMap.geoObjects.add(route);
                    myPlacemarkSecond.properties.set('balloonContentBody', '<button type="button" class="btn btn-warning">Vitaliy</button><br></br>' + route.getHumanLength());
                    myMap.geoObjects.remove(route);
                }
            );
            if ($('input[placeholder$="Откуда"]').val() == '' && $('input[placeholder$="Куда"]').val() == '') {

            }
            else {
                setTimeout(cl, 2000);
            }
        }


        if (i == 2) {
            ymaps.route([latitude + " " + longitude, myPlacemarkThird.geometry.getCoordinates()], {mapStateAutoApply: true}).then(
                function (route) {
                    myMap.geoObjects.add(route);
                    myPlacemarkThird.properties.set('balloonContentBody', '<button type="button" class="btn btn-warning">Andrey</button><br></br>' + route.getHumanLength());
                    myMap.geoObjects.remove(route);
                }
            );
            if ($('input[placeholder$="Откуда"]').val() == '' && $('input[placeholder$="Куда"]').val() == '') {

            }
            else {
                setTimeout(cl, 2000);
                setTimeout(change, 5000);
            }
        }


        // $('input[placeholder$="Откуда"]').val(routePanelControl.routePanel.geolocate('from'));

    }


    function calculate(routeLength) {
        return Math.max(routeLength * DELIVERY_TARIFF, MINIMUM_COST) * 0.0326;
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