/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.util.Random;
public class Laberinto{

  private Casilla[][] laberinto;
  private Casilla inicio;
  private Casilla fin;

  /**
  *Constructor de clase
  */
  public Laberinto(){
    laberinto = new Casilla[1][1];
  }

  /**
  *nos devuelve un numero aleatorio conforme a la lista es para abrirse camino si hay pared
  * @return x entero
  */
  private int moverse(ListaCircular l){
    int i =0;
    int ñ = (int) (Math.random() * 10) + 1;
    java.util.Iterator it = l.elementos();
    while(it.hasNext()){
      if (i == ñ) {
        return (int)it.next();
      }
      it.next();
      i++;
    }
    return -1;
  }

  /**
  *imprime el laberinto
  */
  public void imprimeLaberinto(){
    for (int i = 0; i < laberinto .length ; i++ ) {
      for (int j= 0;j < laberinto[1].length ;j++ ) {
        System.out.print(laberinto[i][j]);
      }
      System.out.println();
    }
  } // fin imprime

  /**
  *Crea un techo y quita las paredes izquierda de todas las casillas, simplemente para que se vea bien en la terminal
  */
  private void creaTecho(){
    for (int i = 0; i < laberinto.length ; i++ ) {
      for (int j= 0;j < laberinto[1].length ;j++ ) {
        laberinto[i][j] = new Casilla();
        laberinto[i][j].setN(i);
        laberinto[i][j].setM(j);
      }
    }
    for (int i = 0; i < laberinto[1].length ;i++ ) {
      laberinto[0][i].eliminaIzquierda();
      laberinto[0][i].eliminaDerecha();
    }
    for (int i = 1 ; i < laberinto.length ; i++ ) {
      for (int j = 1; j < laberinto[1].length  ;j++ ) {
        laberinto[i][j].eliminaIzquierda();
      }
    }
  }

