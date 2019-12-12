 package net.wurstclient.hacks;
 
 import net.minecraft.class_259;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.CactusCollisionShapeListener;
 import net.wurstclient.hack.Hack;
 
 
 
 
 
 
 
 
 @SearchTags({"NoCactus", "anti cactus", "no cactus"})
 public final class AntiCactusHack
   extends Hack
   implements CactusCollisionShapeListener
 {
   public AntiCactusHack() {
     super("防仙人掌", "Protects you from cactus damage.");
     setCategory(Category.BLOCKS);
   }
 
 
 
   
   protected void onEnable() { EVENTS.add(CactusCollisionShapeListener.class, this); }
 
 
 
 
   
   protected void onDisable() { EVENTS.remove(CactusCollisionShapeListener.class, this); }
 
 
 
 
   
   public void onCactusCollisionShape(CactusCollisionShapeListener.CactusCollisionShapeEvent event) { event.setCollisionShape(class_259.method_1077()); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AntiCactusHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */