package SnakeGame.Food;

import java.util.concurrent.Callable;

import SnakeGame.Effect.BombFoodEffect;
import SnakeGame.Effect.CancelEffect;
import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BombFood extends Food {
    private static final long serialVersionUID = 2L;
    private BombFoodEffect Effect;
    private CancelEffect Cancel;
    public BombFood(Point p) {
      super(p);
    }
    public BombFood(){
        super();
    }
    @Override
    protected void FoodInit() {
        Effect = new BombFoodEffect();
        Cancel = new CancelEffect(5000,1);
        image = ResourcesLoader.getImage("img/bomb.png");
        body.setFill(new ImagePattern(image));
    }
    @Override
    protected void Cast(SnakeBody s) {
    }
    @Override
    protected void OnSnakeHeadTouch(SnakeBody s) {
        s.AddNewBody();
        s.setSkill(3, new Callable<Void>(){
            @Override
            public Void call() throws Exception {
                FoodGenerator.NewBomb(s);
                return null;
            }
        });
        Effect.trigger(s);
        Cancel.trigger(s);
    }
    @Override
    protected void OnSnakeBodyTouch(SnakeBody s) {
      FoodGenerator.RefreshFood();
    }
  }
