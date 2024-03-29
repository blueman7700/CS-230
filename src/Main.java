import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//starts the GUI
        Parent root = FXMLLoader.load(getClass().getResource("views/TitleScreen.fxml"));
        primaryStage.setOnCloseRequest( e -> {
        	e.consume();
        	primaryStage.close();
        	System.exit(0);
        });
        primaryStage.setTitle("Block Build");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
