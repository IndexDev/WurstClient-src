 package net.wurstclient.keybinds;
 
 import java.nio.file.Path;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.LinkedHashSet;
 import java.util.List;
 import java.util.Set;
 
 
 
 
 
 
 
 
 public final class KeybindList
 {
   public static final Set<Keybind> DEFAULT_KEYBINDS = createDefaultKeybinds(); private final KeybindsFile keybindsFile;
   
   public KeybindList(Path keybindsFile) {
     this.keybinds = new ArrayList();
 
 
     
     this.keybindsFile = new KeybindsFile(keybindsFile);
     this.keybindsFile.load(this);
   }
   private final ArrayList<Keybind> keybinds;
   
   public String getCommands(String key) {
     for (Keybind keybind : this.keybinds) {
       
       if (!key.equals(keybind.getKey())) {
         continue;
       }
       return keybind.getCommands();
     } 
     
     return null;
   }
 
 
   
   public List<Keybind> getAllKeybinds() { return Collections.unmodifiableList(this.keybinds); }
 
 
   
   public void add(String key, String commands) {
     this.keybinds.removeIf(keybind -> key.equals(keybind.getKey()));
     this.keybinds.add(new Keybind(key, commands));
     this.keybinds.sort(null);
     this.keybindsFile.save(this);
   }
 
   
   public void setKeybinds(Set<Keybind> keybinds) {
     this.keybinds.clear();
     this.keybinds.addAll(keybinds);
     this.keybinds.sort(null);
     this.keybindsFile.save(this);
   }
 
   
   public void remove(String key) {
     this.keybinds.removeIf(keybind -> key.equals(keybind.getKey()));
     this.keybindsFile.save(this);
   }
 
   
   public void removeAll() {
     this.keybinds.clear();
     this.keybindsFile.save(this);
   }
 
   
   private static Set<Keybind> createDefaultKeybinds() {
     set = new LinkedHashSet();
     addKB(set, "b", "fastplace;fastbreak");
     addKB(set, "b", "fastplace;fastbreak");
     addKB(set, "c", "fullbright");
     addKB(set, "g", "flight");
     addKB(set, "semicolon", "speednuker");
     addKB(set, "h", "say /home");
     addKB(set, "j", "jesus");
     addKB(set, "k", "multiaura");
     addKB(set, "n", "nuker");
     addKB(set, "r", "killaura");
     addKB(set, "right.shift", "navigator");
     addKB(set, "right.control", "clickgui");
     addKB(set, "u", "freecam");
     addKB(set, "x", "x-ray");
     addKB(set, "y", "sneak");
     return Collections.unmodifiableSet(set);
   }
 
 
   
   private static void addKB(Set<Keybind> set, String key, String cmds) { set.add(new Keybind("key.keyboard." + key, cmds)); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\keybinds\KeybindList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */