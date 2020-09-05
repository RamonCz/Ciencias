module Practica8 where

--Estructuras Discretas 2018-1
--Profesor: Laura Freidberg Gojman
--Ayudante: Ricardo Jiménez Méndez
--Práctica 8
--Alumno: Cruz Pérez Ramón
--No. de Cuenta: 315008148

data Natural = Cero
             | Suc Natural deriving Eq

--Hacemos a Natural una instancia de Show para mostrar de mejor manera en consola
instance Show Natural where
  show (Cero) = "0"
  show (Suc n) = "S(" ++ show n ++ ")"

--Suma de dos naturales
suma :: Natural -> Natural -> Natural
suma n Cero = n
suma Cero m = m
suma n (Suc m) = (Suc (suma n m))

--Producto de dos naturales
prod :: Natural -> Natural -> Natural
prod n Cero = Cero
prod Cero m = Cero
prod n (Suc m) = (suma (prod n m) n)
--Igualdad  de naturales
igual :: Natural -> Natural -> Bool
igual n Cero = False
igual Cero n = False
igual n (Suc m) = if (n == (Suc m))
                then True
                else False

--Nos indica si el natural de la izquierda es mayor que el de la derecha
mayorQue :: Natural -> Natural -> Bool
--mayorQue n Cero = if( n /= Cero) then True else False
--mayorQue Cero m = False
mayorQue n m = if((natToInt n) > (natToInt m))
              then True
              else False

--Nos indica si el natural de la izquierda es menor que el de la derecha
menorQue ::Natural -> Natural -> Bool
menorQue n m = if((natToInt n) < (natToInt m))
              then True
              else False

--Convertir de Natural a Entero
natToInt :: Natural -> Int
natToInt n = case n of
            Cero -> 0
            Suc n -> 1 + natToInt n

--Convertir de Entero a Natural
intToNat :: Int -> Natural
intToNat n = case n of
            0 -> Cero
            n -> Suc(intToNat (n - 1))

--Longitud (naturales) de una lista
longNat :: [a] -> Natural
longNat [] = Cero
longNat (x:xs) = Suc(longNat (xs))

--Factorial de naturales
factNat :: Natural -> Natural
factNat (Suc n) = if( menorQue (Suc n) (intToNat 2))
                  then Suc Cero
                  else prod (Suc n) (factNat n)

fib :: Int -> Int
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2)
--Fibonacci de naturales
fibNat :: Natural -> Natural
fibNat n = intToNat(fib (natToInt n))



{--Pruebas--}

--Debe regresar S(S(S(S(S(S(S(0)))))))
prueba1 = suma (Suc(Suc(Suc(Cero)))) (Suc(Suc(Suc(Suc(Cero)))))

--Debe regresar S(S(S(S(S(S(S(S(S(S(S(S(0))))))))))))
prueba2 = prod (Suc(Suc(Suc(Cero)))) (Suc(Suc(Suc(Suc(Cero)))))

--Debe regresar False
prueba3 = igual (Suc(Suc(Suc(Cero)))) (Suc(Suc(Suc(Suc(Suc(Cero))))))

--Debe regresar False
prueba4 = mayorQue (Suc(Suc(Suc(Cero)))) (Suc(Suc(Suc(Suc(Suc(Cero))))))

--Debe regresar True
prueba5 = menorQue (Suc(Suc(Suc(Cero)))) (Suc(Suc(Suc(Suc(Suc(Cero))))))

--Debe regresar 34
prueba6 = natToInt $ Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Suc(Cero))))))))))))))))))))))))))))))))))

--Debe regresar S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(0)))))))))))))))))))))))))
prueba7 = intToNat 25

--Debe regresar S(S(S(S(S(S(S(S(S(S(0))))))))))
prueba8 = longNat [1,2,3,4,5,6,7,8,9,10]

--Debe regresar S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(0))))))))))))))))))))))))
prueba9 =  factNat $ Suc(Suc(Suc(Suc(Cero))))

--Debe regresar S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(S(0)))))))))))))))))))))
prueba10 = fibNat $ intToNat 8
