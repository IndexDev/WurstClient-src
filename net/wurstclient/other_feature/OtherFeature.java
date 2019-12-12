 package net.wurstclient.other_feature;
 
 import net.wurstclient.Feature;
 
 
 
 
 
 
 
 
 public abstract class OtherFeature
   extends Feature
 {
   private final String name;
   private final String description;
   
   public OtherFeature(String name, String description) {
     this.name = name;
     this.description = description;
   }
 
 
 
   
   public final String getName() { return this.name; }
 
 
 
 
   
   public String getDescription() { return this.description; }
 
 
 
 
   
   public boolean isEnabled() { return false; }
 
 
 
 
   
   public String getPrimaryAction() { return ""; }
   
   public void doPrimaryAction() {}
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\other_feature\OtherFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */