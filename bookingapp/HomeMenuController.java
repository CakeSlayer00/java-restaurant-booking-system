
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

public class HomeMenuController implements Initializable {
    @FXML
    private Button home;
    
    @FXML
    private Button logInButton;

    @FXML
    private Button signInButton;

    @FXML
    private Button exitButton;

    @FXML
    private HBox images;

    @FXML
    private StackPane burgerBackgroundFake;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane dropDownMenu;

    @FXML
    private ImageView tacoImage;

    @FXML
    private Button myProfileButton;

    private static final double initialScaleOfImageX = 1;
    private static final double initialScaleOfImageY = 1;

    private boolean isScaled = false;

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
    private Button gallery;

    @FXML
    private Button contactUsMenu1;

    @FXML
    private Button bookingMenu1;

    @FXML
    private Button mainMenu1;

    @FXML
    private Button gallery1;

    @FXML
    protected void switchToLogInScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("logInMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToSingInScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signInMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToProfileScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profilePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToBookingScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("bookingMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root , 1280 , 720);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToMaimMenuScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root , 1280 , 720);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToGalleryScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gallery.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root , 1280 , 720);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToContactUsScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("contactUs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root , 1280 , 720);
        stage.setScene(scene);
        stage.show();
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        home.setDisable(true);

        if(App.isSignedOrLoggedIn) {
            logInButton.setDisable(true);
            signInButton.setDisable(true);
            exitButton.setDisable(false);

            myProfileButton.setVisible(true);
            FadeTransition transition = new FadeTransition(Duration.seconds(2) , myProfileButton);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.play();
        } else {
            mainMenu.setDisable(true);
            bookingMenu.setDisable(true);
            contactUsMenu.setDisable(true);
            gallery.setDisable(true);
            mainMenu1.setDisable(true);
            bookingMenu1.setDisable(true);
            contactUsMenu1.setDisable(true);
            gallery1.setDisable(true);
        }

        tacoImage.setScaleX(initialScaleOfImageX);
        tacoImage.setScaleY(initialScaleOfImageX);

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

        tacoImage.setOnMouseClicked(e -> {
            if (!isScaled) {
                tacoImage.setScaleX(initialScaleOfImageX * 3);
                tacoImage.setScaleY(initialScaleOfImageY * 3);
                tacoImage.setFitHeight(200);
                tacoImage.setTranslateX(450);
                isScaled = true;
            } else {
                tacoImage.setScaleX(1);
                tacoImage.setScaleY(1);
                tacoImage.setFitHeight(289);
                tacoImage.setFitWidth(1057);
                tacoImage.setTranslateX(0);
                isScaled = false;
            }
        });

        exitButton.setOnAction(e-> {
            App.isSignedOrLoggedIn = false;
            
            logInButton.setDisable(false);
            signInButton.setDisable(false);
            exitButton.setDisable(true);
            mainMenu.setDisable(true);
            bookingMenu.setDisable(true);
            contactUsMenu.setDisable(true);
            gallery.setDisable(true);
            home.setDisable(true);
           
            fadeOutMyProfileButton();
        });     
    }

    protected void fadeOutMyProfileButton() {
        FadeTransition transition = new FadeTransition(Duration.seconds(2), myProfileButton);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
        transition.setOnFinished(e -> myProfileButton.setVisible(true));
    }

    protected void moveImages() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), images);
        transition.setByX(-400);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);

        transition.play();
    }

    protected void collect() {
        menu.setDisable(true);
        burgerBackgroundFake.setVisible(true);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.3), dropDownMenu);
        transition.setToY(150);
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.play();
        transition.setOnFinished(e -> {
            dropDownMenu.setVisible(false);
            burgerBackgroundFake.setVisible(false);
            menu.setDisable(false);
        });

    }

    protected void drop() {
        menu.setDisable(true);
        burgerBackgroundFake.setVisible(true);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.3), dropDownMenu);
        transition.setToY(150);
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.play();
        dropDownMenu.setVisible(true);

        transition.setOnFinished(e -> menu.setDisable(false));
    }
}
