
/**
 *
 * @author ramon
 */
public class Pizza {

    private int tamaño = 0;
    private int precio=0;
    private int extras;
    private int indicador ;
    private String[] pizza = {"Pepperoni", "Hawaiana","Mexicana","Cuatro quesos","Pollo","Vegetarian" ,"Especial"}; //6s

    public Pizza(int t, int p, int x){
        tamaño = t;
        indicador = p;
        extras =x;
    }


    public void asignaPrecio(){
        if(tamaño ==1){
            precio = 80;
        }else if ( tamaño == 2){
            precio = 120;
        }else if(tamaño == 3){
            precio = 160;
        }else{
            precio = 200;
        }
        precio += (10*extras);
    }

    public int getPrecio(){
        asignaPrecio();
        return precio;
    }

    public int tipoPizza(){
        return indicador;
    }

    public String toString(){
        String s ="";
        s += pizza[indicador]+" ...... $"+getPrecio();
        return s;
    }
}
