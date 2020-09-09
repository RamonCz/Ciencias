import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable {

    private Label label;
    @FXML
    private Button refresco;
    @FXML
    private Button cerveza;
    @FXML
    private Button fresas;
    @FXML
    private Button flan;
    @FXML
    private Button jugo;
    @FXML
    private Label lista;
    private int tipo = -1;
    private static Caja  c = new Caja();
    @FXML
    private Menu menuPeperoni;
    @FXML
    private Button saliir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtener();
        c.nuevoTicket();
    }

    @FXML
    private void onActionRefresco(ActionEvent event) {
        c.setRefrescos();
        lista.setText(c.getTicket());
    }


    @FXML
    private void onActionCerveza(ActionEvent event) {
        c.setCerveza();
        lista.setText(c.getTicket());
    }

    @FXML
    private void onActionFresas(ActionEvent event) {
        c.setFresas();
        lista.setText(c.getTicket());
    }

    @FXML
    private void onActionFlan(ActionEvent event) {
        c.setFlan();
        lista.setText(c.getTicket());
    }

    @FXML
    private void onActionJugo(ActionEvent event) {
        c.setJugos();
        lista.setText(c.getTicket());
    }




    @FXML
    private void onActionChica(ActionEvent event) {
        c.añadirPizza(tipo,1);
        tipo = -1;
        lista.setText(c.getTicket());
    }

    @FXML
    private void onActionMediana(ActionEvent event) {
         c.añadirPizza(tipo,2);
         tipo = -1;
         lista.setText(c.getTicket());
    }

    @FXML
    private void onActionGrande(ActionEvent event) {
         c.añadirPizza(tipo,3);
         tipo = -1;
         lista.setText(c.getTicket());
    }

    @FXML
    private void onActionFamiliar(ActionEvent event) {
         c.añadirPizza(tipo,4);
         tipo = -1;
         lista.setText(c.getTicket());
    }

    @FXML
    private void onMenuPeperoni(Event event) {
        tipo =0;
    }

    @FXML
    private void onMenuHawaiana(Event event) {
        tipo =1;
    }

    @FXML
    private void onMenuMexicana(Event event) {
        tipo =2;
    }

    @FXML
    private void onMenuCuatro(Event event) {
        tipo =3;
    }

    @FXML
    private void onMenuPollo(Event event) {
        tipo =4;
    }

    @FXML
    private void onMenuVegeriana(Event event) {
        tipo =5;
    }

    @FXML
    private void onMenuEspecial(Event event) {
        tipo =6;
    }

    public boolean pagar(){
      TextInputDialog dialog = new TextInputDialog("monto a pagar");
      dialog.setTitle("Pagar");
      dialog.setHeaderText("Total a pagar: $"+Integer.toString(c.total()));
      dialog.setContentText("Ingrese el monto a que desea pagar:");
      Optional<String> result = dialog.showAndWait();
      int n=0;
      if (result.isPresent()){
        try {
          n = (Integer.parseInt(result.get()) - c.total());
        } catch(Exception e) {
          return false;

        }
      }
      if (n < 0) {
        return false;
      }else {
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Cambio");
        dialogo.setContentText("$ "+(Integer.parseInt(result.get())-c.total()));
        dialogo.setHeaderText(null);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
      }
      return true;
    }
    @FXML
    private void onActionPagar(ActionEvent event) {
      boolean salida = true;
      do {
        salida = pagar();
      } while (!salida);



      c.nuevoTicket();
      lista.setText("");
    }
    public  void obtener(){
      FileOutputStream fos = null;
      ObjectOutputStream salida = null;
      FileInputStream fis = null;
      ObjectInputStream entrada = null;
      boolean ex = true;
      try {
        fis = new FileInputStream("caja.dat");
        entrada = new ObjectInputStream(fis);
        c = (Caja) entrada.readObject();
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
    public void guardar(){
      FileOutputStream fos = null;
      ObjectOutputStream salida = null;
      FileInputStream fis = null;
      ObjectInputStream entrada = null;
      try {
        fos = new FileOutputStream("caja.dat");
        salida = new ObjectOutputStream(fos);
        salida.writeObject(c);

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

    @FXML
    private void onAdmi(MouseEvent event) {
      Alert dialogo = new Alert(AlertType.INFORMATION);
      dialogo.setTitle("Ventas del mes");
      dialogo.setContentText(c.ventas());
      dialogo.setHeaderText(null);
      dialogo.initStyle(StageStyle.UTILITY);
      dialogo.showAndWait();
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
      guardar();
      System.exit(0);
    }
}
