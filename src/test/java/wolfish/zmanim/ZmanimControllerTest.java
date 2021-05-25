package wolfish.zmanim;

import io.reactivex.rxjava3.core.Single;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZmanimControllerTest {

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }


    public ZmanimController givenZmanimController()
    {
        LatLongService lservice = mock(LatLongService.class);
        ZmanimService zservice = mock(ZmanimService.class);

        ZmanimController controller = new ZmanimController(zservice, lservice);
        controller.alos = mock(Label.class);
        controller.chatzos = mock(Label.class);
        controller.minchaGedolah = mock(Label.class);
        controller.neitz = mock(Label.class);
        controller.place = mock(TextField.class);
        controller.placeLabel = mock(Label.class);
        controller.plag = mock(Label.class);
        controller.shkiah = mock(Label.class);;
        controller.sofZmanShema = mock(Label.class);
        controller.sofZmanTefillah = mock(Label.class);
        controller.tzeis = mock(Label.class);

        return controller;

    }

    @Test
    public void onSearch()
    {
        //given
        String location = "London";
        ZmanimController controller = givenZmanimController();
        doReturn(Single.never()).when(controller.lService).getCoordinates(location);
        doReturn(location).when(controller.place).getText();

        //when
        controller.onSearch();

        //then
        verify(controller.place).getText();
        verify(controller.lService).getCoordinates(location);
    }


    @Test
    public void latLongRunLater()
    {
        //given
        ZmanimController controller = givenZmanimController();
        LatLongFeed feed = mock(LatLongFeed.class);
        List<LatLongFeed.Place> places = Arrays.asList(mock(LatLongFeed.Place.class));
        feed.results = places;
        feed.results.get(0).components = mock(LatLongFeed.Components.class);
        feed.results.get(0).components.country = "United Kingdom";
        feed.results.get(0).components.state = "England";
        feed.results.get(0).components.city = "London";

        //when
        controller.latLongRunLater(feed);

        //then
        verify(controller.placeLabel).setText(feed.results.get(0).components.city + ", " + feed.results.get(0).components.state + " " + feed.results.get(0).components.country);
    }

    @Test
    public void zmanimRunLater()
    {
        //given

        ZmanimController controller = givenZmanimController();
        ZmanimFeed feed = mock(ZmanimFeed.class);

        //when
        controller.zmanimRunLater(feed);

        //then

        verify(controller.alos).setText(feed.Alos16point1Degrees);
        verify(controller.neitz).setText(feed.Sunrise);
        verify(controller.sofZmanShema).setText(feed.SofZmanShemaGra);
        verify(controller.sofZmanTefillah).setText(feed.SofZmanTefilahGra);
        verify(controller.chatzos).setText(feed.Chatzos);
        verify(controller.minchaGedolah).setText(feed.MinchaGedolah);
        verify(controller.plag).setText(feed.PlagHamincha);
        verify(controller.shkiah).setText(feed.Shkia);
        verify(controller.tzeis).setText(feed.Tzais);
    }

}