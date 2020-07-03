package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.dataCenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class userDeletePromptController implements Initializable {
    public Label userToDelete;
    public TextField typedUserToDelete;
    private dataCenter data = new dataCenter();

    public void setUserLabel() throws FileNotFoundException {
        userToDelete.setText(data.getUserToDelete());
    }

    // Confirms the deletion of a user in the database then returns to admin dashboard

    public void confirmDeletion(MouseEvent mouseEvent) throws IOException {
        if (!typedUserToDelete.getText().trim().equals("")) {
            if (typedUserToDelete.getText().trim().replace(" ", "_").equals(userToDelete.getText())) {
                data.deleteUser(userToDelete.getText());

                Parent adminDashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/adminDash.fxml")));
                Scene dashboard = new Scene(adminDashRoot);

                Stage adminDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                adminDash.setScene(dashboard);
                adminDash.show();
            }
        }
    }

    // takes the admin back to their dash without deleting user

    public void cancelPrompt(MouseEvent mouseEvent) throws IOException {
        Parent adminDashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/adminDash.fxml")));
        Scene dashboard = new Scene(adminDashRoot);

        Stage adminDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        adminDash.setScene(dashboard);
        adminDash.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUserLabel();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
