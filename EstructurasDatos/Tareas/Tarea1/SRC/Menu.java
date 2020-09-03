/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Menu implements Serializable{

private Inventario x = new Inventario();
public transient Scanner sc = new Scanner(System.in);

 public void menuAdmin(){
   boolean salida = true;
   int opcion = 0;
   do{
     do{
       try {
         sc = new Scanner(System.in);
         System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
         System.out.println("¬¬¬ Seleccione una opcion ¬¬¬");
         System.out.println("°°1.- Ver el inventario completo°°");
         System.out.println("°°2.- Dar de alta nuevos productos.°°");
         System.out.println("°°3.- Quitar productos del inventario.°°");
         System.out.println("°°4.- Modificar el número de productos en existencia vı́a el número de producto.°°");
         System.out.println("°°5.- Modificar el precio de un producto vı́a el número de producto.°°");
         System.out.println("°°6.- Obtener el total de ventas de un dı́a.°°");
         System.out.println("°°7.- Obtener el total de ventas de un dı́a por producto.°°");
         System.out.println("°°8.- Salir del sistema°°");
         opcion = sc.nextInt();
         if (opcion < 9 && opcion > 0) {
           salida = false;
         }else {
           System.out.println("## Solo usa numeros del 1 a 8. ##");
         }
         System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
       }catch (Exception e) {
         System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
         sc = new Scanner(System.in);
       }
     } while (salida);
     salida = true;
     switch (opcion) {
       case 1:
        x.verInventario();
       break;
       case 2:
        x.darAltaNuevos();
       break;
       case 3:
        x.quitarProductos();
       break;
       case 4:
        x.modificaProductos();
       break;
       case 5:
        x.modificaPrecio();
       break;
       case 6:
        x.obtenerVentasDia();
       break;
       case 7:
        x.obtenerVentasProducto();
       break;
       case 8:
        System.out.println("¬¬¬ Gracias ¬¬¬");
        salida = false;
       break;
     }
    }while(salida);
 }//fin metodo menuAdmin

 public void menuVendedor(){
   boolean salida = true;
   int opcion = 0;
   do{
     do{
       try {
         System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
         System.out.println("¬¬¬ Seleccione una opcion ¬¬¬");
         System.out.println("1.- Vender un producto vı́a el número de producto");
         System.out.println("2.- Obtener la información de un producto vı́a el número de producto");
         System.out.println("3.- Salir del sistema");
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        opcion = sc.nextInt();
        if (opcion < 4 && opcion > 0) {
          salida = false;
        }else {
          System.out.println("## Solo usa numeros del 1 a 4. ##");
        }
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
      }catch (Exception e) {
        System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
        sc = new Scanner(System.in);
      }
    } while (salida);
    salida = true;
    switch (opcion) {
      case 1:
       x.vender();
      break;
      case 2:
       x.obtenerInformacion();
      break;
      case 3:
       System.out.println("¬¬¬ Gracias ¬¬¬");
       salida = false;
      break;
    }
   }while(salida);

 }//fin menuVendedor

 public void menuPrincipal(){
   boolean salida = true;
   int opcion = 0;
   do {
     try {
       System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
       System.out.println("Seleccione una opcion.");
       System.out.println("1.- Menu del Admin.");
       System.out.println("2.- Menu del vendedor.");
       System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
       opcion = sc.nextInt();
       if (opcion < 4 && opcion > 0) {
         salida = false;
       }else {
         System.out.println("## Solo usa numeros del 1 a 2. ##");
       }
       System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
     }catch (Exception e) {
       System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
       sc = new Scanner(System.in);
     }
  } while (salida);
  switch (opcion) {
    case 1:
      this.menuAdmin();
    break;
    case 2:
      this.menuVendedor();
    break;
  }
 }



}//fin classe
