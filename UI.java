import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UI extends Application{
 public static void main(String [] args) {
	 launch(args);
	 
 }
 public void start(Stage primaryStage) throws Exception {
	 try {
		 Parent root = FXMLLoader.load(getClass().getResource("Project_UI.fxml"));
		 Scene scene = new Scene(root);
		 primaryStage.setTitle("design");
		 primaryStage.setScene(scene);
		 primaryStage.show();
	 }
	 catch(Exception e) {
		 System.out.println(e.getMessage());
	 } 
  }
}
