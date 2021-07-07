package SnakeGame.SingletonAndTemplate;

import SnakeGame.Controller.HomeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class GameFlow {
    private Timeline flow;
    public GameFlow(KeyFrame keyFrame, int cycleCount){
        flow=new Timeline(keyFrame);
        flow.setCycleCount(cycleCount);
        if (!HomeController.isOnlineGame)
            GameFlowController.add(this);
        flow.play();
        if (!HomeController.isOnlineGame)
            flow.setOnFinished((e)->GameFlowController.remove(this));
    }
    void pause() {
        flow.pause();
    }
    void play() {
        flow.play();
    }
    public void stop() {
        flow.stop();
        if(GameFlowController.contain(this))GameFlowController.remove(this);
    }
}
