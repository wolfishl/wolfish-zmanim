package wolfish.zmanim;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Create wolfish.zmanim.ZmanimService classes using Retrofit
 */

public class ZmanimServiceFactory {

    public ZmanimService newInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wyrezmanim.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        ZmanimService service = retrofit.create(ZmanimService.class);

        return service;
    }

}
