
module Practica7 where

--Estructuras Discretas 2018-1
--Profesor: Laura Freidberg Gojman
--Ayudante: Ricardo Jiménez Méndez
--Práctica 7
--Alumno: Cruz Pérez Ramón
--No. de Cuenta: 315008148

--Se define el tipo de dato Tree como
data Arbol a = Vacio                        --Árbol vacío
             | Nodo a (Arbol a) (Arbol a)   --Nodo con sub-árbol izquierdo y sub-árbol derecho
             deriving (Show,Eq)

--Regresa el número de nodos de un árbol
noNodos :: Arbol a -> Int
noNodos arbol = case arbol of
                Vacio -> 0
                (Nodo arbol t1 t2) -> 1 + noNodos(t1) + noNodos(t2)

--Regresa el número de hojas de un árbol
noHojas :: (Eq a) => Arbol a -> Int
noHojas a = case a of
            Vacio -> 0
            (Nodo a Vacio Vacio) -> 1
            (Nodo a t1 t2) -> 0 + noHojas(t1) + noHojas(t2)




--Regresa el número de nodos internos de un árbol
noInt :: (Eq a) => Arbol a -> Int
noInt a = case a of
          Vacio -> 0
          (Nodo a Vacio Vacio) -> 0
          (Nodo a t1 t2) -> 1 + noInt(t1) + noInt(t2)

--Regresa el número de aristas de un árbol
aristas :: (Eq a) => Arbol a -> Int
aristas a = case a of
            Vacio -> 0
            (Nodo a Vacio Vacio) -> 0
            (Nodo a t1 Vacio) -> 1 + aristas(t1)
            (Nodo a Vacio t2) -> 1 + aristas(t2)
            (Nodo a t1 t2) -> 2 + aristas(t1) + aristas(t2)

--Nos regresa los nodos internos de un árbol en una lista
nodosInt :: (Eq a) => Arbol a -> [a]
nodosInt a = case a of
          Vacio -> []
          (Nodo a Vacio Vacio) -> []
          (Nodo a t1 t2) -> [a] ++ nodosInt(t1) ++ nodosInt(t2)


--Nos regresa las hojas de un árbol en una lista
hojas :: (Eq a) => Arbol a -> [a]
hojas a = case a of
            Vacio -> []
            (Nodo a Vacio Vacio) -> [a]
            (Nodo a t1 t2) -> [] ++ hojas(t1) ++ hojas(t2)

--Nos dice si el nodo que estamos buscando se encuentra en el árbol dado
buscaNodo :: (Eq a) => a -> Arbol a -> Bool
buscaNodo x a = x `elem` inorder a


--Hace un recorrido inorder a un árbol y regresa el recorrido en una lista
inorder :: Arbol a -> [a]
inorder Vacio = []
inorder (Nodo a t1 t2) = (inorder t1) ++ [a] ++ (inorder t2)

--Hace un recorrido preorder a un árbol y regresa el recorrido en una lista
preorder :: Arbol a -> [a]
preorder Vacio = []
preorder (Nodo a t1 t2) = [a] ++ (preorder t1) ++ (preorder t2)

--Hace un recorrido posorder a un árbol y regresa el recorrido en una lista
posorder :: Arbol a -> [a]
posorder Vacio = []
posorder (Nodo a t1 t2) = (posorder t1) ++ (posorder t2) ++ [a]

{--pruebas--}

--Debe regresar 10
prueba1 = noNodos (Nodo 1
                    (Nodo 2
                      (Nodo 9
                        (Vacio)
                        (Nodo 10 (Vacio) (Vacio))
                      )
                      (Nodo 4
                        (Nodo 5 (Vacio) (Vacio))
                        (Nodo 6 (Vacio) (Vacio))
                      )
                    )
                    (Nodo 3
                      (Nodo 7 (Vacio) (Vacio))
                      (Nodo 8 (Vacio) (Vacio))
                    )
                  )

--Debe regresar 4
prueba2 = noHojas (Nodo 1
                    (Nodo 2
                      (Vacio)
                      (Nodo 4
                        (Nodo 5 (Vacio) (Vacio))
                        (Nodo 6 (Vacio) (Vacio))
                      )
                    )
                    (Nodo 3
                      (Nodo 7 (Vacio) (Vacio))
                      (Nodo 8 (Vacio) (Vacio))
                    )
                  )

