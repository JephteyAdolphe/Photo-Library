package jFiles.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import resources.data.dataCenter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    public Button addButton;

    //private ObservableList<String> observableAlbums = FXCollections.observableArrayList();
    private ArrayList<String> albums = new ArrayList<>();
    @FXML
    private ListView<String> albumList = new ListView<>();


    public void addAlbum(MouseEvent mouseEvent) {
        albumList.getStylesheets().add("resources/views/lihst_vue.css");
        albumList.getStyleClass().add("list-cell");
        //observableAlbums.add("teststts");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataCenter c = new dataCenter();
        //c.listAlbums("jeff");
        //albumList.setItems(observableAlbums);
        
        albumList.getStylesheets().add("resources/views/lihst_vue.css");
        albumList.getStyleClass().add("list-cell");
        albumList.setItems(c.listAlbums("jeff"));
    }
}
