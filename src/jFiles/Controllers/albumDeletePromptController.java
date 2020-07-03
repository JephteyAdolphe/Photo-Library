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

public class albumDeletePromptController {
    public TextField albumNameToDelete;
    dataCenter data = new dataCenter();

    // Deletes an existing album

    public void deleteAlbum(MouseEvent mouseEvent) {
        try {
            String user = data.getUser();
            if (!albumNameToDelete.getText().trim().equals("")) {
                String nameOfAlbum = albumNameToDelete.getText().trim().replace(" ", "_");
                data.deleteAlbum(user, nameOfAlbum);

                Parent dashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/dashboard.fxml")));
                Scene dashboard = new Scene(dashRoot);

                Stage userDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                userDash.setScene(dashboard);
                userDash.show();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // takes user back to dash without deleting an album

    public void cancelPrompt(MouseEvent mouseEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/dashboard.fxml")));
        Scene dashboard = new Scene(dashRoot);

        Stage userDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        userDash.setScene(dashboard);
        userDash.show();
    }
}
