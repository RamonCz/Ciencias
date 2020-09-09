

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author ramon
 */
public class Caja implements Serializable {
    private static int pizzas = 0;
    private static int refrescos =0;
    private int cerveza =0;
    private int jugos=0;
    private int fresas =0;
    private int flan=0;
    private int id =0;
    private int total=0;
    private static Ticket  t;

    public Caja(){
    }
    public class Producto{
        private String producto;
        private int numero;

        public Producto(int n, String p){
            producto = p;
            numero = n;
        }

        public int compareTo(Producto n){
            if(n.numero == numero) {
                return 0;
            }
            return (n.numero < numero)? -1:0;
        }
        public String producto(){
            return producto;
        }
        public int numero(){
            return numero;
        }
    }

    public String topVentas(){
        String s = "";
        s += " ---Top 5 en ventas--- \n";

        ArrayList<Producto> top= new ArrayList();
        top.add(new Producto(pizzas," Pizzas"));
        top.add(new Producto(refrescos," Refresco"));
        top.add(new Producto(cerveza," Cerveza"));
        top.add(new Producto(jugos," Jugos"));
        top.add(new Producto(fresas," Fresas"));
        top.add(new Producto(flan," Flan"));
        //Collections.sort(top);
       top.sort((o1, o2) -> o1.compareTo(o2));
        for(int i=0; i < 5; i++){
        s += top.get(i).producto()+": "+top.get(i).numero()+"\n";
        }
       return s;
    }
    public String ventas(){
        String s ="";
        s += " Pizzas: "+pizzas+"\n";
        s += " Refrescos: "+refrescos+"\n";
        s += " Cerveza: "+cerveza+"\n";
        s += " Jugos: "+jugos+"\n";
        s += " Fresas: "+fresas+"\n";
        s += " Flan: "+flan+"\n";
        s += " Transacciones: "+id+"\n";
        s += topVentas();
        return s;
    }
    public int total(){
      return t.getTotal();
    }
    public String getTicket(){
        return t.getTicket();
    }
    public void nuevoTicket(){
        t = new Ticket(this.getId());
        aumentaId();
    }

    public void aumentaId(){
        id++;
    }

    public void setTotal(){
       total = t.getTotal();
    }
    public int getId() {
        return id;
    }

    public void setRefrescos() {
        t.setRefrescos();
        refrescos++;
    }
    public void setCerveza() {
        t.setCerveza();
        cerveza++;
    }
    public void setJugos() {
        t.setJugos();
        jugos++;
    }
    public void setFresas() {
        t.setFresas();
        fresas++;
    }
    public void setFlan() {
        t.setFlan();
        flan++;
    }




    public void añadirPizza(int n,int tamaño){
        pizzas++;
        t.addPizza(false,n,tamaño,0);
    }

    public void paquete1(){
        pizzas++;
        this.setRefrescos();
        t.addPizza(true,0,2,0);//pep
    }
    public void paquete2(){
        pizzas++;
        this.setCerveza();
        t.addPizza(true,1,3,0); // haw
    }
    public void paquete3(){
        pizzas++;
        this.setRefrescos();
        t.addPizza(true,2,3,0);//mexi
    }
    public void paquete4(){
         pizzas += 2;
         this.setRefrescos();
         t.addPizza(true,0,2,2);//pep
    }
    public void paquete5(){
         pizzas +=2;
         this.setCerveza();
         this.setCerveza();
         t.addPizza(true,2,2,2);

    }

}
