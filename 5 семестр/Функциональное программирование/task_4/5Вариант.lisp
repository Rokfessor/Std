(defclass Paint () ;; Объяление класса
    ( ;;Конструктор
        (name :initarg :name)
        (color :initarg :color)
        (cost :initarg :cost)
        (consumption :initarg :consumption))
    )

(defmethod printPaint (paint) ;; Метод вывода полей класса
    (print (format t "Paint name - ~S; Color - ~S; Cost - ~S; Consumption - ~S;" (slot-value *paint* 'name) (slot-value *paint* 'color) (slot-value *paint* 'cost) (slot-value *paint* 'consumption)))
)

(defmethod otbor (paintList consu) ;; Метод отбора красок по расходу за стекший месяц
    (loop for paint in paintList ;; Цикл по каждому элементу списка
        do ( (lambda 
                (setq result '()) ;; Результирующий список
                (if (> (slot-value *paint* 'consumption) consu) ;; Если расход больше заданного
                    (setq result (append result '(slot-value *paint* 'consumption))) ;; то добавляем объект в результирующий список
                )
            )
        )
    )
)

(defparameter *paintA* (make-instance 'Paint :name "Green paint" :color "Green" :cost 5000 :consumption 12)) ;; Создание списка объектов
(defparameter *paintB* (make-instance 'Paint :name "Red paint" :color "Red" :cost 5001 :consumption 13))
(defparameter *paintC* (make-instance 'Paint :name "Blue paint" :color "Blue" :cost 5002 :consumption 18))
(defparameter *paintD* (make-instance 'Paint :name "Black paint" :color "Black" :cost 5003 :consumption 22))
(defparameter *paintE* (make-instance 'Paint :name "White paint" :color "White" :cost 5004 :consumption 18))
(defparameter *paintF* (make-instance 'Paint :name "Yellow paint" :color "Yellow" :cost 5005 :consumption 9))

(setq paintsArray (make-array '(5) :initial-contents '(*paintA* *paintB* *paintC* *paintD* *paintE*)))
(loop for paint in paintsArray ;; Вывод элементов списка
        do (printPaint (*paint*))
)
(loop for paint in (otbot paintsArray 15) ;; Вывод отобранных элментов списка
        do (printPaint (*paint*))
)