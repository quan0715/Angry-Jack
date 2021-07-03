package SnakeGame.Client;

import SnakeGame.App;
import SnakeGame.Controller.GameThreeController;
import SnakeGame.Controller.HomeController;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.scene.Scene;

import java.io.*;

public class ioThread extends Thread {
    private Scene scene;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private GameThreeController controller;
    private InputStream rawInputStream;
    private OutputStream rawOutputStream;

    public ioThread(InputStream inputStream, OutputStream outputStream, GameThreeController controller) {
        this.scene = scene;
        this.controller = controller;
        this.rawInputStream = inputStream;
        this.rawOutputStream = outputStream;
        start();
    }

    public void run() {
        try {
            //io
            outputStream = new ObjectOutputStream(rawOutputStream);
            inputStream = new ObjectInputStream(rawInputStream);
            App.stage.setOnCloseRequest(windowEvent -> {
                try {
                    outputStream.writeObject("disconnect");
                    outputStream.flush();
                    controller.connect.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            //sendSnake
            String str = inputStream.readObject().toString();
            if (str.equals("StartGameAsPlayer1")) {
                outputStream.writeObject("StartGame");
                outputStream.writeObject(HomeController.Player1);
                controller.snake1Instance = HomeController.Player1;
                str = inputStream.readObject().toString();
                if (str.equals("sameSnake")) {
                    controller.snake2Instance = HomeController.Player2;
                } else {
                    controller.snake2Instance = (Snake) inputStream.readObject();
                }
            } else {
                outputStream.writeObject("StartGame");
                outputStream.writeObject(HomeController.Player1);
                controller.snake2Instance = HomeController.Player1;
                str = inputStream.readObject().toString();
                if (str.equals("sameSnake")) {
                    controller.snake1Instance = HomeController.Player2;
                } else {
                    controller.snake1Instance = (Snake) inputStream.readObject();
                }
            }
            controller.startGame();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
        App.stage.getScene().setOnKeyPressed(keyEvent -> {
            try {
                outputStream.writeObject(keyEvent.getCode());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        while (true) {
            try {
                System.out.println(inputStream.readObject());
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
                break;
            }
        }
    }
}
