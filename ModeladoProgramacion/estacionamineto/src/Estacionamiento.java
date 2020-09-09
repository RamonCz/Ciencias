/**
* @author Cruz Perez Ramon
* @version 1.0
*/
import java.util.Scanner;
public class Estacionamiento{
  private Boleto[][] estacionamiento = new Boleto[10][10];
  private int lugares=100;
  private Scanner sc = new Scanner(System.in);

  /**
  *Constructor
  */
  public Estacionamiento(){
    for (int i =0;i<10;i++ ) {
      for (int j =0; j<10; j++) {
        estacionamiento[i][j]= new Boleto();
      }
    }
  }

  /**
  *elimina el boleto del estacionamiento
  *@param boleto a eliminae
  *@return boleto
  */
  public Boleto elimina(Boleto obj){
    Boleto x = new Boleto();
    for (int i =0 ;  i< 10; i++ ) {
        for (int j=0; j < 10; j++ ) {
            if (obj.compareTo(estacionamiento[i][j]) == 0) {
              x = estacionamiento[i][j];
              estacionamiento[i][j]= new Boleto();
              if (!x.mensual()) { //si no tiene renta mensual
                  lugares--;
              }
              return x;
            }
        }
    }
    return x;
  }

  /**
  *agregar un atuo al estacionamiento
  *@param auto ingresado
  *@param x coordenada
  *@param y coordenada
  */
  public void agregar(Boleto obj){
    if (lugares != 0 || obj.mensual()) {
      System.out.println("Ingresa el lugar donde quieres guardar tu Automovil");
      System.out.println("Coordenada x: ");
      int x = errorDedo();
      System.out.println("Coordenada y:");
      int y = errorDedo();
      estacionamiento[x][y] = obj;
      lugares--;
    }else {
      System.out.println("Lo sentimos no hay lugares");
    }
  }

  /**
  *pregunta un numero al usuario
  *@return entero que ingresado
  */
  public int errorDedo(){
    boolean salida = true;
    int s = 0;
    sc = new Scanner(System.in);
    do {
      try {
        s = sc.nextInt();
        if (s < 10 && s >=0) {
          salida = false;
        }else {
          System.out.println("Valor incorrectos....");
        }
      } catch(Exception e) {
          System.out.println("......Usa solo numeros.....");
          sc = new Scanner(System.in);
      }
    } while (salida);
    return s;
  }

  /**
  *muestra todo el estacionamiento
  *@return estacionamiento
  */
  public String mostrar(){
    String s ="";
    for (int i = 0;i <10 ;i++ ) {
       s +="  "+i+"";
     }
     s +="\n";
    for (int i =0 ;  i< 10; i++ ) {
      s += i;
        for (int j=0; j < 10; j++ ) {
            s +="["+estacionamiento[i][j].muestraAuto()+"]";
        }
        s+="\n";
    }
    return s;
  }

}
