package com.siegester.skyrimalchemy.Ingredient;

import android.util.Log;

import junit.framework.Assert;

import java.util.HashSet;

/**
 * SkyrimAlchemy
 * <p/>
 * Created by Siegester on 7/14/2016.
 */
public class Ingredient
{
   private static final String TAG = "Ingr";

   private int    _id           = -1;
   private String _name         = "No Item";
   private String _firstEffect  = null;
   private String _secondEffect = null;
   private String _thirdEffect  = null;
   private String _fourthEffect = null;
   private String _imgLocation  = null;
   private int    _value        = 0;
   private float  _weight       = 0.0f;

   public final boolean compareTo( final Ingredient secondIngredient )
   {
      return _id == secondIngredient.get_id();
   }

   public final int get_id()
   {
      return _id;
   }

   final void set_id( final int id )
   {
      this._id = id;
   }

   public String get_name()
   {
      return _name;
   }

   final void set_name( String _name )
   {
      this._name = _name;
   }

   final String get_firstEffect()
   {
      return _firstEffect;
   }

   final void set_firstEffect( String _firstEffect )
   {
      this._firstEffect = _firstEffect;
   }

   final String get_secondEffect()
   {
      return _secondEffect;
   }

   final void set_secondEffect( String _secondEffect )
   {
      this._secondEffect = _secondEffect;
   }

   final String get_thirdEffect()
   {
      return _thirdEffect;
   }

   final void set_thirdEffect( String _thirdEffect )
   {
      this._thirdEffect = _thirdEffect;
   }

   final String get_fourthEffect()
   {
      return _fourthEffect;
   }

   final void set_fourthEffect( String _fourthEffect )
   {
      this._fourthEffect = _fourthEffect;
   }

   public String get_imgLocation()
   {
      return _imgLocation;
   }

   final void set_imgLocation( String _imgLocation )
   {
      this._imgLocation = _imgLocation;
   }

   final int get_value()
   {
      return _value;
   }

   final void set_value( int _value )
   {
      this._value = _value;
   }

   final float get_weight()
   {
      return _weight;
   }

   final void set_weight( float _weight )
   {
      this._weight = _weight;
   }

   public boolean hasEffect( final String EffectName )
   {
      boolean result = false;
      if ( _firstEffect.compareTo( EffectName ) == 0 )
      {
         result = true;
      }
      if ( _secondEffect.compareTo( EffectName ) == 0 )
      {
         result = true;
      }
      if ( _thirdEffect.compareTo( EffectName ) == 0 )
      {
         result = true;
      }
      if ( _fourthEffect.compareTo( EffectName ) == 0 )
      {
         result = true;
      }
      return result;
   }

   public HashSet <String> findMatchingEffects( Ingredient ingredient2 )
   {
      HashSet <String> matches = new HashSet <>();

      Assert.assertTrue( "Comparing same ingredient!", _id != ingredient2.get_id() );

      if ( hasEffect( ingredient2.get_firstEffect() ) )
      {
         matches.add( ingredient2.get_firstEffect() );
      }

      if ( hasEffect( ingredient2.get_secondEffect() ) )
      {
         matches.add( ingredient2.get_secondEffect() );
      }

      if ( hasEffect( ingredient2.get_thirdEffect() ) )
      {
         matches.add( ingredient2.get_thirdEffect() );
      }

      if ( hasEffect( ingredient2.get_fourthEffect() ) )
      {
         matches.add( ingredient2.get_fourthEffect() );
      }

      Log.d( TAG, "Find matches for " + _name + " and " + ingredient2.get_name() );
      for ( String s : matches )
      {
         Log.d( TAG, "\tMatch: " + s );
      }

      return matches;
   }

   public final HashSet <String> findMatchingEffects( final Ingredient ingredient2, final Ingredient ingredient3 )
   {
      final HashSet <String> matches = new HashSet <>( 0 );

      matches.addAll( findMatchingEffects( ingredient2 ) );

      matches.addAll( findMatchingEffects( ingredient3 ) );

      matches.addAll( ingredient2.findMatchingEffects( ingredient3 ) );

      return matches;
   }
}
