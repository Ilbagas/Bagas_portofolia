import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class loginCon {

    @FXML
    private TextField usernameField;

    @FXML 
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("1234")) {
            try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
                Stage stage = (Stage)usernameField.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Password anda salah !");
            alert.setContentText("Silahkan coba lagi");
            alert.showAndWait();
        }
    }
}
