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

public class albumAddPromptController {
    public TextField albumName;
    private dataCenter data = new dataCenter();

    public void addAlbum(MouseEvent mouseEvent) {
        try {
            String user = data.getUser();
            if (!albumName.getText().trim().equals("")) {
                String nameOfAlbum = albumName.getText().trim().replace(" ", "_");
                data.createAlbumTable(user, nameOfAlbum);

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

    // takes user back to dash without creating an album

    public void cancelPrompt(MouseEvent mouseEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/dashboard.fxml")));
        Scene dashboard = new Scene(dashRoot);

        Stage userDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        userDash.setScene(dashboard);
        userDash.show();
    }
}
