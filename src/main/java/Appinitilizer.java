import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appinitilizer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/layardproject/view/loadingPageForm.fxml"));
        stage.setScene(new Scene(load));
        stage.setTitle("Juice Bar Management System - Loading Page");
        stage.centerOnScreen();
        stage.show();
    }
}
