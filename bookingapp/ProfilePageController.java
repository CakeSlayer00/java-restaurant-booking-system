

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfilePageController implements Initializable{
    @FXML
    private Label username;

    @FXML
    private Label password;

    @FXML
    private Label orderDescription1;

    @FXML
    private Label orderDescription2;

    @FXML
    private Button modify1;

    @FXML
    private Button modify2;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Consumer currConsumer = App.consumer;
        username.setText(currConsumer.getUsername());
        password.setText(currConsumer.getPassword());

        if(App.data1 != null) 
            orderDescription1.setText(App.data1.toString());

        if(App.data2 != null) 
            orderDescription2.setText(App.data2.toString());

        modify1.setOnAction(e -> {
            App.modify1 = true;
            try {
                switchToBookingScene(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        modify2.setOnAction(e -> {
            App.modify2 = true;
            try {
                switchToBookingScene(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    private void switchToHomeScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
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
    
}
