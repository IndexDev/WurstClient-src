 package net.wurstclient.other_features;
 
 import java.util.function.BooleanSupplier;
 import net.wurstclient.SearchTags;
 import net.wurstclient.WurstClient;
 import net.wurstclient.other_feature.OtherFeature;
 import net.wurstclient.settings.EnumSetting;
 
 
 
 
 
 
 
 @SearchTags({"wurst logo", "top left corner"})
 public final class WurstLogoOtf
   extends OtherFeature
 {
   private final EnumSetting<Visibility> visibility = new EnumSetting("Visibility", 
       Visibility.values(), Visibility.ALWAYS);
 
   
   public WurstLogoOtf() {
     super("WurstLogo", "Shows the Wurst logo and version on the screen.");
     addSetting(this.visibility);
   }
 
 
   
   public boolean isVisible() { return ((Visibility)this.visibility.getSelected()).isVisible(); }
 
   
   public enum Visibility
   {
     ALWAYS("Always", () -> true),
     
     ONLY_OUTDATED("Only when outdated", () -> 
       WURST.getUpdater().isOutdated());
     
     private final String name;
     
     private final BooleanSupplier visible;
     
     Visibility(String name, BooleanSupplier visible) {
       this.name = name;
       this.visible = visible;
     }
 
 
     
     public boolean isVisible() { return this.visible.getAsBoolean(); }
 
 
 
 
     
     public String toString() { return this.name; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\other_features\WurstLogoOtf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */