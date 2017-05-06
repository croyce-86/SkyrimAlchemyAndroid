package com.siegester.skyrimalchemy.Effect;

/**
 * SkyrimAlchemy
 * <p/>
 * Created by Siegester on 7/14/2016.
 */
public class Effect {

   public Effect()
   {
      _name = "";
      _id = 0;
      _effectClass = EffectClass.NoEffect;
      _isBeneficial = false;
      _baseMagnitude = 0;
      _baseDuration = 0;
      _value = 0;
   }
   public Effect( String Name, int ID, EffectClass Effect, boolean Beneficial, int Magnitude, int Duration, int Value)
   {
      _name = Name;
      _id = ID;
      _effectClass = Effect;
      _isBeneficial = Beneficial;
      _baseMagnitude = Magnitude;
      _baseDuration = Duration;
      _value = Value;
   }

   public String get_name() {
      return _name;
   }

   public void set_name( String _name ) {
      this._name = _name;
   }

   public int get_id() {
      return _id;
   }

   public void set_id( int _id ) {
      this._id = _id;
   }

   public EffectClass get_effectClass() {
      return _effectClass;
   }

   public void set_effectClass( EffectClass _effectClass ) {
      this._effectClass = _effectClass;
   }
   public void set_effectClass( String _effectClass ) {
      this._effectClass = EffectClass.valueOf( _effectClass );
   }

   public boolean is_isBeneficial() {
      return _isBeneficial;
   }

   public void set_isBeneficial( boolean _isBeneficial ) {
      this._isBeneficial = _isBeneficial;
   }

   public int get_baseMagnitude() {
      return _baseMagnitude;
   }

   public void set_baseMagnitude( int _baseMagnitude ) {
      this._baseMagnitude = _baseMagnitude;
   }

   public int get_baseDuration() {
      return _baseDuration;
   }

   public void set_baseDuration( int _baseDuration ) {
      this._baseDuration = _baseDuration;
   }

   public int get_value() {
      return _value;
   }

   public void set_value( int _value ) {
      this._value = _value;
   }

   private String _name;
   private int _id;
   private EffectClass _effectClass;
   private boolean _isBeneficial;
   private int _baseMagnitude;
   private int _baseDuration;
   private int _value;
}
