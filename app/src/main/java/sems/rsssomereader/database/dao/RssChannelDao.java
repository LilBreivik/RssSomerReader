package sems.rsssomereader.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import sems.rsssomereader.database.RssChannel;

@Dao
public interface RssChannelDao {

    @Insert
    void insertChannel(RssChannel channel);

    @Delete
    void deleteChannel(RssChannel channel);

    @Query("SELECT * FROM RssChannels WHERE RssChannelId IN(:channelId)")
    RssChannel  findChannelById(int channelId);

    @Query("SELECT * FROM RssChannels WHERE ChannelKind IN(:channelKind)")
    List<RssChannel> findChannelByKind(String channelKind);

    @Query("SELECT * FROM RssChannels")
    List<RssChannel> getAllChannels();



}
