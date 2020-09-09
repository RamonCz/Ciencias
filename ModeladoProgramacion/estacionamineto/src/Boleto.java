/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.util.Scanner;
import java.util.Calendar;
public class Boleto{
  private Calendar calendario = Calendar.getInstance();
  private Auto auto;
  private Double hrEntrada;
  private Double hrSalida;
  private Double montoPagado;
  private Double cambio;
  private boolean mes = false;
  private int[] tiempo = new int[2];
  private long tI,tF; //Variables para determinar el tiempo de ejecución
  private Scanner sc = new Scanner(System.in);

  /**
  *classe que modela los veiculos
  */
  public class Auto{
    private boolean auto ;
    private boolean moto ;
    private String marca;
    private String modelo;
    private int año;
    private String placas;

    /**
    *Constructor
    */
    public Auto(int x){
      if (x == 1) {
        auto = true;
      }else if (x == 2) {
        moto = true;
      }else{
        auto = moto = false;
      }
    }

    /**
    *@param auto
    *@return  si los autos son iguales
    */
    public boolean sonIguales(Auto auto){
      if (this.modelo.equals(auto.modelo)) {
        if (this.placas.equals(auto.placas)) {
          return true;
        }
      }
      return false;
    }

    /**
    *Muesta el tipo del Automovil
    *@return Auto o Motocicleta
    */
    public String muestra(){
      String s = "";
      if (auto) {
        s += "A";
        return s;
      }else if(moto){
        s += "M";
        return s;
      }
      return s +=" ";
    }

  }//fin clase auto

  /**
  *Constructor
  */
  public Boleto(){
    tI = System.currentTimeMillis();
    auto = new Auto(0);
  }

  /**
  *Cambia los atributos del Automovil
  *@param Placas
  *@param Modelo
  */
  public void setAuto(String p, String m){
    auto = new Auto(0);
    this.auto.placas  =p;
    this.auto.modelo =m;
  }

  /**
  *Toma el tiempo del boleto
  *@return hrs y minutos
  */
  public int[] getTiempo(){
    tF = System.currentTimeMillis();
    long t = tF - tI;
    tiempo[1] = (int)(t%60000)/1000;
    tiempo[0] += (int)(t/60000);
    return  this.tiempo;
  }

  /**
  *Manda el precio que se debe Pagar
  *@param tiempo
  *@return precio
  */
  public Double getPrecio(int[] s){
    if (tiempo[0]<2) {
        return (double)10;
    }else if(tiempo[0]==2) {
      return (double)10+(15*((int)(tiempo[1]/15)));
    }else {
      tiempo[0]-=2;
      return (double)(tiempo[0]*60)+15*((int)(tiempo[1]/15))+10 ;
    }
  }

  /**
  *muestra el auto
  *@return Auto
  */
  public String muestraAuto(){
    return this.auto.muestra();
  }

  /**
  *Comara los boletos
  *@param boleto
  *@return 0 o 1
  */
  public int compareTo(Boleto boleto){
    if (auto.sonIguales(boleto.auto)) {
      return 0;
    }
    return -1;
  }

  /**
  *pregunta sobre el pago mensual
  */
  public void pagoMesual(){
    int x = 750;
    System.out.println("Pago por mensualidad es $750");
    int p = errorDedo();
    if(p < x) {
      System.out.println("Monto insuficiente.");
    }else{
      System.out.println("Cambio : "+(p-x));
        mes = true;
    }
  }

  /**
  *@return pago el mes
  */
  public boolean mensual(){
     return mes;
  }

  /**
  *Pregunta todo sobre el auto
  */
  public void preguntaAuto(){
    boolean salida = true;
    do {
      try {
        System.out.println("¿Que desea agregar:");
        System.out.println("1.- Automovil");
        System.out.println("2.- Motocicleta");
        int a = errorDedo();
        if (a == 1 || a == 2) {
          this.auto = new Auto(a);
          System.out.println("Ingresa la Marca:");
          this.auto.marca = sc. nextLine();
          System.out.println("Ingresa el Modelo:");
          this.auto.modelo = sc.nextLine();
          System.out.println("Ingresa el año: ");
          int añ = errorDedo();
          this.auto.año = añ;
          System.out.println("Ingresa las placas:");
          this.auto.placas = sc.nextLine();
          System.out.println("¿Desea Pagar la pension por 1 mes?");
          System.out.println("1.- Si");
          System.out.println("2.- No");
          int p = errorDedo();
          if (p == 1) {
            pagoMesual();
          }
          System.out.println("Datos guardados.");
          int hora = calendario.get(Calendar.HOUR_OF_DAY);
          int minutos = calendario.get(Calendar.MINUTE);
          this.hrEntrada = (double)hora;
          System.out.println(hora + ":"+minutos);
          salida = false;
        }else {
          System.out.println("Valores incorrectos....");
        }
      } catch(Exception e) {
          System.out.println("Valores incorrectos...");
          sc = new Scanner(System.in);
      }
    } while (salida);
  }

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
  *Pregunta sobre el pago
  *@return  si paga o no paga
  */
  public boolean pago(){
    Double x = getPrecio(getTiempo());
    System.out.println("Moto a pagar: "+x);
    System.out.println("¿Cuanto desea pagar?");
    int p = errorDedo();
    if(p < x) {
      System.out.println("Monto insuficiente.");
    }else{
      System.out.println("Cambio : "+(p-x));
      return true;
    }
    return false;
  }

}
