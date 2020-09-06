#lang plai
(require (file "./grammars.rkt"))

;; Toma una lista de números, symbolos o listas
;; y la traduce a un árbol de sintaxis abstracta CFWBAE
;; A::=<number>
;;    | <symbol>
;;    | listof(A)
;; parse: A -> SCFWBAE
;; parse: s-expression -> SCFWBAE
(define (parse sexp)
 (cond
    [(number? sexp ) (numS sexp)]
    [(symbol? sexp) (idS sexp)]
    [(boolean? sexp) (boolS sexp)]
    [(empty? sexp) '()]
    [(list? sexp)
     (cond
       [(number? (car sexp)) (append (list (parse (car sexp))) (parse (cdr sexp)))]
       [(boolean? (car sexp)) (append (list (parse (car sexp))) (parse (cdr sexp)))]
       
       [(list? (car sexp)) (append (list (parse(car sexp))) (parse (cdr sexp)))]
       [(procedure? (with-handlers ([(lambda (v) #t)(lambda (v) #f)]) (eval (car sexp))))
        ;;(display (car sexp))
        (case (car sexp)
          [(+) (opS + (parse (cdr sexp)))]
          [(-) (opS - (parse (cdr sexp)))]
          [(*) (opS * (parse (cdr sexp)))]
          [(/) (opS / (parse (cdr sexp)))]
          [(modulo) (opS modulo (parse (cdr sexp)))]
          [(expt) (opS expt (parse (cdr sexp)))]
          [(add1) (opS add1 (parse (cdr sexp)))]
          [(sub1) (opS sub1 (parse (cdr sexp)))]
          [(<) (opS < (parse (cdr sexp)))]
          [(<=) (opS <= (parse (cdr sexp)))]
          [(=) (opS = (parse (cdr sexp)))]
          [(>) (opS > (parse (cdr sexp)))]
          [(>=) (opS >= (parse (cdr sexp)))]
          [(not) (opS not (parse (cdr sexp)))]
          [(zero?) (opS zero? (parse (cdr sexp)))]
          [(And) (opS And (parse (cdr sexp)))]
          [(Or) (opS Or (parse (cdr sexp)))]
          [(condS) (if (list? (cdr sexp))
                       (condS (conds (cadr sexp)))
                       (error "sintaxis incorreca falta de parentesis"))]
          [(iFS) (iFS (if (= 1 (length(cadr sexp))) (parse (caadr sexp)) (parse (cadr sexp))) (if (= 1 (length(caddr sexp))) (parse (caaddr sexp)) (parse (caddr sexp)))
                                     (if (= 1 (length (cadddr sexp))) (parse (car (cadddr sexp)))(parse (cadddr sexp))))]
          [(withS) (if (list? (car(cadr sexp)))
                                      (withS (bindin (cadr sexp)) (parse (caddr sexp)))
                                      (error "sintaxis incorreca falta de parentesis"))]
          [(withS*) (if (list? (car(cadr sexp)))
                                       (withS* (bindin (cadr sexp)) (parse (caddr sexp)))
                                       (error "sintaxis incorreca falta de parentesis"))]
          [(funS)(if (= (length sexp) 5) (if (list? (cadr sexp))
                                    (funS (params (second sexp)) (tipar (fourth sexp)) (parse (fifth sexp)))
                                    (error "sintaxis incorreca falta de parentesis (param)"))
                                    (error "funcion mal escrita"))]
          [(appS)(appS (parse (second sexp)) (parse (third sexp)))]
          [else error "operacion no definina en SCFWBAE"])]
       [(symbol=? (car sexp) 'and) (parse (append '(And) (cdr sexp)))] ; cambia el and por la funcion  And
       [(symbol=? (car sexp) 'or) (parse (append '(And) (cdr sexp)))]  ; cambia el or por la funcion Or
       [(symbol? (car sexp)) (append (list (parse (car sexp))) (parse (cdr sexp)))])]))

(define (auxx ls)
  ;;(display (cadr ls))
  (display (first (cadr ls)))
  (display (second (cadr ls))))
  ;;(display (caaddr ls))
  ;;(display (cdddr ls)))

;;crea una lista de binding para el with
;;(listof s-xpresion)-> (listof Binding)
(define (bindin lista)
  (cond
    [(empty? lista) '()]
    [else (append (list (binding  (first (car lista)) (tipar (third (car lista)))   (parse (fourth (car lista))))) (bindin (cdr lista)))])
  )

;;crea una lista de params para func
;;(listof s-xpresion)-> (listof param)
(define (params lista)
  (cond
    [(empty? lista) '()]
    [else (append (list(param  (first (car lista)) (tipar (third (car lista)))) ) (params (cdr lista)))])
  )


;;verifica que se halla escrito bien el tipo 
(define (tipar s)
  (cond
    [(list? s) (cond
                 [(empty? s) '()]
                 [(equal? (car s) '(numberT)) (append (list (numberT)) (tipar (cdr s)))]
                 [(equal? (car s) '(booleanT)) (append  (list (booleanT)) (tipar (cdr s)))]
                 [(equal? (car s) 'funT) (funT (append (tipar (second  s))) )])]
    [(equal? s 'numberT) (numberT)]
    [(equal? s 'booleanT) (booleanT)]
    [else error "El tipo no exite en el lenguaje"]
   ))

;;crea split a:numberT 
(define (split a)
   (list (string->symbol (first (string-split (symbol->string a) ":")))  (tipar (string->symbol (second (string-split (symbol->string a) ":"))))))


;;crea una lista de conditions para el condS
;;(listof s-xpresion)-> (listof Condition)
(define (conds lista)
  (cond
    [(empty? lista) '()]
    [(equal? (first (car lista)) 'else) (append (list (else-cond (parse (second (car lista))))))]
    [else (append (list (condition (parse (first (car lista))) (parse (second(car lista))))) (conds (cdr lista)))])
  )


;definiendo el and
(define (And lista)
  (cond
    [(empty? lista) ]
    [else (and (car lista) (And (cdr lista)))]))
;definiendo el and
(define (Or lista)
  (cond
    [(empty? lista) ]
    [else (or (car lista) (Or (cdr lista)))]))