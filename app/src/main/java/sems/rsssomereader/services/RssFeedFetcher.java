package sems.rsssomereader.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import sems.rsssomereader.database.room.RssRepository;
import sems.rsssomereader.rssfeeds.RssWareHouse;

public class RssFeedFetcher extends Service {

    RssRepository repo;
    RssWareHouse wareHouse;

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public RssFeedFetcher getService() {
            // Return this instance of LocalService so clients can call public methods
            return RssFeedFetcher.this;
        }
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {


        this.repo = new RssRepository( getApplication() );

        this.wareHouse = new RssWareHouse( this.repo);




    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart(Intent intent, int startid) {


    }
}
