
import java.io.*;

public class Diccionario{

  private File dic;
  public  ArbolBinarioBusqueda<Palabra> arbol;
  public Diccionario(){
    dic = new File("diccionario.txt");
    arbol = new ArbolAVL();
    obtenerDicccionario();
  }

  public void agrega(Palabra palabra){
    arbol.agrega(palabra);
  }
  public boolean contiene(Palabra palabra){
    return arbol.contiene(palabra);
  }

  public Palabra busca(Palabra palabra){
    return (arbol.busca(palabra)).get();
  }

  public Lista<Palabra> opcionesSuguerencias(Palabra c){
    Lista<Palabra> l2 = new Lista<Palabra>();
    if (!arbol.contiene(c)) {
      Lista<Palabra> l = arbol.recorrido(c);
      for (int i = 0 ; i < l.longitud() ; i++ ) {
        if(l.getElemento(i).sugerencia(c)){
          l2.agregar(l.getElemento(i));
          System.out.println(i);
        }
      }
      return l2;
    }
    return l2;

  }

  private void obtenerDicccionario(){
    try {
      String cadena;
      FileReader fr = new FileReader(dic);
      BufferedReader br = new BufferedReader(fr);
      Palabra c;
    //  int cont =0;
      while((cadena = br.readLine())!=null) {
          c = new Palabra(cadena);
          arbol.agrega(c);
          /*cont++;
          if (cont == 10) {
            return ;
          }*/
      }
      br.close();
    }catch (Exception e) {
      System.out.println(e);
    }
  }

  public void guardarDiccionario(){
    try{
      System.out.println("guardando los datos...");
      Lista<Palabra> lista = arbol.inOrden();
      File nuevo = new File("diccionario.txt");
      FileWriter fw = new FileWriter(nuevo);
      BufferedWriter bw;
      if (nuevo.exists()) {
        bw = new BufferedWriter(fw);
        for(int i =0 ; i < lista.longitud(); i++){
          bw.write(lista.getElemento(i).getCadena()+"\n");
        }
        bw.close();
      }else {
        System.out.println("Se crea un nuevo fichero");
        bw = new BufferedWriter(fw);
        for(int i =0 ; i < lista.longitud(); i++){
          bw.write(lista.getElemento(i).getCadena());
        }
        bw.close();
      }
    }catch (Exception e) {
      System.out.println(e);
    }
  }

  public void imprimeArbol(){
    System.out.println(arbol.toString());
  }

/**
    public static void main(String[] args) {
      Diccionario x  = new Diccionario();
      System.out.println("222222222222222222");
      x.imprimeArbol();
      System.out.println(x.contiene(new Palabra("abaco")));
      System.out.println((( x.arbol.busca(new Palabra("abaco")) ).get()).toString());
      //x.guardarDiccionario();
  }
*/
}
