package wolfish.zmanim;

import java.util.List;

public class LatLongFeed {

     List<Place> results;

     public class Place{
          Annotation annotations;
          Geometry geometry;
          Components components;
     }

          public class Annotation
          {
               Timezone timezone;
          }

               public class Timezone
               {
                    String name;
               }


          public class Geometry
               {
                    double lat;
                    double lng;
               }

          public class Components{
               String city;
               String country;
               String state;
          }

}
