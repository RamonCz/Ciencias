public class PruebaConjunto{
  public static void main(String[] args) {
    Integer[] t={1,3,5,7,9};
    Integer[] k ={2,4,6,8,10};
    Integer[] q ={3,6,9,10,11,12};
    Conjuntable<Integer> c1 = new Conjunto(t);
    Conjuntable<Integer> c2 = new Conjunto(k);
    Conjuntable<Integer> c3 = new Conjunto(q);
    Conjuntable<Integer> union = c1.union(c2);
    Conjuntable<Integer> interseccion = c1.interseccion(c3);
    Conjuntable<Integer> diferencia = c1.diferencia(c3);
    Conjuntable<Integer> difsimetrica = c1.diferenciaSimetrica(c3);
    System.out.println("U"+union);
    System.out.println("I"+interseccion);
    System.out.println("D: "+diferencia);
    System.out.println("DS: "+difsimetrica);
  }
}
