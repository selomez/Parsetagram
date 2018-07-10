package com.example.selzerai.parsetagram;


import android.app.Application;

import com.parse.Parse;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final Parse.Configuration config = new Parse.Configuration.Builder(this)
                .applicationId("selomez-fbustagram")
                .clientKey("EthiopiaHagare1")
                .server("http://selomez-fbustagram.herokuapp.com/parse")
                .build();

        Parse.initialize(config);
    }
}
