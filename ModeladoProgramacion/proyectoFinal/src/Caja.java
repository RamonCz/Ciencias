/**
*
* @author Cruz Perez Ramon
* @version 1.0
*/
import java.util.ArrayList;
public class Caja extends Thread{
  private Supermercado superM;
  private String ticket = "";
  private boolean rapida ;
  private boolean cerrar = true;
  private Double total =0.0;
  private Cliente cliente;
  private int clientes;
  private int productos;
  private int dinero;

  public Caja(Supermercado superM,boolean x){
    this.superM = superM;
    rapida = x;
    clientes =0;
    productos =0;
    dinero =0;
  }

  /**
  *@return numero de clientes
  */
  public int getClientes(){
    return clientes;
  }

  /**
  *@return numero de productos vendidos
  */
  public int getProductos(){
    return productos;
  }

  /**
  *@return dinero de venta
  */
  public int getDinero(){
    return dinero;
  }
  /**
  *@return si es rapida
  */
  public boolean rapida(){
    return rapida;
  }

  /**
  *@param cliente a asignar
  */
  public void nuevoCliente(Cliente c){
      cliente = c;
  }

  /**
  *cobra la lista que tiene el cliente
  */
  public  void cobra(){
    crearTicket();
    ArrayList<Producto> l = cliente.getLista();
    Producto p ;//= new Producto();
    boolean ex = false;
    clientes++;
    for (int i = 0 ; i < l.size() ; i++ ) {
      p = l.get(i);
      try {
        p.timeCaja(rapida);
        superM.elimina(p);
      }catch (Exception e) {
        ex = true;
      }
      if (ex) {
        ticket += "Producto Cancelado......... \n";
      }else {
        productos++;
        ticket += p.toString()+"\n";
        total += p.getTotal();
      }
    }
    finTicket();
    dinero += total;
    total = 0.0;
    cliente = null;
  }

  /**
  *Cierr la caja cambiando el valor  para que salga del while
  */
  public void cerrarCaja(){
    cerrar = false;
  }

  @Override
  public void run(){
    while(cerrar){
      nuevoCliente(superM.getCola(rapida));
      if (cliente!= null) {
        cobra();
      }
    }


  }
/**
*Crea el ticket nuevo
*/
  public void crearTicket(){
    ticket +="# "+superM.getVentas()+"-TICKET DE COMPRA\n----------------------------------------------------\n#Producto  Cantidad  Nombre    Precio   Total\n";

  }

/**
*termina del ticket y lo guardar en el Supermercado
*/
  public void finTicket(){
    ticket += "----------------------------------------------------\n                                      Subtotal:"+total+"\n                                      IVA: "+.5+"\n                                      Total: $"+((total*0.015)+total)+"\n----------------------------------------------------\n¡GRACIAS POR TU COMPRA, VUELVE PRONTO!\n----------------------------------------------------\n";
    System.out.println(ticket);
    superM.guardarTicket(ticket);
    ticket = "";
  }




}
