package SnakeGame.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import SnakeGame.App;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import SnakeGame.Snake.JackSnake;
import SnakeGame.Snake.SBBSnake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HomeController implements Initializable{
  @FXML private Button ButtonOne;
  @FXML private Button ButtonTwo;
  @FXML private Button ButtonThree;
  @FXML public TextField GamePin;
  @FXML private ImageView Sound;
  @FXML private ImageView Theme;
  @FXML private AnchorPane Jack;
  @FXML private Text Title;
  private static boolean SoundOff = false;
  public  static boolean ThemeColor = false;
  private Image Light = ResourcesLoader.getImage("img/light.png");
  private Image Dark = ResourcesLoader.getImage("img/dark.png");
  private Image sound = ResourcesLoader.getImage("img/sound.png");
  private final Image unsound = ResourcesLoader.getImage("img/no-sound.png");
  public static Snake Player1=new JackSnake();
  public static Snake Player2=new SBBSnake();
  public void SwitchOneManGame() throws IOException{
    MusicController.StopBackground2();
    MusicController.ButtonClickSound();
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/table.fxml");
    String PinName = GamePin.getText();
    Parent root = loader.load();
    Scene scene = new Scene(root);
    App.stage.setScene(scene);
    GameOneController controller = loader.getController();
    controller.init();
    controller.GetPinName(PinName);
    scene.setOnKeyPressed(new javafx.event.EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        try {
          controller.KeyEven(event);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
  public void SwitchToGameGuide() throws IOException{
    MusicController.ButtonClickSound();
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/GameGuideHome.fxml");
    Parent root = loader.load();
    Scene scene = new Scene(root);
    App.stage.setScene(scene);
  }
  public void SwitchChoseSnake(){
    MusicController.ButtonClickSound();
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/Snakecontrol.fxml");
    try {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      ChoseSnakeController controller=loader.getController();
      controller.init();
      App.stage.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void SwitchTwoManGame(ActionEvent event) throws IOException {
    MusicController.StopBackground2();
    MusicController.ButtonClickSound();
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/table2.fxml");
    String PinName = GamePin.getText();
    Parent root = loader.load();
    Scene scene = new Scene(root);
    App.stage.setScene(scene);
    GameTwoController controller = loader.getController();
    controller.init();
    controller.GetPinName(PinName);
    scene.setOnKeyPressed(event1 -> {
      try {
        controller.KeyEven(event1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
  public void SwitchOnlineBattleGame(ActionEvent event) throws IOException {
    MusicController.StopBackground2();
    MusicController.ButtonClickSound();
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/table3.fxml");
    String PinName = GamePin.getText();
    Parent root = loader.load();
    Scene scene = new Scene(root);
    App.stage.setScene(scene);
    GameThreeController controller = loader.getController();
    controller.GetPinName(PinName);
    controller.init();
  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    GameCurrentChildrenArray.Instance.set(null);
    MusicController.PlayBackground2();
    SwitchSoundIcon(SoundOff);
    SwitchThemeIcon(ThemeColor);
    ButtonOne.getScene();
    ButtonOne.setOnKeyPressed((e) -> {
      if(e.getCode() == KeyCode.ENTER){
        e.consume();
        try {
          SwitchOneManGame();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    GamePin.setOnAction(e->{
      e.consume();
      ButtonOne.requestFocus();
    });
  }
  public void SetTheme(){
    ThemeColor = !ThemeColor;
    MusicController.ButtonClickSound();
    SwitchThemeIcon(ThemeColor);
  }
  public void SetMute(){
    SoundOff = !SoundOff;
    SwitchSoundIcon(SoundOff);
    MusicController.ButtonClickSound();
    MusicController.SetMute(SoundOff);
  }
  private void SwitchSoundIcon (boolean SoundOff){
    if(SoundOff){
      Sound.setImage(unsound);
    }
    else Sound.setImage(sound);
  }
  private void SwitchThemeIcon (boolean ThemeColor){
    if(ThemeColor){
      Theme.setImage(Light);
      Jack.setId("LightPane");
      Title.setId("LightText");
      ButtonOne.setId("LightButton");
      ButtonTwo.setId("LightButton");
      ButtonThree.setId("LightButton");
      GamePin.setId("LightField");
    }
    else {
      Theme.setImage(Dark);
      Jack.setId("DarkPane");
      Title.setId("DarkText");
      ButtonOne.setId("DarkButton");
      ButtonTwo.setId("DarkButton");
      ButtonThree.setId("DarkButton");
      GamePin.setId("DarkField");
    }
  }
}
