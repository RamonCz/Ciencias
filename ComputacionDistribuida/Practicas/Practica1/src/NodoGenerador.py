import simpy
'''
import sys
sys.path.insert(0,'Canales/')
'''
from Canales.CanalBroadcast import CanalBroadcast
from Nodo import *

class NodoGenerador(Nodo):
    ''' implementacion de la interfaz'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida, distinguido = False):
        ''' Inicializacion del nodo '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributo extra para algoritmo
        self.distinguido = distinguido
        self.padre = None
        self.expected_msgi = len(vecinos)
        self.hijos = set()
    


    def genera_arbol(self, envi):
        ''' implementacion del algoritmo'''
        if self.distinguido or (self.padre == self.id_nodo) or (self.id_nodo == 0):
            self.padre = self.id_nodo
            self.distinguido = True
            yield envi.timeout(1)
            print('%d inicia'%(self.id_nodo))
            self.canal_salida.envia2(self.id_nodo, self.vecinos, 'go')
        
        while True:
            mensaje = yield self.canal_entrada.get()
            if mensaje[1] == 'go':
                if self.padre == None:
                    self.padre = mensaje[0]
                    self.expected_msgi -= 1
                    print('%d recibío GO de %d en el %d' %(self.id_nodo, mensaje[0], envi.now))
                    if self.expected_msgi == 0: 
                        lst = [self.padre]
                        yield envi.timeout(1)
                        self.canal_salida.envia2(self.id_nodo,lst, 'back')
                    else: 
                        vecinos_aux = self.vecinos
                        vecinos_aux.remove(self.padre)
                        yield envi.timeout(1)
                        self.canal_salida.envia2(self.id_nodo, vecinos_aux,'go')
                else:
                    print('%d recibío GO de %d en el %d' %(self.id_nodo, mensaje[0], envi.now))
                    lst = [mensaje[0]]
                    yield envi.timeout(1)
                    self.canal_salida.envia2(None, lst,'back')
            elif mensaje[1] == 'back':
                self.expected_msgi -= 1
                if mensaje[0] == None:
                    print('%d recibio BACK en el %d' %(self.id_nodo, envi.now))
                else:
                    print('%d recibio BACK de %d en el %d' %(self.id_nodo,mensaje[0], envi.now))
                    self.hijos.add(mensaje[0])
                if self.expected_msgi == 0:
                    if (not self.distinguido):
                        lst = [self.padre]
                        yield envi.timeout(1)
                        self.canal_salida.envia2(self.id_nodo,lst,'back')
            
       
