package SnakeGame.Effect;

import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.util.Duration;


public class BombFoodEffect extends SnakeEffect {
    public BombFoodEffect(){
        init();
    }
    @Override
    public void init() {
    }
    @Override
    public void trigger(SnakeBody s) {
        MusicController.EatFoodPop();
        FoodGenerator.RefreshFood();
        s.SkillText("Boom", "Alert");
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(4000), e -> {
            s.SkillText(null,null);
        }),1);
    }
    @Override
    public void Terminate(SnakeBody s) {
        s.setEffect(null);
        s.SkillText(null, null);
    }
}
