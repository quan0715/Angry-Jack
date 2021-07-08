package SnakeGame.Food;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Light.Distant;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class StarFood extends Food {
  private static final long serialVersionUID = 8L;
  private Distant lightY;
  private Distant lightG;
  private Distant lightB;
  private Lighting l;
  private int spark = 0;
  private double SpeedUp = 1.5;
  public StarFood(Point p) {
    super(p);
  }
  public StarFood(){
    super();
  }
  @Override
  protected void FoodInit() {
    lightY = new Distant(45, 45, Color.web("#ffee00"));
    lightG = new Distant(45, 45, Color.web("#0fff6e"));
    lightB = new Distant(45, 45, Color.web("#0fc2ff"));
    l = new Lighting();
    l.setSpecularConstant(1.5);
    l.setDiffuseConstant(1.5);
    l.setSurfaceScale(0.0);
    image = ResourcesLoader.getImage("img/star.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    s.RateBuff(SpeedUp);
    s.woody++;
    s.SkillText("SUPER", "Star");
    s.SnakeEffect(l);
    GameFlow SparkTimeline = new GameFlow( new KeyFrame(Duration.millis(50),e ->{
      switch(spark){
        case 0:
          l.setLight(lightY);
          break;
        case 1:
          l.setLight(lightB);
          break;
        case 2:
          l.setLight(lightG);
          break;
        case 3:
          l.setLight(null);
          break;
      }
      spark = (spark+1) % 4;
    }),80);
    GameFlow speedup = new GameFlow(new KeyFrame(Duration.millis(4000), e -> {
      s.SnakeEffect(null);
      s.RateNuff(SpeedUp);
      MusicController.SuperStarFood(false);
      s.SkillText(null,"");
      s.woody--;
    }),1);
    MusicController.SuperStarFood(true);
    FoodGenerator.RefreshFood();
  }
  @Override
  protected void Cast(SnakeBody s) {  
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    FoodGenerator.RefreshFood();
  }
}
