import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    public void setWelcomeText(String username) {

        welcomeLabel.setText("Welcome " + username);
    }
}
