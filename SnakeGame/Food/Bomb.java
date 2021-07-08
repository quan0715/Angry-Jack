package SnakeGame.Food;

import SnakeGame.Effect.BombEffect;
import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.scene.paint.ImagePattern;

public class Bomb extends Food {
  private static final long serialVersionUID = 1L;
  public double duration;

  private BombEffect Effect;
  public Bomb(Point p,double duration) {
    super(p);
    this.duration=duration;
  }
  public Bomb(){
    super();
  }
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/TNT.png");
    body.setFill(new ImagePattern(image));
    Effect = new BombEffect(this,body);
    Effect.ThemeEffect(body);
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
    Effect.trigger(s);
    for(int i=0;i<3;i++){
      s.RemoveBody();
    }
  }
}
