package SnakeGame.SingletonAndTemplate;


import SnakeGame.ResourcesLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicController {
  private static MusicController instance=new MusicController();
  private MediaPlayer BackGround1MediaPlayer;
  private MediaPlayer BackGround2MediaPlayer;
  private MediaPlayer EatFood;
  private MediaPlayer GameOver;
  private MediaPlayer Click;
  private MediaPlayer Growing;
  private MediaPlayer Throwing;
  private Media TNT;
  private Media Lock;
  private MediaPlayer SuperStar;
  private static int woody = 0;
  private MusicController(){
    BackGround1MediaPlayer = ResourcesLoader.getMediaPlayer("Music/background2.mp3");
    BackGround2MediaPlayer = ResourcesLoader.getMediaPlayer("Music/background1.mp3");
    EatFood = ResourcesLoader.getMediaPlayer("Music/Pop.mp3");
    GameOver = ResourcesLoader.getMediaPlayer("Music/GameOver.mp3");
    Click = ResourcesLoader.getMediaPlayer("Music/Click.mp3");
    SuperStar = ResourcesLoader.getMediaPlayer("Music/star.mp3");
    Growing = ResourcesLoader.getMediaPlayer("Music/ban.mp3");
    Throwing = ResourcesLoader.getMediaPlayer("Music/Fireballs.mp3");
    TNT = ResourcesLoader.getMedia("Music/TnT.mp3");
    Lock = ResourcesLoader.getMedia("Music/Click.mp3");
    BackGround1MediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    BackGround2MediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    BackGround2MediaPlayer.setVolume(0.6);
    Growing.setVolume(0.4);
  }
  public static void FireTrowing(){
    instance.Throwing.seek(Duration.millis(350));
    instance.Throwing.play();
  }
  public static boomPlayer newboom() {
    return new boomPlayer(instance.TNT);
  }
  
  public static void GrowingUp(){
    instance.Growing.seek(Duration.millis(700));
    instance.Growing.play();
  }
  public static void PlayBackground1(){
    instance.BackGround1MediaPlayer.play();
  }
  public static void StopBackground1() {
    instance.BackGround1MediaPlayer.stop();
  }
  public static void PlayBackground2() {
    instance.BackGround2MediaPlayer.play();
  }
  public static void StopBackground2() {
    instance.BackGround2MediaPlayer.stop();
  }
  public static void EatFoodPop(){
    instance.EatFood.seek(Duration.millis(150));
    instance.EatFood.play();
  }
  public static void GameOverSound() {
    instance.GameOver.seek(Duration.millis(0));
    instance.GameOver.play();
  }
  public static void SuperStarFood(boolean s){
    if(s){
      woody++;
      instance.BackGround1MediaPlayer.setVolume(0.0);
      instance.BackGround2MediaPlayer.setVolume(0.0);
      instance.SuperStar.seek(Duration.millis(0));
      instance.SuperStar.play();
    }
    else{
      woody --;
      if (woody <= 0) {
        woody = 0;
        instance.BackGround1MediaPlayer.setVolume(1.0);
        instance.BackGround2MediaPlayer.setVolume(1.0);
        instance.SuperStar.stop();
      }
    }
  }
  public static void ButtonClickSound() {
    instance.Click.seek(Duration.millis(400));
    instance.Click.play();
  }
  public static void LockSound(){
    MediaPlayer player=new MediaPlayer(instance.Lock);
    player.seek(Duration.millis(300));
    player.setVolume(0.4);
    player.play();
    Timeline shutDownTimeline=new Timeline(new KeyFrame(Duration.millis(1000),e->player.dispose()));
    shutDownTimeline.setCycleCount(1);
    shutDownTimeline.play();
  }
  public static void SetMute(boolean SoundOff){
    instance.BackGround1MediaPlayer.setMute(SoundOff);
    instance.BackGround2MediaPlayer.setMute(SoundOff);
  }
  public static void GameOver(){
    instance.SuperStar.stop();
  }
}