--Debe regresar 5
prueba3 = noInt (Nodo 1
                     (Nodo 2
                       (Nodo 9
                         (Vacio)
                         (Nodo 10 (Vacio) (Vacio))
                       )
                       (Nodo 4
                         (Nodo 5 (Vacio) (Vacio))
                         (Nodo 6 (Vacio) (Vacio))
                       )
                     )
                     (Nodo 3
                       (Nodo 7 (Vacio) (Vacio))
                       (Nodo 8 (Vacio) (Vacio))
                     )
                   )

--Debe regresar 9
prueba4 = aristas (Nodo 1
                    (Nodo 2
                      (Nodo 9
                        (Vacio)
                        (Nodo 10 (Vacio) (Vacio))
                      )
                      (Nodo 4
                        (Nodo 5 (Vacio) (Vacio))
                        (Nodo 6 (Vacio) (Vacio))
                      )
                    )
                    (Nodo 3
                      (Nodo 7 (Vacio) (Vacio))
                      (Nodo 8 (Vacio) (Vacio))
                    )
                  )

--Debe regresar [1,2,9,4,3]
prueba5 = nodosInt (Nodo 1
                    (Nodo 2
                     (Nodo 9
                      (Vacio)
                      (Nodo 10 (Vacio) (Vacio))
                     )
                     (Nodo 4
                      (Nodo 5 (Vacio) (Vacio))
                      (Nodo 6 (Vacio) (Vacio))
                     )
                    )
                    (Nodo 3
                     (Nodo 7 (Vacio) (Vacio))
                     (Nodo 8 (Vacio) (Vacio))
                    )
                   )

--Debe regresar [5,6,3]
prueba6 = hojas (Nodo 1
                  (Nodo 2
                    (Vacio)
                    (Nodo 4
                      (Nodo 5 (Vacio) (Vacio))
                      (Nodo 6 (Vacio) (Vacio))
                    )
                  )
                  (Nodo 3
                    (Vacio)
                    (Vacio)
                  )
                )

--Debe regresar True
prueba7 = buscaNodo 5 (Nodo 1
                        (Nodo 2
                          (Vacio)
                          (Nodo 4
                            (Nodo 5 (Vacio) (Vacio))
                            (Nodo 6 (Vacio) (Vacio))
                          )
                        )
                        (Nodo 3
                          (Nodo 7 (Vacio) (Vacio))
                          (Nodo 8 (Vacio) (Vacio))
                        )
                      )

--Debe regresar [9,10,2,5,4,6,1,7,3,8]
prueba8 = inorder (Nodo 1
                    (Nodo 2
                      (Nodo 9
                       (Vacio)
                       (Nodo 10 (Vacio) (Vacio))
                       )
                      (Nodo 4
                        (Nodo 5 (Vacio) (Vacio))
                        (Nodo 6 (Vacio) (Vacio))
                      )
                    )
                    (Nodo 3
                      (Nodo 7 (Vacio) (Vacio))
                      (Nodo 8 (Vacio) (Vacio))
                    )
                  )

--Debe regresar [1,2,9,10,4,5,6,3,7,8]
prueba9 = preorder (Nodo 1
                     (Nodo 2
                       (Nodo 9
                         (Vacio)
                         (Nodo 10 (Vacio) (Vacio))
                       )
                       (Nodo 4
                         (Nodo 5 (Vacio) (Vacio))
                         (Nodo 6 (Vacio) (Vacio))
                       )
                     )
                     (Nodo 3
                       (Nodo 7 (Vacio) (Vacio))
                       (Nodo 8 (Vacio) (Vacio))
                     )
                   )

--Debe regresar [10,9,5,6,4,2,7,8,3,1]
prueba10 = posorder (Nodo 1
                      (Nodo 2
                        (Nodo 9
                          (Vacio)
                          (Nodo 10 (Vacio) (Vacio))
                        )
                        (Nodo 4
                          (Nodo 5 (Vacio) (Vacio))
                          (Nodo 6 (Vacio) (Vacio))
                        )
                      )
                      (Nodo 3
                        (Nodo 7 (Vacio) (Vacio))
                        (Nodo 8 (Vacio) (Vacio))
                      )
                    )
