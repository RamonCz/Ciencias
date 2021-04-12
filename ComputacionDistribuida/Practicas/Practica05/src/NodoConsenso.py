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

        # Nuevos atributos para la implentacion de detectores
        self.crashed = [None]
        self.suspected = []
        self.timer = 0


    
    def consenso(self, env, f):
            '''El algoritmo de consenso.'''
            beta = 1 # rondas en el detector
            delta = 1 #cuanto tiempo empiezan los procesos
            mjs_recibidos = 0 #contador para los mensajes recibidos
            if self.id_nodo < f:
                self.fallare = True #nodos que van a fallar
            else:
                #implementacion del detector 
                for i in range(0,beta):
                    vencinos_no = [x for x in self.vecinos if x not in self.suspected]
                    self.canal_salida.envia(["INQUIRY", self.id_nodo],vencinos_no)
                    crashed = [True] * (len(self.vecinos) + 1)
                    crashed[self.id_nodo] = False
                    self.timer = delta 
                    mjs_recibidos = 0
                    while mjs_recibidos != 2 * (len(self.vecinos) - f):
                        
                        mjs = yield self.canal_entrada.get()
                        mjs_recibidos = mjs_recibidos + 1
                        
                        if mjs[0] == "INQUIRY":
                            self.canal_salida.envia(["ECHO", self.id_nodo],[mjs[1]])
                        
                        if mjs[0] == "ECHO":
                            crashed[mjs[1]] = False

                    mjs_recibidos = 0
                    
                    self.timer -= 1 #decremento timer para agregar a los sospechosos 
                                    #pues como los procesos fallan en a la primera no hay necesidad de hacer mas rondas

                    if self.timer == 0:
                        self.suspected = [i for i in range(0,len(crashed)) if crashed[i]]
                #fin del detector

                mjs_recibidos = 0 
                while env.now < f+1: # f = 0, pues el detector ya nos dice quien falla

                    if self.New != set():
                        self.canal_salida.envia(self.New,self.vecinos)
                        #print('%d envio %s sus vecinos en  %d' %(self.id_nodo,self.New,env.now))
                    
                    while mjs_recibidos < (len(self.vecinos)-(f+1)):
                        x = yield self.canal_entrada.get()
                        #print('%d recibio msj %s en  %d' %(self.id_nodo,x ,env.now))
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
                print('El proceso %d sospecha de: %s'%(self.id_nodo,self.suspected)) #imprime la var supect
                for x in self.V:
                    if x != None:
                        self.lider = x
                        break

