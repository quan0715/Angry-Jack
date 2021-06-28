package SnakeGame.SingletonAndTemplate;

import java.util.concurrent.Callable;

import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class SnakeBodyPlayer {
    private GameFlow snakeTimeline;
    private SnakeBody snake=null;
    private int startSpeed;
    private DirectionController directionController;
    private int counter=0;
    private boolean stop=false;
    private Callable<Boolean> shouldStop;
    private Callable<Void> skill=null;
    private int skillAmount=0;
    private int skillCount=0;
    public SnakeBodyPlayer(DirectionController d, int sp, Callable<Boolean> f){
        startSpeed=sp;
        directionController=d;
        shouldStop=f;
        snakeTimeline=new GameFlow(new KeyFrame(Duration.millis(1),e->{
            control();}),-1);
    }
    public void SetSnakeBody(SnakeBody b){
        GameEntityCenter.removeSnakeBody(snake);
        skill=null;
        snake=b;
        snake.m_player=this;
        play();
    }
    public SnakeBody getSnakeBody(){return snake;}
    private void control() {
        if(snake==null)return;
        if(!stop){
            counter++;
            if(counter >= snake.GetSpeed()){
                counter=0;
                snake.Move(directionController.NextDirection());
                if(directionController.GetCanFire()){
                    // interface to fire skill
                    if(skill!=null){
                        skillCount--;
                        try {
                            skill.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(skillCount==0)skill = null;
                    }
                    directionController.setCanFire(false);
                }
            }
            try {
                if(shouldStop.call()){stop();}
            } 
            catch (Exception e) {
                System.out.println("at SnakeBodyPlayer.controll shouldStop==null");
            }
        }
    }
    private void stop() {
        stop=true;
        snakeTimeline.stop();
    }
    private void play() {
        snakeTimeline.play();
        stop=false;
    }
    void setSkill(int count, Callable<Void> m_skill){
        if(m_skill==null){
            skillAmount--;
            if(skillAmount==0)
                skill=null;
            return;
        }
        skill=m_skill;
        skillCount=count;
        skillAmount++;
    }
}
