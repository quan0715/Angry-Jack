package SnakeGame.Food;


import SnakeGame.Controller.HomeController;
import SnakeGame.Effect.CancelEffect;
import SnakeGame.Effect.JackEffect;
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

public class JackFood extends Food {
  private static final long serialVersionUID = 6L;
  public JackFood(Point p) {
    super(p);
  }
  public JackFood(){
    super();
  }
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/Jack.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.RateBuff(2);
    //Use sample
    s.setEffect(new JackEffect());
    GameFlow Ef = new GameFlow(new KeyFrame(Duration.millis(3000), e -> {
      s.RateNuff(2);
    }),1);
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
