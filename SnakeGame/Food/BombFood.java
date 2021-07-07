package SnakeGame.Food;

import java.util.concurrent.Callable;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BombFood extends Food {
    public BombFood(Point p) {
      super(p);
    }
    public BombFood(){}
    @Override
    protected void FoodInit() {
        image = ResourcesLoader.getImage("img/bomb.png");
        body.setFill(new ImagePattern(image));
    }
  
    @Override
    protected void Cast(SnakeBody s) {
    }
    @Override
    protected void OnSnakeHeadTouch(SnakeBody s) {
        s.AddNewBody();
        MusicController.EatFoodPop();
        FoodGenerator.RefreshFood();
        s.SkillText("Boom", "Alert");
        s.setSkill(3, new Callable<Void>(){
            @Override
            public Void call() throws Exception {
                FoodGenerator.NewBomb(s);
                return null;
            }
        });
        GameFlow cancelFlow=new GameFlow(new KeyFrame(Duration.millis(5000), e->{
            s.SnakeEffect(null);
            s.setSkill(0, null);
            s.SkillText(null, null);
        }),1);
    }
    @Override
    protected void OnSnakeBodyTouch(SnakeBody s) {
      FoodGenerator.RefreshFood();
    }
  }
