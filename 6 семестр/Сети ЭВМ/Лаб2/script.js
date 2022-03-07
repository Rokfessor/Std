var BIGGER = 'Число больше чем',
    SMALLER = 'Число меньше чем',
    EQUALS = 'Вы победили!',
    LOSS = "Вы не угадали число",
    WRONG_NUMBER = "Некорректное число",
    WRONG_RANGE = "Число вне диапозона",
    NOT_STATED = "Введите ваше число";

var attemptCount;
var A = 1, B = 1000;

init();

function init() {
    generateNumber();
    document.getElementById('generateNumber').onclick = () => generateNumber();
    document.getElementById('checkAnswer').onclick = () => checkAnswer();
    document.getElementById('range').innerText = '[' + A + ';' + B + ']';
    document.getElementById('attemptCount').innerText = calcAttemptCount();
    document.getElementById('number').innerText = '';
}

function checkAnswer() {
    let val = document.getElementById('number').value;
    let hiddenNumber = document.getElementById('hiddenNumber');

    if (!val) {
        setState(WRONG_NUMBER);
        return;
    }

    let number = Number(val);

    if (!Number.isInteger(number)) {
        setState(WRONG_NUMBER);
        return;
    }

    if (number < A || number > B) {
        setState(WRONG_RANGE);
        return;
    }

    if (number == atob(hiddenNumber.value)) {
        setState(EQUALS);
        document.getElementById('checkAnswer').disabled = true;
    }
    else if (number < atob(hiddenNumber.value))
        setState(BIGGER);
    else
        setState(SMALLER);
  
    if (attemptCount > 0)
        attemptCount--;
        setAttemptCountText();

    if (attemptCount == 0) {
        document.getElementById('checkAnswer').disabled = true;
        if (number != (atob(hiddenNumber.value))) 
            setState(LOSS);
    }
}

function setState(state) {
    document.getElementById('addon-wrapping').innerText = state;
}

function generateNumber() {
    let hiddenNumber = document.getElementById('hiddenNumber');
    hiddenNumber.value = btoa(getRandomInt(1, 1001));
    setState(NOT_STATED);

    attemptCount = calcAttemptCount();
    setAttemptCountText();
    document.getElementById('checkAnswer').disabled = false;

    //!!!
    console.log(atob(hiddenNumber.value));
}

function setAttemptCountText() {
    document.getElementById('checkAnswer').innerText = 'Проверить ответ (' + attemptCount + ')';
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function calcAttemptCount() {
    return Math.ceil(Math.log2(B - A + 1));
}