import simpy
'''
import sys
sys.path.insert(0,'Canales/')
'''
from Canales.CanalBroadcast import CanalBroadcast
from Nodo import *

class NodoVecinos(Nodo):
    ''' implementacion de la interfaz'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida):
        ''' Inicializacion del nodo '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributo extra para algoritmo
        self.identifiers = set() 
    
    def conoceVecinos(self, envi):
        ''' Funcion para conocer lo vecinos '''
        self.canal_salida.envia( self.vecinos, self.vecinos)
        while True:
            id = yield self.canal_entrada.get()
            for x in id:
                self.identifiers.add(x)
            print('%d recibío identifiers en el %d' %(self.id_nodo, envi.now))
            yield envi.timeout(1)
            


