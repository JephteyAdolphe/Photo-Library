package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.dataCenter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class adminDashController implements Initializable {
    public ListView userList;

    public void createUser(MouseEvent mouseEvent) throws IOException {
        Parent createRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/createUser.fxml")));
        Scene newUser = new Scene(createRoot);

        Stage creation = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        creation.setScene(newUser);
        creation.show();
    }

    public void deleteUser(MouseEvent mouseEvent) {
        String selectedUser = (String) userList.getSelectionModel().getSelectedItem();

        try {
            File file = new File("src/resources/data/userToDelete.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.close();

            FileWriter fw = new FileWriter(file);
            fw.write(selectedUser);
            fw.close();

            Parent delRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/userDeletionPrompt.fxml")));
            Scene deleteUser = new Scene(delRoot);

            Stage deletion = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            deletion.setScene(deleteUser);
            deletion.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataCenter data = new dataCenter();

        userList.getStylesheets().add("resources/views/lihst_vue.css");
        userList.getStyleClass().add("list-cell");
        userList.setItems(data.listUsers());
    }

    public void signOut(MouseEvent mouseEvent) throws IOException {
        Parent signRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/signIn.fxml")));
        Scene signIn = new Scene(signRoot);

        Stage signInPrompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        signInPrompt.setScene(signIn);
        signInPrompt.show();
    }
}
