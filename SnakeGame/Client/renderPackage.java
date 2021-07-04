package SnakeGame.Client;

import SnakeGame.Enum.Point;
import SnakeGame.SingletonAndTemplate.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class renderPackage implements Serializable {
    public ArrayList<Food> foodList;
    public ArrayList<Point> SnakeOneList;
    public ArrayList<Point> SnakeTwoList;
    public renderPackage(ArrayList<Food> foodList, ArrayList<Point> SnakeOneList, ArrayList<Point> SnakeTwoList){
        this.foodList=foodList;
        this.SnakeOneList=SnakeOneList;
        this.SnakeTwoList=SnakeTwoList;
    }
}
