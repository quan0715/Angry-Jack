package SnakeGame.SingletonAndTemplate;

import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;

public abstract class SnakeEffect {
    protected Distant distant;
    protected Distant distant2;
    protected Distant distant3;
    protected Lighting MainLight;
    protected int spark;
    protected int Times;
    protected double Buff ;
    protected GameFlow EffectControl;
    protected GameFlow EffectControl2;
    protected GameFlow EffectControl3;
    protected int Cycle;
    protected abstract void init();
    protected abstract void trigger(SnakeBody s);
}
