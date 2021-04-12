import simpy
from Nodo import *
import random
from Canales.CanalRecorridos import *

# La unidad de tiempo
TICK = 1

class NodoDFS(Nodo):
    ''' Implementa la interfaz de Nodo para el algoritmo de Broadcast.'''
    def __init__(self, id_nodo, vecinos, canal_entrada, canal_salida,num):
        ''' Constructor de nodo que implemente el algoritmo DFS. '''
        # Tu implementación va aquí
        self.id_nodo = id_nodo
        self.vecinos = vecinos
        self.canal_entrada = canal_entrada
        self.canal_salida = canal_salida
        #extra 
        self.padre = None
        self.hijos = [] # set()
        #extra para relojes
        self.reloj = [0]*num # Reloj vectorial
        self.eventos = [] # Lista de eventos
        self.num = num # numero de nodos en la grafica
    def dfs(self, env):
        ''' Implementacion del algoritmo DFS. '''
        if self.id_nodo == 0:
            self.padre = self.id_nodo
            k = min(self.vecinos)
            self.hijos.append(k) #.add
            m = [{self.id_nodo},self.id_nodo,'GO',self.reloj]
            k2 = [k]
            yield env.timeout(random.randint(0, 10))
            self.agrega_eventos(m, 'E', self.id_nodo, k2) #enviamos el mensaje
        
        while True:
            mensaje = yield self.canal_entrada.get()
            visitados = mensaje[0]
            id = mensaje[1]
            tipo = mensaje[2]
            relojes = mensaje[3]
            self.agrega_eventos([None,id,None,relojes], 'R', id , [self.id_nodo]) # Agregamos el evento de recepción.
            if visitados == -1:
                print('vacio')
                print(tipo)
            elif tipo == 'GO':
                self.padre = id
                if set(self.vecinos).issubset(visitados): # verificamos por medio de conjuntos la contencion
                    self.hijos = []
                    visitados.add(self.id_nodo)
                    m = [visitados,self.id_nodo,'BACK',self.reloj]
                    id2 = [id]
                    yield env.timeout(random.randint(0, 10))
                    self.agrega_eventos(m, 'E', self.id_nodo, id2) #enviamos el mensaje              
                else:
                    k = min(set(self.vecinos) - visitados)
                    self.hijos.append(k)
                    k2 = [k]
                    visitados.add(self.id_nodo)
                    m = [visitados,self.id_nodo,'GO',self.reloj]
                    yield env.timeout(random.randint(0, 10))
                    self.agrega_eventos(m, 'E', self.id_nodo, k2) #enviamos el mensaje  

            elif tipo == 'BACK':
                if set(self.vecinos).issubset(visitados):
                    if self.padre == self.id_nodo:
                        break
                    else:
                        m = [visitados,self.id_nodo,'BACK',self.reloj]
                        p = [self.padre]
                        yield env.timeout(random.randint(0, 10))
                        self.agrega_eventos(m, 'E', self.id_nodo, p) #enviamos el mensaje  
                else:
                    k = min(set(self.vecinos) - visitados)
                    self.hijos.append(k)
                    m = [visitados,self.id_nodo,'GO',self.reloj]
                    k2 = [k]
                    yield env.timeout(random.randint(0, 10))
                    self.agrega_eventos(m, 'E', self.id_nodo, k2) #enviamos el mensaje  


         
    def agrega_eventos(self, mensaje, tipo, emisor, receptores):
        ''' Función auxiliar para agregar los eventos a la lista correspondiente y aumenta los reloj por cada envio '''
        id = mensaje[1]
        relojes = mensaje[3]
        if tipo == 'R':     
            for i in range(self.num):
                self.reloj[i] = relojes[i] if i != emisor else (self.reloj[emisor] + 1)
            self.eventos.append([self.reloj, tipo, receptores[0], receptores[0], emisor])
        else:
            for receptor in receptores:
                self.reloj[emisor] += 1 #aumenta su reloj 
                self.canal_salida.envia(mensaje,[receptor])
                self.eventos.append([self.reloj, tipo, emisor, emisor, receptor])