package SnakeGame.Effect;

import SnakeGame.SingletonAndTemplate.*;


public class BombFoodEffect extends SnakeEffect {
    public BombFoodEffect(){
        init();
    }
    @Override
    public void init() {
    }
    @Override
    public void trigger(SnakeBody s) {
        MusicController.EatFoodPop();
        FoodGenerator.RefreshFood();
        s.SkillText("Boom", "Alert");
    }
}
