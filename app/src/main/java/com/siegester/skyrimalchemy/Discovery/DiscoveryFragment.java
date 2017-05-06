package com.siegester.skyrimalchemy.Discovery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.siegester.skyrimalchemy.Ingredient.Ingredient;
import com.siegester.skyrimalchemy.Ingredient.IngredientAdaptor;
import com.siegester.skyrimalchemy.R.layout;
import com.siegester.skyrimalchemy.R.id;
import com.siegester.skyrimalchemy.Utilities.IngredientList;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Created by Siegester on 3/31/2017.
 */

public class DiscoveryFragment extends Fragment
{

   private static final String TAG = "DiscFrag";

   private Ingredient _firstIngredient  = new Ingredient();
   private Ingredient _secondIngredient = new Ingredient();
   private Ingredient _thirdIngredient  = new Ingredient();

   private Spinner _firstSpinner  = null;
   private Spinner _secondSpinner = null;
   private Spinner _thirdSpinner  = null;

   private IngredientAdaptor _firstIngredientAdaptor  = null;
   private IngredientAdaptor _secondIngredientAdaptor = null;
   private IngredientAdaptor _thirdIngredientAdaptor  = null;

   /**
    * Use this factory method to create a new instance of
    * this fragment using the provided parameters.
    *
    * @return A new instance of fragment EffectFragment.
    */
   public static DiscoveryFragment newInstance()
   {
      final DiscoveryFragment fragment = new DiscoveryFragment();
      final Bundle            args     = new Bundle();
      fragment.setArguments( args );
      return fragment;
   }

   @Override
   public final void onCreate( final Bundle savedInstance )
   {
      super.onCreate( savedInstance );
   }

   @Override
   public final View onCreateView( final LayoutInflater inflater, final ViewGroup container,
                                   final Bundle savedInstanceState )
   {
      final View baseView = inflater.inflate( layout.discovery_layout, container, false );
      baseView.setBackgroundColor( Color.WHITE );

      _firstSpinner = (Spinner) baseView.findViewById( id.spinner3 );
      _secondSpinner = (Spinner) baseView.findViewById( id.spinner4 );
      _thirdSpinner = (Spinner) baseView.findViewById( id.spinner5 );

      final IngredientList         iList    = IngredientList.getInstance( getContext() );
      final ArrayList <Ingredient> iArrList = iList.getAllIngredients();
      iArrList.add( 0, new Ingredient() );

      _firstIngredientAdaptor = new IngredientAdaptor( getContext(), iArrList );
      _secondIngredientAdaptor = new IngredientAdaptor( getContext(), iArrList );
      _thirdIngredientAdaptor = new IngredientAdaptor( getContext(), iArrList );

      _firstSpinner.setAdapter( _firstIngredientAdaptor );
      _secondSpinner.setAdapter( _secondIngredientAdaptor );
      _thirdSpinner.setAdapter( _thirdIngredientAdaptor );

      final IngredientClickListener _firstIngredientOnClick  = new IngredientClickListener();
      final IngredientClickListener _secondIngredientOnClick = new IngredientClickListener();
      final IngredientClickListener _thirdIngredientOnClick  = new IngredientClickListener();

      _firstSpinner.setOnItemSelectedListener( _firstIngredientOnClick );
      _secondSpinner.setOnItemSelectedListener( _secondIngredientOnClick );
      _thirdSpinner.setOnItemSelectedListener( _thirdIngredientOnClick );

      return baseView;

   }

   final Ingredient get_firstIngredient()
   {
      return _firstIngredient;
   }

   final void set_firstIngredient( final Ingredient ingredient )
   {
      this._firstIngredient = ingredient;
   }

   final Ingredient get_secondIngredient()
   {
      return _secondIngredient;
   }

   final void set_secondIngredient( final Ingredient ingredient )
   {
      this._secondIngredient = ingredient;
   }

   final Ingredient get_thirdIngredient()
   {
      return _thirdIngredient;
   }

   final void set_thirdIngredient( final Ingredient ingredient )
   {
      this._thirdIngredient = ingredient;
   }

   private class IngredientClickListener implements OnItemSelectedListener
   {

      @Override
      public final void onItemSelected( final AdapterView adptView, final View v, final int selectedItem, final long arg0 )
      {
         if ( Objects.equals( adptView, _firstSpinner ) )
         {
            if ( selectedItem == 0 )
            {
               _secondIngredientAdaptor.setDisabled( -1 );
               _thirdIngredientAdaptor.setDisabled( -1 );
            }
            else
            {
               _secondIngredientAdaptor.setDisabled( selectedItem );
               _thirdIngredientAdaptor.setDisabled( selectedItem );
            }

            set_firstIngredient( _firstIngredientAdaptor.getItem( selectedItem ) );
         }

         if ( Objects.equals( adptView, _secondSpinner ) )
         {
            if ( selectedItem == 0 )
            {
               _firstIngredientAdaptor.setDisabled( -1 );
               _thirdIngredientAdaptor.setDisabled( -1 );
            }
            else
            {
               _firstIngredientAdaptor.setDisabled( selectedItem );
               _thirdIngredientAdaptor.setDisabled( selectedItem );
            }

            set_secondIngredient( _secondIngredientAdaptor.getItem( selectedItem ) );
         }

         if ( Objects.equals( adptView, _thirdSpinner ) )
         {
            if ( selectedItem == 0 )
            {
               _firstIngredientAdaptor.setDisabled( -1 );
               _secondIngredientAdaptor.setDisabled( -1 );
            }
            else
            {
               _firstIngredientAdaptor.setDisabled( selectedItem );
               _secondIngredientAdaptor.setDisabled( selectedItem );
            }

            set_thirdIngredient( _thirdIngredientAdaptor.getItem( selectedItem ) );
         }
         updateEffects();
      }

      private void updateEffects()
      {
         final boolean FirstSet  = get_firstIngredient().compareTo( new Ingredient() ) == false;
         final boolean SecondSet = get_secondIngredient().compareTo( new Ingredient() ) == false;
         final boolean ThirdSet  = get_thirdIngredient().compareTo( new Ingredient() ) == false;

         if ( FirstSet && SecondSet && ThirdSet )
         {
            get_firstIngredient().findMatchingEffects( get_secondIngredient(), get_thirdIngredient() );
         }
         else
         {
            if ( FirstSet )
            {
               if ( SecondSet )
               {
                  get_firstIngredient().findMatchingEffects( get_secondIngredient() );
               }
               else if ( ThirdSet )
               {
                  get_firstIngredient().findMatchingEffects( get_thirdIngredient() );
               }
               else
               {
                  Log.d( TAG, "Not enough items set" );
               }
            }
            else if ( SecondSet )
            {
               if ( ThirdSet )
               {
                  get_secondIngredient().findMatchingEffects( get_thirdIngredient() );
               }
               else
               {
                  Log.d( TAG, "Not enough items set" );
               }
            }
            else
            {
               Log.d( TAG, "Not enough items set" );
            }
         }
      }

      @Override
      public final void onNothingSelected( final AdapterView parent )
      {
         // Another interface callback
      }
   }

}
