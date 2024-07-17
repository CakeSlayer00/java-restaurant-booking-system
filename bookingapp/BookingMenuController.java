
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BookingMenuController extends HomeMenuController {
    @FXML
    private MenuItem Chilesennogado;

    @FXML
    private MenuItem Elote;

    @FXML
    private MenuItem Guacamole;

    @FXML
    private MenuItem Nacho;

    @FXML
    private MenuItem Pozole;

    @FXML
    private MenuItem Quesadilla;

    @FXML
    private MenuItem Tacos;

    @FXML
    private MenuItem Huaraches;

    @FXML
    private Button bookButton;

    @FXML
    private Button bookingMenu;

    @FXML
    private StackPane burgerBackgroundFake;

    @FXML
    private TextField date;

    @FXML
    private MenuButton dish;

    @FXML
    private AnchorPane dropDownMenu;

    @FXML
    private TextField email;

    @FXML
    private Button exitButton;

    @FXML
    private MenuItem four;

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
    private MenuItem one;

    @FXML
    private MenuButton peopleCount;

    @FXML
    private TextField phone;

    @FXML
    private Button signInButton;

    @FXML
    private TextArea specialRequests;

    @FXML
    private MenuItem three;

    @FXML
    private MenuItem two;

    @FXML
    private MenuItem orderOne;

    @FXML
    private MenuItem orderTwo;

    @FXML
    private MenuButton order;

    @FXML
    private Button contactUsMenu;

    @FXML
    private Button home;

    @FXML
    private Button mainMenu;

    @FXML
    private Button gallery;



    private Stage stage;
    private Scene scene;
    private Parent root;

    private LocalDateTime dateTime;
    private int peopleCountInt;
    private boolean isOrderOne = false;

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
        bookingMenu.setDisable(true);

        if(App.modify1 || App.modify2) {
            if(App.modify1 && !App.modify2){
                order.setText(orderOne.getText());
            } else if(!App.modify1 && App.modify2){
                order.setText(orderTwo.getText());
            }
            order.setDisable(true);
        }

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

        orderOne.setOnAction(e -> {
            isOrderOne = true;
            order.setText(orderOne.getText());
        });
        orderTwo.setOnAction(e -> {
            isOrderOne = false;
            order.setText(orderTwo.getText());
        });

        Dish chosenDish = new Dish();

        Chilesennogado.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Chilesennogado.getText());
        });

        Elote.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Elote.getText());
        });

        Guacamole.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Guacamole.getText());
        });

        Huaraches.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Huaraches.getText());
        });

        Nacho.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Nacho.getText());
        });

        Pozole.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Pozole.getText());
        });

        Quesadilla.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Quesadilla.getText());
        });

        Tacos.setOnAction(e -> {
            dish.setText(Chilesennogado.getText());
            chosenDish.setName(Tacos.getText());
        });

        one.setOnAction(e -> {
            peopleCount.setText(one.getText());
            peopleCountInt = Integer.parseInt(one.getText());
        });

        two.setOnAction(e -> {
            peopleCount.setText(two.getText());
            peopleCountInt = Integer.parseInt(two.getText());
        });

        three.setOnAction(e -> {
            peopleCount.setText(three.getText());
            peopleCountInt = Integer.parseInt(three.getText());
        });

        four.setOnAction(e -> {
            peopleCount.setText(four.getText());
            peopleCountInt = Integer.parseInt(four.getText());
        });


        bookButton.setOnAction(e -> {
            boolean check = false;

            if (name.getText().isEmpty()
                    || email.getText().isEmpty()
                    || date.getText().isEmpty()
                    || phone.getText().isEmpty()
                    || specialRequests.getText().isEmpty()) {
                message.setStyle("-fx-text-fill: red;");
                message.setText("Data is not filled !");
                message.setVisible(true);
            } else check = true;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a dd.MM.yyyy");
                dateTime = LocalDateTime.parse(date.getText(), formatter);
            } catch (DateTimeParseException exception) {
                message.setStyle("-fx-text-fill: red;");
                message.setText("Uncorrect format of date");
                message.setVisible(true);

            }

            if (check) {
                if (isOrderOne) {
                    App.data1 = new PersonData(
                            name.getText(),
                            email.getText(),
                            phone.getText(),
                            dateTime,
                            peopleCountInt,
                            specialRequests.getText(), chosenDish);
                } else {
                    App.data2 = new PersonData(
                            name.getText(),
                            email.getText(),
                            phone.getText(),
                            dateTime,
                            peopleCountInt,
                            specialRequests.getText(), chosenDish);

                }
                message.setStyle("-fx-text-fill: #0dd13e;");
                message.setText("Booking complete");
                message.setVisible(true);
            }
        });
        
    }

    public void modify1(){
        order.setText(orderOne.getText());
        order.setDisable(true);
    }

    public void modify2(){
        order.setText(orderTwo.getText());
        order.setDisable(true);
    }
}
