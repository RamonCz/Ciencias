public class Prueba{
  public static void main(String[] args){
    Pila<Integer> p = new Pila();
    for(int i = 0;i<10;i++){
      p.push(i);
    }
    System.out.println("Pila\n" + p.toString());
    Integer t = p.pop();
    System.out.println("pop " + t);
    System.out.println("pila\n" + p.toString());

    System.out.println("--------------------------------");

    Cola<Integer> c = new Cola();
    for(int i = 0;i<10;i++){
      c.push(i);
    }
    System.out.println("Cola\n" + c.toString());
    Integer tt = c.pop();
    System.out.println("pop " + tt);
    System.out.println("cola\n" + c.toString());
  }
}
