"""
--Estructuras Discretas 2018-1
--Profesor: Laura Freidberg Gojman
--Ayudante: Ricardo Jimenez Mendez
--Practica 9
--Alumno: Cruz Perez Ramon
--No. de Cuenta: 31508148
"""

import types
"""
La funcion distancia de la pracica 1
"""
def distancia((x1,y1),(x2,y2)):
    r = (((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)) )**(0.5)
    return r

"""
La funcion areaCirculo de la practica 1
"""
def areaCirculo(radio):
    r = (radio ** 2) * 3.1416
    return (r)

"""
La funcion volCono de la practica 2
"""
def volCono(r,h):
    resultado = (3.1416 * (r ** 2)) * h/3
    return resultado

"""
La funcion edad de la practica 2
"""
def edad (n):
    if (n < 0):
        print ("jajaja no mames")
    elif (n < 10):
        print ("chavito")
    elif (n < 18):
        print ("Adolescente")
    else:
        print ("Camara vamos por unas chelas xD")

"""
La funcion eliminaUno de la practica 3
"""
def eliminaUno(n,l):
    for i in range(len(l)):
        if (n == l[i]):
            del l[i]
            return l

"""
La funcion eliminaTodos de la practica 3
"""

def eliminaTodos(n,l):
    cont = 0
    for i in range(len(l)):
        if (n == l[cont-i]):
            del l[cont -i]
            cont += 1

    return l

"""
La funcion tira de la practica 3
"""
def tira(n,l):
    for i in range(n):
        del l[i]
    return l

"""
La funcion promedio de la practica 4
"""
def promedio (l):
    s = 0
    cont = 0
    for i in range(len(l)):
        s += l[i]
        cont += 1
    resultado = s/cont
    return resultado

"""
La funcion cuentaNum de la practica 4
"""
def cuentaNum (n,l):
    cont = 0
    for i in range(len(l)):
        if (n == l[i]):
            cont += 1
    return cont

"""
Implementar una funcion que calcule el fibonacci de un numero
"""
def fibonacci (n):
    if (n == 0):
        return 0
    elif (n == 1):
        return 1
    else:
        return (fibonacci (n-1)) + (fibonacci (n-2))

if __name__ == "__main__":
    p1 = distancia((0,1),(10,1))
    print("Distancia de los puntos ((0,1),(10,1)) = " + str(p1))
    p2 = areaCirculo (4)
    print("Area del circulo con radio (4) = " + str(p2))
    p3 = volCono(4,4)
    print("Volumen del cono con radio 4 y altura 4 = "+str(p3))
    print("La edad de 15 es : ")
    p4 = edad(15)
    p5 = eliminaUno(2,[1,2,2,2,3,4,5,6])
    print("Elimina Uno de una lista es. 2 , [1,2,2,2,3,4,5,6] ")
    for i in range(len(p5)):
        print(str(p5[i])+", ")
    p6 = eliminaTodos(2,[1,2,2,2,3,4,5,6])
    print("Elimina Todos de una lista es. 2 , [1,2,2,2,3,4,5,6]")
    for i in range(len(p6)):
        print(str(p6[i])+", ")
    p7 = tira(2,[1,2,3,4,5,6])
    print("Tirar de una lista es. 2 , [1,2,3,4,5,6]")
    for i in range(len(p7)):
        print(str(p7[i])+", ")
    p8 = promedio([10,10,10,1])
    print ("El promedio es [10,10,10,1] : "+str(p8))
    p9 = cuentaNum(2,[1,2,2,2,2,3,4,5,6,7,8,9,10])
    print ("cuenta nuemeros de : 2 , [1,2,2,2,2,3,4,5,6,7,8,9,10]")
    print (str(p9))
    p10 = fibonacci(6)
    print ("El fibonacci de 6 es :"+str(p10))
