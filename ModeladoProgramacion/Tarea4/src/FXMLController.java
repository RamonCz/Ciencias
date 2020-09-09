/**
* @author Cruz Perez Ramon
* @version 2.0
*/
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button facil;
    @FXML
    private Button medio;
    @FXML
    private Button dificil;


    /**
     * Creea la ventana juego dependiendo la dificultad
     */
    private void nuevaVentana(){
         try{

            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Juego.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
       }catch(Exception e){
           System.out.println(e);
        }
    }

    @FXML
    private void accionFacil(ActionEvent event) throws IOException {
      MainApp.simon.setDificultad(3);
      nuevaVentana();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void accionButtonMedio(ActionEvent event) {
        MainApp.simon.setDificultad(2);
        nuevaVentana();
    }

    @FXML
    private void accionButtonDificil(ActionEvent event) {
       MainApp.simon.setDificultad(1);
       nuevaVentana();
    }
}
