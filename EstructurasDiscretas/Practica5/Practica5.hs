module Practica5 where

{--
Estructuras Discretas 2018-1
Profesor: Laura Freidberg Gojman
Ayudante: Ricardo Jiménez Méndez
Práctica 5
Alumno: Cruz Pérez Ramón
No. de Cuenta: 315008148
--}

{--
Datos de la siguiente gramática
S ::= [] | N:S | (S)
N ::= D | ND
D ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
--}

data S = Corch | Junta N P S | Encierra PI S PD
data N = Dig D | Combina N D
data D = D0 | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8 | D9
data P = Puntos
data PI = Pi
data PD = Pd

--Función para evaluar un dato de tipo S
evalS :: S -> String
evalS s = case s of
  Corch -> "[]"
  Junta n p s -> (evalN n) ++ (evalP p) ++ (evalS s)
  Encierra piz s pdr -> (evalPI piz) ++ (evalS s) ++ (evalPD pdr)


--Función para evaluar un dato de tipo N
evalN :: N -> String
evalN n = case n of
  Dig d -> (evalD d)
  Combina n d -> (evalN n) ++ (evalD d)

--Función para evaluar un dato de tipo D
--Regresa el dígito correspondiente
evalD :: D -> String
evalD d = case d of
  D0 -> "0"
  D1 -> "1"
  D2 -> "2"
  D3 -> "3"
  D4 -> "4"
  D5 -> "5"
  D6 -> "6"
  D7 -> "7"
  D8 -> "8"
  D9 -> "9"

--Función para evaluar un dato de tipo PI
--Nos regresa un paréntesis izquierdo
evalPI :: PI -> String
evalPI Pi = "("

--Función para evaluar un dato de tipo PD
--Nos regresa un paréntesis derecho
evalPD :: PD -> String
evalPD Pd = ")"

--Función para evaluar un dato de tipo P
--Nos regresa dos puntos
evalP :: P -> String
evalP Puntos = ":"

--Debe regresar "567:3810:[]"
prueba1 = evalS $ Junta (Combina (Combina (Dig D5) (D6)) (D7)) (Puntos) (Junta (Combina (Combina (Combina (Dig D3) (D8)) (D1)) (D0)) (Puntos) (Corch))

--Debe regresar "609:3710:(71:[])"
prueba2 = evalS $ Junta (Combina (Combina (Dig D6) (D0)) (D9)) (Puntos) (Junta (Combina (Combina (Combina (Dig D3) (D7)) (D1)) (D0)) (Puntos) (Encierra (Pi) (Junta (Combina (Dig D7) (D1)) (Puntos) (Corch)) (Pd)))

--Debe regresar "1357:(25:(479:[]))"
prueba3 = evalS $ Junta (Combina (Combina (Combina (Dig D1) (D3)) (D5)) (D7)) (Puntos) (Encierra (Pi) (Junta (Combina (Dig D2) (D5)) (Puntos) (Encierra (Pi) (Junta (Combina (Combina (Dig D4) (D7)) (D9)) (Puntos) (Corch)) (Pd))) (Pd))

--Debe regresar "56:234:[]"
prueba4 = evalS $ Junta (Combina (Dig (D5))(D6)) (Puntos) (Junta (Combina (Combina (Dig D2)(D3))(D4))(Puntos)(Corch))

--Debe regresar "2451:10:(7:[])"
prueba5 = evalS $ Junta (Combina (Combina (Combina (Dig D2)(D4))(D5))(D1)) (Puntos) (Junta (Combina (Dig D1)(D0)) (Puntos) (Encierra (Pi)(Junta (Dig D7) (Puntos) (Corch))(Pd)))
