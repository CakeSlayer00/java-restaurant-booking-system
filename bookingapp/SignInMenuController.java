

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignInMenuController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label message;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void signInPressed(ActionEvent event) throws IOException, InterruptedException {
        boolean isSignedIn = checkForCorrectData();
        App.isSignedOrLoggedIn = true;

        if (isSignedIn) {
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
    }

    private boolean checkForCorrectData() {
        String username = this.username.getText();
        String password = this.password.getText();
        File file = new File("D:\\Vladislav_Sovostyanov_230103134\\BookingApp\\src\\bookingapp\\users.txt");

        if(username.isEmpty() || password.isEmpty()) {
            message.setStyle("-fx-text-fill: red;");
            message.setText("Write some data");
            message.setVisible(true);
            return false;
        }

        if (containsSymbol(username)) {
            message.setStyle("-fx-text-fill: red;");
            message.setText("Username contains inappropriate symbols");
            message.setVisible(true);
            return false;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(":");
                if (userData.length == 2 && userData[0].equals(username) && userData[1].equals(password)) {
                    message.setStyle("-fx-text-fill: red;");
                    message.setText("User already exists");
                    message.setVisible(true);
                    writer.close();
                    reader.close();
                    return false;
                }
            }

            writer.newLine();
            writer.write(username + ":" + password);
            writer.flush();

            App.consumer = new Consumer(username, password);

            message.setStyle("-fx-text-fill: #0dd13e;");
            message.setText("Registration successfull");
            message.setVisible(true);

            writer.close();
            reader.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean containsSymbol(String input) {
        char[] symbols = new char[] { '!', '@', '#', '$', '%', '-', '.', ',', ':', ';' };

        for (char symbol : symbols) {
            if (input.indexOf(symbol) != -1) {
                return true;
            }
        }
        return false;
    }
}