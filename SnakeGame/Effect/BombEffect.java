package SnakeGame.Effect;

import SnakeGame.Controller.HomeController;
import SnakeGame.Food.Bomb;
import SnakeGame.SingletonAndTemplate.*;
import javafx.animation.KeyFrame;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class BombEffect extends SnakeEffect {
    private boomPlayer m_boomPlayer;
    private int lifeCounter;
    private int speed;
    private Bomb bomb;
    public BombEffect(Bomb bomb){
        this.bomb = bomb;
        speed=(int)(50*(540+FoodGenerator.BombDuration)/540);
        init();
    }
    @Override
    public void init() {
        distant = new Light.Distant(45,45, Color.WHITE);
        spark = 0;
        MainLight = new Lighting(distant);
        bomb.setEffect(MainLight);
        MainLight.setSurfaceScale(0.0);
        MainLight.setSpecularExponent(0.0);
        MainLight.setSpecularConstant(2.0);
        MainLight.setDiffuseConstant(2.0);
        m_boomPlayer=MusicController.newboom();
        Times = 1;
        Cycle = -1;
        spark = 0;
        lifeCounter = 0;
        EffectControl = new GameFlow(new KeyFrame(Duration.millis(Times), e ->{
            if(!GameEntityCenter.contain(bomb)){
                m_boomPlayer.stop();
                EffectControl.stop();
            }
            spark++;
            lifeCounter++;
            if(lifeCounter>=FoodGenerator.BombDuration-4000)m_boomPlayer.preboom();
            if(spark>=speed*0.4||spark>=200)MainLight.setSpecularExponent(40);
            if(spark >= speed && lifeCounter <= FoodGenerator.BombDuration){
                MainLight.setSpecularExponent(0);
                speed*=0.9;
                if(speed<=60) speed = 60;
                spark = 0;
            }
            if(lifeCounter>=FoodGenerator.BombDuration){
                EffectControl.stop();
                m_boomPlayer.boom();
            }
        }),Cycle);
    }
    @Override
    public void trigger(SnakeBody s) {
        m_boomPlayer.boom();
    }
    public void ThemeEffect(Rectangle body){
        if(HomeController.ThemeColor) body.setEffect(this.MainLight);
    }
}
