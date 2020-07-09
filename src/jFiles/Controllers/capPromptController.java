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

public class capPromptController implements Initializable {
    public TextField cap;
    public Button capCancelButton;
    private int index;
    private String inAlbum;

    private dataCenter data = new dataCenter();

    public void capSubmit(MouseEvent mouseEvent) throws FileNotFoundException {
        if (cap.getText().length() <= 50) {
            if (cap.getText().trim().equals("")) {
                data.setCaption(data.getUser(), inAlbum, "", index);
            } else {
                data.setCaption(data.getUser(), inAlbum, cap.getText().replace(" ", "_"), index);
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

    public void capCancel(MouseEvent mouseEvent) {
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
