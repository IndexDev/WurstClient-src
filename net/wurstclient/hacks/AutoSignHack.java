 package net.wurstclient.hacks;
 
 import net.minecraft.class_2561;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.hack.DontSaveState;
 import net.wurstclient.hack.Hack;
 
 
 
 
 
 
 
 
 @SearchTags({"auto sign"})
 @DontSaveState
 public final class AutoSignHack
   extends Hack
 {
   private class_2561[] signText;
   
   public AutoSignHack() {
     super("AutoSign", "Instantly writes whatever text you want on every sign\nyou place. Once activated, you can write normally on\nthe first sign to specify the text for all other signs.");
 
 
     
     setCategory(Category.BLOCKS);
   }
 
 
 
   
   public void onDisable() { this.signText = null; }
 
 
 
   
   public class_2561[] getSignText() { return this.signText; }
 
 
   
   public void setSignText(class_2561[] signText) {
     if (isEnabled() && this.signText == null)
       this.signText = signText; 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoSignHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */