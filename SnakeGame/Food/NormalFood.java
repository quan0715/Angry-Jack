package SnakeGame.Food;

import SnakeGame.Effect.NormalEffect;
import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class NormalFood extends Food{
    private static final long serialVersionUID = 7L;
    private NormalEffect Effect ;
    public NormalFood(Point p) {
      super(p);
    }
    public NormalFood(){
        super();
    }

    @Override
    protected void FoodInit() {
        Effect = new NormalEffect();
        image = ResourcesLoader.getImage("img/avocado.png");
        body.setFill(new ImagePattern(image));
    }

    @Override
    protected void OnSnakeHeadTouch(SnakeBody s) {
      s.AddNewBody();
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
