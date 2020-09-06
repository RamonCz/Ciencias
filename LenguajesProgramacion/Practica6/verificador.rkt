#lang plai
(require (file "./grammars.rkt"))
(require (file "./parser.rkt"))

;; Toma un Árbol de sintaxis abstracta SCFWBAE y obtiene el tipo
;; de la expresión mínima.
;; typeof CFWBAE -> Type-Context -> Type
;; (define (typeof expr context)
(define (typeof expr context)
  (cond
    [(empty? expr) '()]
    [(list? expr) (append (list (typeof (car expr) context)) (typeof (cdr expr) context)) ]
    [else (type-case SCFWBAE expr
            [numS (n) (numberT)]
            [boolS (b) (booleanT)]
            [iFS (test-expr then-expr else-expr)
                 (if (booleanT? (typeof test-expr context))
                     (let* ([type-then  (typeof then-expr context)]
                            [type-else (typeof else-expr context)])
                     (if (equal? type-then type-else) type-then (error "El then y else deben ser del mismo tipo")))
                     (error "No la condicional debe ser de tipo booleanT"))]
            [opS (o l) (cond
                         [(equal? o +) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o -) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o *) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o /) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o modulo) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o expt) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o add1) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o sub1) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o <) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o <=) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o >) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o >=) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o zero?) (if (andmap numberT? (map (lambda (x)(typeof x context)) l)) (numberT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o And) (if (andmap booleanT? (map (lambda (x)(typeof x context)) l)) (booleanT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o Or) (if (andmap booleanT? (map (lambda (x)(typeof x context)) l)) (booleanT) (error "No se puede operar con tipos incorrectos"))]
                         [(equal? o not) (if (andmap booleanT? (map (lambda (x)(typeof x context)) l)) (booleanT) (error "No se puede operar con tipos incorrectos"))]
                         )]
            [condS (cases) (let ([lista (map (lambda (x) (verif x)) cases)])
                             (if (= 1 (length (remove-duplicates lista))) (car lista) (error  "El then y else deben ser del mismo tipo")))] 
            [funS (params typ body) (let ([p (contexto params context)])
                                      (funT (append (funP params) (list typ)))  )]

            
            [withS (bins body) (let ([parametros (map (lambda (x)  (param (binding-id x) (binding-tipo x))) bins) ])
                                 (typeof (appS (funS parametros (typeof body (contexto parametros context)) body) (map (lambda (x) (binding-value x)) bins)) context) )]
            [withS* (bins body) (let ([parametros (map (lambda (x)  (param (binding-id x) (binding-tipo x))) bins) ])
                                 (typeof (appS (funS parametros (typeof body (contexto parametros context)) body) (map (lambda (x) (binding-value x)) bins)) context) )]

            
            [appS (fun-expr arg-expr ) (let* ([types (type-case Type (typeof fun-expr context)
                                                       [numberT () (#f)]
                                                       [booleanT () (#f)]
                                                       [funT (params) params])]
                                              [args (map (lambda (x y) (typeof x y)) arg-expr (for/list ([i (in-range (length arg-expr))]) context))]
                                              [ultimo (last types)])
                                         (if (list? types) (if (tipoArgs (remove ultimo types) args) ultimo (error "los argumentos no coinciden con los tipos")) (error "la funcion no es tipo FunT"))  )]
            [idS (i) (lookup i context)])]))



;;busca el tipo en el context
(define (lookup i cont)
  (type-case Type-Context cont
    [phi () (error "Id no encontrado")]
    [gamma (id typ rest-cont) (if (equal? id i) typ (lookup i rest-cont))]))

;; verfica que los tipos de la funcion sean igual a lo argumentos
(define (tipoArgs types args)0
    ( if (= (length types)(length args))
      (cond
       [(empty? args)  #t]
       [(equal? (car types)(car args)) (tipoArgs (cdr types)(cdr args))]
       [else #f])
      #f
  ))

;; crea lista de params para FunT
(define (funP params)
  (cond
    [(empty? params) '()]
     [else (type-case Param (car params)
          [param (s typ) (append (list typ) (funP (cdr params))) ])]))

;;agrega los elementos al contexto
(define (contexto p context)
  (cond
    [(empty? p) context]
    [else (type-case Param (car p)
            [param (par tipo ) (gamma par tipo (contexto (cdr p) context))])]))

;; ayuda a la verificacion de tipo condition
(define (verif c)
  (type-case Condition c
    [condition (test-expr then-expr) (if (booleanT? (typeof test-expr (phi))) (typeof then-expr (phi)) (error "No la condicional debe ser de tipo booleanT") )]
    [else-cond (else-expr) (typeof else-expr (phi))])
  )

(define (prueba exp)
  (typeof (parse exp) (phi)))