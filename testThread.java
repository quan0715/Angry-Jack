import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class testThread extends Thread {
    BufferedReader reader;
    Scene scene;
    ObjectOutputStream outputStream;
    public testThread(BufferedReader reader, ObjectOutputStream outputStream, Scene scene) {
        this.reader=reader;
        this.scene=scene;
        this.outputStream=outputStream;
        start();
    }

    public void run(){
        try {
            System.out.println(reader.readLine());
            scene.setOnKeyPressed(keyEvent -> {
                try {
                    outputStream.writeObject(keyEvent.getCode());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
