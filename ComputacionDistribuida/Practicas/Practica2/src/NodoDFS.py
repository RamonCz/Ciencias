import simpy
from Nodo import *
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = 1

class NodoDFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast.'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo DFS. '''
        # Tu implementación va aquí
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        #extra 
        self.padre = None
        self.hijos = [] # set()

    def dfs(self, env):
        ''' Implementacion del algoritmo DFS. '''
        if self.id_nodo == 0:
            self.padre = self.id_nodo
            k = min(self.vecinos)
            self.hijos.append(k) #.add
            m = [{self.id_nodo},self.id_nodo,'GO']
            k2 = [k]
            yield env.timeout(TICK)
            print('%d inicia'%(self.id_nodo))
            self.canal_salida.envia(m,k2)
        
        while True:
            mensaje = yield self.canal_entrada.get()
            visitados = mensaje[0]
            id = mensaje[1]
            tipo = mensaje[2]
            print('%d recibío %s de %d en el %d' %(self.id_nodo,tipo, id, env.now))
            if visitados == -1:
                print('vacio')
                print(tipo)
            elif tipo == 'GO':
                self.padre = id
                if set(self.vecinos).issubset(visitados): # verificamos por medio de conjuntos la contencion
                    self.hijos = []
                    visitados.add(self.id_nodo)
                    m = [visitados,self.id_nodo,'BACK']
                    id2 = [id]
                    yield env.timeout(TICK)
                    self.canal_salida.envia(m,id2)                 
                else:
                    k = min(set(self.vecinos) - visitados)
                    self.hijos.append(k)
                    k2 = [k]
                    visitados.add(self.id_nodo)
                    m = [visitados,self.id_nodo,'GO']
                    yield env.timeout(TICK)
                    self.canal_salida.envia(m,k2)

            elif tipo == 'BACK':
                if set(self.vecinos).issubset(visitados):
                    if self.padre == self.id_nodo:
                        break
                    else:
                        m = [visitados,self.id_nodo,'BACK']
                        p = [self.padre]
                        yield env.timeout(TICK)
                        self.canal_salida.envia(m,p)
                else:
                    k = min(set(self.vecinos) - visitados)
                    self.hijos.append(k)
                    m = [visitados,self.id_nodo,'GO']
                    k2 = [k]
                    yield env.timeout(TICK)
                    self.canal_salida.envia(m,k2)