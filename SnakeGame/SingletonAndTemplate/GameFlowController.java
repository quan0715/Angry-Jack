package SnakeGame.SingletonAndTemplate;


import java.util.ArrayList;

public class GameFlowController {
    static private GameFlowController instance = new GameFlowController();
    private GameFlowController(){
        GameFlows=new ArrayList<>();
    }
    private ArrayList<GameFlow> GameFlows;
    public static void GameFlowPause(){
        for(GameFlow flow: instance.GameFlows){
            flow.pause();
        }
    }
    public static void GameFlowPlay(){
        for(GameFlow flow: instance.GameFlows){
            flow.play();
        }
    }
    static void Clear(){
        ArrayList<GameFlow> stopList=new ArrayList<>(instance.GameFlows);
        for(GameFlow flow: stopList){
            flow.stop();
        }
        instance.GameFlows.clear();
    }

    public static void add(GameFlow gameFlow) {
        instance.GameFlows.add(gameFlow);
    }

    public static void remove(GameFlow gameFlow) {
        instance.GameFlows.remove(gameFlow);
        gameFlow.stop();
    }

    public static boolean contain(GameFlow gameFlow) {
        if(instance.GameFlows.contains(gameFlow))return true;
        else return false;
    }
}
