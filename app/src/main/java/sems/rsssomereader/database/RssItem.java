package sems.rsssomereader.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;



@Entity(tableName = "RssItems",
        foreignKeys = @ForeignKey(entity =  RssChannel.class,
        parentColumns = "RssChannelId",
        childColumns = "RssChannelId",
        onDelete = CASCADE))

public class RssItem {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "RssItemId")
    public int rssItemId;


    public RssItem(){ }


    public RssItem(int rssItemId,
                      int rssChannelId,
                        String title,
                            String description,
                                String link,
                                    String publicationDate,
                                        String imagePath
                   ){

        this.setrssItemId(rssItemId);
        this.setrssChannelId(rssChannelId);

        this.settitle(title);
        this.setdescription(description);
        this.setlink(link);
        this.setpublicationDate(publicationDate);
        this.setimagePath(imagePath);


    }


    public int getrssItemId(){

        return this.rssItemId;
    }

    public void setrssItemId(int rssItemId){

        this.rssItemId = rssItemId;
    }



    @ColumnInfo(name = "RssChannelId")
    @NonNull
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

    @ColumnInfo(name = "PublicationDate")
    @NonNull
    public String publicationDate;



    public String getpublicationDate(){

        return this.publicationDate;
    }

    public void setpublicationDate(String publicationDate){

        this.publicationDate = publicationDate;
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