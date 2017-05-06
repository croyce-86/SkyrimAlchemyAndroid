package com.siegester.skyrimalchemy.Utilities;

import android.content.Context;

import com.siegester.skyrimalchemy.Effect.Effect;
import com.siegester.skyrimalchemy.Effect.EffectParser;
import com.siegester.skyrimalchemy.R;

import junit.framework.Assert;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SkyrimAlchemy
 * <p/>
 * Created by Siegester on 7/14/2016.
 */
public class EffectList {

   private final String XMLFileLocation = "xml/all_effects.xml";
   private ArrayList<Effect> _allEffects;

   private static EffectList _listInstance = null;

   public static EffectList getInstance( Context activeContext )
   {
      if ( _listInstance == null )
      {
         _listInstance = new EffectList();
         InputStream effectIStream;
         effectIStream = activeContext.getResources().openRawResource( R.raw.all_effects );
         EffectList._listInstance.addEffectsFromXML( effectIStream );
         //addEffectsFromXML( XMLFileLocation );
      }
      return _listInstance;
   }

   private EffectList()
   {
      // do nothing
   }

   public void addEffectsFromXML(String xmlFile)
   {
      try
      {
         File inpFile = new File(xmlFile);
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         EffectParser userHandler = new EffectParser();
         saxParser.parse(inpFile, userHandler);
         _allEffects = userHandler.getCompletedList();
      }
      catch ( SAXException | IOException | ParserConfigurationException e )
      {
         e.printStackTrace();
      }
   }

   public void addEffectsFromXML( InputStream inpStream )
   {
      Assert.assertTrue( "InputStream cannot be null", inpStream != null );
      try
      {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         EffectParser userHandler = new EffectParser();
         saxParser.parse(inpStream, userHandler);
         _allEffects = userHandler.getCompletedList();
      }
      catch ( SAXException | IOException | ParserConfigurationException e )
      {
         e.printStackTrace();
      }
   }

   public Effect findEffect(String EffectName )
   {
      for ( Effect Eff : _allEffects )
      {
         if ( EffectName.compareTo( Eff.get_name() ) == 0 )
         {
            return Eff;
         }
      }
      return null;
   }

   public Effect findEffect( int EffectId )
   {
      for ( Effect Eff : _allEffects )
      {
         if ( EffectId == Eff.get_id() )
         {
            return Eff;
         }
      }
      return null;
   }

   public ArrayList<Effect> getAllEffects()
   {
      return _allEffects;
   }

}
