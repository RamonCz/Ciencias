/**
 *
 * @author Cruz Perez Ramon
 * @version 1.0
 **/
//package proyecto.productos;
public class Producto{

    private int id;
    private String nombre;
    private int tiempoCaja;
    private Double precio;
    private int numero;
    private int total;
    public Producto(int id,String nombre,int tiempoCaja,Double precio,int numero){
      this.id = id;
      this.nombre = nombre;
      this.tiempoCaja = tiempoCaja;
      this.precio =precio;
      this.numero = numero;
    }
    public String toString(){
      String s= " "+id+"             "+numero+"     "+nombre+"     $"+(int)(precio*1)+"    $"+getTotal();
      return s;
    }
    public void incremetarNumero(){
      numero++;
    }
    public int getNumero(){
      return numero;
    }
    public int getTotal(){
      return (int)(numero * precio);
    }
    public int compareTo(Producto x){
      if (id < x.id)
        return -1;
      return id > x.id? 1:0;
    }

    public int getId(){
      return id;
    }
    public String getNombre(){
      return nombre;
    }
    public void setNumero(int n){
      numero = n;
    }
    public void timeCaja(boolean x){
      int n = 0;
      while(tiempoCaja *1000 < n && x){
        n++;
      }
    }



  }
