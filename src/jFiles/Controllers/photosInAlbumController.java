package jFiles.Controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.dataCenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class photosInAlbumController implements Initializable {
    public ListView photoList;
    public ToggleButton toggleDelete;
    private String inAlbum;

    private dataCenter data = new dataCenter();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        photoList.getStylesheets().add("resources/views/lihst_vue.css");
        photoList.getStyleClass().add("image-cell");

        try {
            photoList.setItems(data.listPhotos(data.getUser(), inAlbum));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void goToDisplay(MouseEvent mouseEvent) throws FileNotFoundException {
        ObservableList<ImageView> observablePhotos = FXCollections.observableArrayList();
        observablePhotos = photoList.getItems();

        int index = photoList.getSelectionModel().getSelectedIndex();

        if (toggleDelete.isSelected()) {
            // delete the selected photo
            data.deletePicture(data.getUser(), inAlbum, photoList.getSelectionModel().getSelectedIndex());
            toggleDelete.setSelected(false);
        } else {
            // go to the image display of that folder

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
    }

    public void addPhoto(MouseEvent mouseEvent) throws IOException {
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();
        if (f.length > 0 && fd.getFiles()[0].getAbsolutePath().endsWith("png")) {

            File incomingImg = new File(fd.getFiles()[0].getAbsolutePath());
            BufferedImage bImage = ImageIO.read(incomingImg);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos);
            byte[] d = bos.toByteArray();   // gets sent to the database
            data.addPictureByteArray(data.getUser(), inAlbum, d);

            photoList.setItems(data.listPhotos(data.getUser(), inAlbum));

        } else if (f.length > 0 && fd.getFiles()[0].getAbsolutePath().endsWith("jpg")) {

            File incomingImg = new File(fd.getFiles()[0].getAbsolutePath());
            BufferedImage bImage = ImageIO.read(incomingImg);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] d = bos.toByteArray();   // gets sent to the database
            data.addPictureByteArray(data.getUser(), inAlbum, d);

            photoList.setItems(data.listPhotos(data.getUser(), inAlbum));
        }
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/dashboard.fxml")));
        Scene dashboard = new Scene(dashRoot);

        Stage userDash = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        userDash.setScene(dashboard);
        userDash.show();
    }

    void chosenAlbum(String chosen) throws FileNotFoundException {
        inAlbum = chosen;   // use this in initialize

        photoList.setItems(data.listPhotos(data.getUser(), inAlbum));
    }
}
