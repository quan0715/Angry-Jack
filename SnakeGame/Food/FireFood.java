package SnakeGame.Food;

import java.util.concurrent.Callable;

import SnakeGame.Effect.CancelEffect;
import SnakeGame.Effect.FireEffect;
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
  private static final long serialVersionUID = 4L;
  private FireEffect Effect;
  private CancelEffect Cancel;
  public FireFood(Point p) {
    super(p);
  }
  public FireFood(){
    super();
  }
  @Override
  protected void FoodInit() {
    Effect = new FireEffect();
    Cancel = new CancelEffect(5000,1);
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
    Effect.trigger(s);
    Cancel.trigger(s);
  }
  @Override
  protected void Cast(SnakeBody s) {  
    
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    FoodGenerator.RefreshFood();
  }
}
