
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class TestController implements Initializable {

    eluer_chemin e;
    @FXML
    private Label message;

    @FXML
    private Button afficher;

    @FXML
    private Button court;

    @FXML
    private TextField nom;

    @FXML
    private ComboBox<String> sommet_1;

    @FXML
    private ComboBox<String> sommet_2;

    @FXML
    private Button Afficher_sommet;

    @FXML
    private Shape c;

    @FXML
    private Shape ci;

    @FXML
    private Shape cii;

    @FXML
    private Pane p;

    double sceneX, sceneY;
    private Graph_eleur euler1;

    @FXML
    private Line l;

    @FXML
    private Line line2;

    @FXML
    private double distance;

    @FXML
    private static boolean but = true;

    @FXML
    private static int nbline = 0;

    @FXML
    private Button suppButton;

    @FXML
    private BorderPane borderPan;

    private Graphe g;

    static ArrayList<Shape> listSommetCircle = new ArrayList<>();
    static ArrayList<Line> listLine = new ArrayList<>();
    static ArrayList<Label> listL = new ArrayList<>();
    static ArrayList<Button> listB = new ArrayList<>();
    private static boolean supprime = false;

    private static int nb = 0;
    private Label l1;

    @FXML
    private void CourtCheminStart() {
        g = new Graphe(Sommet.getNbr() + 1);
        String src = sommet_1.getSelectionModel().getSelectedItem();
        String dis = sommet_2.getSelectionModel().getSelectedItem();
        TestController.but = !but;
        if (but) {
            court.setStyle("-fx-background-color: white");
            Afficher_sommet.setDisable(false);
        } else {
            court.setStyle("-fx-text-fill: white; -fx-background-color: blue;");
            Afficher_sommet.setDisable(true);
        }
        LinePlusCourtChemin(Sommet.getSommetByNom(src).getIndex(), Sommet.getSommetByNom(dis).getIndex());
        // RemplireComboBox1();

    }

    @FXML
    // ajouter sommet :
    public void AjouteSommetEvent() {
        if (!but) {
            but = true;
            suppButton.setStyle("-fx-background-color: white");
        }
        if (nom.getText().isEmpty() || nom.getText() == null) {
            dialogueBox(Alert.AlertType.WARNING, "Somme Warning",
                    "Saisir Un nom pour Le Sommet");
        } else {
            if (Sommet.getNbr() != -1) {
                String str = nom.getText();
                Sommet test = Sommet.getSommetByNom(str);
                if (test == null) {
                    ajout();
                } else {
                    dialogueBox(Alert.AlertType.ERROR, "Exist",
                            "le Nom de Sommet que vous etes Saisir est deja Exist");
                }
            } else {
                ajout();
            }

        }

    }

    private void ajout() {
        Sommet sommet = new Sommet(nom.getText());
        message.setText("sommet " + sommet.getNom() + " ajouter avec succes!" + sommet.getIndex());
        sommet_1.getItems().clear();
        sommet_2.getItems().clear();
        RemplireComboBox1();
        // RemplireComboBoxSupp();
        creerSommet(sommet, nom.getText());
        nom.clear();
    }

    @FXML
    // supprimer sommet :
    public void supprimerSommetEvent(ActionEvent e) {

        TestController.but = !but;
        if (but) {
            suppButton.setStyle("-fx-background-color: white");
        } else
            suppButton.setStyle("-fx-text-fill: white; -fx-background-color: red;");

        System.out.println("BUT statut  " + but);
        p.setOnMouseClicked(f -> {
            if (f.getButton() == MouseButton.PRIMARY && !but) {
                System.out.println("AYOUB TARGET HH" + f.getTarget());
                for (Shape circle : listSommetCircle) {
                    if (f.getTarget().equals(circle)) {
                        if (!Sommet.getSommetByNom(circle.getId()).isBlocked()) {

                            for (Sommet sms : Sommet.getSommets()) {
                                if (sms.getIndex() == Sommet.getNbr() && sms.getNom().equals(circle.getId())) {
                                    supprime = true;
                                }

                            }

                            if (supprime) {

                                for (Label lb : listL) {
                                    if (lb.getId().equals(circle.getId())) {
                                        lb.setVisible(false);
                                    }
                                }

                                for (Button b : listB) {
                                    if (b.getId().equals(circle.getId())) {
                                        b.setVisible(false);
                                    }
                                }
                                boolean test = Sommet.supprimerSommet(circle.getId());
                                p.getChildren().remove(f.getTarget());
                                sommet_1.getItems().clear();

                                RemplireComboBox1();
                                System.out.println("AYOUB TETS id  ==> " + test + " --> " + circle.getId());
                                System.out.println(Sommet.getSommets());
                                supprime = false;

                            } else
                                dialogueBox(Alert.AlertType.WARNING, "Message",
                                        "Vous ne pouvez pas supprimer cette sommet");

                        } else
                            dialogueBox(Alert.AlertType.ERROR, "Erreur", "Cette sommet est lié");

                    }
                }
            }
        });

        /*
         * String nom = supp.getSelectionModel().getSelectedItem().toString();
         * 
         * Sommet.supprimerSommet(nom);
         * message.setText("supprmer avec succes");
         * for(Sommet s: Sommet.getSommets()){
         * System.out.println("les sommet rest : "+s.getNom());
         * }
         */

    }

    @FXML
    private void creeLine() {
        // dialogueBox(Alert.AlertType.INFORMATION, title, message);

        String src = sommet_1.getSelectionModel().getSelectedItem();// A
        System.out.println(src);
        String dis = sommet_2.getSelectionModel().getSelectedItem();// B
        System.out.println(dis);

        Sommet s1 = Sommet.getSommetByNom(src);
        // System.out.println("src x :"+s1.getX());
        // System.out.println("src y :"+s1.getY());

        Sommet s2 = Sommet.getSommetByNom(dis);
        // System.out.println("dis x :"+s2.getX());
        // System.out.println("dis y :"+s2.getY());

        if (!s1.isAdj(s2)) {
            nbline++;

            TextInputDialog dialog = new TextInputDialog("0.0");

            dialog.setTitle("Distance entre " + s1 + " et " + s2);
            dialog.setHeaderText("Enter distance:");
            dialog.setContentText("dist:");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
                this.distance = Double.parseDouble(name);
            });
            s1.addAdj(s2, distance);
            s2.addAdj(s1, distance);
            Label labD = new Label(Double.toString(this.distance));
            labD.setLayoutX((s1.getX() + s2.getX()) / 2);
            labD.setLayoutY((s1.getY() + s2.getY()) / 2);
            l = new Line(s1.getX(), s1.getY(), s2.getX(), s2.getY());

            p.getChildren().addAll(l, labD);
            Graph_eleur eluer = new Graph_eleur(Sommet.getNbr());
            eluer.addEdge(s1.getIndex(), s2.getIndex());
            euler1 = eluer;
            s1.setBlocked(true);
            s2.setBlocked(true);
        } else {
            System.out.println("ADJACENT DEJA EXISTE");
            dialogueBox(Alert.AlertType.INFORMATION, "Informer", "ADJACENT DEJA EXISTE");
        }

        /*
         * System.out.println(s1.toString()+"Adj[] : "+s1.getAdjasonts().get(0).
         * getDistance());
         * System.out.println(s2.toString()+"Adj[] : "+s2.getAdjasonts().get(1).
         * getDistance());
         * creerLine(Sommet.getSommetByNom(src), Sommet.getSommetByNom(dis));
         * System.out.println("nom A : "+Sommet.getSommetByNom("A").getX()+"  //  "
         * +Sommet.getSommetByNom("A").getY());
         * System.out.println("nom B : "+Sommet.getSommetByNom("B").getX()+"  //  "
         * +Sommet.getSommetByNom("B").getY());
         */

    }

    public void verfierEluerCycle() {
        try {
            e = new eluer_chemin(euler1);
            int result = e.eulerianCircuit();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Eulerien Cycle");
            alert.setHeaderText("Type de eulerien Cycle :");
            if (result == 0) {
                System.out.println("Graph is not Eulerian");
                alert.setContentText("Graph is not Eulerian");
            } else if (result == 1) {
                System.out.println("Graph contains a Euler path");
                alert.setContentText("Graph contains a Euler path");
            } else {// 2{
                alert.setContentText("Graph contains a Euler cycle");
                System.out.println("Graph contains a Euler cycle");
            }

            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Graph is not Eulerian");
        }
    }

    public void LinePlusCourtChemin(int src, int dis) {
        ++nb;
        ArrayList<Integer> court = Graphe.PlusCourtChemin(g.getMatriceAdj(), src, dis);
        // ci = new Circle(10);
        // ci.setFill(Paint.valueOf("#bd1520"));

        for (int i = 0; i < court.size() - 1; i++) {
            Sommet sommet1 = Sommet.getSommetByIndex(court.get(i));
            // ci.setLayoutX(sommet1.getX());
            // ci.setLayoutY(sommet1.getY());
            Sommet sommet2 = Sommet.getSommetByIndex(court.get(i + 1));

            line2 = new Line(sommet1.getX(), sommet1.getY(), sommet2.getX(), sommet2.getY());
            line2.setStyle("-fx-stroke: red;");
            line2.setStrokeWidth(6);
            line2.setSmooth(true);
            if (nb % 2 == 0) {
                System.out.println("nbr " + court.size());
                for (Line l : listLine) {
                    p.getChildren().remove(l);
                }
                listLine.clear();
            } else {
                listLine.add(line2);
                // p.getChildren().add(ci);
                p.getChildren().add(line2);
            }

        }
        court.clear();
    }

    // ------remplire comboBox1 sommets:
    public void RemplireComboBox1() {
        sommet_1.getItems().clear();
        for (String s : Sommet.getListNomsSommet()) {
            sommet_1.getItems().add(s);
        }
    }

    // ------remplire comboBox1 sommets:
    public void RemplireComboBox2(String nom) {
        sommet_2.getItems().clear();
        for (String s : Sommet.getNomSommetRest(nom)) {
            sommet_2.getItems().add(s);
        }
    }

    @FXML
    void Select(ActionEvent event) {
        String s = sommet_1.getSelectionModel().getSelectedItem();
        RemplireComboBox2(s);
    }

    @FXML
    void disAcvt(MouseEvent event) {
        TestController.but = !but;
        System.out.println("butt sup " + but);
    }

    @FXML
    private void creerSommet(Sommet s, String n) {
        // p.getChildren().clear();
        Button b = new Button();
        b.setStyle("-fx-background-color: blue; -size: 40;\n" +
                "    -fx-min-height: -size;\n" +
                "    -fx-min-width: -size;\n" +
                "    -fx-max-height: -size;\n" +
                "    -fx-max-width: -size; -fx-shape:' M 100, 100\r\n" +
                "        m -75, 0\r\n" +
                "        a 75,75 0 1,0 150,0\r\n" +
                "        a 75,75 0 1,0 -150,0'");
        c = new Circle(10);
        c.setId(n);
        c.setLayoutX(320);
        c.setLayoutY(320);
        b.setLayoutX(300);
        b.setLayoutY(300);
        b.setId(n);
        s.setX(320);
        s.setY(320);
        c.setCursor(Cursor.HAND);
        // c.setStroke(Paint.valueOf("#499110"));
        c.setFill(Paint.valueOf("#00000000"));
        l1 = new Label(n);
        l1.setLayoutX(295);
        l1.setLayoutY(270);
        l1.setStyle("-fx-font-family:'consolas'; -fx-font-weight: bold;");
        l1.setId(n);
        c.setOnMousePressed((t) -> {
            sceneX = t.getSceneX();
            sceneY = t.getSceneY();

            s.setX(sceneX);
            s.setY(sceneY);
            /*
             * l.setStartX(sceneX);
             * l.setStartY(sceneY);
             */
        });

        c.setOnMouseDragged((t) -> {

            if (!s.isBlocked()) {
                double offsetX = t.getSceneX() - sceneX;
                double offsetY = t.getSceneY() - sceneY;

                Circle c = (Circle) (t.getSource());

                c.setCenterX(c.getCenterX() + offsetX);
                c.setCenterY(c.getCenterY() + offsetY);

                System.out.println(t.getScreenX() + " // " + sceneX);
                sceneX = t.getSceneX();
                sceneY = t.getSceneY();
                s.setX(sceneX);
                s.setY(sceneY);
                for (Label lb : listL) {
                    if (lb.getId().equals(c.getId())) {
                        lb.setLayoutX(sceneX - 5);
                        lb.setLayoutY(sceneY - 40);
                    }
                }

                /*
                 * for(Button btn: listB){
                 * if(btn.getId().equals(c.getId())){
                 * btn.setLayoutX(sceneX);
                 * btn.setLayoutY(sceneY);
                 * 
                 * }
                 * }
                 */

                b.setLayoutX(sceneX - 20);
                b.setLayoutY(sceneY - 20);
                System.out.println("sommet " + s.getNom() + " (x, y) : " + s.getX() + " , " + s.getY());
                // System.out.println(" 2=> "+s);

                /*
                 * l.setStartX(sceneX);
                 * l.setStartY(sceneY);
                 * l.setVisible(true);
                 */
            }
        });

        listSommetCircle.add(c);
        listL.add(l1);
        listB.add(b);
        p.getChildren().addAll(l1, b, c);
    }

    public void dialogueBox(Alert.AlertType type, String title, String message) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(message);
        Optional<ButtonType> res = a.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RemplireComboBox1();
    }

}
