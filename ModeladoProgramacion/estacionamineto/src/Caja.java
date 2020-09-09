/**
* @author Cruz Perez Ramon
* @version 1.0
*/
import java.util.Scanner;
import java.util.ArrayList;
public class Caja{
  private Estacionamiento estacionamiento = new Estacionamiento();
  private ArrayList<Boleto> lista = new ArrayList<Boleto>();
  private Scanner sc = new Scanner(System.in);

  /**
  *pregunta un numero al usuario
  *@return entero que ingresado
  */
  public int errorDedo(){
    boolean salida = true;
    int s = 0;
    do {
      try {
        s = sc.nextInt();
        if (s > 0) {
          salida = false;
        }
      } catch(Exception e) {
          System.out.println("......Usa solo numeros.....");
          sc = new Scanner(System.in);
      }
    } while (salida);
    sc = new Scanner(System.in);
    return s;
  }

  /**
  *Controla todo el programa
  */
  public void principal(){
    boolean salida = true;
    do {
      System.out.println(estacionamiento.mostrar());
      System.out.println("''' CAJA ESTACIONAMIENTO '''");
      System.out.println("1.- Agregar Automovil");
      System.out.println("2.- Sacar Auto del Estacionamiento");
      System.out.println("3.- Ingresar Automovil registrado:");
      System.out.println("4.- Salir de aplicacion");
      int d = errorDedo();
      if (d == 1) {
        agregarAutomvil();
      }else if (d == 2) {
        salirEtacionamiento();
      }else if(d == 3){
        registrado();
      }else if ( d ==4){
        System.out.println("Gracias........");
        salida = false;
      }else{
        System.out.println("Valor incorrectos");
      }
    } while (salida);
  }

  /**
  * metodo para sacar un carro
  */
  public void salirEtacionamiento(){
    System.out.println("Ingresa el Modelo:");
    String m = sc.nextLine();
    System.out.println("Ingresa el Placas: ");
    String p = sc.nextLine();
    Boleto s = new Boleto();
    s.setAuto(p,m);
    s = estacionamiento.elimina(s);
    if (s == null) {
      System.out.println("No se encontro el Automovil");
    }else if (s.mensual()) {
      lista.add(s);
    }else if(!s.pago()){
      System.out.println("Pago no hecho vuelva a guardar el Automovil");
      estacionamiento.agregar(s);
    }
  }

  /**
  *metodo que verifica si esta registrado un Automovil
  */
  public void registrado(){
    System.out.println("Ingresa el Modelo:");
    String m = sc.nextLine();
    System.out.println("Ingresa el Placas: ");
    String p = sc.nextLine();
    Boleto s = new Boleto();
    s.setAuto(p,m);
    for ( Boleto x :  lista) {
      if (s.compareTo(x) ==0) {
        s =x;
      }
    }
    if (s.mensual()) {
      estacionamiento.agregar(s);
    }else {
      System.out.println("No se encontro el auto registrado.");
    }
  }

  /**
  *metodo que agregar un Automovil
  */
  public void agregarAutomvil(){
    Boleto b = new Boleto();
    b.preguntaAuto();
    System.out.println(estacionamiento.mostrar());
    estacionamiento.agregar(b);
  }

  public static void main(String[] args) {
    Caja c = new Caja();
    c.principal();
  }

}
