

def producto (c1,c2):
    return [(i,j) for i in c1 for j in c2]

def union(c1,c2):
    return (c1 | c2)

def interseccion(c1,c2):
    return (c1 & c2)

def diferencia(c1,c2):
    return (c1 - c2)

def difererenciaSimetrica(c1,c2):
    return (c1 ^ c2)

def reflexiva(r):
    return all([x in r for x in [(p[0],p[0]) for p in r]])

def simetrica(r):
    return all([x in r for x in [(p[1],p[0]) for p in r] ])

def transitiva(r):
    return all ([ x in r and y in r and (x[0],y[1]) in r for x in r for y in r if (x[1] == y[0]) ])

c1 = {1,2,3,4,5,6}
c2 = {1,2,3,7,8,9}
x = {(1,1),(2,2),(3,3),(1,2),(2,3),(1,3),(2,1),(3,2),(3,1)}  # En todo debe dar True
y = {(1,2),(2,3),(1,4)}  #con este hice la prueba de la tranasitividad da False

if __name__ == "__main__":
    prueba1 = producto(c1,c2)
    print ("El producto es: " +str(prueba1))
    prueba2 = union(c1,c2)
    print ("La union es "+ str(prueba2) )
    prueba3 = interseccion(c1,c2)
    print ("La interseccion es :"+ str(prueba3 ) )
    prueba4 = diferencia(c1,c2)
    print ("La diferencia es :"+ str(prueba4) )
    prueba5 = reflexiva(x)
    print (" Es reflexiva  "+str(prueba5))
    prueba6 = simetrica(x)
    print ("Es simetrica " +str(prueba6))
    prueba7 = transitiva(y)
    print ("Es transitiva "+str(prueba7))
