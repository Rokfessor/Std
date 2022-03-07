;; вспомогательная функция, вырезающая из списка lst отрезок длины l с позиции n
(defun cut-list (lst n l) 
  (cond ((or (zerop l) (< n 1)) nil) ;;Проверка аргументов
        ((and (= n 1) (> l 0)) (cons (car lst) (cut-list (cdr lst) 1 (- l 1)))) ;;Рекурсивно вырезаем отрезок
        (t (cut-list (cdr lst) (- n 1) l))))
;; Решение
(defun swap (lst p q)
    (cond
        ((= p q) lst) ;;если индекс элеметов равен, то возвращаем исходный список
        ((or (< p 1) (< q 1)) nil) ;;проверка аргументов
        ((> p q) (swap lst q p)) ;;если индексы стоят в порядке уменьшения вызываем ф-цию и меняем местами аргументы
        (t 
            (cond 
                ((listp lst) ;;Проверка на список
                (cond
                    ((or (= (length lst) 0) (< (length lst) q)) nil) ;;Если длина списка 0 или q > длины списка возвращаем nil
                    (t  
                        (let* ((len (length lst))
                              (l (cut-list lst 1 (- p 1))) ;;Обрезаем список с 1 до p
                              (m (cut-list lst p (- q p))) ;;Обрезаем список с p до q
                              (r (cut-list lst q (- len q -1)))) ;;Обрезаем список с q до длины исходного списка
                        (append l (list (car r)) (cdr m) (list (car m)) (cdr r)) ;; Собираем новый список из l + 1 элемент r + все элементы m, кроме первого + 1 элемент m + все элементы r, кроме первого
                        )
                    )
                )
            )
                (t ;;Иначе если массив
                    (setf tmp (aref lst p)) ;;Стандартный swap элементов массива
                    (setf (aref lst p) (aref lst q))
                    (setf (aref lst q) tmp)
                    (return-from swap lst) ;;Возвращаем из функции массив
                )
            )
        )
    )
)
;;!!!(setf (aref my-array 6) 25)
(print "Список")
(print (swap '(1 2 3 4 5 6 7) 5 3))
(print (swap '(a b c d e f g h i) 1 9))
(print "Массив")
(print (swap #(1 2 3 4 5 6 7) 5 3))
(print (swap #(a b c d e f g h i) 1 8))
(print "Ошибки")
(print (swap '(1 2 3 4 5 6 7) -5 -3)) ;;Отрицательное значение
(print (swap '(1 2 3 4 5 6 7) 1 8)) ;;Выход за пределы кол-ва элементов