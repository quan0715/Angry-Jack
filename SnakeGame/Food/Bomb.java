package SnakeGame.Food;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Light.Distant;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class Bomb extends Food {
  private static final long serialVersionUID = 1L;
  private Distant lightW;
  private Lighting l;
  private GameFlow ef;
  private int count = 0;
  private int speed;
  private boomPlayer m_boomPlayer;
  private double duration;
  private int lifeCounter;
  public Bomb(Point p,double duration) {
    super(p);
    this.duration=duration;
    speed=(int)(50*(540+duration)/540);
  }
  public Bomb(){
    super();
  }
  @Override
  protected void FoodInit() {
    lightW = new Distant(45,45,Color.WHITE);
    image = ResourcesLoader.getImage("img/TNT.png");
    body.setFill(new ImagePattern(image));
    count = 0;
    l = new Lighting(lightW);
    l.setSurfaceScale(0.0);
    l.setSpecularExponent(0.0);
    l.setSpecularConstant(2.0);
    l.setDiffuseConstant(2.0);
    body.setEffect(l);
    m_boomPlayer=MusicController.newboom();
    ef = new GameFlow(new KeyFrame(Duration.millis(1),e ->{
      if(!GameEntityCenter.contain(this)){
        m_boomPlayer.stop();
        ef.stop();
      }
      count++;
      lifeCounter++;
      if(lifeCounter>=duration-4000)m_boomPlayer.preboom();
      if(count>=speed*0.4||count>=200)l.setSpecularExponent(40);
      if(count >= speed && lifeCounter <= duration){
        l.setSpecularExponent(0);
        speed*=0.9;
        if(speed<=60) speed = 60;
        count = 0;
      }
      if(lifeCounter>=duration){
        ef.stop();
        m_boomPlayer.boom();
      }
    }),-1);
  }
  
  @Override
  protected void Cast(SnakeBody s) {
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody b) {
    OnSnakeBodyTouch(b);
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
   m_boomPlayer.boom();
    for(int i=0;i<3;i++){
      s.RemoveBody();
    }
  }
}
