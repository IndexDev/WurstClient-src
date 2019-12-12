 package net.wurstclient.keybinds;
 
 import com.google.gson.JsonObject;
 import java.io.IOException;
 import java.nio.file.NoSuchFileException;
 import java.nio.file.Path;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Set;
 import net.minecraft.class_3675;
 import net.wurstclient.util.json.JsonUtils;
 import net.wurstclient.util.json.WsonObject;
 
 
 
 
 
 
 
 
 
 
 
 
 
 public final class KeybindsFile
 {
   private final Path path;
   
   public KeybindsFile(Path path) { this.path = path; }
 
 
 
   
   public void load(KeybindList list) {
     try {
       WsonObject wson = JsonUtils.parseFileToObject(this.path);
       Set<Keybind> newKeybinds = new HashSet<Keybind>();
       
       for (Map.Entry<String, String> entry : wson.getAllStrings().entrySet()) {
         
         String keyName = (String)entry.getKey();
         String commands = (String)entry.getValue();
         
         if (!isValidKeyName(keyName)) {
           continue;
         }
         Keybind keybind = new Keybind(keyName, commands);
         newKeybinds.add(keybind);
       } 
       
       if (newKeybinds.isEmpty()) {
         newKeybinds = KeybindList.DEFAULT_KEYBINDS;
       }
       list.setKeybinds(newKeybinds);
     }
     catch (NoSuchFileException e) {
       
       list.setKeybinds(KeybindList.DEFAULT_KEYBINDS);
     }
     catch (IOException|net.wurstclient.util.json.JsonException e) {
       
       System.out.println("Couldn't load " + this.path.getFileName());
       e.printStackTrace();
       
       list.setKeybinds(KeybindList.DEFAULT_KEYBINDS);
     } 
   }
 
 
   
   private boolean isValidKeyName(String key) {
     try {
       class_3675.method_15981(key);
       return true;
     }
     catch (IllegalArgumentException e) {
       
       return false;
     } 
   }
 
   
   public void save(KeybindList list) {
     JsonObject json = createJson(list);
 
     
     try {
       JsonUtils.toJson(json, this.path);
     }
     catch (IOException|net.wurstclient.util.json.JsonException e) {
       
       System.out.println("Couldn't save " + this.path.getFileName());
       e.printStackTrace();
     } 
   }
 
   
   private JsonObject createJson(KeybindList list) {
     JsonObject json = new JsonObject();
     
     for (Keybind kb : list.getAllKeybinds()) {
       json.addProperty(kb.getKey(), kb.getCommands());
     }
     return json;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\keybinds\KeybindsFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */