public class Prueba{
  public static void main(String[] args) {
    System.out.println("Prueba Heaps");
    Lista<Integer> l = new Lista<Integer>();
    for (int i = 7; i > 0 ; i-- ) {
      l.agregar(i);
    }
    System.out.println("Imprimimos una lista");
    System.out.println(l.toString());
    System.out.println("Creamos un Heap Minimo");
    Heap h = new MinHeap(l);
    System.out.println("¿Es vacio? "+h.esVacio());
    System.out.println(h.toString());
    System.out.println("Esta el 1: "+h.contiene(1));
    System.out.println("Eliminamos el tope: ");
    Integer tope = (Integer)h.elimina();
    System.out.println("¿Contiene el tope que eliminamos?: "+h.contiene(tope));
    System.out.println(h.toString());
    int tam = h.getTamanio();
    System.out.println("Eliminamos todo el heap.");
    for (int i = 0; i < tam; i++ ) {
      System.out.println(h.elimina());
      System.out.println(h.toString());
    }
    System.out.println(h.toString());
    System.out.println("Agregamos:");
    for (int i = -10; i <10 ;i++ ) {
      h.agrega(i);
    }
    System.out.println(h.toString());
    System.out.println();
    // Prueba para heap
    System.out.println("Creamos un Heap Maximo");
    Heap t = new MaxHeap(l);
    System.out.println("¿Es vacio? "+t.esVacio());
    System.out.println(t.toString());
    System.out.println("Esta el 1: "+t.contiene(1));
    System.out.println("Eliminamos el tope: ");
    Integer top = (Integer)t.elimina();
    System.out.println("¿Contiene el tope que eliminamos?: "+t.contiene(top));
    System.out.println(t.toString());
    tam = t.getTamanio();
    System.out.println("Eliminamos todo el heap.");
    for (int i = 0; i < tam; i++ ) {
      System.out.println(t.elimina());
      System.out.println(t.toString());
    }
    System.out.println(t.toString());
    System.out.println("Agregamos:");
    for (int i = -10; i <10 ;i++ ) {
      t.agrega(i);
    }
    System.out.println(t.toString());
  }
}
