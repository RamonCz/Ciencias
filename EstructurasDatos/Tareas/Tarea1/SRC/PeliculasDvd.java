/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.Scanner;
public class PeliculasDvd implements Serializable{
  public transient Scanner sc = new Scanner(System.in);
  private String titulodelapelicula;
  private int añodelanzamiento;
  private String casaproductora;
  private String genero;
  public double precio;

/**
* Constructor
*/
  public PeliculasDvd(String titulodelapelicula, int añodelanzamiento, String casaproductora, String genero){
    this.titulodelapelicula = titulodelapelicula;
    this.añodelanzamiento = añodelanzamiento;
    this.casaproductora = casaproductora;
    this.genero = genero;
  }

  public  PeliculasDvd(){
    this.titulodelapelicula = "";
    this.añodelanzamiento = 0;
    this.casaproductora = "";
    this.genero = "";
  }

/**
*getters
*/
  public  double  getPrecio(){
    return precio;
  }
  public String getTitulodelapelicula(){
    return titulodelapelicula;
  }
  public int getAñodelanzamiento(){
    return añodelanzamiento;
  }
  public String getCasaproductora(){
    return casaproductora;
  }
  public String getGenero(){
    return genero;
  }

/**
*setters
*/
  public void setPrecio(double x){
    precio = x;
  }
  public void setTitulodelapelicula( String x){
    titulodelapelicula = x;
  }
  public void setAñodelanzamiento(int x){
    añodelanzamiento = x;
  }
  public void setCasaproductora(String x){
    casaproductora = x;
  }
  public void setGenero(String x){
    genero = x;
  }

  public void tostring(){
    System.out.println("**Pelicula**");
    System.out.println("Titulo: "+titulodelapelicula);
    System.out.println("Año de lanzamiento: "+añodelanzamiento);
    System.out.println("Casa productora: "+casaproductora);
    System.out.println("Genero: "+genero);

  }

  public void crearNuevo(){
    System.out.println("**Pelicula**");
    System.out.println("--Por favor escriba lo que se pide--");
    System.out.println("Titulo: ");
    this.titulodelapelicula = sc.nextLine();
    int a = 0;
    boolean salida= true;
    do {
      try {
        System.out.println("-.Año de lanzamiento: ");
        sc = new Scanner(System.in);
        a = sc.nextInt();
      }catch (Exception e) {
        System.out.println("##### ERROR SOLO USA NUMEROS ##### ");
        System.out.println(" --Intente de nuevo.--");
      }
      salida = false;
    } while (salida);
    sc = new Scanner(System.in);

    System.out.println("Casa productora: ");
    this.casaproductora = sc.nextLine();
    System.out.println("Genero: ");
    this.genero = sc.nextLine();

    System.out.println();

  }


}//fin de clase
