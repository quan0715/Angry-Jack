package SnakeGame.Food;


import SnakeGame.Controller.HomeController;
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
  public IceFood(Point p) {
    super(p);
  }
  private double SlowDown = 0.5;
  private Distant light ;
  private Lighting l;
  @Override
  protected void FoodInit() {
    light = new Distant(45, 45, Color.web("#009aff"));
    l = new Lighting();
    l.setLight(light);
    l.setSurfaceScale(0.0);
    l.setSpecularExponent(0.0);
    l.setSpecularConstant(2.0);
    l.setDiffuseConstant(2.0);
    image = ResourcesLoader.getImage("img/ice.png");

    body.setFill(new ImagePattern(image));
    if(HomeController.ThemeColor)body.setEffect(l);
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
    s.SnakeEffect(l);
    s.SkillText("Frozen", "Ice");
    GameFlow slowdown = new GameFlow(new KeyFrame(Duration.millis(3000), e -> {
      s.RateNuff(SlowDown);
      s.SnakeEffect(null);
      s.SkillText(null, "");

    }),1);
  }

  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    FoodGenerator.RefreshFood();
  }
}
