 package net.wurstclient;
 
 import java.util.Collections;
 import java.util.LinkedHashMap;
 import java.util.LinkedHashSet;
 import java.util.Map;
 import java.util.Set;
 import net.minecraft.class_310;
 import net.wurstclient.event.EventManager;
 import net.wurstclient.keybinds.PossibleKeybind;
 import net.wurstclient.mixinterface.IMinecraftClient;
 import net.wurstclient.settings.Setting;
 
 
 
 
 
 
 
 
 
 public abstract class Feature
 {
   protected static final WurstClient WURST = WurstClient.INSTANCE;
   protected static final EventManager EVENTS = WURST.getEventManager();
   protected static final class_310 MC = WurstClient.MC;
   protected static final IMinecraftClient IMC = WurstClient.IMC;
   
   private final LinkedHashMap<String, Setting> settings = new LinkedHashMap();
   
   private final LinkedHashSet<PossibleKeybind> possibleKeybinds = new LinkedHashSet();
 
 
   
   private final String searchTags = getClass().isAnnotationPresent(SearchTags.class) ? String.join("§", ((SearchTags)
       getClass().getAnnotation(SearchTags.class)).value()) : "";
 
   
   public abstract String getName();
 
   
   public abstract String getDescription();
   
   public Category getCategory() { return null; }
 
 
   
   public abstract String getPrimaryAction();
 
 
   
   public void doPrimaryAction() {}
 
 
   
   public boolean isEnabled() { return false; }
 
 
 
   
   public final Map<String, Setting> getSettings() { return Collections.unmodifiableMap(this.settings); }
 
 
   
   protected final void addSetting(Setting setting) {
     String key = setting.getName().toLowerCase();
     
     if (this.settings.containsKey(key)) {
       throw new IllegalArgumentException("Duplicate setting: " + 
           getName() + " " + key);
     }
     this.settings.put(key, setting);
     this.possibleKeybinds.addAll(setting.getPossibleKeybinds(getName()));
   }
 
 
   
   protected final void addPossibleKeybind(String command, String description) { this.possibleKeybinds.add(new PossibleKeybind(command, description)); }
 
 
 
   
   public final String getSearchTags() { return this.searchTags; }
 
 
 
   
   public final Set<PossibleKeybind> getPossibleKeybinds() { return Collections.unmodifiableSet(this.possibleKeybinds); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\Feature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */