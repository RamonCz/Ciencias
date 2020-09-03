
import java.io.*;
/**
 *Esta clase nos ayuda a obtener los datos del diccionario
 * @author Ramon Cruz Perez
 */
public class Diccionario{

  private File dic;
  public  ArbolBinarioBusqueda<Palabra> arbol;

  public Diccionario(){
    dic = new File("diccionario.txt");
    arbol = new ArbolAVL();
    obtenerDicccionario();
  }

  /**
   * Agrega la palabra al arbol
   * @param palabra
   */
  public void agrega(Palabra palabra){
    arbol.agrega(palabra);
  }

  /**
   * Pregunta si esta la palabra en el arbol
   * @param palabra
   * @return true || false
   */
  public boolean contiene(Palabra palabra){
    return arbol.contiene(palabra);
  }

  /**
   * busca la palabra en el arbol
   * @param palabra
   * @return palabra encontrada o null
   */
  public Palabra busca(Palabra palabra){
    return (arbol.busca(palabra)).get();
  }

  /**
   *Nos da las sugerencias encontradas en el arbol con ayuda de la clase palabra
   * @param c
   * @return h heap con las suguerencias
   */
  public MaxHeap<Palabra> opcionesSuguerencias(Palabra c){
    Lista<Palabra> l2 = arbol.recorrido(c) ;
    MaxHeap<Palabra> l = new MaxHeap(new Lista());
    if (!arbol.contiene(c)) {
      for (int i = 0 ; i < l2.longitud() ; i++ ) {
        if(l2.getElemento(i).sugerencia(c)){
          l.agrega(l2.getElemento(i));
        }
      }
      return l;
    }
    return l;
  }

  /**
   * obtiene el diccionario.txt y lo guarda en el arbol
   */
  private void obtenerDicccionario(){
    try {
      String cadena;
      FileReader fr = new FileReader(dic);
      BufferedReader br = new BufferedReader(fr);
      Palabra c;
      while((cadena = br.readLine())!=null) {
          c = new Palabra(cadena);
          arbol.agrega(c);
      }
      br.close();
    }catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * guarda el diccionario en el txt
   */
  public void guardarDiccionario(){
    try{
      Lista<Palabra> lista = arbol.inOrden();
      File nuevo = new File("diccionario.txt");
      FileWriter fw = new FileWriter(nuevo);
      BufferedWriter bw;
      bw = new BufferedWriter(fw);
      for(int i =0 ; i < lista.longitud(); i++){
        bw.write(lista.getElemento(i).getCadena()+"\n");
      }
      bw.close();
    }catch (Exception e) {
      System.out.println(e);
    }
  }

}
