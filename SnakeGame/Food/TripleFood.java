package SnakeGame.Food;

import SnakeGame.Controller.HomeController;
import SnakeGame.Effect.TripleEffect;
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

public class TripleFood extends Food {
  private static final long serialVersionUID = 9L;
  public TripleFood(Point p) {
    super(p);
  }
  public TripleFood(){
    super();
  }
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/bananas.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    s.setEffect(new TripleEffect());
    GameFlow Add = new GameFlow( new KeyFrame(Duration.millis(500) , e -> {
      s.AddNewBody();
    }),2);
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
