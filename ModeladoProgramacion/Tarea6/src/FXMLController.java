
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
*Clase hecha para manejar correctamente la interfaz
*@author Cruz Perez Ramon
**/
public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button siete;
    @FXML
    private Button dos;
    @FXML
    private Button uno;
    @FXML
    private Button cinco;
    @FXML
    private Button tres;
    @FXML
    private Button seis;
    @FXML
    private Button cuatro;
    @FXML
    private Button ocho;
    @FXML
    private Button nueve;
    @FXML
    private Button multiplicacion;
    @FXML
    private Button menos;
    @FXML
    private Button mas;
    @FXML
    private Button igual;
    @FXML
    private Button division;
    @FXML
    private Button raiz;
    @FXML
    private Button borrar;
    @FXML
    private Button tan;
    @FXML
    private Button cos;
    @FXML
    private Button sin;
    @FXML
    private Button cero;
    @FXML
    private Button punto;
    @FXML
    private Button abre;
    @FXML
    private Button cierra;

    private Compilador compilador = new Compilador();
    private CompositeEA nodo = null;
    private String operacion = "";
    private String aux = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onActionSiete(ActionEvent event) {
      aux +="7";
      operacion +="7";
      label.setText(aux);
    }

    @FXML
    private void onActionDos(ActionEvent event) {
      aux +="2";
      operacion +="2";
      label.setText(aux);
    }

    @FXML
    private void onActionUno(ActionEvent event) {
      aux +="1";
      operacion +="1";
      label.setText(aux);
    }

    @FXML
    private void onActionCinco(ActionEvent event) {
      aux +="5";
      operacion +="5";
      label.setText(aux);
    }

    @FXML
    private void onActionTres(ActionEvent event) {
      aux +="3";
      operacion +="3";
      label.setText(aux);
    }

    @FXML
    private void onActionSeis(ActionEvent event) {
      operacion +="6";
      aux +="6";
      label.setText(aux);
    }

    @FXML
    private void onActionCuatro(ActionEvent event) {
      aux +="4";
      operacion +="4";
      label.setText(aux);
    }

    @FXML
    private void onActionOcho(ActionEvent event) {
      aux +="8";
      operacion +="8";
      label.setText(aux);
    }

    @FXML
    private void onActionNueve(ActionEvent event) {
      aux +="9";
      operacion +="9";
      label.setText(aux);
    }

    @FXML
    private void onActionMultiplicacion(ActionEvent event) {
      aux +="*";
      operacion +="*";
      label.setText(aux);
    }

    @FXML
    private void onActionMenos(ActionEvent event) {
      aux +="-";
      operacion +="-";
      label.setText(aux);
    }

    @FXML
    private void onActionMas(ActionEvent event) {
      aux +="+";
      operacion +="+";
      label.setText(aux);
    }

    @FXML
    private void onActionIgual(ActionEvent event) {
      try {
        nodo = compilador.arbolDeAnalisisSintactico(compilador.analisisLexico(operacion));
      }catch (Exception e) {
        label.setText("  Error");
      }
      try {
        label.setText( String.valueOf(nodo.evalua()));
      }catch (Exception e) {
        label.setText("  Error");
      }

      aux ="";
      operacion = "";
    }

    @FXML
    private void onActionDivision(ActionEvent event) {
      aux +="/";
      operacion +="/";
      label.setText(aux);
    }

    @FXML
    private void onActionRaiz(ActionEvent event) {
      aux +="sqr ";
      operacion +="r";
      label.setText(aux);
    }

    @FXML
    private void onActionBorrar(ActionEvent event) {
      operacion ="";
      aux ="";
      label.setText("");
    }

    @FXML
    private void onActionTan(ActionEvent event) {
      aux += "Tan ";
      operacion +="t";
      label.setText(aux);
    }

    @FXML
    private void onActionCos(ActionEvent event) {
      aux += "Cos ";
      operacion +="c";
      label.setText(aux);
    }

    @FXML
    private void onActionSin(ActionEvent event) {
      aux += "Sin ";
      operacion +="s";

      label.setText(aux);
    }

    @FXML
    private void onActionCero(ActionEvent event) {
      aux += "0";
      operacion +="0";
      label.setText(aux);
    }
    @FXML
    private void onActionPunto(ActionEvent event) {
      aux += ".";
      operacion +=".";
      label.setText(aux);
    }
    @FXML
    private void onActionAbre(ActionEvent event) {
      aux += "(";
      operacion +="(";
      label.setText(aux);
    }
    @FXML
    private void onActionCierra(ActionEvent event) {
      aux += ")";
      operacion +=")";
      label.setText(aux);
    }
}
