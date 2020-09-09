/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.Serializable;
import java.util.ArrayList;
public class Simon implements Serializable{
    
    private static ArrayList<Integer> lista = new ArrayList();
    private static int nivel;
    private static int dificultad=0;   
    
    public Simon(){
        nivel=1;
        
    }
    
    /**
     * Hace la lista con los botones random
     */
    public void subirDificultad(){
        for(int i=0; i < nivel;i++){
            lista.add((int) (Math.random() * 5) + 1);
        }
    }
    
    /**
     * Da la lista con los botones random
     * @return secuencia
     */
    public ArrayList<Integer> secuencia(){
        this.subirDificultad();
        return lista;
    }
    
    /**
     * incrementa el numero de la secuencia
     */
    public static void subirNivel(){
        nivel++;
    }
    
    /**
    *Vacia la lista y empieza la secuencia de nuevo
    */
     public static void bajarNivel(){
        lista.clear();
        nivel=1;
    }
     
    /**
     * Da la dificultad
     * @return s 
     */
    public static int getDificultad(){
        return dificultad;
    }
    /**
     * Cambia la dificultad
     * @param n 
     */
    public static void setDificultad(int n){
        dificultad=n;
    }
    
    

}
