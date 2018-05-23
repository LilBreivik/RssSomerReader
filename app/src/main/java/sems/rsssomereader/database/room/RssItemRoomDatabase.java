package sems.rsssomereader.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import sems.rsssomereader.database.RssChannel;
import sems.rsssomereader.database.dao.RssItemDao;
import sems.rsssomereader.database.RssItem;


@Database(entities = {RssChannel.class, RssItem.class}, version = 1)
public abstract class RssItemRoomDatabase extends RoomDatabase {

    public abstract RssItemDao rssItemDao();


    private static RssItemRoomDatabase INSTANCE;


    static RssItemRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (RssItemRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            RssItemRoomDatabase .class, "RssItems")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}