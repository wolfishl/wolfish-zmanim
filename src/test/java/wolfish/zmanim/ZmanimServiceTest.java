package wolfish.zmanim;


import io.reactivex.rxjava3.core.Single;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZmanimServiceTest {

    ZmanimServiceFactory factory = new ZmanimServiceFactory();

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void getZmanim()
    {
        //given

        ZmanimService service = factory.newInstance();
        String timeZome = "Europe/London";
        double lat = 51.5073219;
        double lng = -0.1276474;

        //when
        Single<ZmanimFeed> single = service.getZmanim(timeZome, lat, lng);

        ZmanimFeed feed = single.blockingGet();

        //then
        assertNotNull(feed);
        assertNotEquals(0, feed.Alos16point1Degrees);
        assertNotEquals(0, feed.Sunrise);
        assertNotEquals(0, feed.SofZmanShemaGra);
        assertNotEquals(0, feed.SofZmanTefilahGra);
        assertNotEquals(0, feed.Chatzos);
        assertNotEquals(0, feed.MinchaGedolah);
        assertNotEquals(0, feed.PlagHamincha);
        assertNotEquals(0, feed.Shkia);
        assertNotEquals(0, feed.Tzais);
    }

}