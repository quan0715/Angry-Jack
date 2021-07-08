package SnakeGame.Effect;

import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class NormalEffect extends SnakeEffect {
    public NormalEffect(){
        init();
    }
    @Override
    public void init() {
        Times = 2000;
        Cycle = 1;
    }
    @Override
    public void trigger(SnakeBody s) {
        s.SkillText("JACK","Normal");
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(Times), e -> {
            s.SkillText(null,null);
        }),Cycle);
        MusicController.EatFoodPop();
    }
}
