--Estructuras Discretas 2018-1
--Profesor: Laura Freidberg Gojman
--Ayudante: Ricardo Jiménez Méndez
--Práctica 2
--Alumno: Cruz Pérez Ramón
--No. de Cuenta: 315008148

--Calcula el volumen de un cono
volCono :: (Num a,Floating a) => a -> a -> a
volCono r h = 3.1416*(r*r)*h/3

--Calcula el area de un trapecio
areaTrap :: (Num a,Floating a) => a -> a -> a -> a
areaTrap bm b h = (bm+b)/2*h

--Te indica como eres dependiendo de tu edad
edad :: Int -> String
edad ed
  | ed <= -1 = "Te estan haciendo"
  | ed <= 10 = "Peque"
  | ed <= 18 = "Chavo"
  | ed >= 19 = "Ya vamos por chelas "

  --Nos indica si un entero es par
par :: Int -> Bool
par p = if p `mod` 2 == 0
  then True
  else False

--Nos indica si un entero es impar
impar :: Int -> Bool
impar im = if im `mod` 2 /= 0
  then True
  else False 

{--Pruebas--}

--Debe regresar 1413.72
prueba1 = volCono 10 (13.5)

--Debe regresar 76328.6288072
prueba2 = volCono (-45.7) (34.9)

--Debe regresar 1038.78
prueba3 = areaTrap (13) (46.7) (34.8)

--Debe regresar 568.26
prueba4 = areaTrap (56.2) (34) (12.6)

--Debe regresar el mensaje que pusiste
prueba5 = edad (-5)

--Debe regresar el mensaje que pusiste
prueba6 = edad 46

--Debe dar True
prueba7 = par 2358

--Debe dar False
prueba8 = par (-367)

--Debe dar True
prueba9 = impar (-8925)

--Debe dar False
prueba10 = impar 123456
