package SnakeGame.SingletonAndTemplate;

import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;

public abstract class SnakeEffect {
    protected Distant distant;
    protected Lighting MainLight;
    protected int Times;
    protected double Buff ;
    protected GameFlow EffectControl;
    protected int Cycle;
    protected abstract void init();
    protected abstract void trigger(SnakeBody s);
}
