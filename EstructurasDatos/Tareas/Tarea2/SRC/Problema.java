/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
public class  Problema{
  Scanner sc = new Scanner(System.in);

  public void version1(int n, int m){
    System.out.println("--Version 1--");
    ListaCircular l = new ListaCircular();
    Lista x  = new Lista();
    int posicion =0;
    int ejecucion =0;
    String r= "";
    for (int i =1; i < n ;i++ ) {
      l.insertar(i);
    }

    java.util.Iterator it = l.elementos();
    while(it.hasNext()) {
      posicion++;
      String dead = (it.next()).toString();
      if(posicion == m-1){
        ejecucion++;
        r  = "("+ejecucion+","+dead+")";
        x.agregar(r);
        System.out.print("En la ejecucion "+ejecucion+" se murio: "+dead);
        it.remove();
        System.out.println();
        posicion = 0;
      }
    }
    System.out.println("Fin Version 1");
    System.out.println("Permutaciones resultantes");
    System.out.println(x);

  }

  public void version2(int n){
    System.out.println("--Version 2--");
    System.out.println("ESTA VERSION PUEDE TARDA PORFAVOR ESPERE");
    ListaCircular l = new ListaCircular();
    Lista x  = new Lista();
    int posicion =0;
    int ejecucion =0;
    String r= "";
    for (int i =1; i < n ;i++ ) {
      l.insertar(i);
    }

    java.util.Iterator it = l.elementos();
    int m = (int) ((Math.random() * n) + 1);
    while(it.hasNext()) {
      posicion++;
      String dead = (it.next()).toString();
      if(posicion == m-1){
        ejecucion++;
        r  = "("+ejecucion+","+dead+")";
        x.agregar(r);
        System.out.print("Con "+m+" permutaciones en la ejecucion "+ejecucion+" se murio: "+dead);
        it.remove();
        System.out.println();
        posicion = 0;
        m = (int) ((Math.random() * n) + 1);
      }
    }
    System.out.println("Fin Version 2");
    System.out.println("Permutaciones resultantes");
    System.out.println(x);
  }

  public int numeroP(){
    boolean salida= true;
    int n=0;
    do {
      try {
        System.out.println("Numero de personas en el circulo: ");
        n  = sc.nextInt();
        if (n<0) {
          System.out.println("Valor invalido, use positivos");
        }else{
          return n;
        }
      }catch (Exception e) {
        System.out.println("Valor invalido");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return n;
  }
  public void menuVersion1(){
    boolean salida= true;
    int n=0;
    int m=0;
    System.out.println("--Version 1--");
    n = numeroP();
    do {
      try {
        System.out.println("Numero de pasos a dar para ejecutar a una persona: ");
        m = sc.nextInt();
        if (m<0) {
          System.out.println("Valor invalido, use positivos");
        }else {
          salida = false;
        }
      }catch (Exception e) {
        System.out.println("Valor invalido");
        sc = new Scanner(System.in);
      }
    } while (salida);
    version1(n,m);
  }
  public void menuVersion2(){
    int n=0;
    System.out.println("--Version 2--");
    n = numeroP();
    version2(n);
  }
  public void menu(){
    int opcion=0;
    boolean salida= true;
    do{
      try {
        System.out.println("Bienvenido");
        System.out.println("Escriba en numero de una opcion:");
        System.out.println("1.- Version 1");
        System.out.println("2.- Version 2");
        System.out.println("3.- Salir");
        opcion = sc.nextInt();
        if (opcion < 0) {
          System.out.println("Solo usa los numeros de las opciones");
        }else if(opcion < 4){
          switch (opcion) {
            case 1:
            menuVersion1();
            break;
            case 2:
            menuVersion2();
            break;
            case 3:
            salida = false;
            break;
          }
        }else {
          System.out.println("Solo usa los numeros de las opciones");
        }
      }catch (Exception e) {
        System.out.println("Valor invalido");
        sc = new Scanner(System.in);
      }
    }while(salida);
  }

  public static void main(String[] args) {
    Problema x = new Problema();
    x.menu();
  }


}
