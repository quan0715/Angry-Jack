package SnakeGame.Food;


import SnakeGame.Controller.HomeController;
import SnakeGame.Effect.IceEffect;
import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Light.Distant;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class IceFood extends Food {
  private static final long serialVersionUID = 5L;
  public IceFood(Point p) {
    super(p);
  }
  public IceFood(){
    super();
  }
  private double SlowDown = 0.5;
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/ice.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    MusicController.EatFoodPop();
    FoodGenerator.RefreshFood();
  }
  @Override
  protected void Cast(SnakeBody s) {
    s.RateBuff(SlowDown);
    s.setEffect(new IceEffect());
    GameFlow Ef = new GameFlow(new KeyFrame(Duration.millis(3000), e -> {
      s.RateNuff(SlowDown);
    }),1);
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    FoodGenerator.RefreshFood();
  }
}
