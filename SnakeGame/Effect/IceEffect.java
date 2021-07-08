package SnakeGame.Effect;

import SnakeGame.Controller.HomeController;
import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class IceEffect extends SnakeEffect {
    public IceEffect(){
        init();
    }
    @Override
    public void init() {
        this.distant = new Light.Distant(45, 45, Color.web("#009aff"));
        this.MainLight = new Lighting();
        this.MainLight.setLight(this.distant);
        this.MainLight.setSurfaceScale(0.0);
        this.MainLight.setSpecularExponent(0.0);
        this.MainLight.setSpecularConstant(2.0);
        this.MainLight.setDiffuseConstant(2.0);
        this.Times = 3000;
        this.Buff = 0.5;
        this.Cycle = -1;
    }
    @Override
    public void trigger(SnakeBody s) {
        s.SnakeEffect(this.MainLight);
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(this.Times), e -> {
            s.SnakeEffect(null);
            s.SkillText(null, "");
        }),this.Cycle);
    }
    public void ThemeEffect(Rectangle body){
        if(HomeController.ThemeColor) body.setEffect(this.MainLight);
    }
}
