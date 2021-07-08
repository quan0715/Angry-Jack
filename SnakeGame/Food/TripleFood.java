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
  private TripleEffect Effect ;
  public TripleFood(Point p) {
    super(p);
  }
  public TripleFood(){
    super();
  }
  @Override
  protected void FoodInit() {
    Effect= new TripleEffect();
    image = ResourcesLoader.getImage("img/bananas.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    if (HomeController.ThemeColor) s.SkillText("BIG", "LightNormal");
    else s.SkillText("BIG", "DarkNormal");
    MusicController.GrowingUp();
    Effect.trigger(s);
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
