package projectolpc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ProjectoLPC extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ViewPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image image = new Image("/Image/configuracao.png");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setTitle("Mini-Pascal Lexical Analyzer");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
