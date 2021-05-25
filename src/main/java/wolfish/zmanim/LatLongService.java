package wolfish.zmanim;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LatLongService {

    @GET("geocode/v1/json?key=b287f9d0ff91428d9cc8c64778db4c03")
    Single<LatLongFeed> getCoordinates(@Query("q") String location);

}
