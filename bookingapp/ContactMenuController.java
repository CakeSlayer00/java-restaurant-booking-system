
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ContactMenuController extends HomeMenuController{

    @FXML
    private StackPane burgerBackgroundFake;

    @FXML
    private AnchorPane dropDownMenu;

    @FXML
    private TextField email;

    @FXML
    private Button exitButton;

    @FXML
    private HBox images;

    @FXML
    private Button logInButton;

    @FXML
    private ImageView menu;

    @FXML
    private Label message;

    @FXML
    private Button myProfileButton;

    @FXML
    private TextField name;

    @FXML
    private Button signInButton;

    @FXML
    private TextArea additional;

    @FXML
    private Button contact;

    @FXML
    private Button contactUsMenu;

    @FXML
    private Button bookingMenu;

    @FXML
    private Button mainMenu;

    @FXML
    private Button gallery;

    @FXML
    private Button home;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void switchToHomeScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        contactUsMenu.setDisable(true);

        if(App.isSignedOrLoggedIn) {
            logInButton.setDisable(true);
            signInButton.setDisable(true);
            exitButton.setDisable(true);

            myProfileButton.setVisible(true);
            FadeTransition transition = new FadeTransition(Duration.seconds(2) , myProfileButton);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.play();
        } 
        moveImages();

        burgerBackgroundFake.setVisible(false);
        dropDownMenu.setVisible(false);

        menu.setOnMouseClicked(e -> {
            RotateTransition transition = new RotateTransition(Duration.seconds(.3), menu);
            transition.setByAngle(-270);
            transition.play();

            if (dropDownMenu.isVisible()) {
                collect();
            } else {
                drop();
            }
        });   

        contact.setOnAction(e -> {
            if(checkForCorrectData()){
                setOnSuccess();    
            }
            
        });
    }

    private void setOnSuccess(){
        message.setVisible(true);


    }

    private boolean checkForCorrectData() {
        File file = new File("D:\\Vladislav_Sovostyanov_230103134\\BookingApp\\src\\bookingapp\\contactUsFeedback.txt");

        if(name.getText().isEmpty() || name.getText() == null
        || additional.getText().isEmpty() || additional.getText() == null
        || email.getText().isEmpty() || email.getText() == null) {
            message.setVisible(true);
            message.setText("Data is incorrect!");
            message.setStyle("-fx-text-fill: red;");
            return false;
        }       

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.newLine();
            writer.write(App.consumer.getUsername() + ":\nName:" + name.getText() +"\nAdditional:" + additional.getText() + "\nEmail:" +email.getText());
            writer.flush();

            message.setVisible(true);
            message.setText("Perfect!");
            message.setStyle("-fx-text-fill: #0dd13e;");

            writer.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
