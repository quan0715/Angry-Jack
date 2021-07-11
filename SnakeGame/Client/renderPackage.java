package SnakeGame.Client;

import SnakeGame.Enum.Point;
import SnakeGame.SingletonAndTemplate.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class renderPackage implements Serializable {
    public ArrayList<Food> addedFoodList;
    public ArrayList<Integer> removedFoodIndexList;
    public ArrayList<Point> SnakeOneList;
    public ArrayList<Point> SnakeTwoList;
    public renderPackage(ArrayList<Food> addedFoodList, ArrayList<Integer> removedFoodIndexList, ArrayList<Point> SnakeOneList, ArrayList<Point> SnakeTwoList){
        this.addedFoodList=addedFoodList;
        this.removedFoodIndexList=removedFoodIndexList;
        this.SnakeOneList=SnakeOneList;
        this.SnakeTwoList=SnakeTwoList;
    }
}
