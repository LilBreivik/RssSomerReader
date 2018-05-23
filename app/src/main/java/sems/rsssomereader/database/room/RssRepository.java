package sems.rsssomereader.database.room;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sems.rsssomereader.database.RssItem;
import sems.rsssomereader.database.dao.RssChannelDao;
import sems.rsssomereader.database.dao.RssItemDao;
import sems.rsssomereader.database.RssChannel;

public class RssRepository {

    private RssItemDao rssItemDao;

    private RssChannelDao rssChannelDao;


    /**
     *
     * Universal Wrapper
     *
     * for all database related-actions
     *
     * */


    public RssRepository(Context context) {

        RssChannelRoomDatabase rssChannels = RssChannelRoomDatabase.getDatabase(context);
        RssItemRoomDatabase rssItems = RssItemRoomDatabase.getDatabase(context);

        rssChannelDao = rssChannels.rssChannelDao();
        rssItemDao =  rssItems.rssItemDao();

    }



    public void insertChannel (RssChannel rssChannel) {

        new insertChannelAsynchronous(rssChannelDao).execute(rssChannel);
    }


    public void insertItem (RssItem rssItem) {

        new insertItemAsynchronous( rssItemDao).execute( rssItem);
    }



    public RssItem selectRssFeeds(String title, String description, String link){

        return rssItemDao.findItemByTitleDescriptionLink(title, description, link);
    }


    public Map<Integer , List<String> >  createRssSchedule(){


        Map<Integer , List<String> > RssSchedule = new HashMap<Integer , List<String> >();

        for (RssChannel channel: rssChannelDao.getAllChannels()) {


            List<String> channelLinks = new ArrayList<String>();

            rssItemDao.findItemByChannel(channel.rssChannelId).forEach(

                    (item) ->{channelLinks.add(item.link);}
            );

            RssSchedule.put(channel.rssChannelId, channelLinks);
        }


        return RssSchedule;
    }

    private static class insertItemAsynchronous extends AsyncTask<RssItem, Void, Void> {

        private RssItemDao mAsyncRssItemDao;

        insertItemAsynchronous(RssItemDao dao) {

            mAsyncRssItemDao = dao;
        }

        @Override
        protected Void doInBackground(final RssItem... params) {

            mAsyncRssItemDao.insertItem(params[0]);
            return null;
        }
    }


    private static class insertChannelAsynchronous  extends AsyncTask<RssChannel, Void, Void> {

        private RssChannelDao mAsyncRssChannelDao;

        insertChannelAsynchronous(RssChannelDao dao) {

            mAsyncRssChannelDao = dao;
        }

        @Override
        protected Void doInBackground(final RssChannel... params) {

            mAsyncRssChannelDao.insertChannel(params[0]);
            return null;
        }
    }
}