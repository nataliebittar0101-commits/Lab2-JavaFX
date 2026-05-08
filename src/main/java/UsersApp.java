import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp extends Application {

    public static ArrayList<User> validUsers = new ArrayList<>();

    public static void main(String[] args) {

        readUsersFromFile();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/login.fxml"));

        Scene scene = new Scene(loader.load(), 400, 300);

        stage.setTitle("Users Login");

        stage.setScene(scene);

        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }

    public static void readUsersFromFile() {

        File file = new File("Users.txt");

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length < 2) {
                    continue;
                }

                String username = parts[0];
                String password = parts[1];

                try {

                    User user = new User(username, password);

                    validUsers.add(user);

                } catch (Exception e) {

                    System.out.println(line + " -> " + e.getMessage());
                }
            }

            Collections.sort(validUsers,
                    (u1, u2) ->
                            u1.getUsername().compareTo(u2.getUsername()));

        } catch (FileNotFoundException e) {

            System.out.println("Users.txt not found");
        }
    }
}