package SnakeGame.Snake;

import SnakeGame.Enum.Point;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomizeSnake2 extends Snake {
    private static File ImgSource;
    @Override
    public void InitialSnakeBody(Point position) {
        this.position = position;
        this.body = new Rectangle(position.getX(), position.getY(), SnakeWidth, SnakeWidth);
        //this.image = new Image("/Users/apple/Desktop/python/gmail/image.jpg");
        if (ImgSource == null) this.image = ResourcesLoader.getImage("img/Question.png");
        else {
            try {
                this.image = new Image(ImgSource.toURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        this.body.setFill(new ImagePattern(this.image));
    }
    public static void SetImageSource(File file){
        ImgSource = file;
    }
}
