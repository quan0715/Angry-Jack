package SnakeGame.Controller;

import java.io.IOException;

import SnakeGame.App;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputController{
  private boolean NewGame;
  private boolean GamePause;
  private String NormalAlert;
  private Label AlertText;
  private SnakeBodyPlayer s1;
  private SnakeBodyPlayer s2;
  private DirectionController d1;
  private DirectionController d2;
  // game one 
  public InputController(Label AlertText,SnakeBodyPlayer s1,DirectionController d1) {
    this.AlertText = AlertText;
    this.s1 = s1;
    this.d1 = d1;
    init();
   }
  // game two
  public InputController(Label AlertText, SnakeBodyPlayer s1, SnakeBodyPlayer s2, DirectionController d1,DirectionController d2) {
    this.AlertText = AlertText;
    this.s1 = s1;
    this.d1 = d1;
    this.s2 = s2;
    this.d2 = d2;
    init();
  }
  public void init(){
    if(HomeController.ThemeColor){
      NormalAlert = "LightNormal";
    }
    else {
      NormalAlert = "DarkNormal";
    }
    GamePause = false;
    NewGame = true;
  }
  public boolean GameTwoFlow(KeyEvent event) throws IOException{
    KeyCode key = event.getCode();
    if (key == KeyCode.H) {
      BackToHomePage();
    }
    if (key == KeyCode.SPACE) {
      if(GameContinue()){
        GameFlowController.GameFlowPause();
        setAlertText("TAP SPACE --> CONTINUE THE GAME\n\nTAP H --> RETURN HOME PAGE", NormalAlert);
        GamePause = true;
      }
      else if(GameIsPause()){
        GameFlowController.GameFlowPlay();
        setAlertText("", NormalAlert);
        GamePause = false;
      }
    } 
    if (key == KeyCode.ENTER && NewGame){
      return true;
       //StartGame();
    }
    if (GameContinue()) {
      d1.Direction1(key);
      d2.Direction2(key);
    }
    if(key == KeyCode.Q){
      d1.setCanFire(true);
    }
    if (key == KeyCode.SLASH) {
      d2.setCanFire(true);
    }
    return false;
  }

  public boolean GameOneFlow(KeyEvent event) throws IOException {
    KeyCode key = event.getCode();
    if (key == KeyCode.H) {
      BackToHomePage();
    }
    if (key == KeyCode.SPACE) {
      if (GameContinue()) {
        GameFlowController.GameFlowPause();
        setAlertText("TAP SPACE --> CONTINUE THE GAME\n\nTAP H --> RETURN HOME PAGE", NormalAlert);
        GamePause = true;
      } else if (GameIsPause()) {
        GameFlowController.GameFlowPlay();
        setAlertText("", NormalAlert);
        GamePause = false;
      }
    }
    if (key == KeyCode.ENTER && NewGame) {
      return true;
    }
    if (GameContinue()) {
      d1.Direction3(key);
    }
    if (key == KeyCode.Q) {
      d1.setCanFire(true);
    }
    return false;
  }
  private boolean GameContinue(){
    return !NewGame && !GamePause ;
  }
  private boolean GameIsPause(){
    return  GamePause && !NewGame;
  }
  public void setAlertText(String text, String Id) {
    ObservableList<Node> children = GameCurrentChildrenArray.Instance.get();
    children.remove(AlertText);
    AlertText.setText(text);
    AlertText.setAlignment(Pos.CENTER);
    AlertText.setId(Id);
    children.add(AlertText);
  }
  public void BackToHomePage() throws IOException{
    MusicController.StopBackground1();
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/Home.fxml");
    Parent root = loader.load();
    App.stage.setScene(new Scene(root));
  }
  public void SetNewGame(boolean n){
    this.NewGame = n;
  }
  public void SetGamePause(boolean n){
    this.GamePause = n;
  }
  public boolean IsNewGame(){
    return NewGame;
  }
  public boolean IsPause(){
    return GamePause;
  }
  public void welcome() {
    setAlertText("TAP ENTER TO START NEW GAME", NormalAlert);
  }
}
