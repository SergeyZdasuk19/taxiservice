let map = new Map();
$(document).ready(function () {
    $('.btn.btn-outline-success').click(function () {
        $('input[name$="username"]').val($('.btn.btn-outline-secondary span').text());
        $('input[name$="placeFrom"]').val($('input[placeholder$="Откуда"]').val());
        $('input[name$="placeTo"]').val($('input[placeholder$="Куда"]').val());
        $('input[name$="price"]').val(map.get(1));

    });

});
function changeStyle(){
    alert('s');

}
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
    $('.ymaps-2-1-73-route-panel__input ymaps-2-1-73-route-panel__input_from').css({"background-color": 'black'});
    $('.ymaps-2-1-73-route-panel__input ymaps-2-1-73-route-panel__input_from').css({"position": 'absolute'});
    $('.ymaps-2-1-73-route-panel__input ymaps-2-1-73-route-panel__input_from').css({"z-index": '10'});
    $('.ymaps-2-1-73-route-panel__input ymaps-2-1-73-route-panel__input_from').css({"right": '70px'});
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
    $(".ymaps-2-1-73-route-panel-input__input")[0].setAttribute("onchange", "foo()");
    $(".ymaps-2-1-73-route-panel-input__input")[1].setAttribute("onchange", "foo()");
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
    var DELIVERY_TARIFF = 20;
    // Минимальная стоимость.
    var MINIMUM_COST = 500;
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

    function calculate(routeLength) {
        return routeLength * 0.5;
    }

    function countDistanceWithoutMyPlace() {
        $('#map').css({"opacity": '0'});
        $('div.newForAnimation').toggleClass('cssload-spin-box');
        $('div.newForAnimation').toggleClass('newForAnimation');
        for (var i = 0; i < num.length; i++) {
            if (i == 0) {


                        var distanceMarkerTo = route.getHumanLength().replace(/&#160;км/i, '');
                        var price;
                        ymaps.route([$('input[placeholder$="Откуда"]').val(), $('input[placeholder$="Куда"]').val()], {mapStateAutoApply: true}).then(
                            function (routeFirst) {
                                myMap.geoObjects.add(routeFirst);
                                price = calculate(routeFirst.getHumanLength().replace(/&#160;км/i, ''));
                                map.set(1, price);
                                myMap.geoObjects.remove(routeFirst);
                            }


                );
            }


            if (i == 1) {

                ymaps.route([$('input[placeholder$="Откуда"]').val(), myPlacemarkSecond.geometry.getCoordinates()], {mapStateAutoApply: true}).then(
                    function (route) {
                        myMap.geoObjects.add(route);
                        var distanceMarkerTo = route.getHumanLength().replace(/&#160;км/i, '');
                        var price;
                        ymaps.route([$('input[placeholder$="Откуда"]').val(), $('input[placeholder$="Куда"]').val()], {mapStateAutoApply: true}).then(
                            function (routeSecond) {
                                myMap.geoObjects.add(routeSecond);
                                price = calculate(routeSecond.getHumanLength().replace(/&#160;км/i, ''));
                                map.set(2, price);
                                myPlacemarkSecond.properties.set('balloonContentBody', '<button type="button" class="btn btn-warning" onclick="addSecondPriceToModal()">Vitaliy</button><br></br>' + distanceMarkerTo + ' км<span id="priceSecond"> ' + price + ' р </span>');
                                myMap.geoObjects.remove(routeSecond);
                            }
                        );
                        myMap.geoObjects.remove(route);
                    }
                );
            }


            if (i == 2) {
                ymaps.route([$('input[placeholder$="Откуда"]').val(), myPlacemarkThird.geometry.getCoordinates()], {mapStateAutoApply: true}).then(
                    function (route) {
                        myMap.geoObjects.add(route);
                        var distanceMarkerTo = route.getHumanLength().replace(/&#160;км/i, '');
                        var price;
                        ymaps.route([$('input[placeholder$="Откуда"]').val(), $('input[placeholder$="Куда"]').val()], {mapStateAutoApply: true}).then(
                            function (routeThird) {
                                myMap.geoObjects.add(routeThird);
                                price = calculate(routeThird.getHumanLength().replace(/&#160;км/i, ''));
                                map.set(3, price);
                                myPlacemarkThird.properties.set('balloonContentBody', '<button type="button" class="btn btn-warning" onclick="addThirdPriceToModal()">Andrey</button><br></br>' + distanceMarkerTo + ' км<span id="priceSecond"> ' + price + ' р </span>');
                                myMap.geoObjects.remove(routeThird);
                            }
                        );
                        myMap.geoObjects.remove(route);
                    }
                );
                if ($('input[placeholder$="Откуда"]').val() == '' && $('input[placeholder$="Куда"]').val() == '') {

                }
                else {
                    setTimeout(change, 5000);
                    changeTextOfInput();
                }
            }


        }
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
function get_coords(address)
{
    // Поиск координат
    ymaps.geocode(address, { results: 1 }).then(function (res)
        {
            // Выбираем первый результат геокодирования
            var firstGeoObject = res.geoObjects.get(0);
            var cords = firstGeoObject.geometry.getCoordinates();

            alert(cords[0]+' '+cords[1])


        },
        function (err)
        {
            // Если геокодирование не удалось,
            // сообщаем об ошибке
            alert(err.message);
        })

}