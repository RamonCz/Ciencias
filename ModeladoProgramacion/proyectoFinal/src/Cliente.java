/**
*
* @author Cruz Perez Ramon
* @version 1.0
*/
import java.util.ArrayList;
public class Cliente extends Thread{
  private ArrayList<Producto> lista = new ArrayList();
  private int numeroCosas;
  private Supermercado superM;
  /**
  *Constructor genera las el numero de cosas que va a comprar
  **/
  public Cliente(Supermercado superM){
    numeroCosas = (int) (Math.random()*50+1);
    this.superM = superM;
    //generaLista();
  }

  public int getNumeroDeCosas(){
    return numeroCosas;
  }
  /**
  * Genera la lista de compras del cliente
  */
  public void generaLista(){
    FabricaDeProductos fabrica = new FabricaDeProductos();
    Producto x;
    while(numeroCosas > 0) {
      int n = (int)(Math.random()*10 + 1);
      x = fabrica.productoNuevo(n);
      boolean s = true;
      if (lista.isEmpty()) {
        lista.add(x);
      }else {
        for (int i = 0; i < lista.size() ; i++ ) {
          if (lista.get(i).compareTo(x) == 0) {
              Producto y = lista.get(i);
              lista.remove(i);
              y.incremetarNumero();
              lista.add(i,y);
              s = false;
          }
        }
        if (s) {
          lista.add(x);
        }
        //lista.sort((o1, o2) -> o1.compareTo(o2));
      }
      numeroCosas--;
    }
  }

  /**
  *@return  lista de compras
  */
  public ArrayList getLista(){
    return lista;
  }

  public void run(){
    generaLista();
    superM.atenderClientes(this);
  }

}
