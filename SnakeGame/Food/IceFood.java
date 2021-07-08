package SnakeGame.Food;


import SnakeGame.Controller.HomeController;
import SnakeGame.Effect.IceEffect;
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

public class IceFood extends Food {
  private static final long serialVersionUID = 5L;
  public IceFood(Point p) {
    super(p);
  }
  public IceFood(){
    super();
  }
  private double SlowDown = 0.5;
  private Distant light ;
  private Lighting l;
  private IceEffect Effect;
  @Override
  protected void FoodInit() {
    Effect= new IceEffect();
    image = ResourcesLoader.getImage("img/ice.png");
    body.setFill(new ImagePattern(image));
    Effect.ThemeEffect(body);
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    MusicController.EatFoodPop();
    FoodGenerator.RefreshFood();
  }
  @Override
  protected void Cast(SnakeBody s) {
    s.SkillText("Frozen", "Ice");
    Effect.trigger(s);
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    FoodGenerator.RefreshFood();
  }
}
