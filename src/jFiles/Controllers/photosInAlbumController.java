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



        /*try {
            photoList.setItems(data.listPhotos(data.getUser(), inAlbum));;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("made it here");
            System.out.println(e.getMessage());
        }*/


        /*try {
            BufferedImage bImage = ImageIO.read(new File("src/resources/views/bk pic.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] d = bos.toByteArray();   // gets sent to the database

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(d));  // here d is retrieved from the data base
            ImageView iv = new ImageView();
            ImageView v = new ImageView();

            System.out.println(img.getHeight());
            System.out.println(img.getWidth());

            ObservableList<ImageView> b = FXCollections.observableArrayList();

            iv.setImage(SwingFXUtils.toFXImage(img, null));
            v.setImage(SwingFXUtils.toFXImage(img, null));
            iv.setFitHeight(50);
            iv.setFitWidth(50);
            v.setFitWidth(50);
            v.setFitHeight(50);
           // v.setId(); // set an ID so that when cell is clicked we get the child (image view) then display captions, tags
            b.add(iv);
            b.add(v);

            photoList.setItems(b);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }

    public void testClick(MouseEvent mouseEvent) throws FileNotFoundException {
        System.out.println(photoList.getSelectionModel().getSelectedIndex());
        System.out.println(photoList.getItems().get(0));

        if (toggleDelete.isSelected()) {
            // delete the selected photo
            data.deletePicture(data.getUser(), inAlbum, photoList.getSelectionModel().getSelectedIndex());
            toggleDelete.setSelected(false);
        } else {
            // go to the image display of that folder
        }
    }

    public void addPhoto(MouseEvent mouseEvent) throws IOException {
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();
        if (f.length > 0 && fd.getFiles()[0].getAbsolutePath().endsWith("png")) {
            System.out.println(fd.getFiles()[0].getAbsolutePath());

            File incomingImg = new File(fd.getFiles()[0].getAbsolutePath());
            BufferedImage bImage = ImageIO.read(incomingImg);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos);
            byte[] d = bos.toByteArray();   // gets sent to the database
            data.addPictureByteArray(data.getUser(), inAlbum, d);

            /*Parent reloadRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/photosInAlbum.fxml")));
            Scene photos = new Scene(reloadRoot);

            Stage photoList = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            photoList.setScene(photos);
            photoList.show();*/
            photoList.setItems(data.listPhotos(data.getUser(), inAlbum));

        } else if (f.length > 0 && fd.getFiles()[0].getAbsolutePath().endsWith("jpg")) {
            System.out.println(fd.getFiles()[0].getAbsolutePath());

            File incomingImg = new File(fd.getFiles()[0].getAbsolutePath());
            BufferedImage bImage = ImageIO.read(incomingImg);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] d = bos.toByteArray();   // gets sent to the database
            data.addPictureByteArray(data.getUser(), inAlbum, d);

            /*Parent reloadRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/photosInAlbum.fxml")));
            Scene photos = new Scene(reloadRoot);

            Stage photoList = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            photoList.setScene(photos);
            photoList.show();*/
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
