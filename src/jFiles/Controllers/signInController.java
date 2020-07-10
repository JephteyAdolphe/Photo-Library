package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class signInController {
    public TextField signInField;


    public void signIn(MouseEvent mouseEvent) {
        if (signInField.getText().trim().equals("admin")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/adminDash.fxml"));

                Parent dashRoot = loader.load();

                Scene photos = new Scene(dashRoot);
                dashboardController init = loader.getController();
                init.load(signInField.getText().trim());

                Stage dashboard = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                dashboard.setScene(photos);
                dashboard.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/dashboard.fxml"));

                Parent dashRoot = loader.load();

                Scene photos = new Scene(dashRoot);
                dashboardController init = loader.getController();
                init.load(signInField.getText().trim());

                Stage dashboard = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                dashboard.setScene(photos);
                dashboard.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
