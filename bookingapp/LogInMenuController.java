

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LogInMenuController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label message;

    @FXML
    private Button home;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void logInPressed(ActionEvent event) throws IOException, InterruptedException {
        boolean isLoggedIn = checkForCorrectData();
        App.isSignedOrLoggedIn = true;

        if (isLoggedIn) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try {
                    switchToHomeScreen(event);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            pause.play();
        }
    }

    private void switchToHomeScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        home.setOnAction(e -> {
            try {
                App.isSignedOrLoggedIn = false;
                switchToHomeScreen(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private boolean checkForCorrectData() {
        String username = this.username.getText();
        String password = this.password.getText();
        File file = new File("D:\\Vladislav_Sovostyanov_230103134\\BookingApp\\src\\bookingapp\\users.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(":");

                if (userData.length == 2 && userData[0].equals(username) && userData[1].equals(password)) {
                    message.setStyle("-fx-text-fill: #0dd13e;");
                    message.setText("Autorization successful");
                    message.setVisible(true);

                    App.consumer = new Consumer(username, password);
                    App.consumer.setPersonData1(App.data1);
                    App.consumer.setPersonData2(App.data2);
                    return true;
                } else if (userData.length == 2 && userData[0].equals(username) && !userData[1].equals(password)) {
                    message.setStyle("-fx-text-fill: red;");
                    message.setText("Password is incorrect");
                    message.setVisible(true);
                    return false;
                }

                message.setStyle("-fx-text-fill: red;");
                message.setText("User is not found");
                message.setVisible(true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}