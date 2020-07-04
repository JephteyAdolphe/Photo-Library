package jFiles.Controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.data.dataCenter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class photosInAlbumController implements Initializable {
    public ListView photoList;

    private dataCenter data = new dataCenter();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        photoList.getStylesheets().add("resources/views/lihst_vue.css");
        photoList.getStyleClass().add("image-cell");
        //photoList.prefHeightProperty().bind(Bindings.size(photoList.getProperties()).multiply(10000));

        try {
            BufferedImage bImage = ImageIO.read(new File("src/resources/views/bk pic.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] d = bos.toByteArray();

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(d));
            ImageView iv = new ImageView();
            ImageView v = new ImageView();

            ObservableList<ImageView> b = FXCollections.observableArrayList();
            iv.setImage(SwingFXUtils.toFXImage(img, null));
            iv.setFitHeight(200);
            iv.setFitWidth(200);
            v.setImage(SwingFXUtils.toFXImage(img, null));
            v.setFitHeight(100);
            v.setFitWidth(100);
            b.add(iv);
            b.add(v);

            photoList.setItems(b);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
