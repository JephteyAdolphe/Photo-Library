package jFiles.Controllers;

import java.io.FileNotFoundException;

public class refreshPageController {
    String inAlbum;
    int index;

    void chosenAlbum(String chosen) throws FileNotFoundException {
        inAlbum = chosen;   // use this in initialize
    }
}
