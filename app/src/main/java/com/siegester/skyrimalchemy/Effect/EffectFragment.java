package com.siegester.skyrimalchemy.Effect;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.siegester.skyrimalchemy.Ingredient.Ingredient;
import com.siegester.skyrimalchemy.Ingredient.IngredientAdaptor;
import com.siegester.skyrimalchemy.Utilities.EffectList;
import com.siegester.skyrimalchemy.Utilities.IngredientList;
import com.siegester.skyrimalchemy.R;

import junit.framework.Assert;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EffectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EffectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EffectFragment extends Fragment {

   private OnFragmentInteractionListener mListener;
   private ListView _ingrLayout;

   private ArrayList<Ingredient> _selectedList = new ArrayList<>();

   private EffectAdaptor _effListAdaptor;
   private IngredientAdaptor _ingListAdaptor;

   /**
    * Use this factory method to create a new instance of
    * this fragment using the provided parameters.
    *
    * @return A new instance of fragment EffectFragment.
    */
   public static EffectFragment newInstance() {
      EffectFragment fragment = new EffectFragment();
      Bundle args = new Bundle();
      fragment.setArguments(args);
      return fragment;
   }

   public EffectFragment() {
      // Required empty public constructor
   }

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {

      View baseView = inflater.inflate(R.layout.fragment_effect, container, false);
      baseView.setBackgroundColor( Color.WHITE);

      ListView effLayout = ( ListView ) baseView.findViewById(R.id.lst_Effects);

      _effListAdaptor = new EffectAdaptor( getContext(), EffectList.getInstance( getContext() ).getAllEffects() );
      effLayout.setAdapter( _effListAdaptor );
      effLayout.setOnItemClickListener( new EffectClickListener() );

      _ingListAdaptor = new IngredientAdaptor( getContext(), _selectedList );
      _ingrLayout = ( ListView ) baseView.findViewById( R.id.lst_IngredientsEff );
      _ingrLayout.setAdapter( _ingListAdaptor );

      // Inflate the layout for this fragment
      return baseView;
   }

   // TODO: Rename method, update argument and hook method into UI event
   public void onButtonPressed( Uri uri ) {
      if ( mListener != null ) {
         mListener.onFragmentInteraction(uri);
      }
   }

   @Override
   public void onDetach() {
      super.onDetach();
      mListener = null;
   }

   /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p/>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
   interface OnFragmentInteractionListener {
      // TODO: Update argument type and name
      void onFragmentInteraction( Uri uri );
   }

   private class EffectAdaptor extends ArrayAdapter<Effect>
   {
      EffectAdaptor( Context ctx, ArrayList<Effect> effects )
      {
         super(ctx, R.layout.effect_layout, effects);
      }

      @NonNull
      @Override
      public View getView(int pos, View convertView, @NonNull ViewGroup parent )
      {
         Effect eff = getItem( pos );
         Assert.assertNotNull( "Effect not found", eff );
         if ( convertView == null )
         {
            convertView = LayoutInflater.from( getContext()).inflate( R.layout.effect_layout, parent, false );
         }
         TextView txvName = (TextView) convertView.findViewById( R.id.txv_IngNameEffect);
         ImageView imgV = (ImageView) convertView.findViewById( R.id.img_class );
         Drawable drw;
         switch ( eff.get_effectClass() )
         {
            case Alteration:
               drw = ContextCompat.getDrawable( getContext(), R.mipmap.ic_alteration);
               drw.setTint( Color.WHITE );
               break;
            case Destruction:
               drw = ContextCompat.getDrawable( getContext(), R.mipmap.ic_destruction);
               drw.setTint( Color.BLUE );
               break;
            case Illusion:
               drw = ContextCompat.getDrawable( getContext(), R.mipmap.ic_illusion);
               drw.setTint( Color.GREEN );
               break;
            case Restoration:
               drw = ContextCompat.getDrawable( getContext(), R.mipmap.ic_restoration);
               drw.setTint( Color.CYAN );
               break;
            default:
               drw = null;
               break;
         }
         imgV.setImageDrawable( drw );
         txvName.setText( eff.get_name() );
         if ( eff.is_isBeneficial() )
            txvName.setTextColor( Color.GREEN );
         else
            txvName.setTextColor( Color.RED );

         return convertView;
      }
   }

   private class EffectClickListener implements AdapterView.OnItemClickListener {
      @Override
      public void onItemClick( AdapterView adptView, View vi, int selectedItem, long arg3 )
      {
         Log.v("clicked", "SelectedItem: " + selectedItem);
         Effect eff = EffectList.getInstance( getContext() ).findEffect( selectedItem );
         _selectedList = new ArrayList<>();
         for ( Ingredient ing : IngredientList.getInstance( getContext() ).getAllIngredients() ) {
            if ( ing.hasEffect(eff.get_name()) ) {
               _selectedList.add(ing);
               Log.d( "Eff_CL", "Effect: " + ing.get_name() );
            }
         }

         _ingListAdaptor = new IngredientAdaptor( getContext(), _selectedList );
         _ingrLayout.setAdapter( _ingListAdaptor );
         _ingListAdaptor.notifyDataSetChanged();
      }
   }
}
