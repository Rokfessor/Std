function swapArray(array, p, q) {
	if (p < 0 || q < 0 || array.length <= q || p == q) //Проверка на корректность введенных индексов
		return;

	if (p > q) //если индексы стоят в порядке уменьшения вызываем ф-цию и меняем местами аргументы
		swapArray(array, q, p);

	let temp1 = array[p]; //Создаем временную переменную
	array[p] = array[q]; //Меняем элементы местами
	array[q] = temp1; //
}

let array = [1, 2, 3, 4, 5, 6, 7]; //Тестовый массив
console.log(array); //Вывод в консоль начального массива
swapArray(array, 0, 6); //Вызов функции
console.log(array); //Вывод в консоль измененного массива