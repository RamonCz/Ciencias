import java.util.ArrayList;

public class Huffman{
  ArrayList<Nodo> lista = new ArrayList<Nodo>();
  private class Nodo{
    String caracter;
    int peso;
    String valor;
    Nodo padre;
    Nodo izquierdo;
    Nodo derecho;
    Boolean hoja= false;
    public Nodo (){
      caracter = "";
      peso = 0;
      valor = "-1";
      izquierdo = null;
      derecho = null;
    }
    public Nodo(String s, int p){
      caracter = s;
      peso = p;
      valor = "-1";
      izquierdo = null;
      derecho = null;
    }
    public void tostring(){
      String s = caracter+","+peso;
    }
    public String caracter(){
      return caracter;
    }
    public int peso(){
      return peso;
    }
    public int compareTo(Nodo n){
      if (peso < n.peso) {
        return -1;
      }else {
        return (peso == n.peso)? 0:1;
      }
    }

  }


  /**
  **con la cadena que recibe crea el arbol
  */
  public Huffman(String s){
    creaLista(s);
  }

  /**
  **Cuenta el numero de repeticiones de una char
  */
  public int cuenta(char c, String s){
    int cont = 0;
    for (int i =0; i < s.length() ; i++ ) {
        if (c == s.charAt(i)) {
          cont++;
        }
    }
    return cont;
  }

  /**
  *elimina las letras de una String
  */
  public String eliminaString(int cont,char c, String s){
    int j=0;

    while(j != s.length()){//pude que no borre uno
      if (c == s.charAt(j)) {
        s = s.substring(0,j)+ s.substring(j+1);
      }else{
      j++;
      }
    }
    return s;
  }

  /**
  *crea una lista ordena con un cadena (creo las hojas en una listas)
  */
  public void creaLista(String s){
    Nodo n ;
    int i = 0;
      while(!s.equals("")){
        int numero = cuenta(s.charAt(i),s);
        n = new Nodo( Character.toString(s.charAt(i)),numero);
        n.hoja = true;
      //  s= s.replace("a", "");
        s = eliminaString(numero,s.charAt(i),s);
        lista.add(n);
      }
      lista.sort((o1, o2) -> o1.compareTo(o2));
  }

/**
**imprime la lista, sirve para hacer puebas
*/
  public void imprime(){
    for (int i=0; i < lista.size(); i++) {
      System.out.println((lista.get(i)).caracter()+", "+(lista.get(i)).peso());
    }
  }

/**
**Crea el arbol apartir de la lista ordena de las hojas
*/
  public Nodo arbol(){
    Nodo p = new Nodo();
    while(lista.size() != 1){
      Nodo n = (lista.size() >= 1)? lista.get(0):null;
      lista.remove(0);
      Nodo n2 = (lista.size() >= 1)? lista.get(0):null;
      lista.remove(0);
      p = new Nodo();
      String s =  n.caracter + n2.caracter;
      p.caracter = s;
      p.peso = n.peso + n2.peso;
      p.izquierdo = n;
      p.derecho = n2;
      n.valor ="0";
      n.padre = p;
      n2.valor = "1";
      n2.padre = p;
      lista.add(p);
    }
    return lista.get(0);
  }

/**
** recibe la cadena normal  y el arbol hecho
**/
  public String codifica(String s,Nodo n){
    String r  = "";
    Nodo m = n;
    while(s.length() != 0 ){
      if (cuenta(s.charAt(0),(m.izquierdo).caracter) > 0) {
          r += m.izquierdo.valor;
         if(m.izquierdo.hoja){
           s = s.substring(1);
           m = n;
         }else {
           m = m.izquierdo;
         }

      }else if (cuenta(s.charAt(0),(m.derecho).caracter) > 0) {
        r += m.derecho.valor;
        if(m.derecho.hoja){
          s = s.substring(1);
          m = n;
        }else {
          m = m.derecho;
        }

      }
    }
    return r;
  }


  /**
  *decodifica un String de 10101010
  *@return s
  */
  public String decodifica(String s, Nodo n){
    String r  = "";
    Nodo m = n;
    while(s.length() != 0 ){
      if (Character.toString(s.charAt(0)).equals(m.izquierdo.valor)) {
          s = s.substring(1);
         if(m.izquierdo.hoja){
           r += m.izquierdo.caracter;
           m = n;
         }else {
           m = m.izquierdo;
         }
      }else if (Character.toString(s.charAt(0)).equals(m.derecho.valor)) {
        s = s.substring(1);
        if(m.derecho.hoja){
          r += m.derecho.caracter;
          m = n;
        }else {
          m = m.derecho;
        }
      }
    }
    return r;

  }

  public static void main(String[] args) {
    String s = " hola como estass";
    Huffman a = new Huffman(s); // se crea arbol
    String x = a.codifica(s,a.arbol()); //X = 1010101010
    String y = a.decodifica(x,a.arbol());// Y = " hola como estass"
    System.out.println(x);
    System.out.println(y);
  }




}
