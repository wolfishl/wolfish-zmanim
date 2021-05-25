package wolfish.zmanim;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ZmanimController {
    @FXML
    TextField place;
    @FXML
    Label placeLabel;
    @FXML
    Label alos;
    @FXML
    Label neitz;
    @FXML
    Label sofZmanShema;
    @FXML
    Label sofZmanTefillah;
    @FXML
    Label chatzos;
    @FXML
    Label minchaGedolah;
    @FXML
    Label plag;
    @FXML
    Label shkiah;
    @FXML
    Label tzeis;

    private ZmanimService zService;
    LatLongService lService;

    public ZmanimController(ZmanimService zService, LatLongService lService)
    {
        this.zService = zService;
        this.lService = lService;
    }

    public void onSearch()
    {
        String location = place.getText();


        Disposable disposable = lService.getCoordinates(location).
                subscribeOn((Schedulers.io()))
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onLatLongFeed,
                        this::onError);
    }

    public void onLatLongFeed(LatLongFeed feed)
    {

        Disposable disposable = zService.getZmanim(feed.results.get(0).annotations.timezone.name,
                feed.results.get(0).geometry.lat,
                feed.results.get(0).geometry.lng).
                subscribeOn((Schedulers.io()))
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onZmanimFeed,
                        this::onError);

        latLongRunLater(feed);

    }

    public void latLongRunLater(LatLongFeed feed)
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                placeLabel.setText(feed.results.get(0).components.city + ", " + feed.results.get(0).components.state + " " + feed.results.get(0).components.country);
            }
        });
    }

    public void onZmanimFeed(ZmanimFeed feed)
    {
        zmanimRunLater(feed);
    }

    public void zmanimRunLater(ZmanimFeed feed)
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                alos.setText(feed.Alos16point1Degrees);
                neitz.setText(feed.Sunrise);
                sofZmanShema.setText(feed.SofZmanShemaGra);
                sofZmanTefillah.setText(feed.SofZmanTefilahGra);
                chatzos.setText(feed.Chatzos);
                minchaGedolah.setText(feed.MinchaGedolah);
                plag.setText(feed.PlagHamincha);
                shkiah.setText(feed.Shkia);
                tzeis.setText(feed.Tzais);

            }
        });
    }


    public void onError(Throwable throwable)
    {
        throwable.printStackTrace();
    }




}
