package wolfish.zmanim;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZmanimService {

    @GET("/api/zmanim?mode=basic")
    Single<ZmanimFeed> getZmanim(@Query("timezone") String timezone,
                                 @Query("latitude") double lat,
                                 @Query("longitude") double lng);

}
