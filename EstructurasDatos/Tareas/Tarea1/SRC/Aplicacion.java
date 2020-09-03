/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class Aplicacion{
  public static void main(String[] args) {

        FileOutputStream fos = null;
        ObjectOutputStream salida = null;

        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        Menu x  = new Menu();
        boolean ex = true;
        try {
            fis = new FileInputStream("inventario.dat");
            entrada = new ObjectInputStream(fis);
            x = (Menu)entrada.readObject();
            ex = false;
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
        x.menuPrincipal();
        try {
            System.out.println("//Se crea el fichero");
            fos = new FileOutputStream("inventario.dat");
            salida = new ObjectOutputStream(fos);
            salida.writeObject(x);

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
