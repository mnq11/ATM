package atm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ATM extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
//hjsds
        stage.setScene(scene);
        stage.setTitle("ATM Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
