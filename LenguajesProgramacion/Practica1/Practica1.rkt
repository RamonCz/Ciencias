#lang racket
;; Funcion que calcula el area de un cono de base circular
;; area-cono: number number -> number
(define (area-cono d g)
  (+ (* pi (/ d 2) g) (* pi  (*(/ d 2) (/ d 2)))))
;; Funcion que eleva el numero a, a la potencia b
;; potencia: number number
(define (potencia a b)
  (if (zero? b)
      1
      (if (< 0 b) 
      (* a (potencia a (sub1 b)))
      (/ 1 (* a (potencia a (sub1 (* -1 b))))))))
;; Funcion que calcula la distancia entre dos puntos en el plano
;; distancia: (pairof number) (pairof number) -> number
(define (distancia p q)
  (sqrt (+ (potencia (- (first q ) (first p )) 2) (potencia (- (second  q ) (second p )) 2))))
;; Predicado que nos dice si un numero es negativo
;; neg?: number -> Boolean
(define (neg? a)
  (if (<= 0 a)
      #f
      #t))
;; Funcion que nos devuelve el valor absoluto de un nÃomero
;; absoluto: number -> number
(define (absoluto n)
  (if (neg? n)
      (* -1 n)
      n))
;; Predicado que nos dice si un numero m es divisor de otro numero n
;; divisor?: number number -> number
(define (divisor? m n)
  (if (zero? (modulo n m))
      #t
      #f))
;; Funcion que nos da la longitud de una lista
;; longitud: (listof a) -> number
(define (longitud lista)
  (if (empty? lista)
      0
      (+ (longitud (cdr lista)) 1)))
;; Funcion que nos da el elemento maximo de una lista
;; maximo: (listof a) -> number
(define (maximo lista)
  (cond
    [(empty? lista) error "lista vacia"]
    [(= (longitud lista) 1) (car lista)]
    [(< (car lista) (second lista)) (maximo (cdr lista))]
    [else (maximo (append (cons (car lista) empty)(cddr lista)))]))
;; Funcion que nos da una lista invertida de la lista pasada como parametro
;; reversa-lista: (listof a) -> (listof a)
(define (reversa-lista lista)
  (if(empty? lista)
     empty
     (append (reversa-lista (cdr lista)) (list (car lista)))))
;; Predicado que nos dice si una lista contiene elementos que forman un palindromo
;; palindromo-lista?: (listof a) -> Boolean
(define (palindromo? lista)
  (if (empty? lista)
      #t
      (comparaLista lista (reversa-lista lista))))
(define (comparaLista l t)
  (cond
    [(= 0 (longitud l)) #t]  
    [(equal? (car l) (car t)) (comparaLista (cdr l) (cdr t))]
    [else #f]))
;; Funcion que nos da el una lista de divisores de un numero pasado como parametro
;; divisores: number -> (listof number)
(define (divisores n)
  (auxdivisores n 0))
(define (auxdivisores n m)
  (cond
    [(= m 0) (auxdivisores n (+ m 1))]
    [(= m 1) (append (list 1) (auxdivisores n (+ m 1)))]
    [(= m n) (list n)]
    [(= (modulo n m) 0) (append (list m) (auxdivisores n (+ m 1)))]
    [else (auxdivisores n (+ m 1))]))