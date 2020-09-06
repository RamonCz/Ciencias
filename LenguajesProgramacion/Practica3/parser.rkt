#lang plai
(require (file "./grammars.rkt"))

;; Toma una lista de números, symbolos o listas
;; y la traduce a un árbol de sintaxis abstracta WAE
;; A::=<number>
;;    | <symbol>
;;    | listof(A)
;; parse: A -> WAE
;; parse: s-expression -> WAE
(define (parse sexp)
  (cond
    [(number? sexp ) (num sexp)]
    [(symbol? sexp)  (id sexp)]
    [(empty? sexp) '()]
    [(list? sexp)
     (cond
       [(number? (car sexp)) (append (list (parse (car sexp))) (parse (cdr sexp)))]
       [(list? (car sexp)) (append (list (parse(car sexp))) (parse (cdr sexp)))]
       [(procedure? (with-handlers ([(lambda (v) #t)(lambda (v) #f)]) (eval (car sexp)))) (case (car sexp)
               [(+) (op + (parse (cdr sexp)))]
               [(-) (op - (parse (cdr sexp)))]
               [(*) (op * (parse (cdr sexp)))]
               [(/) (op / (parse (cdr sexp)))]
               [(modulo) (op modulo (parse (cdr sexp)))]
               [(expt) (op expt (parse (cdr sexp)))]
               [(add1) (op add1 (parse (cdr sexp)))]
               [(sub1) (op sub1 (parse (cdr sexp)))]
               [(with) (if (list? (car(cadr sexp))) (with (bindin (cadr sexp)) (parse (caddr sexp))) (error "sintaxis incorreca falta de parentesis"))]
               [(with*) (if (list? (car(cadr sexp))) (with* (bindin (cadr sexp)) (parse (caddr sexp))) (error "sintaxis incorreca falta de parentesis"))]
               [else error "operacion no definina en WAE"])]
       [(symbol? (car sexp)) (append (list (parse (car sexp))) (parse (cdr sexp)))]
       )]))

;;crea una lista de binding para el with
;;(listof s-xpresion)-> (listof Binding)
(define (bindin lista)
  (cond
    [(empty? lista) '()]
    [else (append (list(binding (first (car lista)) (parse (second(car lista))))) (bindin (cdr lista)))])
  )
