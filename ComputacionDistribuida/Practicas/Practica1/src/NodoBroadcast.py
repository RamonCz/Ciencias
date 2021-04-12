import simpy
'''
import sys
sys.path.insert(0,'Canales/')
'''
from Canales.CanalBroadcast import CanalBroadcast
from Nodo import *

class NodoBroadcast(Nodo):
    ''' implementacion de la interfaz'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida, distinguido = False, mensaje = None):
        ''' Inicializacion del nodo '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributo extra para algoritmo
        self.distinguido = distinguido
        self.mensaje = mensaje
    
    def broadcast(self, envi):
        ''' Funcion para enviar el mesaje '''
        if self.distinguido == True:
            yield envi.timeout(1)
            print('%d inicia'%(self.id_nodo))
            self.canal_salida.envia(self.mensaje, self.vecinos)
        else:
            while True:
                mensaje = yield self.canal_entrada.get()
                print('%d recibío %s en el %d' %(self.id_nodo, mensaje, envi.now))
                yield envi.timeout(1)
                self.canal_salida.envia(mensaje, self.vecinos)
                break
                