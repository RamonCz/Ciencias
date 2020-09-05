--Estructuras Discretas 2017-2
--Profesor: Laura Freidberg Gojman
--Ayudante: Ricardo Jiménez Méndez
--Práctica 1
--Alumno:Cruz Perez Ramón
--No. de Cuenta: 315008148

module Practica1 where

--Calcula el área de un círculos
areaCirculo :: (Num a,Fractional a) => a -> a
areaCirculo radio = (radio*radio)*3.1416


--Calcula el área de un triángulo
areaTri :: (Num a,Fractional a) => a -> a -> a
areaTri base altura  = base*altura/2

--Calcula el volumen de una esfera
volEsfera :: (Num a,Fractional a) => a -> a
volEsfera radio = 4/3*3.1416*(radio*radio*radio)

--Calcula la distancia entre dos puntos
distancia :: (Num a,Floating a) => (a,a) -> (a,a) -> a
distancia (x1,y1) (x2,y2) = (((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)))**(1/2)

--La primera entrada de cada tripleta se suma
--La segunda entrada de cada tripleta se multiplica
--La tercera entrada de cada tripleta se resta
opTrip :: (String, Int, String) -> (String,String,Int) -> (Int,String,String) -> (String,String,String)
opTrip (a,b,c) (a1,b1,c1) (a2,b2,c2) = (show((read(a)+read(a1)+(a2))),(show(b*read(b1)*read(b2))),(show(read(c)-(c1)-read(c2))))

--Realiza la operación lógica implicación
implica :: Bool -> Bool -> Bool
implica True False = False
implica _ _ = True

--Realiza la operación lógica si y sólo si
syss :: Bool -> Bool -> Bool
syss True True = True
syss False False = True
syss _ _ = False


--Dada una altura decir como es la persona
altura :: Float -> String
altura a = if a <= 1.55
 then ("Pequeño")
 else ("Normal")

--Nos dice si el primero es mayor o igual que el segundo
mayorIgual :: (Num a, Ord a) => a -> a -> Bool
mayorIgual a b = if a >= b
then True
else False

--Nos dice si los tres numeros son iguales
igualTres :: (Num a, Ord a) => a -> a -> a -> Bool
igualTres a b c
  |a == b && b == c = True
  |a < b = False

{-- Pruebas --}

--Debe regresar 88.247544
prueba1 = areaCirculo 5.3

--Debe regresar 11.25
prueba2 = areaTri 3 7.5

--Debe regresar 212.1752864
prueba3 = volEsfera 3.7

--Debe regresar 2.3537204591879637
prueba4 = distancia (5,6.7) (4.5,9)

--Debe regresar ("15","432","-15")
prueba5 = opTrip ("3",9,"5") ("7","6",7) (5,"8","13")

--Debe regresar True
prueba6 = implica False True

--Debe regresar False
prueba7 = syss True False

--Aquí regresa el mensaje que pusiste
prueba8 = altura 1.89

--Debe regresar False
prueba9 = mayorIgual 8.6 12.5

--Debe regresar True
prueba10 = igualTres 8.5 8.5 8.5
