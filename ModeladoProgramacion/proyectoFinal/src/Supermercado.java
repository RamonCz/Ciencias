/**
*
* @author Cruz Perez Ramon
* @version 1.0
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.io.Serializable;
public class Supermercado  implements Serializable{
  private ArrayList<Caja> cajas= new ArrayList();
  private int contCajas = 15;
  private ArrayList<Cliente> clientes = new ArrayList(); // es como una cola
  private String tickets ="";
  private String reporte = "--Reporte del dia-- Caja S es caja rapida\n";
  private int id=0;
  private static Producto[] almacen = new Producto[11];
  private final Gerente gerente = new Gerente();

  /**
  * Gerente que cancela un producto
  */
  private class Gerente{
    public Gerente(){
    }
    public boolean cancelaProducto(){
      int tres = (int) (Math.random() *100+1);
      return (tres == 3)? true:false;
    }
  }

  public Supermercado(){
    cajas = new ArrayList();
    FabricaDeProductos fabrica = new FabricaDeProductos();
    for (int i = 0; i < almacen.length ; i++ ) {
        almacen[i] = fabrica.productoNuevo(i+1);
    }
    Caja uno = new Caja(this, true);
    Caja dos = new Caja(this, false);
    cajas.add(uno);
    cajas.add(dos);
  }

  /**
  *@return id del ticket
  */
  public synchronized int getVentas(){
    return ++id;
  }
  /**
  *Guarda los tickets
  *@param ticket
  */
  public synchronized void guardarTicket(String s){
    tickets += s+"\n";
  }
  /**
  *elimina el producto del almacen si no hay solo cancela la compra de ese producto
  *@param producto
  */
  public synchronized void elimina(Producto p) throws Exception{
    int id = p.getId();
    int n = p.getNumero();
    int numero = almacen[id].getNumero()-n;

    if (numero < 0 || gerente.cancelaProducto()) {
      throw new Exception("Producto Cancelado");
    }else {
      almacen[id].setNumero(numero);
    }

  }

  /**
  *Cuando las cajas preguntan por un cliente se les manda una del la cola
  *@param si es rapida
  *@return cliente si es su caja correcta
  */
  public synchronized Cliente getCola(boolean rapida){
    if (!clientes.isEmpty()) {
      Cliente c = clientes.get(0);
      if (c != null) {
        if (c.getNumeroDeCosas() <= 20 && rapida) {
          clientes.remove(0);
          return c;
        }else if (!rapida){
          clientes.remove(0);
          return c;
        }
      }
    }
    return null;
  }

  /**
  *cuando un cliente se crea, entonces se forma en la cola
  *@param cliente
  */
  public synchronized  void atenderClientes(Cliente c){
    clientes.add(c);
  }

  /**
  *Crea un numero random de clientes y verifica si hay que abrir cajas
  */
  public void clientes(){
    int n = (int) (Math.random()*10+1);
    Cliente x;
    try {
      for (int i = 0; i < n; i++ ) {
        x = new Cliente(this);
        x.start();
      }
      abrirCaja();
    }catch (Exception e) {
    }

  }

  /**
  *Abre cajas dependiendo si los clientes exceden la capacidad de cada caja
  * ademas ve si hay mas clientes para caja rapida o no
  *@param si es rapida
  */
  public void abrirCaja()throws InterruptedException{
    int r1,r=0;
    int n1,n=0;
    for (int i =0; i < cajas.size();i++ ) {//verifica que cajas estan abiertas
      if (cajas.get(i).rapida()) {
        r++;
      }else {
        n++;
      }
    }
    r *=10;//capacidad por caja rapida
    n *=15;//capacidad por caja normal
    r1=0;
    n1=0;
    if ((n+r) > clientes.size()) { // si hay mas clientes que capacidad
      Cliente c ;//= new Cliente();
      for (int i =0; i <clientes.size() ;i++ ) { //que caja se necesita abrir
        c = clientes.get(i);
        if (c.getNumeroDeCosas() <=20) {
          r1++;
        }else {
          n1++;
        }
      }
      if (cajas.size() < contCajas) {
        Caja nueva;
        if (r1>n1) { // hay mas clientes con menos cosas
          nueva = new Caja(this,true);
        }else {
          nueva = new Caja(this,false);
        }
        cajas.add(nueva);
        cajas.get(cajas.size()-1).start();
      }
    }



  }

  /**
  *Ejecuta las cajas predeterminadas y crea lo clientes aleatoriamente
  */
  public void ejecucion() throws InterruptedException{
   for(int i =0; i< cajas.size();i++){
     cajas.get(i).start();
    }
    final Runnable tarea = new Runnable() {
      public void run() {
        clientes();
      }
    };

    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    timer.scheduleAtFixedRate(tarea, 1,1,TimeUnit.SECONDS);
  }

  /**
  *Cierra las cajas
  */
  public void cerrarCajas(){
    Caja c;
    for (int i= 0; i<cajas.size();i++ ) {
      c = cajas.get(i);
      c.cerrarCaja();
      if (c.rapida()) {
        reporte += "Caja S."+i;
      }else{
        reporte += "Caja "+i;
      }
      reporte += ": Atendio :"+c.getClientes()+" Vendio :"+c.getProductos()+" Cobro: $"+c.getDinero()+"\n";
    }
    archivo();
    System.out.println(reporte);
  }

  public void archivo(){
    try {
      File archivo = new File("archivo.txt");
      BufferedWriter bw;
      if(archivo.exists()) {
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(tickets);
      } else {
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(tickets);
      }
      bw.close();
    }catch (Exception e) {
    }
  }
  /**
  *agrega 1000 productos mas de lo que tiene
  */
  public void surtir(){
    for (int i=0; i< almacen.length; i++ ) {
      almacen[i].setNumero((almacen[i].getNumero())+1000);
    }
  }
  /**
  public static void main(String[] args) {
      long inicio = System.currentTimeMillis();
      Supermercado s = new Supermercado();
      try {
        //s.obtener();
        s.surtir();
        s.ejecucion();

        //  s.cerrarCajas();
      }catch (Exception e) {
      }
      new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                      s.cerrarCajas();
                      long fin = System.currentTimeMillis();
                      System.out.println("Tardé "+ (fin-inicio)+" segundos");

                      //s.guardar(s);
                      //System.exit(0);
                  }
                },10000);
                System.out.println("111111111111111111111111111111111111111111111111111111111s");
  }
*/



}
