package com.siegester.skyrimalchemy.Effect;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * SkyrimAlchemy
 * <p/>
 * Created by Siegester on 7/16/2016.
 */
public class EffectParser extends DefaultHandler {

   private ArrayList<Effect> _effectList;
   private Effect _effect;

   private boolean _nameSet = false;
   private boolean _idSet = false;
   private boolean _classSet = false;
   private boolean _beneficialSet = false;
   private boolean _magSet = false;
   private boolean _durSet = false;
   private boolean _valueSet = false;


   @Override
   public void startElement(String uri,
                            String localName,
                            String qName,
                            Attributes attributes)
           throws SAXException {

      if ( qName.equalsIgnoreCase("AllEffects") )
      {
         _effectList = new ArrayList<>();
      }
      else if ( qName.equalsIgnoreCase("Effect") )
      {
         _effect = new Effect();
      }
      else if ( qName.equalsIgnoreCase("Name") )
      {
         _nameSet = true;
      }
      else if ( qName.equalsIgnoreCase("ID") )
      {
         _idSet = true;
      }
      else if ( qName.equalsIgnoreCase("Class") )
      {
         _classSet = true;
      }
      else if ( qName.equalsIgnoreCase("Beneficial"))
      {
         _beneficialSet = true;
      }
      else if ( qName.equalsIgnoreCase("BaseMag"))
      {
         _magSet = true;
      }
      else if ( qName.equalsIgnoreCase("BaseDur"))
      {
         _durSet = true;
      }
      else if ( qName.equalsIgnoreCase("Value"))
      {
         _valueSet = true;
      }
   }

   public void endElement(String uri,
                          String localName,
                          String qName) throws SAXException {
      if (qName.equalsIgnoreCase("Effect"))
      {
         _effectList.add(_effect);
      }
   }

   public void characters( char ch[], int start, int length )
           throws SAXException
   {
      String value = new String(ch, start, length);
      value = value.trim();
      if ( !value.equalsIgnoreCase("") )

      if ( _nameSet )
      {
         _effect.set_name( value );
         _nameSet = false;
      }
      if ( _idSet )
      {
         _effect.set_id( Integer.parseInt( value ) );
         _idSet = false;
      }
      if ( _classSet )
      {
         _effect.set_effectClass( EffectClass.valueOf( value ) );
         _classSet = false;
      }
      if ( _beneficialSet )
      {
         _effect.set_isBeneficial( value.compareTo( "1" ) == 0);
         _beneficialSet = false;
      }
      if ( _magSet )
      {
         _effect.set_baseMagnitude( Integer.parseInt( value ) );
         _magSet = false;
      }
      if ( _durSet )
      {
         _effect.set_baseDuration( Integer.parseInt( value ) );
         _durSet = false;
      }
      if ( _valueSet )
      {
         _effect.set_value( Integer.parseInt( value ) );
         _valueSet = false;
      }
   }

   public ArrayList<Effect> getCompletedList()
   {
      return _effectList;
   }
}
