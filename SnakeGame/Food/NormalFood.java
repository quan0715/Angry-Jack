package SnakeGame.Food;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class NormalFood extends Food{
    private static final long serialVersionUID = 7L;
    public NormalFood(Point p) {
      super(p);
    }
    public NormalFood(){
        super();
    }

    @Override
    protected void FoodInit() {
        image = ResourcesLoader.getImage("img/avocado.png");
        body.setFill(new ImagePattern(image));
    }

    @Override
    protected void OnSnakeHeadTouch(SnakeBody s) {
      s.AddNewBody();
      s.SkillText("JACK","Normal");
      GameFlow text = new GameFlow(new KeyFrame(Duration.millis(2000), e -> {
        s.SkillText(null,null);
      }),1);
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
