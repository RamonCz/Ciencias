import java.util.Scanner;
public class Aplicacion{
  private Scanner sc ;
  private Diccionario dic;

  public Aplicacion(){
    System.out.println("Espere un momento porfavor...");
    sc = new Scanner(System.in);
    dic = new Diccionario();
  }
  public void sugerencia(Palabra c){
    Lista<Palabra> l = dic.opcionesSuguerencias(c);
    int d =0;
    boolean salida = true;
    do{
      try {
        if (!l.esVacia()) {
          System.out.println("---Tal vez quizo decir:");
          for (int i = 0 ; i < l.longitud(); i++ ) {
            System.out.println((i+1)+".- "+l.getElemento(i));
          }
          System.out.println("0.- Si es correcta su palabra: ");
          d = sc.nextInt();
          if (d > 0 && d < l.longitud()+1 ) {
            c.setCadena(l.getElemento(d-1));
            salida = false;
          }else if (d == 0) {
            salida = false;
          }else{
            System.out.println("## Solo escriba del "+1+" al "+l.longitud());
          }
        }else {
          salida  = false;
        }
      }catch (Exception e) {
        sc = new Scanner(System.in);
      }
    }while(salida);
  }

  public void menuPrincipal(){
    String palabra;
    boolean salida = true;
    Palabra p;
    do {
      try {
        System.out.println("----Corrector Ortográfico---");
        System.out.println("--Escriba un palabra: ");
        palabra = sc.nextLine();
        sc = new Scanner(System.in);
        p = new Palabra(palabra);
        boolean continua = p.estaBien();
        if (continua) {
          agrearDiccionario(p);
          salida = pregunta();
        }else {
          System.out.println("#- Palabra mal escrita intente de nuevo -#");
        }
      }catch (Exception e) {
        System.out.println(e);
        sc = new Scanner(System.in);
      }
    } while (salida);
    System.out.println("saliedo....");
    dic.guardarDiccionario();
    System.out.println("Gracias por usar el Corrector Ortográfico");
  }


  public void agrearDiccionario(Palabra palabra){
    sc = new Scanner(System.in);
    if (!dic.contiene(palabra)) {
      sugerencia(palabra);
      boolean salida = true;
      do{
        try {
          int d;
          System.out.println("¿Desea agregar: ´"+palabra.getCadena()+"´ al diccionario?");
          System.out.println("1.- Si");
          System.out.println("2.- No");
          d = sc.nextInt();
          sc = new Scanner(System.in);
          if (d < 1) {
            System.out.println("## Solo escriba 1 o 2.");
          }else if( d > 2) {
            System.out.println("## Solo escriba 1 o 2.");
          }else if (d == 1) {
            dic.agrega(palabra);
            salida = false;
          }else if (d ==2) {
            salida = false;
          }
        }catch (Exception e) {
          System.out.println("### Solo escriba numeros. ###");
          sc = new Scanner(System.in);
        }
      }while (salida);
    }else {
      System.out.println("--La palabra  ya se encuentra en el diccionario.--");
      System.out.println("--"+dic.busca(palabra).toString());
    }
  }

  public boolean pregunta(){
    sc = new Scanner(System.in);
    boolean salida = true;
    do {
      try {
        int d ;
        System.out.println("¿Desea escribir otra palabra?");
        System.out.println("1.- Si");
        System.out.println("2.- No");
        d = sc.nextInt();
        sc = new Scanner(System.in);
        if (d < 1) {
          System.out.println("## Solo escriba 1 o 2.");
        }else if( d > 2) {
          System.out.println("## Solo escriba 1 o 2.");
        }else if (d == 1) {
          return true;
        }else if (d ==2) {
          System.out.println("saliedo....");
          return  false;
        }
        salida = true;
      }catch (Exception e) {
        System.out.println("### Solo escriba numeros. ###");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return false;
  }

  public static void main(String[] args) {
    Aplicacion a = new Aplicacion();
    a.menuPrincipal();
  }
}
