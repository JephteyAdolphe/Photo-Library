package jFiles.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class loginController {
    public Button loginButton;
    public TextField usernameText;

    public void loginPressed(MouseEvent mouseEvent) {
        String userName = usernameText.getText();
        
        if (userName.equals("admin")) {
            System.out.println("administrator is in the building");
        } else {
            System.out.println("hi");
        }
    }
}
