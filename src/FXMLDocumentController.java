
import java.net.URL;
import java.time.chrono.MinguoChronology;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author hicham maghraoui
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label X_label, Y_label, Z_label;
    @FXML
    private TextField X_value, Y_value, X1, X2, Y1, Y2, value1, value2;
    @FXML
    private MenuItem MAX = new MenuItem("MAX");
    @FXML
    private MenuItem MIN = new MenuItem("MIN");
    @FXML
    private ComboBox<String> MaxOrMin;
    @FXML
    private ComboBox<String> InfORSup_equ1;
    @FXML
    private ComboBox<String> InfORSup_equ2;
    private String sign;
    // @FXML
    // private Label X_result, Y_result;

    // @FXML
    // private void handleButtonAction(ActionEvent event) {
    // System.out.println("You clicked me!");
    // label.setText("Hello World!");
    // }

    @FXML
    private void Calculer(ActionEvent event) {

        // String sign = MaxOrMin.getText();
        AjouteSommetEvent();
        // Example problem:
        // maximize 3x + 5y
        // subject to x + y = 4 and
        // x + 3y = 6
        float S;
        if (sign == "MIN") {
            S = 1;

        } else {
            S = -1;
        }
        // float[][] standardized = {
        // { Float.parseFloat(X1.getText()), Float.parseFloat(Y1.getText()), 1, 0,
        // Float.parseFloat(value1.getText()) },
        // { Float.parseFloat(X2.getText()), Float.parseFloat(Y2.getText()), 0, 1,
        // Float.parseFloat(value2.getText()) },
        // { S * Float.parseFloat(X_value.getText()), S *
        // Float.parseFloat(Y_value.getText()), 0, 0, 0 }
        // };
        // System.out.println(Arrays.deepToString(standardized));
        // Simplex simplex = new Simplex(2, 4);
        // simplex.fillTable(standardized);
        // Simplex.ERROR err = simplex.compute();
        // standardized = simplex.getTable();
        // if (err == Simplex.ERROR.IS_OPTIMAL) {
        // System.out.println();
        // simplex.print();

        // } else if (err == Simplex.ERROR.UNBOUNDED) {
        // System.out.println("---Solution is unbounded---");
        // simplex.print();
        // }
        // System.out.println(Arrays.deepToString(standardized));
        // System.out.println("Z :" + standardized[1][4]);
        // Z_label.setText(String.valueOf(standardized[2][4]));
        // X_label.setText(String.valueOf(standardized[2][3]));
        // Y_label.setText(String.valueOf(standardized[2][2]));
        Main s = new Main();
        Solution solution;
        double[][] A = { { Float.parseFloat(X1.getText()), Float.parseFloat(Y1.getText()), 1, 0 },
                { Float.parseFloat(X2.getText()), Float.parseFloat(Y2.getText()), 0, 1 } };
        double[] b = { Float.parseFloat(value1.getText()), Float.parseFloat(value2.getText()) };
        double[] c = { S * Float.parseFloat(X_value.getText()), S * Float.parseFloat(Y_value.getText()), 0, 0 };
        solution = s.simplex(A, b, c, true, null);
        solution.print();
        Z_label.setText(String.valueOf(solution.z * (-1)));
        X_label.setText(String.valueOf(solution.x[0]));
        Y_label.setText(String.valueOf(solution.x[1]));
    }

    @FXML
    private void MAXOrMIN_function(ActionEvent event) {

        // parameters = "prompt" "Icon/Graphics/" "menuItem1"
        sign = MaxOrMin.getSelectionModel().getSelectedItem();

        dialogueBox(Alert.AlertType.WARNING, "Remplissage n est pas complete",
                "Completer le remplissage du valeur !!!");

        System.out.println(sign);
    }

    @FXML
    private void INForSUP_function(ActionEvent event) {

        // parameters = "prompt" "Icon/Graphics/" "menuItem1"
        if (InfORSup_equ1.getSelectionModel().getSelectedItem() == null
                || InfORSup_equ2.getSelectionModel().getSelectedItem() == null) {
            dialogueBox(Alert.AlertType.WARNING, "Remplissage n est pas complete",
                    "Completer le remplissage du valeur !!!");
        } else {
            String sinne_equ1 = InfORSup_equ1.getSelectionModel().getSelectedItem();
            System.out.println("equation1:   " + sinne_equ1);
            String sinne_equ2 = InfORSup_equ2.getSelectionModel().getSelectedItem();
            System.out.println("equation2:   " + sinne_equ2);
        }
    }

    @FXML
    // ajouter sommet :
    public void AjouteSommetEvent() {

        if ((X_value.getText().isEmpty() || X_value.getText() == null)
                || (Y_value.getText().isEmpty() || Y_value.getText() == null)
                || (Y1.getText().isEmpty() || Y1.getText() == null)
                || (Y2.getText().isEmpty() || Y2.getText() == null)
                || (X2.getText().isEmpty() || X2.getText() == null)
                || (X1.getText().isEmpty() || X1.getText() == null)
                || (value1.getText().isEmpty() || value1.getText() == null)
                || (value2.getText().isEmpty() || value2.getText() == null)) {
            dialogueBox(Alert.AlertType.WARNING, "Remplissage n est pas complete",
                    "Completer le remplissage du valeur !!!");
        } else {

            System.out.println("X_value : " + X_value.getText());
            System.out.println("y_value : " + Y_value.getText());
            System.out.println("X1 : " + X1.getText());
            System.out.println("X2 : " + X2.getText());
            System.out.println("Y1 : " + Y1.getText());
            System.out.println("Y2 : " + Y2.getText());
            System.out.println("vlauer 1 : " + value1.getText());
            System.out.println("vlauer 2 : " + value2.getText());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaxOrMin.getItems().add("MAX");
        MaxOrMin.getItems().add("MIN");
        InfORSup_equ1.getItems().add(">");
        InfORSup_equ1.getItems().add("<");
        InfORSup_equ2.getItems().add(">");
        InfORSup_equ2.getItems().add("<");
    }

    public void dialogueBox(Alert.AlertType type, String title, String message) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(message);
        Optional<ButtonType> res = a.showAndWait();
    }
}
