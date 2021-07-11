package SnakeGame.SingletonAndTemplate;

import java.io.Serializable;
import java.util.List;

import Server.Components.GameCenter;
import SnakeGame.Controller.HomeController;
import SnakeGame.Enum.Point;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Food implements Serializable {
  private static final long serialVersionUID = 123456789L;
  public int id;
  transient GameCenter m_gameCenter;
  protected transient Image image;
  protected Point FoodPosition;
  protected transient Rectangle body;
  private FoodEvent m_event;
  protected abstract void FoodInit();
  protected abstract void OnSnakeHeadTouch(SnakeBody b);
  protected abstract void OnSnakeBodyTouch(SnakeBody s);
  protected abstract void Cast(SnakeBody s);
  protected Food(){
    body = new Rectangle(Point.GridWidth, Point.GridWidth);
    FoodInit();
    GameEntityCenter.addFood(this);
  }
  protected Food(Point p){
    FoodPosition = p;
    body = new Rectangle(FoodPosition.getX(), FoodPosition.getY(), Point.GridWidth, Point.GridWidth);
    FoodInit();
    GameEntityCenter.addFood(this);
    if(HomeController.isOnlineGame)return;
    m_event=new FoodEvent(
      this,
      (b)->{OnSnakeHeadTouch(b);},
      (b)->{OnSnakeBodyTouch(b);},
      (b)->{Cast(b);});
  }
  protected Food(Point p, GameCenter gameCenter){}
  public void ChangeFoodPosition(Point point){
    FoodPosition = point;
    body.setX(point.getX());
    body.setY(point.getY());
  }
  public void ChangeFoodPosition(Point point,int id){
    this.id=id;
    ChangeFoodPosition(point);
  }
  public Point GetFoodPosition(){
    return FoodPosition;
  }
  public void showOnScreen() {
    List<Node> children=GameCurrentChildrenArray.Instance.get();
    if(children!=null&&!children.contains(body))
      children.add(body);
  }
  public void clearOnScreen() {
    GameCurrentChildrenArray.Instance.get().remove(body);
  }
  public FoodEvent getEvent() {
    return m_event;
  }
}
