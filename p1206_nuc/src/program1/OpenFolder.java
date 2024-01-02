package program1;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenFolder {
    public static void openFolder(){
        Desktop desktop = Desktop.getDesktop();
        File dirToOpen;
        try {
            dirToOpen = new File("./notes");
            desktop.open(dirToOpen);
        } catch (IllegalArgumentException | IOException iae) {
            System.out.println("File Not Found");
        }
    }

}