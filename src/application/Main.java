package application;

import com.timer.utils.GlobalVariates;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/application/MyScene.fxml"));
            primaryStage.initStyle(StageStyle. DECORATED);
            primaryStage.setTitle("MicroSandClock");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setAlwaysOnTop(true);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            	@Override
            	public void handle(WindowEvent event) {
            		GlobalVariates.timer.cancel();
            	}
            	});
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}