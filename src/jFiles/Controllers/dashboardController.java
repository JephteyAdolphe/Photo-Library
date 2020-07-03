package jFiles.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    public Button addButton;

    private ArrayList<String> albums = new ArrayList<>();
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

    // Takes user to album deleteion prompt

    public void delete(MouseEvent mouseEvent) throws IOException {
        Parent deleteRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/deleteAlbumPrompt.fxml")));
        Scene delete_album = new Scene(deleteRoot);

        Stage delete_album_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        delete_album_prompt.setScene(delete_album);
        delete_album_prompt.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataCenter data = new dataCenter();

        albumList.getStylesheets().add("resources/views/lihst_vue.css");
        albumList.getStyleClass().add("list-cell");
        albumList.setItems(data.listAlbums("jeff"));    // when we have a sign in page we will pass different users to this line

        // Use this to clear the logged in user every time someone signs in
        // by doing this we can use the user from the text file in something like
        // create table when we do 'user'.'name of table'

        /*try {
            data.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
