/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.*;
import java.util.*;
public class Inventario implements Admin, Serializable{

  private int vdiscos, vlibros, vpeliculas, vvideojuegos;
  private DiscosMusicales[] discos = new DiscosMusicales[1000]; //1
  private Libros[] libros = new Libros[1000];//2
  private PeliculasDvd[] peliculas = new PeliculasDvd[1000];//3
  private Videojuegos[] videojuegos = new Videojuegos[1000];//4
  public transient Scanner sc = new Scanner(System.in);

  private int idProcducto(){
    boolean salida = true;
    int c = 0;
    do{
      try {
        System.out.println("--Seleccione un producto: ");
        System.out.println("-1. Discos-");
        System.out.println("-2. Libros-");
        System.out.println("-3. Peliculas- ");
        System.out.println("-4. Videojuegos-");
        System.out.println("-5. Salir al menu anterior-");
        c = sc.nextInt();
        if (c < 6 && c > 0) {
          salida = false;
        }else {
          System.out.println("## Solo usa numeros del 1 a 5. ##");
        }
      }catch (Exception e) {
        System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
        sc = new Scanner(System.in);
      }
    }while (salida);
    return c;
  }

  private int pregunta(){
    boolean salida= true;
    int c = 0;
    do {
      try {
        System.out.println("--¿Quiere volver a hacer la operacion?");
        System.out.println("-1. Si.");
        System.out.println("-2. No.");
        c = sc.nextInt();
        if (c < 3 && c > 0) {
          salida = false;
        }else {
          System.out.println("## Solo usa numeros, 1 o 2. ##");
        }
      }catch (Exception e) {
        System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return c;

  }

  public void verInventario(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
      System.out.println("********-DISCOS-********");
      int cont=0;
      while(discos[cont] != null){
          discos[cont].tostring();
          cont++;
      }
      cont=0;
      System.out.println("********-LIBROS-********");
      while(libros[cont] != null) {
        libros[cont].tostring();
        cont++;
      }
      cont=0;
      System.out.println("********-PELICULAS-********");
      while(peliculas[cont] != null) {
        peliculas[cont].tostring();
        cont++;
      }
      cont=0;
      System.out.println("********-VIDEOJUEGOS-********");
      while(videojuegos[cont] != null) {
        videojuegos[cont].tostring();
        cont++;
      }
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
  }

  private int fin(Object[] x){
    int cont =0;
    while(x[cont] != null){
      cont++;
    }
    return cont;
  }
  public void darAltaNuevos(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----Dar de alta nuevos productos----");
    int id = this.idProcducto();
    if (id == 5  ) {
      return ;
    }
    boolean salida = true;
    int i ;
    switch (id) {
      case 1:
        DiscosMusicales n = new DiscosMusicales();
        do {
          System.out.println("**Creacion de nuevo disco**");
          n.crearNuevo();
          i = fin(discos);
          discos[i]=n;
          int p = this.pregunta();
          if (p == 2) {
            salida = false;
          }
          n = new DiscosMusicales();
        } while (salida);
      break;

      case 2:
        Libros l = new Libros();
        do{
          System.out.println("**Creacion de nuevo libro**");
          l.crearNuevo();
          i = fin(libros);
          libros[i]=l;
          int p = this.pregunta();
          l = new Libros();
          if (p == 2) {
            salida = false;
          }
        } while (salida);
      break;

      case 3:
      PeliculasDvd d = new PeliculasDvd();
      do{
        System.out.println("**Creacion de nueva pelicula**");
        d.crearNuevo();
        i = fin(peliculas);
        peliculas[i]=d;
        int p = this.pregunta();
        d = new PeliculasDvd();
        if (p == 2) {
          salida = false;
        }
      } while (salida);
    break;

    case 4:
      Videojuegos j = new Videojuegos();
      do{
        System.out.println("**Creacion de nuevo videojuego**");
        j.crearNuevo();
        i = fin(videojuegos);
        videojuegos[i]=j;
        int p = this.pregunta();
        j = new Videojuegos();
        if (p == 2) {
          salida = false;
        }
      } while (salida);

    }

    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
  }

  private void elimina(Object[] x){
    int i = 0;
    try {
      System.out.println("--¿Cuantos desea eliminar?--");
      sc = new Scanner(System.in);
      int e = sc.nextInt();
      int j =0;
      while(x[j] != null){
        j++;
      }
      if(j-e < 0  ){
        System.out.println("Error no hay tantos procuctos.");
      }else{
        i = j-e;
        while((i) <= j ) {
          x[j]=null;
          j--;
        }
        System.out.println("Se eliminaron: "+i);
      }
    }catch (Exception e) {
      System.out.println("## Error al eliminar. ##");
      sc = new Scanner(System.in);
      System.out.println("Solo se eliminaron: "+(i+1));
    }
  }

  public void quitarProductos(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----Quitar Productos----");
    int id = this.idProcducto(  );
    if (id == 5  ) {
      return ;
    }
    boolean salida = true;
    switch (id) {
      case 1:
      System.out.println("--Eliminar Discos--");
      this.elimina(discos);
      break;

      case 2:
        System.out.println("--Eliminar Libros--");
        this.elimina(libros);
      break;
      case 3:
        System.out.println("--Eliminar Peliculas--");
        this.elimina(peliculas);
      break;
      case 4:
        System.out.println("--Eliminar Videojuegos--");
        this.elimina(videojuegos);
      break;


    }




  }

  public void modificaProductos(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----Producto a modificar en existencia----");
    int id = this.idProcducto();
    if (id == 5  ) {
      return ;
    }
    boolean salida= true;
    int c = 0;
    do {
      try {
        System.out.println("--¿Que desea hacer?");
        System.out.println("-1. Eliminar.");
        System.out.println("-2. Añadir.");
        c = sc.nextInt();
        if (c < 3 && c > 0) {
          salida = false;
        }else {
          System.out.println("## Solo usa numeros, 1 o 2. ##");
        }
      }catch (Exception e) {
        System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
        sc = new Scanner(System.in);
      }
    } while (salida);

    if (c == 1) {
      this.quitarProductos();
    }else {
      this.darAltaNuevos();
    }

    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");

  }

  private double preguntaPrecio(){
    boolean salida = true;
    double precio;
    do {
      try{
        System.out.println("--¿Ponga el precio?--");
        sc = new Scanner(System.in);
        precio = sc.nextDouble();
        salida = false;
        return precio;
      }catch (Exception e) {
        System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return 0.0;
   }



  public void modificaPrecio(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----Producto a modificar en precio----");
    int id = this.idProcducto();
    double precio;
    if (id == 5  ) {
      return ;
    }
    switch (id) {
      case 1:
      System.out.println("--Precio Discos--");
      precio = this.preguntaPrecio();
      for ( int i = 0 ;discos[i] != null; i++  ) {
        discos[i].setPrecio(precio);
      }
      break;

      case 2:
        System.out.println("--Precio Libros--");
        precio = this.preguntaPrecio();
        for ( int i = 0 ;libros[i] != null; i++  ) {
          libros[i].setPrecio(precio);
        }
      break;
      case 3:
        System.out.println("--Precio Peliculas--");
        precio = this.preguntaPrecio();
        for ( int i = 0 ;peliculas[i] != null; i++  ) {
          peliculas[i].setPrecio(precio);
        }
      break;
      case 4:
      System.out.println("--Precio Videojuegos--");
      precio = this.preguntaPrecio();
      for ( int i = 0 ;videojuegos[i] != null; i++  ) {
        videojuegos[i].setPrecio(precio);
      }
      break;


    }
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
  }

  public void obtenerVentasDia(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    int ventas = vdiscos + vlibros+ vpeliculas+ vvideojuegos;
    System.out.println("Las ventas del dia son:"+ventas);
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
  }

  public void obtenerVentasProducto(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----Venta de productos----");
    int id = this.idProcducto();
    if (id == 5  ) {
      return ;
    }
    switch (id) {
      case 1:
        System.out.println("--Ventas Discos--");
        System.out.println("Las ventas son:"+vdiscos);
      break;
      case 2:
        System.out.println("--Ventas libros--");
        System.out.println("Las ventas son:"+vlibros);
      break;
      case 3:
        System.out.println("--Ventas Peliculas--");
        System.out.println("Las ventas son:"+vpeliculas);
      break;
      case 4:
        System.out.println("--Ventas Videojuegos--");
        System.out.println("Las ventas son:"+vvideojuegos);
      break;

    }
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
  }

  public void vender(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----¿Que producto quiere vender?----");
    int id = this.idProcducto();
    if (id == 5  ) {
      return ;
    }
    boolean x = true;
    int j =0;
    sc = new Scanner(System.in);
    switch (id) {
      case 1:
      int v;
        do {
          do {
              System.out.println("--Venta de Discos--");
              System.out.println("¿Cuantos discos quiere vender?");
              v = sc.nextInt();
              if (v<0 && v>discos.length ) {
                x = true;
              }else {
                x = false;
                System.out.println("Error en el rango de productos en la tienda");
              }
            } while (x);
          vdiscos-= v;
          j = fin(discos);
          for (int i = v-j; i < j ; i++ ) {
            discos[i]= null;
          }
          int c = this.pregunta();
          if (c==1) {
            x = true;
          }else {
            x = false;
          }
        } while (x);
      break;
      case 2:
        System.out.println("--Venta de libros--");
        v=0;
          do {
            do {
                System.out.println("--Venta de libros--");
                System.out.println("¿Cuantos libros quiere vender?");
                v = sc.nextInt();
                if (v<0 && v>libros.length ) {
                  x = true;
                }else {
                  x = false;
                  System.out.println("Error en el rango de productos en la tienda");
                }
              } while (x);
            vdiscos-= v;
            j= fin(libros);
            for (int i = v-j; i < j ; i++ ) {
              libros[i]=null;
            }
            int c = this.pregunta();
            if (c==1) {
              x = true;
            }else {
              x = false;
            }
          } while (x);
      break;
      case 3:
        System.out.println("--Venta de Peliculas--");
        v=0;
          do {
            do {
                System.out.println("--Venta de peliculas--");
                System.out.println("¿Cuantos peliculas quiere vender?");
                v = sc.nextInt();
                if (v<0 && v>peliculas.length ) {
                  x = true;
                }else {
                  x = false;
                  System.out.println("Error en el rango de producto en la tienda");
                }
              } while (x);
            vpeliculas-= v;
            j=fin(peliculas);
            for (int i = v-j; i < j ; i++ ) {
              peliculas[i]=null;
            }
            int c = this.pregunta();
            if (c==1) {
              x = true;
            }else {
              x = false;
            }
          } while (x);
      break;
      case 4:
        System.out.println("--Venta de videojuegos--");
        v=0;
          do {
            do {
                System.out.println("--Venta de videojuegos--");
                System.out.println("¿Cuantos videojuegos quiere vender?");
                v = sc.nextInt();
                if (v<0 && v>videojuegos.length ) {
                  x = true;
                }else {
                  x = false;
                  System.out.println("Error en el rango de producto en la tienda");
                }
              } while (x);
            vvideojuegos-= v;
            j = fin(videojuegos);
            for (int i = v-j; i < j ; i++ ) {
              videojuegos[i]=null;
            }
            int c = this.pregunta();
            if (c==1) {
              x = true;
            }else {
              x = false;
            }
          } while (x);
      break;
    }
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");





  }

  public void obtenerInformacion(){
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    System.out.println("----Obtener Informacion----");
    int id = this.idProcducto();
    if (id == 5  ) {
      return ;
    }
    sc = new Scanner(System.in);
    int cont=0;
    switch (id) {
      case 1:
        System.out.println("--Informacion Discos--");
        while(discos[cont] != null){
            discos[cont].tostring();
            cont++;
        }
        cont=0;
      break;
      case 2:
        System.out.println("--Informacion libros--");
        while(libros[cont] != null) {
          libros[cont].toString();
          cont++;
        }
        cont=0;

      break;
      case 3:
        System.out.println("--Informacion Peliculas--");
        while(peliculas[cont] != null) {
          peliculas[cont].toString();
          cont++;
        }
        cont=0;

      break;
      case 4:
        System.out.println("--Informacion Videojuegos--");
        while(videojuegos[cont] != null) {
          videojuegos[cont].toString();
          cont++;
        }
      break;
    }
    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");


  }

}// fin clase
