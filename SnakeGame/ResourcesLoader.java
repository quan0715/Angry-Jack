package SnakeGame;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResourcesLoader {
    static ResourcesLoader instance=new ResourcesLoader();
    private ResourcesLoader(){
        File Score = new File("RecordScore.txt");
        if(!Score.exists()) {
            try {
                Score.createNewFile();
                FileWriter ScoreWriter = new FileWriter(Score);
                ScoreWriter.write("0");
                ScoreWriter.flush();
                ScoreWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public static FXMLLoader getFXMLLoader(String route){
        return new FXMLLoader(instance.getClass().getResource(route));
    }
    public static Image getImage(String route){
        return new Image(instance.getClass().getResource(route).toString());
    }
    public static Media getMedia(String route){
        return new Media(instance.getClass().getResource(route).toExternalForm());
    }
    public static MediaPlayer getMediaPlayer(String route){
        return new MediaPlayer(new Media(instance.getClass().getResource(route).toExternalForm()));
    }
    public static File getFile(String route){
        return new File(route);
    }
}
