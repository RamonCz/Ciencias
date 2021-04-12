#para la implementacion python ya tiene una notacion de las graficas como sigue:
# fuente:https://www.tutorialspoint.com/python_data_structure/python_graphs.htm
# por lo que son diccionarios a mas bajo nivel
# la implemnetación que se muestra funciona para graficas dirigidas, por lo cual
# es necesario mostrar el regreso para graficas simples
grafica = {
  'A' : ['B','C','D','F'],
  'B' : ['D', 'E','F'],
  'C' : ['F','A','D'],
  'D' : ['A','F','E'],
  'E' : ['F','A'],
  'F' : ['A','C']
}


def bfs(graph, raiz):
  visitados = [] # Lista de vertices a los cuales ya visitamos
  cola = []     # Una cola que servira como apoyo para ir guardando la lista de vecinos del vertice actual

  visitados.append(raiz)
  cola.append(raiz)

  while cola:
    actual = cola.pop(0) 
    #print (actual, end = " ") 

    for vecino in grafica[actual]:
      if vecino not in visitados:
        visitados.append(vecino)
        cola.append(vecino)
  return visitados
#llamada a la función, si se desea escoger otra raiz, solo hace falta cabiar el 'F'
print(bfs(grafica, 'F'))
# otros ejemplos:
# bfs(visitados, grafica, 'A')
# bfs(visitados, grafica, 'B')
# bfs(visitados, grafica, 'C')
# bfs(visitados, grafica, 'D')