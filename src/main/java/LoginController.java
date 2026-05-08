import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void loginButtonClicked() {

        String username = usernameField.getText();

        String password = passwordField.getText();

        boolean found = false;

        for (User user : UsersApp.validUsers) {

            if (user.getUsername().equals(username)
                    &&
                    user.getPassword().equals(password)) {

                found = true;

                break;
            }
        }

        if (found) {

            openWelcomeWindow(username);

        } else {

            errorLabel.setText("Wrong username or password");
        }
    }

    private void openWelcomeWindow(String username) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/welcome.fxml"));

            Scene scene = new Scene(loader.load(), 400, 300);

            WelcomeController controller =
                    loader.getController();

            controller.setWelcomeText(username);

            Stage stage =
                    (Stage) usernameField.getScene().getWindow();

            stage.setScene(scene);

        } catch (Exception e) {

            errorLabel.setText("Error loading screen");
        }
    }
}