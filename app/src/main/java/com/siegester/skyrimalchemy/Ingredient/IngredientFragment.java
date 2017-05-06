package com.siegester.skyrimalchemy.Ingredient;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.siegester.skyrimalchemy.R;
import com.siegester.skyrimalchemy.Utilities.IngredientList;

import junit.framework.Assert;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IngredientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IngredientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientFragment extends Fragment {

   private OnFragmentInteractionListener mListener;
   private IngredientAdaptor _ingListAdaptor;
   private ListView _ingrLayout;

   /**
    * Use this factory method to create a new instance of
    * this fragment using the provided parameters.
    *
    * @return A new instance of fragment IngredientFragment.
    */
   // TODO: Rename and change types and number of parameters
   public static IngredientFragment newInstance() {
      IngredientFragment fragment = new IngredientFragment();
      Bundle args = new Bundle();
      fragment.setArguments(args);
      return fragment;
   }

   public IngredientFragment() {
      // Required empty public constructor
   }

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
      // Inflate the layout for this fragment
      View baseView = inflater.inflate(R.layout.fragment_ingredient, container, false);

      _ingrLayout = ( ListView )baseView.findViewById( R.id.lsv_Ingredients );
      _ingListAdaptor = new IngredientAdaptor( getContext(), IngredientList.getInstance( getContext() ).getAllIngredients() );
      _ingrLayout.setAdapter( _ingListAdaptor );
      _ingrLayout.setOnItemClickListener( new IngredientClickListener() );

      return baseView;
   }

   @Override
   public void onResume()
   {
      super.onResume();
      _ingrLayout.getOnItemClickListener().onItemClick(null, null, 0, 0);
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
   public interface OnFragmentInteractionListener {
      // TODO: Update argument type and name
      void onFragmentInteraction( Uri uri );
   }

   private class IngredientAdaptor extends ArrayAdapter<Ingredient>
   {
      IngredientAdaptor( Context ctx, ArrayList<Ingredient> effects )
      {
         super(ctx, R.layout.effect_layout, effects);
      }

      @NonNull
      @Override
      public View getView(int pos, View convertView, @NonNull ViewGroup parent )
      {
         final Ingredient ingredient = getItem( pos );
         Assert.assertNotNull( "Item not found", ingredient );
         if ( convertView == null )
         {
            convertView = LayoutInflater.from( getContext()).inflate( R.layout.effect_layout, parent, false );
         }
         TextView txvName = (TextView) convertView.findViewById( R.id.txv_IngNameEffect);
         txvName.setText( ingredient.get_name() );

         return convertView;
      }
   }

   private class IngredientClickListener implements AdapterView.OnItemClickListener {
      @Override
      public void onItemClick( AdapterView adptView, View vi, int selectedItem, long arg3 )
      {
         Log.v("clicked", "SelectedItem: " + selectedItem);
         final Ingredient ingredient = IngredientList.getInstance( getContext() ).findIngredient( selectedItem );
         populateIngredientDetails( ingredient );

      }

      private void populateIngredientDetails( Ingredient ingredient )
      {
         TextView txv_IngName = (TextView) getActivity().findViewById( R.id.txv_IngName);
         txv_IngName.setText( ingredient.get_name() );
         TextView txv_EffectOne = (TextView) getActivity().findViewById( R.id.txv_EffectOne );
         txv_EffectOne.setText( ingredient.get_firstEffect() );
         TextView txv_EffectTwo = (TextView) getActivity().findViewById( R.id.txv_EffectTwo );
         txv_EffectTwo.setText( ingredient.get_secondEffect() );
         TextView txv_EffectThree = (TextView) getActivity().findViewById( R.id.txv_EffectThree );
         txv_EffectThree.setText( ingredient.get_thirdEffect() );
         TextView txv_EffectFour = (TextView) getActivity().findViewById( R.id.txv_EffectFour );
         txv_EffectFour.setText( ingredient.get_fourthEffect() );
         TextView txv_Value = (TextView) getActivity().findViewById( R.id.txv_Value );
         txv_Value.setText( Integer.toString( ingredient.get_value() ) );
         TextView txv_Weight = (TextView) getActivity().findViewById( R.id.txv_Weight );
         txv_Weight.setText( Float.toString( ingredient.get_weight() ) );
      }
   }
}
