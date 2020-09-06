#lang plai
(require (file "./parser.rkt")(file "./grammars.rkt"))

;; Recibe una expresión (expr) del lenguaje WAE,
;; un id (sub-id) y otra expresión (value).
;; Sustituye el valor sub-id por value, en expr.
;; subst: WAE symbol WAE -> WAE
(define (subst expr sub-id value)
  (if (list? expr)
      (cond
        [(empty? expr) '()]
        [else (append (list (subst (car expr) sub-id value)) (subst (cdr expr) sub-id value))])
  (type-case WAE expr
    [id (i) (if (symbol=? i sub-id) value expr)]
    [num (n) expr]
    [op (o l) (op o (subst l sub-id value))]
    [with (bins body) (if (ormap (lambda (b) (type-case Binding b [binding (id wae) (symbol=? id sub-id)])) bins)
                                 (with (subst-bind bins sub-id value) body)
                                 (with (subst-bind bins sub-id value) (subst body sub-id value))
                              )]
    [with* (bins body) (if (ormap (lambda (b) (type-case Binding b [binding (id wae) (symbol=? id sub-id)])) bins)
                                 (with* (subst-bind bins sub-id value) body)
                                 (with* (subst-bind bins sub-id value) (subst body sub-id value))
                              )]
    )))
;;Sustituye los la varible en la lista de bindings
;;(listof symbol Binding)->(listof Binding)
(define (subst-bind bins sub-id value)
  (cond
    [(empty? bins) '()]
    [(type-case Binding (car bins)
      [binding (i wae) (append (list (binding i (subst wae sub-id value))) (subst-bind (cdr bins) sub-id value))]
      )]))


;; Toma un árbol de sintáxis abstraca del lenguaje WAE
;; y lo interpreta, devolviendo el valor numérico correspondiente
;; interp: WAE -> number
(define (interp expr)
  (type-case WAE expr
    [id (i) (error "la variable no esta :c")]
    [num (n) n]
    [op (o l) (cond
                [(equal? o modulo) (if (= (length l) 2) (o (interp (car l))(interp (cadr l)))(error "modulo es aridad 2 :c"))]
                [(equal? o expt) (if (= (length l) 2) (o (interp (car l))(interp (cadr l))) (error "expt es aridad 2 :c"))]
                [(equal? o add1) (if (= (length l) 1) (o (interp (car l))) (error "add1 es aridad 1 :c"))]
                [(equal? o sub1) (if (= (length l) 1) (o (interp (car l))) (error "sub1 es aridad 1 :c"))]
                [(equal? o *)(foldl o 1 (map interp l))]
                [(equal? o /)(foldl * 1 (append (list (interp (car l))) (map (lambda (x) (expt (interp x) -1)) (cdr l))))]
                [(equal? o -) (if (= (length (map interp l)) 1)( -(interp (car l)))(resta (map interp l)))]
                [else (foldl o 0 (map interp l))])]
    [with (bins body) (let ([lista (map (lambda (a) (type-case Binding a [binding (id wae) (list id wae)])) bins)])
                        (interp (foldl (lambda (par x) (subst x (first par) (second par))) body lista))) ]
    [with* (bins body) (let ([lista (map (lambda (a) (type-case Binding a [binding (id wae) (list id wae)])) bins)])
                         (let ([lista2 (foldl (lambda (a l) (map (lambda (p) (list (first p)(subst (second p) (first a) (second a)))) l)) lista lista)])
                           (interp (foldl (lambda (par x) (subst x (first par) (second par))) body lista2))))]
        )
  )
;;auxiliar para la resta
(define (resta lista )
   (cond
     [(empty? lista) 0]
     [(= (length lista) 1) (car lista)]
     [else (- (car lista) (cadr lista) (resta (cddr lista)))]
  ))