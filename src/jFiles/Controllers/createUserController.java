package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.dataCenter;

import java.io.IOException;
import java.util.Objects;

public class createUserController {
    public TextField newUserName;
    private dataCenter data = new dataCenter();

    // creates new user then returns back to admin dashboard

    public void createNewUser(MouseEvent mouseEvent) {
        try {
            if (!newUserName.getText().trim().equals("")) {
                String newUser = newUserName.getText().trim().replace(" ", "_");
                data.createNewUser(newUser);

                Parent adminDashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/adminDash.fxml")));
                Scene dashboard = new Scene(adminDashRoot);

                Stage adminDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                adminDash.setScene(dashboard);
                adminDash.show();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // takes the admin back to their dash without creating a new user

    public void cancelPrompt(MouseEvent mouseEvent) throws IOException {
        Parent adminDashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/adminDash.fxml")));
        Scene dashboard = new Scene(adminDashRoot);

        Stage adminDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        adminDash.setScene(dashboard);
        adminDash.show();
    }
}
