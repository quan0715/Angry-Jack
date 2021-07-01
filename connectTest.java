import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class connectTest extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("test.fxml"))));
        stage.show();
        Socket connect=new Socket("127.0.0.1",8787);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        ObjectOutputStream outputStream=new ObjectOutputStream(connect.getOutputStream());
        while(true){
            String tem=reader.readLine();
            if(tem==null || tem.toCharArray()[0]=='S'){
                System.out.println(tem);
                break;
            }
        }
            stage.getScene().setOnKeyPressed(keyEvent -> {
                try {
                    outputStream.writeObject(keyEvent.getCode());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
    }
}
