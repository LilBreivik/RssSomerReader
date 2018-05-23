package sems.rsssomereader.rssfeeds;

import android.content.SyncStatusObserver;

import org.mcsoxford.rss.RSSException;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import java.util.List;
import java.util.Map;
import java.util.Set;

import sems.rsssomereader.database.RssItem;
import sems.rsssomereader.database.room.RssRepository;

public class RssWareHouse {


    private RssRepository repo;


    public RssWareHouse(RssRepository repo){

        this.setRepository(repo);
    }




    public void setRepository( RssRepository repo){

        this.repo = repo;
    }


    public void workOnPipeline(){


        Map<Integer , List<String> >  rssSchedule = this.repo.createRssSchedule();

        this.WareHousePipeline( rssSchedule);


    }



    public void fetchRssChannel(String uri) throws RSSException{

        RSSReader reader = new RSSReader();



        RSSFeed feed = reader.load(uri);

        System.out.println( feed.getDescription() + " " +
                                feed.getTitle() + " ");


    }

    private class RssChecker
    {

        public boolean checkItemIsThere(RssRepository repo, RSSItem item)
        {
            return ( null != repo.selectRssFeeds(  item.getDescription(),
                                                    item.getLink().toString(),
                                                            item.getTitle()));
        }
    }


    private class RssConverter
    {

        public RssItem convertRSSitem(int ChannelId , RSSItem item)
        {

            RssItem rssitem = new  RssItem();

            rssitem.setrssChannelId(ChannelId );

            rssitem.settitle(item.getTitle());
            rssitem.setlink( item.getLink().toString());
            rssitem.setdescription(item.getDescription());
            rssitem.setpublicationDate(item.getPubDate().toString());


            return rssitem;
        }
    }


    private void WareHousePipeline(Map<Integer , List<String> > rssSchedule){

        RssChecker rssItemChecker = new  RssChecker();

        RssConverter rssConverter = new RssConverter();


        Set<Integer> keys =  rssSchedule.keySet();


        for(Integer key : keys){

            for(String rssLink : rssSchedule.get(key) ){

                try{

                    /*
                     *
                     * */

                    RSSFeed fetchedFeed =  fetchFeed(rssLink);

                    fetchedFeed.getItems().forEach((feed) ->{

                        if(!rssItemChecker.checkItemIsThere(this.repo, feed)){


                            RssItem newItemforRssChannel = rssConverter.convertRSSitem(key   , feed);
                            this.repo.insertItem( newItemforRssChannel );


                        }

                    });
                }
                catch(RSSReaderException rssEx){


                }

            }

        }


    }




    private RSSFeed fetchFeed(String RssURI) throws RSSReaderException {

        RSSReader reader = new RSSReader();

        String uri = RssURI;

        RSSFeed feed = reader.load(uri);

        return feed;
    }


}
