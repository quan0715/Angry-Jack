package SnakeGame.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import SnakeGame.App;
import SnakeGame.ResourcesLoader;
import SnakeGame.SingletonAndTemplate.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameGuideController implements Initializable {
    private List<AnchorPane> topic;
    @FXML  private AnchorPane Jack;
    @FXML  private AnchorPane HomeDark;
    @FXML  private AnchorPane HomeLight;
    @FXML  private AnchorPane FoodDark;
    @FXML  private AnchorPane FoodLight;
    @FXML  private AnchorPane KeyboardDark;
    @FXML  private AnchorPane KeyboardLight;
    @FXML  private AnchorPane DeveloperDark;
    @FXML  private AnchorPane DeveloperLight;
    @FXML  private Label TitleLabel;
    @FXML  private Text Title;
    private int Case = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topic = new ArrayList<AnchorPane>();
        SetThemeColor(HomeController.ThemeColor);
        Case = 0;
        TitleLabel.setText("Home Page");
        Setvis(Case);
    }
    public void SetThemeColor(boolean ThemeColor){
        if(ThemeColor){
            topic.add(HomeLight);
            topic.add(FoodLight);
            topic.add(KeyboardLight);
            topic.add(DeveloperLight);
            Jack.setId("LightPane");
            Title.setId("LightText");
            TitleLabel.setId("LightLabel");
            HomeDark.setVisible(false);
            FoodDark.setVisible(false);
            KeyboardDark.setVisible(false);
            DeveloperDark.setVisible(false);
        }
        else {
            topic.add(HomeDark);
            topic.add(FoodDark);
            topic.add(KeyboardDark);
            topic.add(DeveloperDark);
            Jack.setId("DarkPane");
            Title.setId("DarkText");
            TitleLabel.setId("DarkLabel");
            HomeLight.setVisible(false);
            FoodLight.setVisible(false);
            KeyboardLight.setVisible(false);
            DeveloperLight.setVisible(false);
        }
    }
    public void BackToHomePage() throws IOException {
        MusicController.ButtonClickSound();
        FXMLLoader loader = ResourcesLoader.getFXMLLoader("Scene/Home.fxml");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setRoot(root);
        App.stage.setScene(scene);
    }

    public void RightShift() {
        MusicController.ButtonClickSound();
        if (Case < topic.size() - 1) Case += 1;
        Setvis(Case);
    }

    public void LeftShift() {
        MusicController.ButtonClickSound();
        if (Case > 0) Case -= 1;
        Setvis(Case);
    }

    public void setTitle(int Case) {

    }

    public void Setvis(int id) {
        if (Case == 0)
            TitleLabel.setText("HomePage");
        if (Case == 1)
            TitleLabel.setText("Food");
        if (Case == 2)
            TitleLabel.setText("Keyboard");
        if (Case == 3)
            TitleLabel.setText("Developer");
        for (int i = 0; i < topic.size(); i++) {
            if (i != id) ((AnchorPane) topic.get(i)).setVisible(false);
        }
        ((AnchorPane) topic.get(id)).setVisible(true);
    }

    public void QuanEmail() {
        try {
            Desktop.getDesktop().browse(URI.create("https://mail.google.com/mail/u/0/?fs=1&to=quan787887@gmail.com&su=About%20Snake%20Game&tf=cm"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void QuanIG() {
        try {
            Desktop.getDesktop().browse(URI.create("https://www.instagram.com/quan___0715/"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void QuanFB() {
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/profile.php?id=100004049957181"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void AlbertEmail() {
        try {
            Desktop.getDesktop().browse(URI.create("https://mail.google.com/mail/u/0/?fs=1&to=albertlin2468@gmail.com&su=About%20Snake%20Game&tf=cm"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void AlbertIG() {
        try {
            Desktop.getDesktop().browse(URI.create("https://www.instagram.com/linyi246/"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void AlbertFB() {
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/albert.lin.10888938/"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void JackEmail() {
        try {
            Desktop.getDesktop().browse(URI.create("https://mail.google.com/mail/u/0/?fs=1&to=10635052@mail.hpsh.tp.edu.tw&su=About%20Snake%20Game&tf=cm"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void JackIG() {
        try {
            Desktop.getDesktop().browse(URI.create("https://www.instagram.com/tim_jack_duncan/"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void JackFB() {
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/https://www.facebook.com/profile.php?id=100054930077133/"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void GitHubLink() {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/quan0715/Angry-Jack"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
