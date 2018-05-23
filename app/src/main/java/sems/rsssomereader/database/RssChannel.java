package sems.rsssomereader.database;

import android.arch.persistence.room.ColumnInfo;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;


@Entity(tableName = "RssChannels")
public class RssChannel {


    public RssChannel(int rssChannelId,
                         String title,
                             String description,
                                String link,
                                    String imagePath
    ){


        this.setrssChannelId(rssChannelId);

        this.settitle(title);
        this.setdescription(description);
        this.setlink(link);
        this.setimagePath(imagePath);


    }


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "RssChannelId")
    public int rssChannelId;

    public int getrssChannelId(){

        return this.rssChannelId;
    }

    public void setrssChannelId(int rssChannelId){

        this.rssChannelId = rssChannelId;
    }


    @ColumnInfo(name = "Title")
    @NonNull
    public String title;

    public String gettitle(){

        return this.title;
    }

    public void settitle(String title){

        this.title = title;
    }

    @ColumnInfo(name = "Description")
    @NonNull
    public String description;

    public String getdescription(){

        return this.description;
    }

    public void setdescription(String description){

        this.description = description;
    }


    @ColumnInfo(name = "Link")
    @NonNull
    public String link;

    public String getlink(){

        return this.link;
    }

    public void setlink(String link){

        this.link = link;
    }

    /**
     *
     * We have to provide a
     * way, to let the user define
     * an identifier, to categorize different channels
     * on the same tag
     *
     * */

    @ColumnInfo(name = "ChannelKind")
    public String channelKind;


    public String getchannelKind(){

        return this.channelKind;
    }

    public void setchannelKind(String channelKind){

        this.channelKind = channelKind;
    }
    /**
     *
     * Describes the path to
     * the needed rss related image ...
     *
     * If there is no image defined (Null in the filed)
     * we will take our logo....
     */

    @ColumnInfo(name = "ImagePath")
    public String imagePath;

    public String getimagePath(){

        return this.imagePath;
    }

    public void setimagePath(String imagePath){

        this.imagePath = imagePath;
    }


}
