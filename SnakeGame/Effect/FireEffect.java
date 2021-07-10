package SnakeGame.Effect;
import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.MusicController;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FireEffect extends SnakeEffect {
    private double spark = 0;
    public FireEffect(){
        init();
    }
    @Override
    public void init() {
        this.distant =  new Light.Distant(45, 25, Color.web("#ff9700"));
        this.MainLight = new Lighting();
        this.MainLight.setLight(this.distant);
        this.MainLight.setSurfaceScale(0.0);
        this.MainLight.setSpecularExponent(0.0);
        this.MainLight.setSpecularConstant(2.0);
        this.MainLight.setDiffuseConstant(2.0);
        this.Times = 1;
        this.Cycle = 5000;
        this.Buff = 0.5;
        this.spark = 25;
    }
    @Override
    public void trigger(SnakeBody s) {
        s.SnakeEffect(MainLight);
        s.SkillText("On Fire!", "Fire");
        MusicController.EatFoodPop();
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(this.Times), e -> {
            distant = new Light.Distant(45, spark, Color.web("#ff9700"));
            MainLight.setLight(distant);
            spark = (spark - 25 + 0.075) % 100 + 25;
        }),this.Cycle);
        EffectControl2 = new GameFlow(new KeyFrame(Duration.millis(Times*Cycle), e -> {
            s.SnakeEffect(null);
            s.SkillText(null, "");
        }),1);
    }

    @Override
    public void Terminate(SnakeBody s) {
        EffectControl.stop();
        EffectControl2.stop();
        s.SnakeEffect(null);
        s.SkillText(null, "");
    }
}
