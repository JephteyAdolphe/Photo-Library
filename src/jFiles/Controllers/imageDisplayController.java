package jFiles.Controllers;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resources.data.dataCenter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class imageDisplayController implements Initializable {
    public Label caption;
    public Label tag;
    public Label location;
    public AnchorPane backDrop;
    public Button captionButton;
    public Button tagButton;
    public Button locationButton;
    private String inAlbum;

    private int index;
    private dataCenter data = new dataCenter();

    public void setIndex(int i) {
        index = i;
    }

    public void load() throws FileNotFoundException {

        ImageView display = data.getSelectedPhoto(data.getUser(), inAlbum, index);
        display.setFitWidth(385);
        display.setFitHeight(290);
        display.setLayoutX(108);
        display.setLayoutY(14);

        backDrop.getChildren().add(display);

        caption.setText(data.getCaption(data.getUser(), inAlbum, index));
        tag.setText(data.getTag(data.getUser(), inAlbum, index));
        location.setText(data.getLocation(data.getUser(), inAlbum, index));
    }

    void chosenAlbum(String chosen) {
        inAlbum = chosen;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void goBack(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/photosInAlbum.fxml"));

            Parent photoRoot = loader.load();

            Scene photos = new Scene(photoRoot);
            photosInAlbumController init = loader.getController();
            init.chosenAlbum(inAlbum);

            Stage listOfPhotos = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            listOfPhotos.setScene(photos);
            listOfPhotos.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCaption(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/capPrompt.fxml"));

            Parent capRoot = loader.load();

            Scene photos = new Scene(capRoot);
            capPromptController init = loader.getController();
            init.setIndex(index);
            init.chosenAlbum(inAlbum);

            Stage cap = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            cap.setScene(photos);
            cap.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addTag(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/tagPrompt.fxml"));

            Parent tagRoot = loader.load();

            Scene photos = new Scene(tagRoot);
            tagPromptController init = loader.getController();
            init.setIndex(index);
            init.chosenAlbum(inAlbum);

            Stage tag = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            tag.setScene(photos);
            tag.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addLocation(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/locPrompt.fxml"));

            Parent locRoot = loader.load();

            Scene photos = new Scene(locRoot);
            locPromptController init = loader.getController();
            init.setIndex(index);
            init.chosenAlbum(inAlbum);

            Stage loc = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            loc.setScene(photos);
            loc.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
