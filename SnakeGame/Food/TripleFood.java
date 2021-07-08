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

public class TripleFood extends Food {
  private static final long serialVersionUID = 9L;
  private int Case = 0;
  private Distant lightW;
  private Lighting l;
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
    lightW = new Distant(45, 45, Color.WHITE);
    l = new Lighting(lightW);
    l.setSurfaceScale(0.0);
    l.setSpecularExponent(40.0);
    l.setSpecularConstant(2.0);
    l.setDiffuseConstant(2.0);
    Case = 0;
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody s) {
    s.AddNewBody();
    if (HomeController.ThemeColor) s.SkillText("BIG", "LightNormal");
    else s.SkillText("BIG", "DarkNormal");
    MusicController.GrowingUp();
    GameFlow ef = new GameFlow(new KeyFrame(Duration.millis(180), e -> {
      if(Case == 0) s.clearOnScreen();
      else s.showOnScreen();
        Case = (Case + 1) % 2;
    }),-1);
    GameFlow add = new GameFlow( new KeyFrame(Duration.millis(500) , e -> {
      s.AddNewBody();
    }),2);
    GameFlow text = new GameFlow(new KeyFrame(Duration.millis(1500), e -> {
      s.SkillText(null, null);
      ef.stop();
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
