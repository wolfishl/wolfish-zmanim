package wolfish.zmanim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ZmanimApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        LatLongService lService = new LatLongServiceFactory().newInstance();

        ZmanimService zService = new ZmanimServiceFactory().newInstance();
        ZmanimController controller = new ZmanimController(zService, lService);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/zmanim_application.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        //Parent root = FXMLLoader.load(getClass().getResource("/zmanim_application.fxml"));
        Scene scene = new Scene(root, 400, 500);


        stage.setTitle("Zmanim");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[]args){
        launch(args);
    }
}