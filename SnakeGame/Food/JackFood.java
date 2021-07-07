package SnakeGame.Food;


import SnakeGame.Controller.HomeController;
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
  public JackFood(Point p) {
    super(p);
  }
  public JackFood(){}
  private JackEffect Jack;
  @Override
  protected void FoodInit() {
    Jack = new JackEffect();
    image = ResourcesLoader.getImage("img/Jack.png");
    body.setFill(new ImagePattern(image));
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    if (HomeController.ThemeColor) s.SkillText("Angry!", "LightNormal");
    else s.SkillText("Angry!", "DarkNormal");
    Jack.trigger(s);
    MusicController.EatFoodPop();
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
