import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller to handle the Title screen GUI.
 * @author Lewis Pettifer
 *
 */
public class TitleScreenController {

	//GUI elements
    @FXML
    Button startButton;

    @FXML
    public void initialize() {

    }

    /**
     * Changes scene to Log in.
     * @param Action of clicking.
     * @throws IOException can't change scene.
     */
    @FXML
    public void startClick(ActionEvent event) throws IOException{
        //loads new stage by swapping root
        Parent root;
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Login.fxml"));
        root = (Parent)loader.load();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }



}
