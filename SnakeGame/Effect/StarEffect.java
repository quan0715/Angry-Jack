package SnakeGame.Effect;

import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.MusicController;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class StarEffect extends SnakeEffect {
    public StarEffect(){
        init();
    }
    @Override
    public void init() {
        distant = new Distant(45, 45, Color.web("#ffee00"));
        distant2 = new Distant(45, 45, Color.web("#0fff6e"));
        distant3 = new Distant(45, 45, Color.web("#0fc2ff"));
        MainLight = new Lighting();
        MainLight.setSpecularConstant(1.5);
        MainLight.setDiffuseConstant(1.5);
        MainLight.setSurfaceScale(0.0);
        Buff = 1.5;
        Times = 50;
        Cycle = 80;
        spark = 0;
    }
    @Override
    public void trigger(SnakeBody s) {
        s.SnakeEffect(MainLight);
        s.SkillText("SUPER", "Star");
        MusicController.SuperStarFood(true);
        EffectControl = new GameFlow( new KeyFrame(Duration.millis(Times),e ->{
            switch(spark){
                case 0:
                    MainLight.setLight(distant);
                    break;
                case 1:
                    MainLight.setLight(distant2);
                    break;
                case 2:
                    MainLight.setLight(distant3);
                    break;
                case 3:
                    MainLight.setLight(null);
                    break;
            }
            spark = (spark+1) % 4;
        }),Cycle);
        EffectControl2 = new GameFlow(new KeyFrame(Duration.millis(Times * Cycle), e -> {
            s.SnakeEffect(null);
            MusicController.SuperStarFood(false);
            s.SkillText(null,"");
        }),1);
    }
    @Override
    public void Terminate(SnakeBody s) {
        EffectControl.stop();
        EffectControl2.stop();
        s.SnakeEffect(null);
        MusicController.SuperStarFood(false);
        s.SkillText(null,"");
    }
}
