package SnakeGame.Food;

import SnakeGame.Effect.BombEffect;
import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.paint.ImagePattern;

public class Bomb extends Food {
  private static final long serialVersionUID = 1L;
  private BombEffect effect;
  public Bomb(Point p) {
    super(p);
  }
  public Bomb(){
    super();
  }
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/TNT.png");
    body.setFill(new ImagePattern(image));
    effect = new BombEffect(this);
  }
  @Override
  protected void Cast(SnakeBody s) {
  }
  @Override
  protected void OnSnakeHeadTouch(SnakeBody b) {
    OnSnakeBodyTouch(b);
  }
  @Override
  protected void OnSnakeBodyTouch(SnakeBody s) {
    effect.trigger(s);
    for(int i=0;i<3;i++){
      s.RemoveBody();
    }
  }
  public void setEffect(Effect effect){
    this.body.setEffect(effect);
  }
}
