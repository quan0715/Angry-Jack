package SnakeGame.Controller;

import SnakeGame.App;
import SnakeGame.Client.InputThread;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class GameThreeController implements Initializable {
    public Socket connect;
    public Snake snake1Instance;
    public Snake snake2Instance;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connect = new Socket("127.0.0.1", 8787);
            new InputThread(connect.getInputStream(), connect.getOutputStream(), App.stage.getScene(), this);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void GetPinName(String pinName) {
    }
}
