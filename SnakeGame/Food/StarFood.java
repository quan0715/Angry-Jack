package SnakeGame.Food;

import SnakeGame.Effect.StarEffect;
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
  public StarFood(Point p) {
    super(p);
  }
  public StarFood(){
    super();
  }
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/star.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    s.woody++;
    s.RateBuff(1.5);
    s.setEffect(new StarEffect());
    GameFlow WoodyTime = new GameFlow(new KeyFrame(Duration.millis(4000), e -> {
      s.woody--;
      s.RateNuff(1.5);
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
