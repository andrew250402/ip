package andy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * A GUI for Andy using FXML.
 */
public class Main extends Application {

    private Andy andy = new Andy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAndy(andy);  // inject the Andy instance
            stage.setTitle("Andy Task Manager");

            // ⭐ window icon
            stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/images/paimon.png"))
            );
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
