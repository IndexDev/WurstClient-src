 package net.wurstclient.other_features;
 
 import net.minecraft.class_4357;
 import net.wurstclient.SearchTags;
 import net.wurstclient.other_feature.OtherFeature;
 import net.wurstclient.settings.CheckboxSetting;
 import net.wurstclient.settings.EnumSetting;
 
 
 
 
 
 
 
 
 
 @SearchTags({"ArrayList", "ModList", "CheatList", "mod list", "array list", "hack list", "cheat list"})
 public final class HackListOtf
   extends OtherFeature
 {
   private final EnumSetting<Mode> mode = new EnumSetting("Mode", class_4357.field_19629 + "Auto" + class_4357.field_19633 + " mode renders the whole list if it\nfits onto the screen.\n" + class_4357.field_19629 + "Count" + class_4357.field_19633 + " mode only renders the number\nof active hacks.\n" + class_4357.field_19629 + "Hidden" + class_4357.field_19633 + " mode renders nothing.", 
 
 
 
 
 
       
       Mode.values(), Mode.AUTO);
   
   private final EnumSetting<Position> position = new EnumSetting("Position", 
       Position.values(), Position.LEFT);
   
   private final CheckboxSetting animations = new CheckboxSetting("Animations", true);
 
 
   
   public HackListOtf() {
     super("HackList", "Shows a list of active hacks on the screen.\nThe " + class_4357.field_19629 + "Left" + class_4357.field_19633 + " position should only be used while TabGui is\ndisabled.");
 
 
     
     addSetting(this.mode);
     addSetting(this.position);
     addSetting(this.animations);
   }
 
 
   
   public Mode getMode() { return (Mode)this.mode.getSelected(); }
 
 
 
   
   public Position getPosition() { return (Position)this.position.getSelected(); }
 
 
 
   
   public boolean isAnimations() { return this.animations.isChecked(); }
 
   
   public enum Mode
   {
     AUTO("Auto"),
     
     COUNT("Count"),
     
     HIDDEN("Hidden");
 
     
     private final String name;
 
     
     Mode(String name) { this.name = name; }
 
 
 
 
     
     public String toString() { return this.name; }
   }
 
   
   public enum Position
   {
     LEFT("Left"),
     
     RIGHT("Right");
 
     
     private final String name;
 
     
     Position(String name) { this.name = name; }
 
 
 
 
     
     public String toString() { return this.name; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\other_features\HackListOtf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */