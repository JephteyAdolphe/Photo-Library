package jFiles.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.dataCenter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    public Button addButton;

    @FXML
    private ListView<String> albumList = new ListView<>();

    // Takes user to album addition prompt

    public void addAlbum(MouseEvent mouseEvent) throws IOException {
        Parent addRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/albumAddPrompt.fxml")));
        Scene add_album = new Scene(addRoot);

        Stage add_album_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        add_album_prompt.setScene(add_album);
        add_album_prompt.show();

    }

    // Takes user to album name editing prompt

    public void editAlbumName(MouseEvent mouseEvent) throws IOException {
        Parent editRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/editAlbumPrompt.fxml")));
        Scene edit_album = new Scene(editRoot);

        Stage edit_album_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        edit_album_prompt.setScene(edit_album);
        edit_album_prompt.show();
    }

    // Takes user to album deletion prompt

    public void delete(MouseEvent mouseEvent) throws IOException {
        Parent deleteRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/deleteAlbumPrompt.fxml")));
        Scene delete_album = new Scene(deleteRoot);

        Stage delete_album_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        delete_album_prompt.setScene(delete_album);
        delete_album_prompt.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void load(String user) {
        dataCenter data = new dataCenter();

        albumList.getStylesheets().add("resources/views/lihst_vue.css");
        albumList.getStyleClass().add("list-cell");
        albumList.setItems(data.listAlbums(user));
    }

    public void goToPhotos(MouseEvent mouseEvent) {
        String selectedAlbum = albumList.getSelectionModel().getSelectedItem();

        if (selectedAlbum != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/photosInAlbum.fxml"));

                Parent photoRoot = loader.load();

                Scene photos = new Scene(photoRoot);
                photosInAlbumController init = loader.getController();
                init.chosenAlbum(selectedAlbum);

                Stage listOfPhotos = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                listOfPhotos.setScene(photos);
                listOfPhotos.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void signOut(MouseEvent mouseEvent) throws IOException {
        Parent signRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/signIn.fxml")));
        Scene signIn = new Scene(signRoot);

        Stage signInPrompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        signInPrompt.setScene(signIn);
        signInPrompt.show();
    }
}
