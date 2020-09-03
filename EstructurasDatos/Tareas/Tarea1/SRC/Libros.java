/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.Scanner;
public class Libros implements Serializable{
  public transient Scanner sc = new Scanner(System.in);
  private String titulo;
  private String autor;
  private String genero;
  private String isbn;
  private String editorial;
  public double precio;

  /**
  * Constructor
  */
  public Libros(String titulo, String autor, String genero, String isbn, String editorial){
    this.titulo = titulo;
    this.autor = autor;
    this.genero = genero;
    this.isbn= isbn;
    this.editorial = editorial;
  }

  public Libros(){
    this.titulo = "";
    this.autor = "";
    this.genero = "";
    this.isbn= "";
    this.editorial = "";
  }
/**
*getters
*/
  public  double  getPrecio(){
    return precio;
  }
  public String getTitulo(){
    return titulo;
  }
  public String getAutor(){
    return autor;
  }
  public String getGenero(){
    return genero;
  }
  public String getIsbn(){
    return isbn;
  }
  public String getEditorial(){
    return editorial;
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
  public void setAutor( String x){
    autor = x;
  }
  public void setGenero(String x){
     genero = x;
  }
  public void setIsbn(String x){
    isbn = x;
  }
  public void setEditorial(String x){
    editorial = x;
  }

  public void tostring(){
    System.out.println("**Libro**");
    System.out.println("-.Titulo: "+titulo);
    System.out.println("-.Genero: "+genero);
    System.out.println(".-Isbn: "+isbn);
    System.out.println("-.Editorial: "+editorial);
  }

  public void crearNuevo(){
    System.out.println("**Libro**");
    System.out.println("--Por favor escriba lo que se pide--");
    System.out.println("-.Titulo: ");
    this.titulo = sc.nextLine();
    System.out.println("-.Genero: ");
    this.genero = sc.nextLine();
    System.out.println(".-Isbn: ");
    this.isbn = sc.nextLine();
    System.out.println("-.Editorial: ");
    this.editorial = sc.nextLine();

    System.out.println();
  }


}// fin classe
