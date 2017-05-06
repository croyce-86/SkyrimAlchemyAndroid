package com.siegester.skyrimalchemy.Effect;

/**
 * SkyrimAlchemy
 * <p/>
 * Created by Siegester on 7/14/2016.
 */
public enum EffectClass {

   NoEffect("NoEffect"),
   Alteration("Alteration"),
   Destruction("Destruction"),
   Illusion("Illusion"),
   Restoration("Restoration");

   private final String _name;

   EffectClass(String name)
   {
      _name = name;
   }

   public boolean equals( String otherEffect )
   {
      return otherEffect != null && _name.compareTo( otherEffect ) == 0;
   }

   public boolean equals( EffectClass otherEffect ) {
      return otherEffect != null && this.equals( otherEffect.toString() );
   }

   @Override
   public String toString()
   {
      return _name;
   }
}
