import simpy
from Nodo import *
from Canales.CanalRecorridos import *
from sys import maxsize # importamos el maximo valor en de un int.
# La unidad de tiempo
TICK = 1

class NodoBFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast.'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo BFS. '''
        ''' Aquí va tu implementacion '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        #extra 
        self.padre = None
        self.distancia = maxsize

    def bfs(self, env):
        '''Implementacion de bfs'''
        if self.id_nodo == 0:
            self.padre = self.id_nodo
            self.distancia = 0
            m = [self.id_nodo, self.distancia]
            yield env.timeout(TICK)
            print('%d inicia'%(self.id_nodo))
            self.canal_salida.envia(m,self.vecinos)
        while True:
            mensaje = yield self.canal_entrada.get()
            id = mensaje[0]
            d = mensaje[1]
            print('%d recibío msj de %d en el %d' %(self.id_nodo, id, env.now))
            if d+1 < self.distancia:
                self.distancia = d+1
                self.padre = id
                m = [self.id_nodo, self.distancia]
                yield env.timeout(TICK)
                self.canal_salida.envia(m,self.vecinos)