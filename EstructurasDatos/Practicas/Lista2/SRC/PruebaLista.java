public class PruebaLista{
  public static void main(String[] args) {
    Lista<Integer> n = new Lista();
    for (Integer i =0; i < 9 ;i++ ) {
      n.agregar(i);
      System.out.println(n);
    }
    Lista<Integer> reversa = n.reversa();
    Lista<Integer> ordenada = Lista.mergesort(n);
    Lista<Integer> copia = n.copia();
    System.out.println("reversa "+reversa);
    System.out.println("mergesort "+ordenada);
    System.out.println("copia "+copia);

    for (int i =0; i<5 ;i++ ) {
      n.eliminar(i);
    }
    System.out.println("eliminar "+n);





  }
}
