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

public class editAlbumPromptController {
    public TextField oldName;
    public TextField newName;

    private dataCenter data = new dataCenter();

    // Confirms the edit of an album's name

    public void editAlbum(MouseEvent mouseEvent) {
        try {
            String user = data.getUser();
            if (!oldName.getText().trim().equals("") && !newName.getText().trim().equals("")) {
                String old = oldName.getText().trim().replace(" ", "_");
                String newAlbumName = newName.getText().trim().replace(" ", "_");
                data.editAlbum(user, old, newAlbumName);

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

    // takes user back to dash without editing an album name

    public void cancelPrompt(MouseEvent mouseEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/dashboard.fxml")));
        Scene dashboard = new Scene(dashRoot);

        Stage userDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        userDash.setScene(dashboard);
        userDash.show();
    }
}
