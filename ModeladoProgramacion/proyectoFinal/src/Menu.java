/**
 *
 * @author Cruz Perez Ramon
 * @version 1.0
 **/
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
public class Menu{

  public Scanner sc = new Scanner(System.in);
  public Menu(){

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
        if (s <= 3 && s >0) {
          salida = false;
        }else {
          System.out.println("Valor incorrecto. Intente otra vez.-");
        }
      } catch(Exception e) {
          System.out.println("......Usa solo numeros.....");
          sc = new Scanner(System.in);
      }
    } while (salida);
    return s;
  }
  public void menu(Supermercado superM){
    boolean salida = true;
    do{
      long inicio = System.currentTimeMillis();
      int d =0;
      System.out.println("1.- Surtir la tienda.");
      System.out.println("2.- Ejecuta Simulacion.");
      System.out.println("3.- Salir");
      d = errorDedo();
      switch (d) {
        case 1:
        superM.surtir();
        System.out.println("Supermercado surtido.");
        break;
        case 2:
        System.out.println("Ejecutando Simulacion");
        try {
          superM.surtir();
          superM.ejecucion();
        }catch (Exception e) {
        }
        new Timer().schedule(new TimerTask() {
          @Override
          public void run() {
            superM.cerrarCajas();
            long fin = System.currentTimeMillis();
            System.out.println("Tardé "+ (fin-inicio)+" segundos");
            System.exit(0);
          }
        },10000);
        break;
        case 3:
          salida = false;
        break;
      }
    }while(salida);
  }

  public static void main(String[] args) {
    Supermercado superM = new Supermercado();
    Menu n = new Menu();

    FileOutputStream fos = null;
    ObjectOutputStream salida = null;
    FileInputStream fis = null;
    ObjectInputStream entrada = null;
    try {
      fis = new FileInputStream("Supermercado.dat");
      entrada = new ObjectInputStream(fis);
      superM = (Supermercado) entrada.readObject();
    }catch (ClassNotFoundException e) {
      System.out.println(e);
    }catch(FileNotFoundException e) {
      System.out.println("1"+e.getMessage());
    }catch (IOException e) {
      System.out.println("2"+e.getMessage());
    }finally {
      try {
        if(fis!=null) fis.close();
        if(entrada!=null) entrada.close();
      } catch (IOException e) {
        System.out.println("3"+e.getMessage());
      }
    }
    n.menu(superM);

    fos = null;
    salida = null;
    fis = null;
    entrada = null;
    try {
      fos = new FileOutputStream("Supermercado.dat");
      salida = new ObjectOutputStream(fos);
      salida.writeObject(superM);
    } catch (FileNotFoundException e) {
      System.out.println("1"+e.getMessage());
    } catch (IOException e) {
      System.out.println("2"+e.getMessage());
    } finally {
      try {
        if(fos!=null) fos.close();
        if(salida!=null) salida.close();
      } catch (IOException e) {
        System.out.println("3"+e.getMessage());
      }
    }
  }

}
