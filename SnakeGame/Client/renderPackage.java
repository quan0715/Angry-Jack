package SnakeGame.Client;

import SnakeGame.Enum.Point;
import SnakeGame.SingletonAndTemplate.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class renderPackage implements Serializable {
    public ArrayList<Food> addedFoodList;
    public ArrayList<Integer> removedFoodIdList;
    public ArrayList<Point> SnakeOneList;
    public ArrayList<Point> SnakeTwoList;
    public renderPackage(ArrayList<Food> addedFoodList, ArrayList<Integer> removedFoodIdList, ArrayList<Point> SnakeOneList, ArrayList<Point> SnakeTwoList){
        this.addedFoodList=addedFoodList;
        this.removedFoodIdList=removedFoodIdList;
        this.SnakeOneList=SnakeOneList;
        this.SnakeTwoList=SnakeTwoList;
    }
}
