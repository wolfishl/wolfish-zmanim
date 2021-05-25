package wolfish.zmanim;

import io.reactivex.rxjava3.core.Single;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LatLongServiceTest {

    LatLongServiceFactory factory = new LatLongServiceFactory();

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void getCoordinates()
    {
        //given

        LatLongService service = factory.newInstance();
        String location = "London";

        //when
        Single<LatLongFeed> single = service.getCoordinates(location);

        LatLongFeed feed = single.blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.results);
        assertEquals(feed.results.get(0).annotations.timezone.name, "Europe/London");
        assertEquals(feed.results.get(0).components.city, "London");
        assertEquals(feed.results.get(0).components.state, "England");
        assertEquals(feed.results.get(0).components.country, "United Kingdom");
        assertEquals(feed.results.get(0).geometry.lat, 51.5073219);
        assertEquals(feed.results.get(0).geometry.lng, -0.1276474);
    }

}