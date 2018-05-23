package sems.rsssomereader.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import sems.rsssomereader.database.RssChannel;
import sems.rsssomereader.database.RssItem;

@Dao
public interface RssItemDao {

    @Insert
    void insertItem(RssItem item);

    @Delete
    void deleteItem(RssItem item);

    @Query("SELECT * FROM RssChannels INNER JOIN RssItems ON RssChannels.RssChannelId = RssItems.RssItemId WHERE ChannelKind IN(:channelKind)")
    List<RssItem>  findItemByChannelKind(String channelKind);

    @Query("SELECT * FROM RssItems WHERE RssChannelId IN(:channelId)")
    List<RssItem>  findItemByChannel(int channelId);

    @Query("SELECT * FROM RssItems WHERE Title IN(:title) AND Description IN(:description) AND LINK IN(:link)")
    RssItem  findItemByTitleDescriptionLink(String title, String description, String link);

    @Query("SELECT * FROM RssItems")
    List<RssItem> getAllItems();

    @Query("SELECT * FROM RssItems WHERE  PublicationDate")
    List<RssItem> getLastItems();

}