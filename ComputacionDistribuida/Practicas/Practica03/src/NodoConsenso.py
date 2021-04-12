import simpy
from Nodo import *
from Canales.CanalRecorridos import *
# para que un nodo falle en una ronda r
#from random import randint 
# La unidad de tiempo
TICK = 1

class NodoConsenso(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Consenso.'''

    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Constructor de nodo que implemente el algoritmo de consenso. '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributos extra
        self.V = [None] * (len(vecinos) + 1) # Llenamos la lista de Nones
        self.V[id_nodo] = id_nodo
        self.New = set([id_nodo])
        self.rec_from = [None] * (len(vecinos) + 1)
        self.fallare = False      # Colocaremos esta en True si el nodo fallará
        self.lider = None         # La elección del lider.

    def consenso(self, env, f):
        '''El algoritmo de consenso.'''
        # Aquí va su implementación
        mjs_recibidos = 0 #contador para los mensajes recibidos
        if self.id_nodo < f:
            self.fallare = True #nodos que van a fallar
        else: 
            while env.now < f+1:
                if self.New != set():
                    self.canal_salida.envia(self.New,self.vecinos)
                    print('%d envio %s sus vecinos en  %d' %(self.id_nodo,self.New,env.now))
                
                while mjs_recibidos < (len(self.vecinos)-(f+1)):
                    x = yield self.canal_entrada.get()
                    print('%d recibio msj %s en  %d' %(self.id_nodo,x ,env.now))
                    y = x.copy().pop()
                    self.rec_from[y] = y
                    mjs_recibidos += 1 # aumento los mensajes recibidos
                mjs_recibidos = 0 #reinicio
                self.New = set()
                for m in self.rec_from:
                    if m != None:
                        if self.V[m] == None:
                            self.V[m]= m
                            self.New.add(m)
                yield env.timeout(TICK)
        if not self.fallare:
            for x in self.V:
                if x != None:
                    self.lider = x
                    break

