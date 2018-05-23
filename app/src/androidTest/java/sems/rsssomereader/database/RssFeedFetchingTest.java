package sems.rsssomereader.database;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mcsoxford.rss.RSSException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeoutException;

import sems.rsssomereader.RssSomeReaderApp;
import sems.rsssomereader.database.room.RssRepository;
import sems.rsssomereader.rssfeeds.RssWareHouse;
import sems.rsssomereader.services.RssFeedFetcher;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class RssFeedFetchingTest extends Application{

   // @Rule
    //public final ServiceTestRule mServiceRule = new ServiceTestRule();



    private Context instrumentationCtx;

    @Before
    public void setup() {

        instrumentationCtx = InstrumentationRegistry.getContext();

    }


    /**
     *
     * A test to check if the
     * library can find the rss repo, and to check if
     * the device can connect to the internet
     *
     * -> check readme if there were errors...
     * */

    @Test
    public void testFetchingRssFeeds(  ){


//        assertThat(instrumentationCtx, is(notNullValue()));

        RssRepository repo = new RssRepository(instrumentationCtx  );

        RssWareHouse RssWareHouse = new RssWareHouse(repo);

        String uri = "http://feeds.bbci.co.uk/news/world/rss.xml";


        // There shall be no Error
        // if an RssChanne is read  ...
        RSSException dummyEx = null;


        try{

            RssWareHouse.fetchRssChannel(uri);
        }
        catch(RSSException rssEx){

            dummyEx = rssEx;
        }

        assertThat(dummyEx, is(nullValue()));
    }


 /*   public void testWithStartedService() {

       try {

           // Create the service Intent.
           Intent serviceIntent =
                   new Intent(InstrumentationRegistry.getTargetContext(),
                           RssFeedFetcher.class);


           // Bind the service and grab a reference to the binder.
           IBinder binder = mServiceRule.bindService(serviceIntent);

           // Get the reference to the service, or you can call
           // public methods on the binder directly.
           RssFeedFetcher service =
                   (( RssFeedFetcher.LocalBinder) binder).getService();

           // Verify that the service is working correctly.



         //  assertThat(service.getRandomInt(), is(any(Integer.class)));
        }
        catch (TimeoutException e) {


        }

        // Add your test code here.
    }
*/



}
