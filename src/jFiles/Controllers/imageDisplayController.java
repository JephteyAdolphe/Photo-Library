package jFiles.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import resources.data.dataCenter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class imageDisplayController implements Initializable {
    public ImageView displayImage;
    public Label caption;
    public Label tag;
    public Label location;
    public AnchorPane backDrop;
    private String inAlbum;

    private int index;
    private ObservableList<ImageView> photo = FXCollections.observableArrayList();
    private dataCenter data = new dataCenter();

    // just take in the selected image view and enlarge it but I still have to figure out how to match caption/tag/location with the correct image[view]


    public void setIndex(int i) {
        index = i;
    }

    public void setObservable(ObservableList<ImageView> observablePhoto) {
        photo = observablePhoto;
    }

    public void test() throws FileNotFoundException {
        System.out.println(index);

        ImageView display = data.getSelectedPhoto(data.getUser(), inAlbum, index+1);
        display.setFitWidth(385);
        display.setFitHeight(290);
        display.setLayoutX(108);
        display.setLayoutY(14);

        backDrop.getChildren().add(display);

    }

    void chosenAlbum(String chosen) {
        inAlbum = chosen;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void goBack(MouseEvent mouseEvent) {
    }
}
