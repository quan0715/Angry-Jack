package SnakeGame.Effect;
import SnakeGame.SingletonAndTemplate.GameFlow;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class CancelEffect extends SnakeEffect {
    public CancelEffect(int Times,int Cycle){
        init();
        this.Times = Times;
        this.Cycle = Cycle;
    }
    @Override
    public void init() {
        this.distant =  null;
        this.MainLight = null;
    }
    @Override
    public void trigger(SnakeBody s) {
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(this.Times),e ->{
            s.SnakeEffect(null);
            s.setSkill(0, null);
            s.SkillText(null, "");
        }),this.Cycle);
    }

    @Override
    public void Terminate(SnakeBody s) {
        EffectControl.stop();
        s.SnakeEffect(null);
        s.setSkill(0, null);
        s.SkillText(null, "");
    }
}
