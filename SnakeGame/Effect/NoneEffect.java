package SnakeGame.Effect;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakeEffect;
public class NoneEffect extends SnakeEffect {
    public NoneEffect(int Times,int Cycle){
        init();
    }
    @Override
    public void init() {
        this.distant =  null;
        this.MainLight = null;
    }
    @Override
    public void trigger(SnakeBody s) {

    }
}
