/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.Scanner;
public class Videojuegos implements Serializable{
  public transient Scanner sc = new Scanner(System.in);
  private String titulo;
  private String plataforma;
  private String genero;
  private String publisher;
  public double precio;

  /**
  * Constructor
  */
  public Videojuegos(String titulo, String plataforma, String genero, String publisher){
    this.titulo = titulo;
    this.plataforma = plataforma;
    this.genero = genero;
    this.publisher = publisher;
  }
  public Videojuegos(){
    this.titulo = "";
    this.plataforma = "";
    this.genero = "";
    this.publisher = "";
  }

/*
* getters
*/
  public  double  getPrecio(){
    return precio;
  }
  public String getTitulo(){
    return titulo;
  }
  public String getPlataforma(){
    return plataforma;
  }
  public String getGenero(){
    return genero;
  }
  public String getPublisher(){
    return publisher;
  }

/**
*setters
*/
  public void setPrecio(double x){
    precio = x;
  }
  public void setTitulo(String x){
     titulo = x;
  }
  public void setPlataforma(String x){
     plataforma= x;
  }
  public void setGenero(String x ){
    genero = x;
  }
  public void setPublisher(String x){
    publisher = x;
  }

  public void tostring(){
    System.out.println("**Videojuego**");
    System.out.println("Titulo: "+titulo);
    System.out.println("Plataforma: "+plataforma);
    System.out.println("Genero: "+genero);
    System.out.println("Publisher: "+publisher);

  }

  public void crearNuevo(){
    System.out.println("**Videojuego**");
    System.out.println("--Por favor escriba lo que se pide--");
    System.out.println("Titulo: ");
    this.titulo = sc.nextLine();
    System.out.println("Plataforma: ");
    this.plataforma = sc.nextLine();
    System.out.println("Genero: ");
    this.genero= sc.nextLine();
    System.out.println("Publisher: ");
    this.genero = sc.nextLine();

    System.out.println();
  }// crea

}// fin class
