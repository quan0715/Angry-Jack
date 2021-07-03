package SnakeGame.Controller;

import SnakeGame.App;
import SnakeGame.Client.ioThread;
import SnakeGame.SingletonAndTemplate.Snake;



import java.io.IOException;
import java.net.Socket;

public class GameThreeController{
    public Socket connect;
    public Snake snake1Instance;
    public Snake snake2Instance;
    public void init() {
        try {
            connect = new Socket("127.0.0.1", 8787);
            new ioThread(connect.getInputStream(), connect.getOutputStream(), this);
            //wait scene
        } catch (IOException ioException) {
            ioException.printStackTrace();
            //Connect Fail handling
        }
    }

    public void GetPinName(String pinName) {
    }

    public void startGame() {
        //stop wait scene(Input thread will call this when game start)
    }
}
