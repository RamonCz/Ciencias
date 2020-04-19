--Modelado y programacion 2019-2
--Tarea 2
--Alumnos: Cruz Pérez Ramón, Emilio Caballero Jimenez
import Data.Char
import Data.List

data Arbol a = Vacio
            | Nodo (String,Int) (Arbol a) (Arbol a)
            deriving (Eq,Ord,Show,Read)

--Devuelve una lista con el char y sus repeticiones
nodos :: String -> [(String, Int)]
nodos [] = []
nodos (x:xs) = [repeticiones [x] xs] ++ nodos (eliminaRepeticiones [x] xs)
-- Elimina el char del String para que no contemos otra vez el mismo caracter
eliminaRepeticiones :: String -> String -> String
eliminaRepeticiones x xs = [y | y <- xs, [y] /= x]
-- Nos da el numero de repeticiones de un char
repeticiones :: String -> String -> (String,Int)
repeticiones x xs = (x, length [y | y <- xs, [y] == x] +1)

--Ordena la lista de menor mayor
ordenaLista :: [(String,Int)] -> [(String,Int)]
ordenaLista [] = []
ordenaLista (x:xs) = ordenaLista(menores) ++ [x] ++ ordenaLista(mayores)
          where
              menores = [y | y <-xs, snd y < snd x]
              mayores = [z | z <-xs, snd z >= snd x]
--Nos regresa la lista con las repeticiones de cada letra ordena
listaDuplas :: String -> [(String,Int)]
listaDuplas s = ordenaLista(nodos s)

--Crea todos los las hojas con su fucion
crearN :: [(String, Int)] -> [Arbol a]
crearN [] = []
crearN [x] = [Nodo (fst x, snd x) (Vacio) (Vacio)]
crearN (x:xs) = [(Nodo ((fst x ++ fst (head xs)), (snd x + (snd (head xs)))) (Nodo(fst x,snd x) (Vacio) (Vacio) ) (Nodo (fst (head xs), snd(head xs)) (Vacio) (Vacio)))] ++ crearN (tail xs)

--da la triplera de los arboles
dupla :: Arbol a -> (String,Int)
dupla (Nodo (x,y) t1 t2)  = (x,y)

--Nos da el arbol completo gracias a crearArbol
arbolHuffman :: String -> Arbol a
arbolHuffman s = head(crearArbol(crearN(listaDuplas(s))))

--- funcion recursiva que crea el arbol completo, con una lista de arboles con la union de las hojas
crearArbol :: [Arbol a] -> [Arbol a]
crearArbol [] = []
crearArbol [x] = [x]
crearArbol (x:xs) = crearArbol ([Nodo ((fst (dupla x)++ fst (dupla (head xs))), (snd (dupla x) + (snd (dupla (head xs)))))  (x) (head xs)]++(tail xs))

--funcion que devuelve una lista con 101010 dependiendo la letra
listaCodigo :: Arbol a -> [(String,String)]
listaCodigo a = listaC (a) ("")
--funcion auxiliar de metodo listaCodigo Devuelve una lista con 01010 dependiendo la letra
listaC :: Arbol a -> String -> [(String,String)]
listaC a n = case a of
                Vacio -> []
                (Nodo a Vacio Vacio) -> [((n), (fst (a)))]
                (Nodo a t1 t2) ->(listaC t1 (n++"0")) ++ (listaC t2 (n++"1"))

--codifica la cadena con ayuda del metodo codificar
codificar :: String -> Arbol a -> String
codificar s a = crearCodigo s (listaCodigo a)
---Funcion que recibe una cadena y una lista para codificarla
crearCodigo :: String -> [(String,String)] ->  String
crearCodigo [] (x:xs) = []
crearCodigo (x:xs) [] = []
crearCodigo (x:xs) (y:ys) = go [x] (y:ys)++ crearCodigo (xs) (y:ys)
---funcion auxiliar para crearCodigo nos ayuda a obtener la cadena de 101010
go :: String -> [(String,String)] ->  String
go [] (x:xs) = []
go (x:xs) [] = []
go (x:xs) (y:ys) = if isSubsequenceOf [x] (snd(y))
                   then fst(y)
                   else crearCodigo (x:xs) ys

--decodifica la cadena de 10100 a cadela original o cualquier otra
decodifica :: String  -> Arbol a -> Arbol a -> String
decodifica [] e (Nodo a Vacio Vacio) = fst(a)
decodifica (x:xs) e a = case a of
                Vacio -> []
                (Nodo a Vacio Vacio) -> fst(a)++ decodifica (x:xs) e e
                (Nodo a t1 t2)-> if isSubsequenceOf [x] "0"
                              then decodifica (xs) e t1
                              else decodifica (xs) e t2
