 package net.wurstclient.hacks;
 
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.DeathListener;
 import net.wurstclient.hack.Hack;
 
 
 
 
 
 
 
 @SearchTags({"auto respawn", "AutoRevive", "auto revive"})
 public final class AutoRespawnHack
   extends Hack
   implements DeathListener
 {
   public AutoRespawnHack() {
     super("AutoRespawn", "Automatically respawns you whenever you die.");
     setCategory(Category.COMBAT);
   }
 
 
 
   
   public void onEnable() { EVENTS.add(DeathListener.class, this); }
 
 
 
 
   
   public void onDisable() { EVENTS.remove(DeathListener.class, this); }
 
 
 
   
   public void onDeath() {
     MC.field_1724.method_7331();
     MC.method_1507(null);
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoRespawnHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */