/**
*
* @author Cruz Perez Ramon
* @version 1.0
*/
public class FabricaDeProductos{


  /**
  *  Constructor simple de la clase
  */
  public FabricaDeProductos(){

  }

  /**
  *  Factory method que genera instancias de Productos nuevos.
  * @return Una instancia de un Producto concreto.
  */
  public Producto productoNuevo(int n){
    Producto x = new Producto(0,"",0,0.0,0);
    switch (n) {
      case 1:
        x  = new Producto(1,"CocaCola",1,15.0,1);
      break;
      case 2:
        x = new Producto(2,"PanBimbo",9,15.0,1);
      break;
      case 3:
        x = new Producto(3,"Arroz",15,20.0,1);
      break;
      case 4:
        x = new Producto(4,"Manzanita Sol",7,13.0,1);
      break;
      case 5:
        x = new Producto(5,"Frijol",14,22.0,1);
      break;
      case 6:
        x = new Producto(6,"Azucar",14,22.0,1);
      break;
      case 7:
        x = new Producto(7,"Agua",1,10.0,1);
      break;
      case 8:
        x = new Producto(8,"Sopa",8,13.0,1);
      break;
      case 9:
        x = new Producto(9,"Maruchan",9,10.0,1);
      break;
      case 10:
        x = new Producto(10,"Cereal",1,25.0,1);
      break;
      case 11:
        x = new Producto(11,"Axe",5,30.0,1);
      break;
    }
    return x;
  }
}
