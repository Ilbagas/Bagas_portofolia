import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAPP extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("1 - 800 hot n fun");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void setMainStage(Scene scene) {
        mainStage.setScene(scene);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
