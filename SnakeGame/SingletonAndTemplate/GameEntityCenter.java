package SnakeGame.SingletonAndTemplate;

import java.util.ArrayList;
import java.util.List;

public class GameEntityCenter {
    static GameEntityCenter instance=new GameEntityCenter();
    private GameEntityCenter(){};
    public List<SnakeBody> snakes=new ArrayList<>();
    public List<Food> foods=new ArrayList<>();
    public static boolean contain(SnakeBody s){return instance.snakes.contains(s);}
    public static boolean contain(Food f){return instance.foods.contains(f);}
    static List<SnakeBody> GetOtherSnakes(SnakeBody s){
        List<SnakeBody> returnedList=new ArrayList<>();
        for (SnakeBody b : instance.snakes){
            if(b!=s){
                returnedList.add(b);
            }
        }
        return returnedList;
    }
    static boolean addSnakeBody(SnakeBody s){
        if(instance.snakes.contains(s))return false;
        else try {
            instance.snakes.add(s);
            s.showOnScreen();
            return true;
        }
        catch(Exception e){e.printStackTrace();return false;}
    }
    static boolean removeSnakeBody(SnakeBody s){
        if(!instance.snakes.contains(s))return false;
        else try {
            s.clearOnScreen();
            instance.snakes.remove(s);
            return true;
        }
        catch(Exception e){e.printStackTrace();return false;}
    }
    static boolean addFood(Food f){
        if(instance.foods.contains(f))return false;
        else try {
            instance.foods.add(f);
            f.showOnScreen();
            return true;
        }
        catch(Exception e){e.printStackTrace();return false;}
    }
    static boolean removeFood(Food f){
        if(!instance.foods.contains(f))return false;
        else try {
           instance.foods.remove(f);
           f.clearOnScreen();
           FoodEventCenter.removeFoodEvent(f.getEvent());
           return true;
        }
        catch(Exception e){e.printStackTrace();return false;}
    }
    static void clearAll(){
        List<SnakeBody> RemoveListSnake = new ArrayList<>(instance.snakes);
        List<Food> RemoveListFood = new ArrayList<>(instance.foods);
        for (SnakeBody b : RemoveListSnake) removeSnakeBody(b);
        for (Food f : RemoveListFood)  removeFood(f);
        FoodEventCenter.clear();
        GameFlowController.Clear();
    }
    public static void clearFood(ArrayList<Integer> removedFoodIdList){
        if(removedFoodIdList==null)return;
        List<Food> RemoveListFood = new ArrayList<>(instance.foods);
        for (Food f : RemoveListFood)
            if(removedFoodIdList.contains(f.id))
                removeFood(f);
    }
}
