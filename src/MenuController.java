import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.Node;

public class MenuController {

    @FXML
    private Button graphe;

    @FXML
    private Button simplex;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("test.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println();
        }
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("simplex.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
