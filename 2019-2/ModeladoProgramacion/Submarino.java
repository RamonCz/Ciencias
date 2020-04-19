import java.util.Scanner;
import java.util.Random;
/**
*
* @author Ramon Cruz Perez y Larisa
* @version 1.0
*/
public class Submarino extends Juego{

  private Scanner sc = new Scanner(System.in);
  private int m;
  private TableroSubmarino t1,t2;

  /**
  *Classe que implementa tablero
  */
  public class TableroSubmarino implements Tablero{
    private String[][] maya;
    private int m;

    /**
    *Constructor
    */
    public TableroSubmarino(int m){
      this.m = m;
      maya = new String[m][m];
      for (int i = 0; i < m ; i++ ) {
        for (int j = 0; j < m ; j++ ) {
          maya[i][j] = ".";
        }
      }
    }
    /**
    *@return tablero bien establecido
    */
    public String toString(){
      String s = "";
      for (int i = 0;i <m ;i++ ) {
        s +="  "+i+"";
      }
      s +="\n";
      for (int i = 0; i < m ; i++ ) {
        s +=i;
        for (int j = 0; j < m ; j++ ) {
          s += "["+maya[j][i]+"]";
        }
        s +="\n";
      }
      return s;
    }

    /**
    *@return el tablero con los puntos pero sin ver donde esta el submarino
    */
    public String tablero(){
      String s = "";
      for (int i = 0;i <m ;i++ ) {
        s +="  "+i+"";
      }
      s +="\n";
      for (int i = 0; i < m ; i++ ) {
        s +=i;
        for (int j = 0; j < m ; j++ ) {
          if (maya[i][j].equals("S")) {
              s += "[.]";
          }else {
            s+="["+maya[i][j]+"]";
          }
        }
        s +="\n";
      }
      return s;
    }
  }

  /**
  *Constructor
  */
  public Submarino(){
    m = 7;//(int) (Math.random() * 15) + 10;
    t1 = new TableroSubmarino(m);
    t2 = new TableroSubmarino(m);
  }

  /**
  *Asigna un Submarino de en la maya del tablero con un contador del tamaño del Submarino
  */
  public void asignaSubmarino(){
    int n = 5;
    System.out.println("Tamaño de la maya "+m);
    do {
      muestraTablero(t1);
      System.out.println("Submarino de tamaño "+n);
      if (posicionSubmarino(n)) {
          n--;
      }
    } while (n != 0);
  }

