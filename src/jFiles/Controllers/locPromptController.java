package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.dataCenter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class locPromptController implements Initializable {
    public TextField loc;
    public Button locCancelButton;
    private int index;
    private String inAlbum;

    private dataCenter data = new dataCenter();

    public void locSubmit(MouseEvent mouseEvent) throws FileNotFoundException {
        if (loc.getText().length() <= 50) {
            if (loc.getText().trim().equals("")) {
                data.setLocation(data.getUser(), inAlbum, "", index);
            } else {
                data.setLocation(data.getUser(), inAlbum, loc.getText().replace(" ", "_"), index);
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/imageDisplay.fxml"));

            Parent displayRoot = loader.load();

            Scene photos = new Scene(displayRoot);
            imageDisplayController init = loader.getController();
            init.setIndex(index);
            init.chosenAlbum(inAlbum);
            init.load();

            Stage display = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            display.setScene(photos);
            display.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void locCancel(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/imageDisplay.fxml"));

            Parent displayRoot = loader.load();

            Scene photos = new Scene(displayRoot);
            imageDisplayController init = loader.getController();
            init.setIndex(index);
            init.chosenAlbum(inAlbum);
            init.load();

            Stage display = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            display.setScene(photos);
            display.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    void chosenAlbum(String chosen) {
        inAlbum = chosen;
    }

    public void setIndex(int i) {
        index = i;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
