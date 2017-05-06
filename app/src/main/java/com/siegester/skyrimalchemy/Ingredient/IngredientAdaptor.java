package com.siegester.skyrimalchemy.Ingredient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.siegester.skyrimalchemy.R.id;
import com.siegester.skyrimalchemy.R.layout;

import junit.framework.Assert;

import java.util.ArrayList;

/**
 * Created by Siegester on 4/2/2017.
 */

public class IngredientAdaptor extends ArrayAdapter <Ingredient>
{
   private int _disabled = -1;

   public IngredientAdaptor( final Context ctx, final ArrayList <Ingredient> effects )
   {
      super( ctx, android.R.layout.simple_spinner_item, effects );
   }

   public final void setDisabled( final int pos )
   {
      _disabled = pos;
   }

   @NonNull
   @Override
   public final View getView( final int pos, final View convertView, @NonNull final ViewGroup parent )
   {
      final Ingredient ingred = getItem( pos );
      Assert.assertNotNull( "Ingredient not found", ingred );
      View convertView1 = convertView;
      if ( convertView1 == null )
      {
         convertView1 = LayoutInflater.from( getContext() ).inflate( layout.effect_layout, parent, false );
      }
      final TextView txvName = (TextView) convertView1.findViewById( id.txv_IngNameEffect );
      txvName.setText( ingred.get_name() );

      return convertView1;
   }

   @NonNull
   @Override
   public final View getDropDownView( final int position, final View convertView, @NonNull final ViewGroup parent )
   {
      final Ingredient ingredient = getItem( position );
      Assert.assertNotNull( "Cannot find ingredient", ingredient );

      View convertView1 = convertView;
      if ( convertView1 == null )
      {
         convertView1 = LayoutInflater.from( getContext() ).inflate( layout.effect_layout, parent, false );
      }
      final TextView txvName = (TextView) convertView1.findViewById( id.txv_IngNameEffect );
      txvName.setText( ingredient.get_name() );

      return convertView1;
   }

   @Override
   public final boolean isEnabled( final int pos )
   {
      return pos != _disabled;
   }
}
