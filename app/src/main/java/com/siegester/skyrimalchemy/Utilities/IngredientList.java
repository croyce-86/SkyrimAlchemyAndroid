package com.siegester.skyrimalchemy.Utilities;

import android.content.Context;

import com.siegester.skyrimalchemy.Ingredient.Ingredient;
import com.siegester.skyrimalchemy.Ingredient.IngredientParser;
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
 * SkyrimAlchemy2
 * <p/>
 * Created by Siegester on 7/26/2016.
 */
public class IngredientList {

    private final String XMLFileLocation = "xml/all_effects.xml";
    private ArrayList<Ingredient> _allIngredients;

    private static IngredientList _listInstance = null;

    public static IngredientList getInstance( Context activeContext ) {
        if ( _listInstance == null )
        {
            _listInstance = new IngredientList();
            InputStream ingredientIStream;
            ingredientIStream = activeContext.getResources().openRawResource( R.raw.all_ingredients );
            IngredientList._listInstance.addEffectsFromXML( ingredientIStream );
            //addEffectsFromXML( XMLFileLocation );
        }
        return _listInstance;
    }

    private IngredientList()
    {
        // do nothing
    }

    public void addEffectsFromXML(String xmlFile) {
        try {
            File inpFile = new File(xmlFile);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            IngredientParser userHandler = new IngredientParser();
            saxParser.parse(inpFile, userHandler);
            _allIngredients = userHandler.getCompletedList();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void addEffectsFromXML(InputStream inpStream) {
        Assert.assertTrue("InputStream cannot be null", inpStream != null);
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            IngredientParser userHandler = new IngredientParser();
            saxParser.parse(inpStream, userHandler);
            _allIngredients = userHandler.getCompletedList();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Ingredient findIngredient(final String IngredientName) {
        for (Ingredient ingredient : _allIngredients) {
            if (IngredientName.compareTo(ingredient.get_name()) == 0) {
                return ingredient;
            }
        }
        return null;
    }

    public Ingredient findIngredient(final int IngredientId) {
        for (Ingredient ingredient : _allIngredients) {
            if (IngredientId == ingredient.get_id()) {
                return ingredient;
            }
        }
        return null;
    }

    public ArrayList<Ingredient> getAllIngredients() {
        return _allIngredients;
    }
}
