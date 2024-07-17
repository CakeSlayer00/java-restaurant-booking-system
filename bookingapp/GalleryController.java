
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GalleryController extends HomeMenuController {
    @FXML
    private StackPane burgerBackgroundFake;

    @FXML
    private AnchorPane dropDownMenu;

    @FXML
    private Button exitButton;

    @FXML
    private Button home;

    @FXML
    private ImageView image1;

    @FXML
    private Button logInButton;

    @FXML
    private ImageView menu;

    @FXML
    private Button myProfileButton;

    @FXML
    private Button gallery;

    @FXML
    private Button signInButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button contactUsMenu;

    @FXML
    private Button bookingMenu;

    @FXML
    private Button mainMenu;

    


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
        gallery.setDisable(true);
        
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
    }

    
}
