public class Prueba {
  public static void main(String[] args) {
    ArbolAVL<Integer> x = new ArbolAVL();
    for (int i = 1; i < (81920) ; i++ ) {
      x.agrega(i);
      System.out.println(i);
    }
      System.out.println("wwwwwwwwwwwww");

    System.out.println(x.toString());
    System.out.println("wwwwwwwwwwwwwww");
    System.out.println(x.daal());

  }
}
