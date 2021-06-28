package SnakeGame.Food;

import java.util.concurrent.Callable;

import SnakeGame.Enum.Direction;
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

public class FireFood extends Food {
  private Distant lightO;
  private Lighting l;
  private double spark = 0;
  public FireFood(Point p) {
    super(p);
  }

  @Override
  protected void FoodInit() {
    lightO = new Distant(45, 25, Color.web("#ff9700"));
    l = new Lighting();
    l.setSurfaceScale(0.0);
    l.setSpecularExponent(0.0);
    l.setSpecularConstant(2.0);
    l.setDiffuseConstant(2.0);
    image = ResourcesLoader.getImage("img/fire.png");
    body.setFill(new ImagePattern(image));
  }

  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    s.SkillText("On Fire!", "Fire");
    FoodGenerator.RefreshFood();
    MusicController.EatFoodPop();
    s.setSkill(-1, new Callable<Void>(){
      @Override
      public Void call() throws Exception {
        Direction d = s.GetDirection();
        Point p = s.GetHead();
        int sp = s.GetSpeed();
        FoodGenerator.NewBullet(d, p, sp);
        return null;
      }
    });
    GameFlow cancelFlow=new GameFlow(new KeyFrame(Duration.millis(5000), e->{
      s.SnakeEffect(null);
      s.setSkill(0, null);
      s.SkillText(null, "");
    }),1);
    spark = 25;
    s.SnakeEffect(l);
    GameFlow SparkFlow = new GameFlow(new KeyFrame(Duration.millis(1), e -> {
      lightO = new Distant(45, spark, Color.web("#ff9700"));
      l.setLight(lightO);
      spark = (spark - 25 + 0.075) % 100 + 25;
    }),5000);
  }
  @Override
  protected void Cast(SnakeBody s) {  
    
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    FoodGenerator.RefreshFood();
  }
}
