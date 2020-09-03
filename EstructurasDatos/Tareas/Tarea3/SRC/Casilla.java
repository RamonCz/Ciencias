/**
* @author Cruz Perez Ramon
* @version 2.0
*/
public class Casilla{
  public boolean abajo, izquierda, derecha;
  private String relleno, inicio, fin ;
  private int visita, n,m;
  private Casilla anterior;


  public Casilla(){
    abajo=  izquierda= derecha = true;
    visita = 0;
    n = -1;
    m =-1;
    relleno = "";
    inicio = "";
    fin = "";
  }
  public void visita(){
    this.visita = 1;
  }
  public void setN(int n){
    this.n = n;
  }
  public void setM(int m){
    this.m = m;
  }
  public int getVisita(){
    return visita;
  }
  public void ponInicio(){
    inicio ="I";
  }
  public void ponFin(){
    fin ="F";
  }
  public void ponRelleno(){
    relleno =  "x";
  }
  public int getN(){
    return n;
  }
  public int getM(){
    return m;
  }
  public String getFin(){
    return fin;
  }
  public boolean getDerecha(){
    return derecha;
  }
  public boolean getAbajo(){
    return abajo;
  }
  public boolean getIzquierda(){
    return izquierda;
  }
  public void eliminaRelleno(){
    relleno = "";
  }
  public void eliminaVisita(){
    visita =0;
  }
  public void eliminaAbajo(){
    abajo = false;
  }
  public void eliminaIzquierda(){
    izquierda = false;
  }
  public void eliminaDerecha(){
    derecha = false;
  }

  
  public String toString(){
    String s="";
    if (izquierda == true){
      s+="|";
    }else {
      s = " ";
    }
    if(abajo == true) {
      s+="_"+relleno+inicio+fin;
    }else {
      s += " "+relleno+inicio+fin;
    }
    if (derecha == true) {
      s+="|";
    }else {
      s += "";
    }
    return s;
  }

}
