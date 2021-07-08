package SnakeGame.Effect;

import SnakeGame.Controller.HomeController;
import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.MusicController;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TripleEffect extends SnakeEffect {
    public TripleEffect(){
        init();
    }
    @Override
    public void init() {
        distant = new Distant(45, 45, Color.WHITE);
        MainLight = new Lighting(distant);
        MainLight.setSurfaceScale(0.0);
        MainLight.setSpecularExponent(40.0);
        MainLight.setSpecularConstant(2.0);
        MainLight.setDiffuseConstant(2.0);
        Times = 80;
        Cycle = -1;
        spark = 0;
    }
    @Override
    public void trigger(SnakeBody s) {
        if (HomeController.ThemeColor) s.SkillText("BIG", "LightNormal");
        else s.SkillText("BIG", "DarkNormal");
        s.SnakeEffect(MainLight);
        MusicController.GrowingUp();
        EffectControl = new GameFlow( new KeyFrame(Duration.millis(Times),e ->{
            if(spark == 0) s.clearOnScreen();
            else s.showOnScreen();
            spark = (spark + 1) % 2;
        }),Cycle);
        EffectControl2 = new GameFlow(new KeyFrame(Duration.millis(1000), e -> {
            s.SkillText(null, null);
            EffectControl.stop();
        }),1);
    }
}
