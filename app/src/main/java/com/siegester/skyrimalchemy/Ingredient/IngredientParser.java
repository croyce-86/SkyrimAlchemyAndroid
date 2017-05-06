package com.siegester.skyrimalchemy.Ingredient;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * SkyrimAlchemy2
 * <p/>
 * Created by Siegester on 7/26/2016.
 */
public class IngredientParser extends DefaultHandler {

   private ArrayList<Ingredient> _ingredientList;
   private Ingredient _ingredient;

   private boolean _idSet = false;
   private boolean _nameSet = false;
   private boolean _firstSet = false;
   private boolean _secondSet = false;
   private boolean _thirdSet = false;
   private boolean _fourthSet = false;
   private boolean _imgSet = false;
   private boolean _valueSet = false;
   private boolean _weightSet = false;


   @Override
   public void startElement(String uri,
                            String localName,
                            String qName,
                            Attributes attributes)
           throws SAXException {

      if ( qName.equalsIgnoreCase("AllIngredients") )
      {
         _ingredientList = new ArrayList<>();
      }
      else if ( qName.equalsIgnoreCase("Ingredient") )
      {
         _ingredient = new Ingredient();
      }
      else if ( qName.equalsIgnoreCase("Name") )
      {
         _nameSet = true;
      }
      else if ( qName.equalsIgnoreCase("ID") )
      {
         _idSet = true;
      }
      else if ( qName.equalsIgnoreCase("FirstEffect") )
      {
         _firstSet = true;
      }
      else if ( qName.equalsIgnoreCase("SecondEffect"))
      {
         _secondSet = true;
      }
      else if ( qName.equalsIgnoreCase("ThirdEffect"))
      {
         _thirdSet = true;
      }
      else if ( qName.equalsIgnoreCase("FourthEffect"))
      {
         _fourthSet = true;
      }
      else if ( qName.equalsIgnoreCase("Image"))
      {
         _imgSet = true;
      }
      else if ( qName.equalsIgnoreCase("Value"))
      {
         _valueSet = true;
      }
      else if ( qName.equalsIgnoreCase("Weight"))
      {
         _weightSet = true;
      }
   }

   public void endElement(String uri,
                          String localName,
                          String qName) throws SAXException {
      if (qName.equalsIgnoreCase("Ingredient"))
      {
         _ingredientList.add(_ingredient);
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
         _ingredient.set_name( value );
         _nameSet = false;
      }
      if ( _idSet )
      {
         _ingredient.set_id( Integer.parseInt( value ) );
         _idSet = false;
      }
      if ( _firstSet )
      {
         _ingredient.set_firstEffect( value );
         _firstSet = false;
      }
      if ( _secondSet )
      {
         _ingredient.set_secondEffect( value );
         _secondSet = false;
      }
      if ( _thirdSet )
      {
         _ingredient.set_thirdEffect( value );
         _thirdSet = false;
      }
      if ( _fourthSet )
      {
         _ingredient.set_fourthEffect( value );
         _fourthSet = false;
      }
      if ( _valueSet )
      {
         _ingredient.set_value( Integer.parseInt( value ) );
         _valueSet = false;
      }
      if ( _imgSet )
      {
         _ingredient.set_imgLocation( value );
         _imgSet = false;
      }
      if ( _weightSet )
      {
         _ingredient.set_weight( Float.parseFloat( value ) );
         _weightSet = false;
      }
   }

   public ArrayList<Ingredient> getCompletedList()
   {
      return _ingredientList;
   }
}
