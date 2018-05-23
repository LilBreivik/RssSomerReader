package sems.rsssomereader.database.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sems.rsssomereader.database.dao.RssChannelDao;
import sems.rsssomereader.database.RssChannel;


@Database(entities = {RssChannel.class}, version = 1)
public abstract class RssChannelRoomDatabase extends RoomDatabase {

    public abstract RssChannelDao rssChannelDao();


    private static RssChannelRoomDatabase INSTANCE;


    static RssChannelRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (RssChannelRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            RssChannelRoomDatabase.class, "RssChannels")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}