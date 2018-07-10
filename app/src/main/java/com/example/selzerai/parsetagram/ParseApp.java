package com.example.selzerai.parsetagram;


import android.app.Application;

import com.example.selzerai.parsetagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration config = new Parse.Configuration.Builder(this)
                .applicationId("selomez-fbustagram")
                .clientKey("EthiopiaHagare1")
                .server("http://selomez-fbustagram.herokuapp.com/parse")
                .build();
        Parse.initialize(config);
    }
}
