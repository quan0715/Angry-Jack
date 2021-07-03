package SnakeGame.Controller;

import SnakeGame.App;
import SnakeGame.Client.ioThread;
import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


import java.io.IOException;
import java.net.Socket;

public class GameThreeController{
    public Socket connect;
    public Snake snake1Instance;
    public Snake snake2Instance;
    @FXML private Label WaitText;
    @FXML private AnchorPane Wait;
    private double opacity = 0;
    private boolean reverse = true;
    public void init() {
        try{
            connect = new Socket("127.0.0.1", 8787);
            new ioThread(connect.getInputStream(), connect.getOutputStream(), this);
            //wait scene
            Wait.setVisible(true);
            WaitText.setOpacity(0.5);
            Timeline label = new Timeline(new KeyFrame(Duration.millis(80),e ->{
                if (reverse){
                    opacity += 0.05;
                }
                else{
                    opacity -= 0.05;
                }
                if (opacity > 1){
                    opacity = 1;
                    reverse = !reverse;
                }
                if (opacity < 0.1){
                    opacity = 0.1;
                    reverse =!reverse;
                }
                WaitText.setOpacity(opacity);
            }));
            label.setCycleCount(-1);
            label.play();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            //Connect Fail handling
        }
    }

    public void GetPinName(String pinName) {
    }

    public void startGame() {
        //stop wait scene(Input thread will call this when game start)
        Wait.setVisible(false);

    }
}
