package SnakeGame.Snake;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class VscodeSnake extends Snake{
    @Override
    public void InitialSnakeBody(Point position) {
    this.position = position;
    this.body = new Rectangle(position.getX(), position.getY(), SnakeWidth, SnakeWidth);
        this.image = ResourcesLoader.getImage("img/vscode.png");
    this.body.setFill(new ImagePattern(image));
  }
}
