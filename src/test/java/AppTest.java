import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

@ExtendWith(ApplicationExtension.class)
public class AppTest {

    @Start
    private void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void clearUsers() {
        UsersApp.validUsers.clear();
    }

    @Test
    public void testUsernameFieldIsEmptyAtStart() {
        FxAssert.verifyThat("#usernameField",
                TextInputControlMatchers.hasText(""));
    }

    @Test
    public void testPasswordFieldIsEmptyAtStart() {
        FxAssert.verifyThat("#passwordField",
                TextInputControlMatchers.hasText(""));
    }

    @Test
    public void testLoginButtonText() {
        FxAssert.verifyThat(".button",
                LabeledMatchers.hasText("Login"));
    }

    @Test
    public void testWrongLoginShowsError(FxRobot robot) {
        robot.clickOn("#usernameField").write("wrongUser");
        robot.clickOn("#passwordField").write("wrongPassword");
        robot.clickOn(".button");

        FxAssert.verifyThat("#errorLabel",
                LabeledMatchers.hasText("Wrong username or password"));
    }
}