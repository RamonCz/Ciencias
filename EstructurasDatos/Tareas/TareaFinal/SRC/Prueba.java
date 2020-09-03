public class Prueba {
  public static void main(String[] args) {
    Pila<Integer> p = new Pila();
    for (int i = 1; i < (81920) ; i++ ) {
      p.push(i);
      //System.out.println(i);
    }
      ArbolAVL<Integer> x = new ArbolAVL(p);
      System.out.println("wwwwwwwwwwwww");

    System.out.println(x.toString());
    System.out.println("wwwwwwwwwwwwwww");
    System.out.println(x.daal());

  }
}
