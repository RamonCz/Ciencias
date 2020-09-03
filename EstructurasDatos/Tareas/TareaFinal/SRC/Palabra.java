import java.lang.Object;
import java.text.Collator;
/**
 * Esta clase nos ayuda comparar mejor los string palabras
 * @author Ramon Cruz Perez
 */
public class Palabra implements Comparable<Palabra> {

  String cadena;

  public Palabra(){
  }
  
  /**
   * Constructor que transforma un String en palabra
   * @param cadena 
   */
  public Palabra(String cadena){
    this.cadena = cadena;
  }
  
  /**
   * devuelve el string
   * @return cadena
   */
  public String getCadena(){
    return cadena;
  }
  
  /**
   * Cambia la variable cadena
   * @param c 
   */
  public void setCadena(Palabra c){
    cadena = c.getCadena();
  }

  /**
   * Dice si la palabra que resive puede ser un buena sugerencia
   * @param c
   * @return boolean
   */
  public boolean sugerencia(Palabra c){
    String s = c.getCadena();
    int d = s.length() - cadena.length();
    Collator comparador = Collator.getInstance();
    comparador.setStrength(Collator.PRIMARY);
    if ( d==0) {
        char x;
        char y;
        int fallos = 0;
        for (int i = 0; i< cadena.length() ; i++ ) {
           x = cadena.charAt(i);
           y = s.charAt(i);
           if (comparador.compare(Character.toString(x),Character.toString(y)) !=0) {
             fallos++;
           }
        }
        return (fallos < 3)? true: false;
    }
    return false;
  }

  /**
   * Se asegura que la cadena que resive se una palabra bien hecha.
   * @return boolean
   */
  public boolean estaBien(){
    String palabra = cadena;
    int voc = 0;
    int cons = 0;
    if (palabra.length() < 24 || (!palabra.equals(""))) {
      char x = palabra.charAt(0);
      for (int i=0 ; i < palabra.length(); i++ ) {
        x = palabra.charAt(i);
        if(i == 0  && Character.isDigit(x)) {
          return false;
        }
        if(x =='á'||x =='é'|| x =='í'|| x=='ó'|| x=='ú'||x =='a'||x =='e'|| x =='i'|| x=='o'|| x=='u'|| x=='Á'|| x=='É'|| x=='Í'|| x=='Ó'|| x=='Ú'|| x =='A'|| x=='E'|| x=='I'|| x=='O'|| x=='U'){
          voc++;
          cons=0;
        }else {
          voc = 0;
          cons++;
        }
        if (voc > 2 || cons > 2) {
          System.out.println("1");
          return false;
        }
        if (i != 0 && (Character.isDigit(x) || Character.isUpperCase(x) || Character.isWhitespace(x))) {
          return false;
        }
      }//fin for
      return true;
    }
    return false;
  }//fin metodo estaBien
  
  /**
   * Compara dos palabras
   * @param c 
   * @return int 
   */
  public int compareTo(Palabra c){

    //  return cadena.compareTo(c.cadena);
    Collator comparador = Collator.getInstance();
    comparador.setStrength(Collator.PRIMARY);
    return comparador.compare(this.cadena, c.getCadena());
  }
  
  /**
   * equal entre dos palabras para que sirviera mi busqueda en avl
   * @param c
   * @return boolean
   */
  @Override
  public boolean equals(Object c){
    if (compareTo((Palabra)c) == 0) {
      return true;
    }
    return false;
    //return this.cadena.equals(((Palabra)c).cadena);
  }

  /**
   * Da la cadena
   * @return cadena
   */
  public String toString(){
    return cadena;
  }
}
