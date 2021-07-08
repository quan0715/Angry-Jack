package SnakeGame.Effect;

import SnakeGame.Controller.HomeController;
import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.MusicController;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class JackEffect extends SnakeEffect {
    public JackEffect(){
        init();
    }
    @Override
    public void init() {
        this.distant = new Light.Distant(45, 45, Color.web("#575757"));
        this.MainLight = new Lighting();
        this.MainLight.setLight(this.distant);
        this.MainLight.setSpecularConstant(0.0);
        this.MainLight.setSurfaceScale(0.0);
        this.MainLight.setDiffuseConstant(0.4);
        this.Times = 3000;
        this.Buff = 2;
        this.Cycle = 1;
    }
    @Override
    public void trigger(SnakeBody s) {
        MusicController.EatFoodPop();
        s.SkillText("Frozen", "Ice");
        s.SnakeEffect(this.MainLight);
        if (HomeController.ThemeColor) s.SkillText("Angry!", "LightNormal");
        else s.SkillText("Angry!", "DarkNormal");
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(this.Times), e -> {
            s.SkillText(null, "");
            s.SnakeEffect(null);
        }),this.Cycle);
    }

    @Override
    public void Terminate(SnakeBody s) {
        EffectControl.stop();
        s.SkillText(null, "");
        s.SnakeEffect(null);
    }
}
