import simpy
import random
from Nodo import *
from Canales.CanalRecorridos import *

class NodoBroadcast(Nodo):
    ''' implementacion de la interfaz'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida, mensaje = None):
        ''' Inicializacion del nodo '''
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        # Atributo extra para algoritmo
        self.mensaje = mensaje
        #extra para relojes
        self.reloj = 0 # Reloj de lamport
        self.eventos = [] # Lista de eventos
    
    def broadcast(self, env):
        ''' Funcion para enviar el mesaje '''
        if self.id_nodo == 0:
            yield env.timeout(random.randint(0, 10)) # Tenemos sistema asíncrono
            # Agregamos los eventos necesarios y aumentamos el reloj
            self.agrega_eventos(self.id_nodo, 'E', self.id_nodo, self.vecinos)
        else:
            while True:
                mensaje = yield self.canal_entrada.get()
                reloj_recibido = mensaje[0] # reloj que enviaron
                m = mensaje[1] # nodo que envio o mensaje
                self.reloj = max(reloj_recibido, self.reloj) #Actualizamos el reloj
                self.agrega_eventos(m, 'R', m , [self.id_nodo]) # Agregamos el evento de recepción.
                yield env.timeout(random.randint(0, 10))
                if self.vecinos != []:
                    self.agrega_eventos(self.id_nodo, 'E', self.id_nodo, self.vecinos)
                break
     
        
    def agrega_eventos(self, mensaje, tipo, emisor, receptores):
        ''' Función auxiliar para agregar los eventos a la lista correspondiente y aumenta los reloj por cada envio '''
        for receptor in receptores:
            self.reloj += 1
            self.canal_salida.envia([self.reloj, self.id_nodo],[receptor])
            print("%d %s a %d el reloj %d"%(emisor,mensaje,receptor,self.reloj))
            self.eventos.append([self.reloj, tipo, mensaje, emisor, receptor])