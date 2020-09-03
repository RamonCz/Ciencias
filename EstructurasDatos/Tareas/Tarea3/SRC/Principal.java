/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.util.Scanner;
public class Principal{
  public Scanner sc = new Scanner(System.in);
  public int m, n;

  private boolean pregunta(){
    boolean salida = true;
    int d;
    do {
      try {
        System.out.println("--¿Quiere crear otro laberinto?--");
        System.out.println("1.- Si");
        System.out.println("2.- N0.");
        d = sc.nextInt();
        if (d == 1) {
          return true;
        }else if (d == 2) {
          return false;
        }else if(d < 0) {
          System.out.println("#--Escribe solo 1 ó 2.");
        }else if (d < 2) {
          System.out.println("#--Escribe solo 1 ó 2.");
        }
      }catch (Exception e) {
        System.out.println("### Solo usa numeros. ###");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return true;
  }

  
  public void parametros(){
    boolean salida = true;
    do {
      try {
        System.out.println("----Creacion de un Laberinto----");
        System.out.println("-Escriba el ancho del laberinto: ");
        m = sc.nextInt();
        if (m > 0) {
          System.out.println("-Escriba el largo del laberinto: ");
          n = sc.nextInt();
          if (n > 0) {
            Laberinto l = new Laberinto();
            l.creacion(n,m);
            l.imprimeLaberinto();
            System.out.println("______________________________________________________________________");
            System.out.println("--Laberinto resuelto--");
            System.out.println("Nota: el laberinto puede dar mas de una solucion.");
            l.resuelve();
            l.imprimeLaberinto();

            salida = pregunta();
          }
        }
      }catch (Exception e) {
        System.out.println("### Solo usa numeros. ###");
        sc = new Scanner(System.in);
      }
    } while (salida);
    System.out.println("++GRACIAS++");
  }// fin metodo

  public static void main(String[] args) {
    Principal x = new Principal();
    x.parametros();
  }


}
