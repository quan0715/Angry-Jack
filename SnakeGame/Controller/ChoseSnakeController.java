package SnakeGame.Controller;

import java.io.File;
import java.io.IOException;

import SnakeGame.App;
import SnakeGame.Enum.Direction;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import SnakeGame.Snake.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.util.Pair;


public class ChoseSnakeController{
  @FXML private AnchorPane ChoseTable;
  @FXML private AnchorPane Jack;
  @FXML private Text Title;
  @FXML private AnchorPane GameTable1;
  @FXML private Button ButtonBack;
  @FXML private GridPane table;
  @FXML private Pane P1select;
  @FXML private Pane P2select;
  @FXML private Pane P1Hover;
  @FXML private Pane P2Hover;
  @FXML private Pane Hover;
  @FXML private Pane playerOneExhibitPane;
  @FXML private Pane playerTwoExhibitPane;
  @FXML private Label SnakeType1;
  @FXML private Label SnakeType2;
  @FXML private Button SelectImg11;
  @FXML private Button SelectImg12;
  @FXML private Button SelectImg21;
  @FXML private Button SelectImg22;
  @FXML private ImageView LockStatus1;
  @FXML private ImageView LockStatus2;
  private boolean locked1 = false ;
  private boolean locked2 = false ;
  private Image Lock = ResourcesLoader.getImage("img/lock.png");
  private Image UnLock = ResourcesLoader.getImage("img/padlock.png");
  private Snake[][] snakes = new Snake[5][4];
  private Pair<Integer,Integer> hover1 = new Pair<>(3,0);
  private Pair<Integer,Integer> hover2 = new Pair<>(3,3);
  private static Pair<Integer,Integer> select1=new Pair<>(3,0);
  private static Pair<Integer,Integer> select2=new Pair<>(3,3);
  private static String Snake1Name = "JackSnake";
  private static String Snake2Name = "SBBSnake";
  private String[][] name = new String[5][4];
  private boolean fixed1=false;
  private boolean fixed2=false;
  private Timeline player1Timeline;
  private Timeline player2Timeline;
  private SnakeBody player1Body;
  private SnakeBody player2Body;
  private Direction[][] exhibitDir=new Direction[2][8];
  private int[] nextDir=new int[2];
  public void BackToHomePage() throws IOException {
    MusicController.ButtonClickSound();
    player1Body.clearOnScreen();
    player2Body.clearOnScreen();
    player1Timeline.stop();
    player2Timeline.stop();
    if(fixed1)select1=hover1;
    if(fixed2)select2=hover2;
    HomeController.Player1 = snakes[select1.getKey()][select1.getValue()];
    HomeController.Player2 = snakes[select2.getKey()][select2.getValue()];
    FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/Home.fxml");
    Parent root = loader.load();
    Scene scene = new Scene(root);
    scene.setRoot(root);
    App.stage.setScene(scene);
  }
  public void switchLock1Icon(boolean LockStatus){
    if(LockStatus){
      LockStatus1.setImage(Lock);
    }
    else{
      LockStatus1.setImage(UnLock);
    }
  }
  public void switchLock2Icon(boolean LockStatus){
    if(LockStatus){
      LockStatus2.setImage(Lock);
    }
    else{
      LockStatus2.setImage(UnLock);
    }
  }
  public void init() {
    SetThemeColor(HomeController.ThemeColor);
    locked1 = locked2 = false;
    switchLock1Icon(false);
    switchLock2Icon(false);
    GameCurrentChildrenArray.Instance.set(GameTable1.getChildren());
    hover1=select1;
    hover2=select2;
    initializeArrays();
    nextDir[0]=nextDir[1]=0;
    table.getChildren().remove(P1select);
    table.getChildren().remove(P2select);
    table.getChildren().remove(Hover);
    GridPane.setColumnIndex(P1Hover, hover1.getValue());
    GridPane.setRowIndex(P1Hover, hover1.getKey());
    GridPane.setColumnIndex(P2Hover, hover2.getValue());
    GridPane.setRowIndex(P2Hover, hover2.getKey());
    Snake1Name = name[hover1.getKey()][hover1.getValue()];
    Snake2Name = name[hover2.getKey()][hover2.getValue()];
    SnakeType1.setText(Snake1Name);
    SnakeType2.setText(Snake2Name);
    SetSelectButton();
    playSnake1();
    playSnake2();
    table.setOnKeyPressed((e)->{
      e.consume();
      handle(e);
    });
    table.requestFocus();
  }
  private void SetThemeColor (boolean ThemeColor){
    if(ThemeColor){
      Jack.setId("LightPane");
      Title.setId("LightText");
      ButtonBack.setId("LightButton");
      SelectImg11.setId("LightButton");
      SelectImg12.setId("LightButton");
      SelectImg21.setId("LightButton");
      SelectImg22.setId("LightButton");
      GameTable1.setId("LightBattle");
      ChoseTable.setId("LightShow");
    }
    else {
      Jack.setId("DarkPane");
      Title.setId("DarkText");
      ButtonBack.setId("DarkButton");
      SelectImg11.setId("DarkButton");
      SelectImg12.setId("DarkButton");
      SelectImg21.setId("DarkButton");
      SelectImg22.setId("DarkButton");
      GameTable1.setId("DarkBattle");
      ChoseTable.setId("DarkShow");
    }
  }
  private void playSnake1() {
    //start speed useless if SnakeBodyPlayer not used
    player1Body=new SnakeBody(snakes[hover1.getKey()][hover1.getValue()],0, 40,220);
    try {
      player1Body.AddNewBody();
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    player1Timeline=new Timeline(new KeyFrame(Duration.millis(200), e->{
      try {
        player1Body.Move(exhibitDir[0][nextDir[0]]);
        nextDir[0]=(nextDir[0]+1)%8;
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }));
    player1Timeline.setCycleCount(-1);
    player1Timeline.play();
  }
  private void playSnake2() {
    player2Body=new SnakeBody(snakes[hover2.getKey()][hover2.getValue()],0,460,220);
    try {
      player2Body.AddNewBody();
    } catch (Exception e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    player2Timeline=new Timeline(new KeyFrame(Duration.millis(200), e->{
      try {
        player2Body.Move(exhibitDir[1][nextDir[1]]);
        nextDir[1]=(nextDir[1]+1)%8;
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }));
    player2Timeline.setCycleCount(-1);
    player2Timeline.play();
  }
  private void initializeArrays() {
    exhibitDir[0][0]=Direction.UP;
    exhibitDir[0][1]=Direction.UP;
    exhibitDir[0][2]=Direction.LEFT;
    exhibitDir[0][3]=Direction.LEFT;
    exhibitDir[0][4]=Direction.DOWN;
    exhibitDir[0][5]=Direction.DOWN;
    exhibitDir[0][6]=Direction.RIGHT;
    exhibitDir[0][7]=Direction.RIGHT;
    exhibitDir[1][0]=Direction.UP;
    exhibitDir[1][1]=Direction.UP;
    exhibitDir[1][2]=Direction.LEFT;
    exhibitDir[1][3]=Direction.LEFT;
    exhibitDir[1][4]=Direction.DOWN;
    exhibitDir[1][5]=Direction.DOWN;
    exhibitDir[1][6]=Direction.RIGHT;
    exhibitDir[1][7]=Direction.RIGHT;
    snakes[0][0]=new ClassicSnake();
    snakes[0][1]=new IGSnake();
    snakes[0][2]=new PythonSnake();
    snakes[0][3]=new VscodeSnake();
    snakes[1][0]=new NCUSnake();
    snakes[1][1]=new KobeSnake();
    snakes[1][2]=new RainbowSnake();
    snakes[1][3]=new AppleSnake();
    snakes[2][0]=new YoutubeSnake();
    snakes[2][1]=new ChromeSnake();
    snakes[2][2]=new DiscordSnake();
    snakes[2][3]=new LineSnake();
    snakes[3][0]=new JackSnake();
    snakes[3][1]=new QuanSnake();
    snakes[3][2]=new AlbertSnake();
    snakes[3][3]=new SBBSnake();
    snakes[4][0]=new WindowSnake();
    snakes[4][1]=new MacbookSnake();
    snakes[4][2]=new CustomizeSnake1();
    snakes[4][3]=new CustomizeSnake2();
    name[0][0] = "ClassicSnake";
    name[0][1] = "IGSnake";
    name[0][2] = "PythonSnake";
    name[0][3] = "VscodeSnake";
    name[1][0] = "NCUSnake";
    name[1][1] = "KobeSnake";
    name[1][2] = "RainbowSnake";
    name[1][3] = "AppleSnake";
    name[2][0] = "YoutubeSnake";
    name[2][1] = "ChromeSnake";
    name[2][2] = "DiscordSnake";
    name[2][3] = "LineSnake";
    name[3][0] = "JackSnake";
    name[3][1] = "QuanSnake";
    name[3][2] = "AlbertSnake";
    name[3][3] = "SBBSnake";
    name[4][0] = "WindowSnake";
    name[4][1] = "MacbookSnake";
    name[4][2] = "Customize1";
    name[4][3] = "Customize2";
  }
  private void handle(KeyEvent e) {
    table.getChildren().remove(P1select);
    table.getChildren().remove(P2select);
    table.getChildren().remove(Hover);
    table.getChildren().remove(P1Hover);
    table.getChildren().remove(P2Hover);
    int changedPlayer=0;
    if(e.getCode()==KeyCode.SPACE){
      fixed1=!fixed1;
      locked1 = !locked1;
      switchLock1Icon(locked1);
      if(fixed1&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
        hover2=new Pair<>((hover2.getKey())%5,(hover2.getValue()-1)%4);
      }
    }
    else if(e.getCode()==KeyCode.ENTER){
      fixed2=!fixed2;
      locked2 = !locked2;
      switchLock2Icon(locked2);
      if(fixed2&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
        hover1=new Pair<>((hover1.getKey())%5,(hover1.getValue()+1)%4);
      }
    }
    else{
      if(!fixed1)switch(e.getCode()){
        case W:
        changedPlayer=1;
        hover1=new Pair<>((hover1.getKey()+4)%5,(hover1.getValue())%4);
        if(fixed2&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover1=new Pair<>((hover1.getKey()+4)%5,(hover1.getValue())%4);
        }
        break;
        case S:
        changedPlayer=1;
        hover1=new Pair<>((hover1.getKey()+1)%5,(hover1.getValue())%4);
        if(fixed2&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover1=new Pair<>((hover1.getKey()+1)%5,(hover1.getValue())%4);
        }
        break;
        case A:
        changedPlayer=1;
        hover1=new Pair<>((hover1.getKey())%5,(hover1.getValue()+3)%4);
        if(fixed2&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover1=new Pair<>((hover1.getKey())%5,(hover1.getValue()+3)%4);
        }
        break;
        case D:
        changedPlayer=1;
        hover1=new Pair<>((hover1.getKey())%5,(hover1.getValue()+1)%4);
        if(fixed2&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover1=new Pair<>((hover1.getKey())%5,(hover1.getValue()+1)%4);
        }
        break;
        default:
        break;
        }
      if(!fixed2)switch(e.getCode()){
        case UP:
        changedPlayer=2;
        hover2=new Pair<>((hover2.getKey()+4)%5,(hover2.getValue())%4);
        if(fixed1&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover2=new Pair<>((hover2.getKey()+4)%5,(hover2.getValue())%4);
        }
        break;
        case DOWN:
        changedPlayer=2;
        hover2=new Pair<>((hover2.getKey()+1)%5,(hover2.getValue())%4);
        if(fixed1&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover2=new Pair<>((hover2.getKey()+1)%5,(hover2.getValue())%4);
        }
        break;
        case LEFT:
        changedPlayer=2;
        hover2=new Pair<>((hover2.getKey())%5,(hover2.getValue()+3)%4);
        if(fixed1&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover2=new Pair<>((hover2.getKey())%5,(hover2.getValue()+3)%4);
        }
        break;
        case RIGHT:
        changedPlayer=2;
        hover2=new Pair<>((hover2.getKey())%5,(hover2.getValue()+1)%4);
        if(fixed1&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
          hover2=new Pair<>((hover2.getKey())%5,(hover2.getValue()+1)%4);
        }
        break;
        default:
        break;
      }
    }
    if(hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
      table.getChildren().add(Hover);
      GridPane.setColumnIndex(Hover, hover1.getValue());
      GridPane.setRowIndex(Hover, hover1.getKey());
      
    }
    else{
      if(fixed1){
        table.getChildren().add(P1select);
        GridPane.setColumnIndex(P1select, hover1.getValue());
        GridPane.setRowIndex(P1select, hover1.getKey());
      }
      else{
        table.getChildren().add(P1Hover);
        GridPane.setColumnIndex(P1Hover, hover1.getValue());
        GridPane.setRowIndex(P1Hover, hover1.getKey());
      }
      if(fixed2){
        table.getChildren().add(P2select);
        GridPane.setColumnIndex(P2select, hover2.getValue());
        GridPane.setRowIndex(P2select, hover2.getKey());
      }
      else{
        table.getChildren().add(P2Hover);
        GridPane.setColumnIndex(P2Hover, hover2.getValue());
        GridPane.setRowIndex(P2Hover,hover2.getKey());
      }
    }

    Snake1Name = name[hover1.getKey()][hover1.getValue()];
    Snake2Name = name[hover2.getKey()][hover2.getValue()];
    SnakeType1.setText(Snake1Name);
    SnakeType2.setText(Snake2Name);
    if(changedPlayer==1){
      player1Body.clearOnScreen();
      nextDir[0]=0;
      player1Body=new SnakeBody(snakes[hover1.getKey()][hover1.getValue()],0,40,220);
      try {
        player1Body.AddNewBody();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    else if(changedPlayer==2){
      player2Body.clearOnScreen();
      nextDir[1]=0;
      player2Body=new SnakeBody(snakes[hover2.getKey()][hover2.getValue()],0,460,220);
      try {
        player2Body.AddNewBody();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    SetSelectButton();
  }
  public static String GetSnake1Name(){
    return Snake1Name;
  }
  public static String GetSnake2Name(){
    return Snake2Name;
  }
  public void SetSelectButton(){
    SelectImg11.setVisible(false);
    SelectImg12.setVisible(false);
    SelectImg21.setVisible(false);
    SelectImg22.setVisible(false);
    SelectImg11.setDisable(true);
    SelectImg12.setDisable(true);
    SelectImg21.setDisable(true);
    SelectImg22.setDisable(true);
    if((hover1.getKey()==4 && hover1.getValue() == 2)){
      SelectImg11.setVisible(true);
      SelectImg11.setDisable(false);
    }
    if((hover1.getKey()==4 && hover1.getValue() == 3)){
      SelectImg12.setVisible(true);
      SelectImg12.setDisable(false);
    }
    if((hover2.getKey()==4 && hover2.getValue() == 2)){
      SelectImg21.setVisible(true);
      SelectImg21.setDisable(false);
    }
    if((hover2.getKey()==4 && hover2.getValue() == 3)) {
      SelectImg22.setVisible(true);
      SelectImg22.setDisable(false);
    }
  }
  public void setCustomizePhoto1(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("All Images", "*.jpg","*.gif","*.bmp","*.png","*.jpng"),
			new FileChooser.ExtensionFilter("JPG", "*.jpg"),
			new FileChooser.ExtensionFilter("GIF", "*.gif"),
			new FileChooser.ExtensionFilter("BMP", "*.bmp"),
			new FileChooser.ExtensionFilter("PNG", "*.png"),
			new FileChooser.ExtensionFilter("JPNG", "*.jpng")
		);
		File selectedFile = fileChooser.showOpenDialog(App.stage);
		if(selectedFile!=null){
          System.out.println(selectedFile.getAbsolutePath());
          CustomizeSnake1.SetImageSource(selectedFile);
          player1Body.clearOnScreen();
          nextDir[0]=0;
          player1Body=new SnakeBody(snakes[hover1.getKey()][hover1.getValue()],0,40,220);
          try {
            player1Body.AddNewBody();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
          player2Body.clearOnScreen();
          nextDir[1]=0;
          player2Body=new SnakeBody(snakes[hover2.getKey()][hover2.getValue()],0,460,220);
          try {
            player2Body.AddNewBody();
          } catch (Exception e2) {
            e2.printStackTrace();
          }
        }
  }
  public void ClickToLock1(){
    fixed1=!fixed1;
    locked1 = !locked1;
    switchLock1Icon(locked1);
    if(fixed1&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
      hover2=new Pair<>((hover2.getKey())%5,(hover2.getValue()-1)%4);
    }
  }
  public void ClickToLock2(){
    fixed2=!fixed2;
    locked2 = !locked2;
    switchLock2Icon(locked2);
    if(fixed2&&hover1.getKey()==hover2.getKey()&&hover1.getValue()==hover2.getValue()){
      hover1=new Pair<>((hover1.getKey())%5,(hover1.getValue()+1)%4);
    }
  }
  public void setCustomizePhoto2(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.jpg","*.gif","*.bmp","*.png","*.jpng"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png"),
            new FileChooser.ExtensionFilter("JPNG", "*.jpng")
    );
    File selectedFile = fileChooser.showOpenDialog(App.stage);
    if(selectedFile!=null){
      System.out.println(selectedFile.getAbsolutePath());
      CustomizeSnake2.SetImageSource(selectedFile);
      player2Body.clearOnScreen();
      nextDir[1]=0;
      player1Body.clearOnScreen();
      nextDir[0]=0;
      player1Body=new SnakeBody(snakes[hover1.getKey()][hover1.getValue()],0,40,220);
      try {
        player1Body.AddNewBody();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      player2Body=new SnakeBody(snakes[hover2.getKey()][hover2.getValue()],0,460,220);
      try {
        player2Body.AddNewBody();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
  }
}
