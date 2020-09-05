module Practica6 where
import Data.List
{--
Estructuras Discretas 2018-1
Profesor: Laura Freidberg Gojman
Ayudante: Ricardo Jiménez Méndez
Práctica 6
Alumno: Cruz Pérez Ramón
No. de Cuenta: 315008148
--}

--Tipo de dato para crear lógica proposicional
data Proposicion = Falso                                      -- False | 0
                 | Verdadero                                  -- True | 1
                 | Variable Var                               -- Cualquier letra del abecedario en mayúscula
                 | Neg Proposicion                            -- ¬P
                 | Con Proposicion Proposicion                -- P & Q
                 | Dis Proposicion Proposicion                -- P | Q
                 | Imp Proposicion Proposicion                -- P -> Q
                 | Syss Proposicion Proposicion deriving Eq   -- P <-> Q

--Tipo para definir variables en la lógica proposicional
type Var = Char

--Conjunto donde guardaremos el valor de verdad de las variables
--Si la variable se encuentra en el conjunto, su valor de verdad es True
--Si no, es False
type Estado = [Var]

--Lista por comprensión para tomar todas las letras del abecedario
abc = [x | x <- ['A'..'Z']]

--Nos regresa la variable que le pedimos del abecedario
tomaVar :: Var -> [Char] -> Var
tomaVar _ [] = error "no se encuentra el caracter"
tomaVar p (x:xs) = if (p == x)
                      then x
                      else tomaVar p xs

--Hacemos parte de la clase Show a Proposicion
--Para poder mostrar en pantalla de mejor manera las proposiciones
--Por ejemplo
-- Con (Neg (Variable (tomaVar 'P' abc))) (Variable (tomaVar 'Q' abc)) = (¬('P')) & ('Q')
instance Show Proposicion where
  show (Variable p) = "(" ++ show p ++ ")"
  show (Neg p) = "(¬" ++ show p ++ ")"
  show (Con p q) = "(" ++ show p ++ " && " ++ show q ++ ")"
  show (Dis p q) =  "(" ++ show p ++ " || " ++ show q ++ ")"
  show (Imp p q) = "(" ++ show p ++ " => " ++ show q ++ ")"
  show (Syss p q) = "(" ++ show p ++ " <=> " ++ show q ++ ")"
  show (Falso) = show "0"
  show (Verdadero) = show "1"
  -- Se deben de implementar las instancias a show de
  -- Con
  -- Dis
  -- Imp
  -- Syss
  -- Falso
  -- Verdadero

--Dada una proposición y un estado, determinar el valor de verdad de la proposición
interpretacion :: Proposicion -> Estado -> Bool
interpretacion pro est = case pro of
                        Falso -> False
                        Verdadero -> True
                        Neg p -> not (interpretacion p est)
                        Con p q -> (interpretacion p est) && (interpretacion q est)
                        Dis p q -> (interpretacion p est) || (interpretacion q est)
                        Imp p q -> not (interpretacion p est) || not (interpretacion q est)
                        Syss p q -> (not (interpretacion p est) || not (interpretacion q est)) && (not (interpretacion p est) || not (interpretacion q est))
                        Variable v ->  (notElem v est)

--Regresa las variables de una proposición
--Si la variable se repite, solo debe de aparecer una vez
{--notomaVar :: Var -> [Char] -> [Var]
notomaVar _ [] = error "no se encuentra el caracter"
notomaVar p (x:xs) = if (p == x)
                      then []
                      else notomaVar p xs--}
variables :: Proposicion -> [[Var]]
variables pro = case pro of
                        Neg p ->   nub (variables p)
                        Con p q -> nub ((variables p) ++ (variables q))
                        Dis p q ->  nub ((variables p) ++ (variables q))
                        Imp p q ->  nub ((variables p) ++ (variables q))
                        Syss p q -> nub ((variables p) ++ (variables q))
                        Variable v ->   [[v]]


{-- Pruebas --}
--Debe imprimir (('P') -> (¬('Q'))) <-> (('R') & (¬('S')))
prueba1 = Syss (Imp (Variable $ tomaVar 'P' abc) (Neg (Variable $ tomaVar 'Q' abc))) (Con (Variable $ tomaVar 'R' abc) (Neg (Variable $ tomaVar 'S' abc)))

--Debe regresar ["P","Q","R","S"]
prueba2 = variables prueba1

--Debe regresar False
prueba3 = interpretacion prueba1 ['P','S']

--Debe imprimir ((('P') -> ('Q')) & (('P') -> ('R'))) -> (('Q') -> ('R'))
prueba4 = Imp (Con (Imp (Variable $ tomaVar 'P' abc) (Variable $ tomaVar 'Q' abc)) (Imp (Variable $ tomaVar 'P' abc) (Variable $ tomaVar 'R' abc))) (Imp (Variable $ tomaVar 'Q' abc) (Variable $ tomaVar 'R' abc))

--Debe regresar ["P","Q","R"]
prueba5 = variables prueba4

--Debe regresar True
prueba6 = interpretacion prueba4 ['P']

--Debe imprimir ((¬('A')) | ('B')) -> (('C') & (¬('B')))
prueba7 = Imp(Dis (Neg (Variable (tomaVar 'A' abc)) )(Variable (tomaVar 'B' abc)) ) (Con (Variable (tomaVar 'C' abc)) (Neg (Variable (tomaVar 'B' abc))))

--Debe regresar ["A","B","C"]
prueba8 = variables prueba7

--Debe regresar True
prueba9 = interpretacion prueba7 ['C']

--Debe imprimir ( ( (('R') & ('S')) -> ('T'))  &  (¬('T')))  <->  (('T') -> ('Q'))
prueba10 = Syss (Con (Imp (Con (Variable (tomaVar 'R' abc))(Variable (tomaVar 'S' abc)))(Variable (tomaVar 'T' abc)))(Neg(Variable(tomaVar 'T' abc))) ) (Imp (Variable(tomaVar 'T' abc))(Variable (tomaVar 'Q' abc )))