  /**
  *Le pregunta al usuario la posicion de Submarino
  *@return si la coordenadas dadas estan correctas
  */
  public boolean posicionSubmarino(int n){
    boolean salida = true;
    do {
      try {
        int x, y ;
        System.out.println("Dame la coordenada x:");
        x = sc.nextInt();
        System.out.println("Dame la coordenada y:");
        y = sc.nextInt();
        if (verificaCasilla(t1,x,y)){
          sc = new Scanner(System.in);
          return preguntaPosicion(t1,n,x,y);
        }else {
          System.out.println("Coordenadas invalida intenta otra vez...");
        }
      }catch (Exception e) {
        System.out.println("### Solo dame numeros ###");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return false;
  }

  /**
  *Pregunta la posicion de como quiere el Submarino
  *@param tablero
  *@param tamaño del submarino
  *@param x coordenada
  *@param y coordanada
  *@return si se pude poner en la posicion el submarino
  */
  public boolean preguntaPosicion(TableroSubmarino t,int n,int x, int y){
    int b = 0;
    boolean salida = true;
    do {
      try {
        System.out.println("Como quieres el Submarino");
        System.out.println("1.- Vertical hacia abajo ");
        System.out.println("2.- Vertical hacia Arriba");
        System.out.println("3.- Horizontal Derecha");
        System.out.println("4.- Horizontal Izquierda");
        b = sc.nextInt();
        sc = new Scanner(System.in);
        if(ponSubmarino(verificaPosicion(t1,b,n,x,y),t1,n,x,y)){
          return true;
        }else {
          System.out.println("### El Submarino no se pude poner el lugar intenta otra vez..");
          return false;
        }
      }catch (Exception e) {
        System.out.println("### Solo dame numeros ###");
        sc = new Scanner(System.in);
      }
    } while (salida);
    return false;
  }

  /**
  *Llena las casillas del Submarino seleccionado
  *@param posicion del submarino
  *@param tablero
  *@param tamaño del submarino
  *@param x coordenada
  *@param y coordanada
  *@return si el Submarino si se pude poner
  */
  public boolean ponSubmarino(int d,TableroSubmarino t,int n,int x ,int y){
      switch (d) {
        case 0:
          return false;

        case 1:
          for (int i = 0; i < n ;i++ ) {
            t.maya[x][y] = "S";
            y++;
          }
          return true;

        case 2:
          for (int i = 0; i < n ;i++ ) {
            t.maya[x][y] = "S";
            y--;
          }
          return true;

        case 3:
          for (int i = 0; i < n ;i++ ) {
            t.maya[x][y] ="S";
            x++;
          }
          return true;

        case 4:
          for (int i = 0; i < n ;i++ ) {
            t.maya[x][y] = "S";
            x--;
          }
          return true;

      }
      return false;
  }

  /**
  *Verifica que las coordenas dadas este en el rango
  *@param tablero
  *@param tamaño del submarino
  *@param x coordenada
  /@param y coordanada
  *@return boolean
  */
  public boolean verificaCasilla(TableroSubmarino t,int x , int y){
    if (x > m-1 || y < 0 ||y > m-1|| x < 0  ) {
      return false;
    }else if ( t.maya[x][y].equals("S")) {
      return false;
    }else {
      return true;
    }
  }

  /**
  *Verifica si se puede poner el Submarino en alguna posicion
  *@param tablero
  *@param posicion
  *@param tamaño del submarino
  *@param x coordenada
  /@param y coordanada
  *@return posicion en que va el Submarino
  */
  public int verificaPosicion(TableroSubmarino t,int b, int n, int x,int y){
    try {
      switch (b) {
        case 1:
          if ((n+y)-1 < m) {
            for (int i = 0; i < n ;i++ ) {
              if (t.maya[x][y].equals("S")) {
                return 0;
              }
              y++;
            }
            return 1;
          }
          return 0;

      case 2:
        if ((n-y)+1 < m) {
          for (int i = 0; i < n ;i++ ) {
            if (t.maya[x][y].equals("S")) {
              return 0;
            }
            y--;
          }
          return 2;
        }
        return 0;

      case 3:
        if ((n+x)-1 < m) {
          for (int i = 0; i < n ;i++ ) {
            if (t.maya[x][y].equals("S")) {
              return 0;
            }
            x++;
          }
          return 3;
        }
        return 0;

      case 4:
        if ((n-x)+1 < m) {
          for (int i = 0; i < n ;i++ ) {
            if (t.maya[x][y].equals("S")) {
              return 0;
            }
            x--;
          }
          return 4;
        }
        return 0;

      }
    }catch (Exception e) {
      return 0;
    }
    return 0;
  }

  /**
  *Cambia la casilla por un "v" si no le da y por una "x" si le da
  *@param tablero
  *@param x coordenada
  /@param y coordanada
  */
  public void dispara(TableroSubmarino t, int x, int y){
    if (verificaCasilla(t,x,y)) {
      t.maya[x][y]="v";
    }else {
      t.maya[x][y] = "x";
    }
  }

  /**
  *Verifica si todos los Submarinos estan undidos o destruidos
  *@param tablero
  *@return true si ya no hay submarinos
  */
  public boolean verificaGanador(TableroSubmarino t){
    for (int i =0; i <m ;i++ ) {
      for (int j =0; j <m ;j++ ) {
          if(!verificaCasilla(t,i,j)){
            return false;
          }
      }
    }
    return true;
  }

  /**
  *Crea un tablero con los submarinos random
  */
  public void creaSubmarino(){
    int n =5;
    boolean salida = false;
    do {
      int x = (int) (Math.random() * m) + 1;
      int y = (int) (Math.random() * m) + 1;
      if (verificaCasilla(t2,x,y)) {
        do {
          int b = (int) (Math.random() * 4) + 1;
          salida = ponSubmarino(verificaPosicion(t2,b,n,x,y),t2,n,x,y);
        } while (!salida);
        n--;
      }
    }while (n != 0);
  }

  // este método no hace nada porque no hay tablero para este juego.
  @Override
  public void creaTablero() throws NoRequiereTableroException{
  }

  /**
  * Método que maneja el turno de la computadora.
  */
  @Override
  public void turnoComputadora(){
    boolean salida = true;
    do {
      int x = (int) (Math.random() * m-1) + 1;
      int y = (int) (Math.random() * m-1) + 1;
      if (t1.maya[x][y].equals("x")) {
        salida = true;
      }else {
        dispara(t1,x,y);
        salida = false;
      }
    } while (salida);
    if (verificaGanador(t1)) {
      System.out.println("¡¡¡A ganado la computadora!!!");
    }

  }

  /**
  * Método que maneja el turno del usuario.
  */
  @Override
  public void turnoUsuario(){
    boolean salida = true;
    do {
      try {
        int x, y ;
        System.out.println("Dame la coordenada x:");
        y = sc.nextInt();
        System.out.println("Dame la coordenada y:");
        x = sc.nextInt();
        if (x > m-1 || y < 0 ||y > m-1|| x < 0) {
          System.out.println("Valores incorrectos...");
        }else {
          dispara(t2,x,y);
          if (verificaGanador(t2)) {
            System.out.println("¡¡¡¡¡¡¡¡Tu eres el ganador!!!!!!!!");
            salida = false;
          }
        }
      }catch (Exception e) {
        System.out.println("### Solo dame numeros ###");
        sc = new Scanner(System.in);
      }
    } while (false);
  }

  /**
  * Método que maneja el turno del usuario invitado.
  */
  @Override
  public void turnoUsuarioInvitado(){
  }

  /**
  * Método que guarda la puntuación del usuario que está jugando.
  */
  @Override
  public void guardaPuntuacion(){
  }

  /**
  * Método que muestra las puntuaciones en algún momento del juego.
  */
  @Override
  public void muestraPuntuaciones(){
  }

  /*Aunque la clase abstracta supone un tablero, en este
  caso simplemente se manda un mensaje, se vale sobreescribir el
  método*/

  /**
  * Método que muestra el tablero en pantalla.
  * Al utilizar JavaFX, puede sustituirse la impresión en pantalla por
  * mostrar una ventana de la interfaz.
  *@param tablero
  */
  public void muestraTablero(TableroSubmarino t){
    System.out.println(t.toString());
  }
  public void muestraTablero2(TableroSubmarino t){
    System.out.println(t.tablero());
  }

  /**
  *Flujo del juego
  */
  @Override
  public void jugar() throws NoRequiereTableroException {
      asignaSubmarino(); // crea todos los submarinos del usuario
      creaSubmarino(); // crea todos los submarinos de la computadora
      System.out.println("¡¡ Partida empezada !!");
      while (!juegoTerminado()) {
          System.out.println("Seguimos jugando");
          turnoUsuario();
          turnoComputadora();
          System.out.println("Mi Tablero");
          muestraTablero(t1);
          System.out.println("Tablero Enemigo");
          muestraTablero2(t2);
      }
      System.out.println("Fin del juego");
  }

   /**
    * Método invocado para saber si el juego ha terminado.
    * @return
    */
  @Override
   public boolean juegoTerminado() {
      return verificaGanador(t1)||verificaGanador(t2);
   }



}
