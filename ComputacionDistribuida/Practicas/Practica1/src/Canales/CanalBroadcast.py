import simpy

class CanalBroadcast(object):
    '''
    Interfaz que modela el comportamiento que cualquier canal debe tomar.
    '''
    def __init__(self, env: simpy.Environment):
        '''Constructor de la clase. Se debe inicializar la lista de objetos Store al
        ser creado un canal.
        '''
        self.env = env
       # self.capacidad = capacidad
        self.canales = []
        self.canal_de_salida = None
        self.vecinos = []

    def envia(self, mensaje, vecinos ):
        '''
        Envia un mensaje a los canales de entrada de los vecinos.
        '''
        if not self.canales:
            raise RuntimeError('No hay canales de salida.')
        eventos = list()
        for i in range(len(self.canales)):
            if i in vecinos:
                eventos.append(self.canales[i].put(mensaje))
        return self.env.all_of(eventos)

    def envia2(self, id ,vecinos, funcion):
        '''
        Envia un mensaje a los canales de entrada de los vecinos.
        '''
        mensaje = [id,funcion]
        if not self.canales:
            raise RuntimeError('No hay canales de salida.')
        eventos = list()
        for i in range(len(self.canales)):
            if i in vecinos:
                eventos.append(self.canales[i].put(mensaje))
        return self.env.all_of(eventos)



    def crea_canal_de_entrada(self):
        '''
        Creamos un objeto Store en el un nodo recibirá los mensajes.
        '''
        canal = simpy.Store(self.env )#, capacity=self.capacidad)
        self.canales.append(canal)
        self.canal_de_salida = canal
        return canal
