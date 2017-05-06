package com.siegester.skyrimalchemy;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.siegester.skyrimalchemy.Discovery.DiscoveryFragment;
import com.siegester.skyrimalchemy.Effect.EffectFragment;
import com.siegester.skyrimalchemy.Utilities.EffectList;
import com.siegester.skyrimalchemy.Ingredient.IngredientFragment;
import com.siegester.skyrimalchemy.Utilities.IngredientList;
import com.siegester.skyrimalchemy.Tips.TipsFragment;

import java.io.InputStream;


public class TabBarMain extends AppCompatActivity {

   private enum Tabs {
      Effects("Effects"),
      Ingredients("Ingredients"),
      Discovery("Discovery"),
      Tips("Tips");

      private final String _name;

      Tabs(String name)
      {
         _name = name;
      }

      public boolean equals( String otherEffect )
      {
         return otherEffect != null && _name.compareTo( otherEffect ) == 0;
      }

      public boolean equals( Tabs otherEffect ) {
         return otherEffect != null && this.equals( otherEffect.toString() );
      }

      @Override
      public String toString()
      {
         return _name;
      }

      public static Tabs fromInteger(int x) {
         switch(x) {
            case 0:
               return Effects;
            case 1:
               return Ingredients;
            case 2:
               return Discovery;
            case 3:
               return Tips;
         }
         return null;
      }
   }

   /**
    * The {@link android.support.v4.view.PagerAdapter} that will provide
    * fragments for each of the sections. We use a
    * {@link FragmentPagerAdapter} derivative, which will keep every
    * loaded fragment in memory. If this becomes too memory intensive, it
    * may be best to switch to a
    * {@link android.support.v4.app.FragmentStatePagerAdapter}.
    */
   private SectionsPagerAdapter mSectionsPagerAdapter;

   /**
    * The {@link ViewPager} that will host the section contents.
    */
   private ViewPager mViewPager;

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tab_bar_activity);

      Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
      setSupportActionBar(toolbar);
      // Create the adapter that will return a fragment for each of the three
      // primary sections of the activity.
      mSectionsPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager() );

      // Set up the ViewPager with the sections adapter.
      mViewPager = ( ViewPager ) findViewById(R.id.container);
      if ( mViewPager != null )
         mViewPager.setAdapter( mSectionsPagerAdapter );

      TabLayout tabLayout = ( TabLayout ) findViewById(R.id.tabs);
      if ( tabLayout != null )
         tabLayout.setupWithViewPager( mViewPager );
   }


   @Override
   public boolean onCreateOptionsMenu( Menu menu ) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_ingredient_details_view, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected( MenuItem item ) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if ( id == R.id.action_settings ) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   /**
    * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
    * one of the sections/tabs/pages.
    */
   private class SectionsPagerAdapter extends FragmentPagerAdapter {

      SectionsPagerAdapter( FragmentManager fm ) {
         super(fm);
      }

      @Override
      public Fragment getItem( int position ) {
         Tabs tabPosition = Tabs.fromInteger( position );
         Fragment value = null;
         if ( tabPosition != null )
         {
            switch ( tabPosition ) {
               case Discovery:
                  value = DiscoveryFragment.newInstance();
                  break;
               case Ingredients:
                  value = IngredientFragment.newInstance();
                  break;
               case Effects:
                  value = EffectFragment.newInstance();
                  break;
               case Tips:
                  value = TipsFragment.newInstance();
                  break;
               default:
                  // do nothing
            }
         }
         return value;
      }

      @Override
      public int getCount() {
         return Tabs.values().length;
      }

      @Override
      public CharSequence getPageTitle( int position ) {
         Tabs tabPosition = Tabs.fromInteger( position );
         if ( tabPosition != null )
         {
            return tabPosition.toString();
         }
         return null;
      }
   }
}
