var wrapperOne = document.querySelector(".letterOne");
var textOne = document.querySelector(".spanForSliderFirst");
var textContOne = textOne.textContent;
textOne.style.display = "none";

for (var i = 0; i < textContOne.length; i++) {
  (function(i) {
    setTimeout(function() {
      var textsOne = document.createTextNode(textContOne[i])
      var spanOne = document.createElement('span');
      spanOne.appendChild(textsOne);
      spanOne.classList.add("wave");
      wrapperOne.appendChild(spanOne);

    }, 75 * i);
  }(i));
}

var wrapperTwo = document.querySelector(".letterTwo");
var textTwo = document.querySelector(".spanForSliderSecond");
var textContTwo = textTwo.textContent;
textTwo.style.display = "none";

for (var i = 0; i < textContTwo.length; i++) {
    (function(i) {
        setTimeout(function() {
            var textsTwo = document.createTextNode(textContTwo[i])
            var spanTwo = document.createElement('span');
            spanTwo.appendChild(textsTwo);
            spanTwo.classList.add("wave");
            wrapperTwo.appendChild(spanTwo);

        }, 75 * i);
    }(i));
}

var wrapperThree = document.querySelector(".letterThree");
var textThree = document.querySelector(".spanForSliderThird");
var textContThree = textThree.textContent;
textThree.style.display = "none";

for (var i = 0; i < textContThree.length; i++) {
    (function(i) {
        setTimeout(function() {
            var textsThree = document.createTextNode(textContThree[i])
            var spanThree = document.createElement('span');
            spanThree.appendChild(textsThree);
            spanThree.classList.add("wave");
            wrapperThree.appendChild(spanThree);

        }, 75 * i);
    }(i));
}

var wrapperFour = document.querySelector(".letterFour");
var textFour = document.querySelector(".spanForSliderFourth");
var textContFour = textFour.textContent;
textFour.style.display = "none";

for (var i = 0; i < textContFour.length; i++) {
    (function(i) {
        setTimeout(function() {
            var textsFour = document.createTextNode(textContFour[i])
            var spanFour = document.createElement('span');
            spanFour.appendChild(textsFour);
            spanFour.classList.add("wave");
            wrapperFour.appendChild(spanFour);

        }, 75 * i);
    }(i));
}
var wrapperFive = document.querySelector(".letterFive");
var textFive = document.querySelector(".spanForSliderFifth");
var textContFive = textFive.textContent;
textFive.style.display = "none";

for (var i = 0; i < textContFive.length; i++) {
    (function(i) {
        setTimeout(function() {
            var textsFive = document.createTextNode(textContFive[i])
            var spanFive = document.createElement('span');
            spanFive.appendChild(textsFive);
            spanFive.classList.add("wave");
            wrapperFive.appendChild(spanFive);

        }, 75 * i);
    }(i));
}