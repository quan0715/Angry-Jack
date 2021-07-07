package SnakeGame.Effect;

import SnakeGame.SingletonAndTemplate.GameFlow;
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
        s.RateBuff(this.Buff);
        s.SnakeEffect(this.MainLight);
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(this.Times), e -> {
            s.RateNuff(this.Buff);
            s.SnakeEffect(null);
            s.SkillText(null, "");
        }),this.Cycle);
    }
}