  /**
  *nos un numero de la pared que vamos a quitar
  *@param n coordena en donde esta la casilla
  *@param m coordena en donde esta la casilla
  *@return x entero que es el nuemero de la pared
  */
  private int aleatorio(int n, int m){
    ListaCircular l = new ListaCircular();
    java.util.Iterator it = l.elementos();
    if (n == 1 && m == 0) {
      if (laberinto[n][m+1].getVisita() == 0)
        l.insertar(1);
      if (laberinto[n+1][m].getVisita() == 0) {
        l.insertar(2);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if (n == 1 && m == laberinto[1].length-1 ) {
      if (laberinto[n+1][m].getVisita() == 0) {
        l.insertar(2);
      }
      if (laberinto[n][m-1].getVisita() == 0) {
        l.insertar(3);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if (n == laberinto.length-1 && m == 0 ) {
      if (laberinto[n-1][m].getVisita() == 0) {
        l.insertar(4);
      }
      if (laberinto[n][m+1].getVisita() == 0) {
        l.insertar(1);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if (n == laberinto.length-1 && m == laberinto[1].length-1) {
      if (laberinto[n][m-1].getVisita() == 0) {
        l.insertar(3);
      }
      if (laberinto[n-1][m].getVisita() == 0) {
        l.insertar(4) ;
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if (n == 1 && m != 0 && m != laberinto[1].length-1) {
      if (laberinto[n][m+1].getVisita() == 0) {
        l.insertar(1);
      }
      if (laberinto[n+1][m].getVisita() == 0) {
        l.insertar(2);
      }
      if (laberinto[n][m-1].getVisita() == 0) {
        l.insertar(3);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if (n == laberinto.length-1 && m != 0 && m != laberinto[1].length-1) {
      if (laberinto[n][m+1].getVisita() == 0) {
        l.insertar(1);
      }
      if (laberinto[n][m-1].getVisita() == 0) {
        l.insertar(3);
      }
      if (laberinto[n-1][m].getVisita() == 0) {
        l.insertar(4);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if (m == 0 && n != 1 && n != laberinto.length-1 ) {
      if (laberinto[n-1][m].getVisita() == 0) {
        l.insertar(4);
      }
      if ( laberinto[n][m+1].getVisita() == 0) {
        l.insertar(1);
      }
      if (laberinto[n+1][m].getVisita() == 0) {
        l.insertar(2);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else if ( m == laberinto[1].length-1 && n != 1 && n != laberinto.length-1) {
      if (laberinto[n+1][m].getVisita() == 0) {
        l.insertar(2);
      }
      if (laberinto[n][m-1].getVisita() == 0) {
        l.insertar(3);
      }
      if (laberinto[n-1][m].getVisita() == 0) {
        l.insertar(4);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }else{
      if (laberinto[n][m+1].getVisita() == 0) {
        l.insertar(1);
      }
      if (laberinto[n+1][m].getVisita() == 0) {
        l.insertar(2);
      }
      if (laberinto[n][m-1].getVisita() == 0) {
        l.insertar(3);
      }
      if (laberinto[n-1][m].getVisita() == 0) {
        l.insertar(4);
      }
      if (!l.estaVacia()) {
        return moverse(l);
      }else{
        return 0;
      }
    }
    //return a;
  }

  /**
  *Crea el laberinto
  *@param n largo del la Laberinto
  *@param m ancho del la Laberinto
  */
  public void creacion(int n, int m){
    this.laberinto = new Casilla[n+1][m];
    this.creaTecho();
    Pila p = new Pila();
    int alM = (int) (Math.random() * m-1) + 1;
    int alN = (int) (Math.random() * n-1) + 1;
    Casilla x = laberinto[alN][alM];
    x.visita();
    x.ponInicio();
    this.inicio = x;
    p.agrega(laberinto[alN][alM]);

    while(!p.esVacio()){
      int a = this.aleatorio(alN,alM);
      if (a == 1) {
        x.eliminaDerecha();
        x = laberinto[alN][alM+1];
        alM++;
        x.visita();
        p.agrega(x);
      }else if (a == 2) {
        x.eliminaAbajo();
        x = laberinto[alN+1][alM];
        alN++;
        x.visita();
        p.agrega(x);
      }else if(a == 3) {
        x = laberinto[alN][alM-1];
        alM--;
        x.visita();
        x.eliminaDerecha();
        p.agrega(x);
      }else if (a == 4) {
        x = laberinto[alN-1][alM];
        alN--;
        x.visita();
        x.eliminaAbajo();
        p.agrega(x);
      }else if (a == 0) {
        x = (Casilla) p.pop();
        alN = x.getN();
        alM = x.getM();
      }

    }//while
    alM = (int) (Math.random() * m-1) + 1;
    alN = (int) (Math.random() * n-1) + 1;
    x = laberinto[alN][alM];
    x.ponFin();
    this.fin= x;
  }//fin creacion

  /**
  *verifica si hay un hay un vecino para moverse
  *@param n coordena en donde esta la casilla
  *@param m coordena en donde esta la casilla
  *@return x entero donde moverse
  */
  private int hayVecino(int n, int m){
    Casilla x = (Casilla) laberinto[n][m];
    if(!x.getDerecha() && !(laberinto[n][m+1].getIzquierda()) && laberinto[n][m+1].getVisita() == 0){
      return 1;
    }else if (!x.getAbajo() && laberinto[n+1][m].getVisita() == 0) {
      return 2;
    }else if (!x.getIzquierda() && !(laberinto[n][m-1].getDerecha()) && laberinto[n][m-1].getVisita() == 0) {
      return 3;
    }else if (!(laberinto[n-1][m]).getAbajo() && laberinto[n-1][m].getVisita() == 0) {
      return 4;
    }
    return 0;
  }

  /**
  * resuelve el laberinto
  */
  public void resuelve(){
    Pila c = new Pila();
    int n = inicio.getN();
    int m = inicio.getM();

    for (int i = 0; i < laberinto.length ; i++ ) {
      for (int j =0 ; j < laberinto[1].length ; j++ ) {
          laberinto[i][j].eliminaVisita();
      }
    }
    inicio.visita();
    c.push(inicio);
    Casilla x = inicio;
    while(!c.esVacio()){
      int a = hayVecino(n,m);
      if (a == 1) {
        x = laberinto[n][m+1];
        m++;
        if ( (n == (this.fin).getN() && m == (this.fin).getM())) {
          while(!c.esVacio()){
            x = (Casilla)c.pop();
            x.ponRelleno();
          }
        }else{
          x.visita();
          c.push(x);
        }
      }else if ( a == 2) {
        x = laberinto[n+1][m];
        n++;
        if ((n == (this.fin).getN() && m == (this.fin).getM())) {
          while(!c.esVacio()){
            x = (Casilla)c.pop();
            x.ponRelleno();
          }
        }else{
          x.visita();
          c.push(x);
        }
      }else if (a == 3) {
        x = laberinto[n][m-1];
          m--;
        if (n == (this.fin).getN() && m == (this.fin).getM()) {
          while(!c.esVacio()){
            x = (Casilla)c.pop();
            x.ponRelleno();
          }
        }else{
          x.visita();
          c.push(x);
        }
      }else if (a == 4) {
        x = laberinto[n-1][m];
        n--;
        if ((n == (this.fin).getN() && m == (this.fin).getM())) {
          while(!c.esVacio()){
            x = (Casilla)c.pop();
            x.ponRelleno();
          }
        }else{
          x.visita();
          c.push(x);
        }
      }else if (a == 0) {
          x = (Casilla)c.pop();
          n = x.getN();
          m = x.getM();
      }
    }// fin while

  }// fin metodo


}
