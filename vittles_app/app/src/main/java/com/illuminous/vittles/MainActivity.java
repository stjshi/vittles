package com.illuminous.vittles;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.y;
import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    YelpFusionApiFactory apiFactory;
    TextView minfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        minfo = (TextView) findViewById(R.id.business_display);
        setContentView(R.layout.activity_main);
            apiFactory = new YelpFusionApiFactory();
            try {
                Map<String, String> params = new HashMap<>();
                YelpFusionApi yelpFusionApi = apiFactory.createAPI("0x2eOuAzs_QARDOGy6UFpw", "lCQaEU3PrlJcCkWS3HS4QHREdRZSY9I6TleyVwFhwV3tf5kW154TSR3CYZSF2qVI");
                params.put("term", "mexican food");
                params.put("latitude", "36.999848");
                params.put("longitude", "-122.062926");
                Call<SearchResponse> call= yelpFusionApi.getBusinessSearch(params);
                SearchResponse searchResponse = call.execute().body();

                ArrayList<Business> businesses = searchResponse.getBusinesses();
                for (Business business : businesses) {
                    String businessName = business.getName();
                    Log.v("business name", businessName);
                }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
