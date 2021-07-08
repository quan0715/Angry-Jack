package SnakeGame.Food;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.Food;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class FireBullet extends Food {
  private static final long serialVersionUID = 3L;
  public FireBullet(Point p) {
    super(p);
  }
  public FireBullet(){
    super();
  }
  @Override
  protected void FoodInit() {
    image = ResourcesLoader.getImage("img/FireBullet.png");
    body.setFill(new ImagePattern(image));
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
    s.RemoveBody();
  }
}
