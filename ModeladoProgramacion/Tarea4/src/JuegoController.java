/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ramon
 */
public class JuegoController implements Initializable {
    
    public static int[] jugadores = new int[10];
    
    @FXML
    private Button amarrillo;
    @FXML
    private Button verde;
    @FXML
    private Button azul;
    @FXML
    private Button naranja;
    @FXML
    private Button rojo;
    @FXML
    private Label puntos;
    @FXML
    private Button empezar;
    
    private int maxPuntos=0;
    private int d=0;
    private ArrayList<Integer> lista = new ArrayList()   ;
    @FXML
    private Ellipse vi;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize( URL url, ResourceBundle rb) {
       
         
    }     
    @FXML
    private void accionAmarillo(ActionEvent event) {
        daClick(5);
    }

    @FXML
    private void accionVerde(ActionEvent event) {
         daClick(1);
    }

    @FXML
    private void accionAzul(ActionEvent event) {
         daClick(2);
    }

    @FXML
    private void accionNaranaja(ActionEvent event) {
         daClick(3);
    }

    @FXML
    private void accionRojo(ActionEvent event) {
         daClick(4);
    }
    
    @FXML
    private void accionEmpezar(ActionEvent event) {
        d = MainApp.simon.getDificultad();
        lista = MainApp.simon.secuencia();
        daSecuencia(0); 
    }
    
    /**
     *Pide una secuencia a la clase simon y espera cierto timpo para que este prendido
     * @param i 
     */
    public void daSecuencia(int i){
        if(i<lista.size()){
            final int n = i;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    cambio(lista.get(n),n);
              }
            }, d*500);
        }else{
           String style = vi.getStyle();
           vi.setStyle("visibility : false;"); 
           new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                String style = vi.getStyle();
                vi.setStyle("visibility : true;"); 
              }
            },500);
            
        }
    }
    
    /**
     * Cambia el color de los botones para que brillen 
     * @param x el borton seleccionado
     * @param n numero de iteracion
     */
    public void cambio(int x, int n){
          String style="";
        switch (x){ 
            case 1:
                style = verde.getStyle();
                verde.setStyle("-fx-background-color: #ffffff;");
                dificultad(verde,"green",n);
           break;
           case 2:
                style = azul.getStyle();
                azul.setStyle("-fx-background-color:  #ffffff;");
                dificultad(azul,"blue",n);
           break;
           case 3:
                style = naranja.getStyle();
                naranja.setStyle("-fx-background-color: #ffffff;");
                dificultad(naranja,"orange",n);
           break;
           case 4:
                style = rojo.getStyle();
                rojo.setStyle("-fx-background-color: #ffffff;");
                dificultad(rojo,"red",n);
           break;
           case 5:
                style = amarrillo.getStyle();
                amarrillo.setStyle("-fx-background-color: #ffffff;");
                dificultad(amarrillo,"yellow",n);
           break;
           
        }
        
        
    }
    
    /**
     * Toma el timpo para que la luz este prendida
     * @param x botton
     * @param s tipo de color 
     * @param n numero de iteracion
     */
    public void dificultad(final Button x,String s,final int n){
        final String cadena = "-fx-background-color:  "+s+";";
        new Timer().schedule(new TimerTask() {
        @Override
        public void run() {
            x.setStyle(cadena);
            daSecuencia(n+1);
        }
      }, d*500);
    }
    
    /**
     * nos Muestra el top 10
     */
    public void verTop(){
        String s = "";
        int tmp = 0;
        int tmp2= 0;
        boolean si= false;
        for(int i=jugadores.length-1; i >= 0 ;i--){
            if (maxPuntos > jugadores[i] && !si) {
                s +="["+(10-i)+"]"+maxPuntos+"<---- Tu\n";
                jugadores[i-1] = maxPuntos;
                Arrays.sort(jugadores);
                si = true;
            }else{
                s +="["+(10-i)+"].-"+jugadores[i]+"\n";
            }              
        }
        Alert dialogo = new Alert(AlertType.INFORMATION); 
        if (si) {
            dialogo.setTitle("Llegaste al top 10");
            dialogo.setContentText(s);
        }else{
            dialogo.setTitle("Intenta de nuevo.");
            dialogo.setContentText("No llegaste el top 10");
        }    
        dialogo.setHeaderText(null);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
    
    /**
     * Nos dice si fallamos o le atinamos al boton correcto
     * @param n boton 
     */
    public void daClick(int n){
         if (lista.get(0)!=n) {
           verTop(); 
           Alert dialogo = new Alert(AlertType.CONFIRMATION); 
           dialogo.setTitle("¿Intentar de nuevo?");
           dialogo.setHeaderText(null);
           dialogo.initStyle(StageStyle.UTILITY);
           dialogo.setContentText("¿Quieres volver a intentarlo?");
           Optional<ButtonType> result = dialogo.showAndWait();
            if (result.get() != ButtonType.OK ) {
                MainApp.simon.bajarNivel();
                guardar();
                System.exit(0);
            }
            MainApp.simon.bajarNivel();
            puntos.setText("Puntos: ");
            maxPuntos=0;
            empezar.fire();
        }else if(lista.size()== 1){
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Siguiente Nivel");
            dialogo.setContentText("Bien hecho vamos al siguiente nivel");
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
            maxPuntos +=16;
            MainApp.simon.subirNivel();
            empezar.fire();
        }else{
            maxPuntos +=16;
            puntos.setText("Puntos: "+maxPuntos);
            lista.remove(0);
        }
    }
   
    /**
     * Guarda el puntaje
     */
    public static void guardar(){
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        try {
            fos = new FileOutputStream("juego.dat");
            salida = new ObjectOutputStream(fos);
            salida.writeObject(jugadores);

        } catch (FileNotFoundException e) {
            System.out.println("1"+e.getMessage());
        } catch (IOException e) {
            System.out.println("2"+e.getMessage());
        } finally {
            try {
                if(fos!=null) fos.close();
                if(salida!=null) salida.close();
            } catch (IOException e) {
                System.out.println("3"+e.getMessage());
            }
        }
    }
    
    /**
    *Lee el puntaje 
    */
    public static void obtener(){
        
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        boolean ex = true;
        try {
            fis = new FileInputStream("juego.dat");
            entrada = new ObjectInputStream(fis);
            jugadores = (int[]) entrada.readObject(); 
            ex = false;
          }catch (ClassNotFoundException e) {
            System.out.println(e);
          }catch(FileNotFoundException e) {
            System.out.println("1"+e.getMessage());
          }catch (IOException e) {
            System.out.println("2"+e.getMessage());
          }finally {
              try {
                  if(fis!=null) fis.close();
                  if(entrada!=null) entrada.close();
              } catch (IOException e) {
                  System.out.println("3"+e.getMessage());
              }
          }
    }
}
