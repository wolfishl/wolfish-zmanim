package wolfish.zmanim;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create wolfish.weather.OpenWeatherMapService classes using Retrofit
 */
public class LatLongServiceFactory {

        public LatLongService newInstance()
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.opencagedata.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            LatLongService service = retrofit.create(LatLongService.class);

            return service;
        }



}
