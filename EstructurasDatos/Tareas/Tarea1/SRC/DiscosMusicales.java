/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.Scanner;
public class DiscosMusicales implements Serializable{

public transient  Scanner sc = new Scanner(System.in);

  private String artista;
  private String nombredeldisco;
  private String genero;
  private int añodelanzamiento;
  private String sellodiscografico;
  public double precio;

/**
* Constructor
*/
  public DiscosMusicales(String artista, String nombredeldisco, String genero, int añodelanzamiento, String sellodiscografico){
    this.artista = artista;
    this.nombredeldisco = nombredeldisco;
    this.genero= genero;
    this.añodelanzamiento = añodelanzamiento;
    this.sellodiscografico = sellodiscografico;
  }

  public DiscosMusicales(){
    this.artista = "";
    this.nombredeldisco = "";
    this.genero= "";
    this.añodelanzamiento = 0;
    this.sellodiscografico = "";
  }
/**
*getters
*/
  public double getPrecio(){
    return precio;
  }
  public String getArtista(){
    return artista;
  }
  public String getNombredeldisco() {
    return nombredeldisco;
  }
  public String getGenero() {
    return genero;
  }
  public int getAñodelanzamiento() {
    return añodelanzamiento;
  }
  public String getSellodiscografico() {
    return sellodiscografico;
  }


/**
*setters
*/
  public void setPrecio(double x){
    precio = x;
  }
  public void setArtista(String x){
    artista = x;
  }
  public void setNombredeldisco(String x) {
    nombredeldisco = x;
  }
  public void setGenero(String x) {
    genero = x;
  }
  public void setAñodelanzamiento(int x) {
    añodelanzamiento = x;
  }
  public void setSellodiscografico(String x) {
    sellodiscografico = x;
  }
  public void setPrecio(int x) {
    precio = x;
  }

  public void tostring(){
    System.out.println("**Disco**");
    System.out.println("-.Nombre: "+nombredeldisco);
    System.out.println("-.Artista: "+artista);
    System.out.println("-.Genero: "+genero);
    System.out.println("-.Año de lanzamiento: "+añodelanzamiento);
    System.out.println("-.Sello discografico: "+sellodiscografico);
  }

  public void crearNuevo(){
    System.out.println("**Disco**");
    System.out.println("--Por favor escriba lo que se pide--");
    System.out.println("-.Nombre: ");
    this.nombredeldisco = sc.nextLine();
    System.out.println("-.Artista: ");
    this.artista = sc.nextLine();
    System.out.println("-.Genero: ");
    this.genero = sc.nextLine();
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
    System.out.println("-.Sello discografico: ");
    this.sellodiscografico = sc.nextLine();

    System.out.println("");
  }



}// fin clase
