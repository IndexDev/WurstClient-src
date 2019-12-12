 package net.wurstclient.hacks;
 
 import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 import java.io.IOException;
 import java.nio.file.Path;
 import net.wurstclient.Category;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.settings.FileSetting;
 import net.wurstclient.util.json.JsonUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 public final class AutoBuildHack
   extends Hack
   implements UpdateListener
 {
   private final FileSetting template;
   
   public AutoBuildHack() {
     super("AutoBuild", "Builds things automatically."); this.template = new FileSetting("Template", "Determines what to build.", "autobuild", folder -> createDefaultTemplates(folder));
     setCategory(Category.BLOCKS);
     addSetting(this.template);
   }
 
 
 
   
   public void onEnable() { EVENTS.add(UpdateListener.class, this); }
 
 
 
 
   
   public void onDisable() { EVENTS.remove(UpdateListener.class, this); }
 
 
 
   
   public void onUpdate() {}
 
 
 
   
   private void createDefaultTemplates(Path folder) {
     for (DefaultTemplate template : DefaultTemplate.values()) {
       
       JsonObject json = createJson(template);
       
       Path path = folder.resolve(template.name + ".json");
 
       
       try {
         JsonUtils.toJson(json, path);
       }
       catch (IOException|net.wurstclient.util.json.JsonException e) {
         
         System.out.println("Couldn't save " + path.getFileName());
         e.printStackTrace();
       } 
     } 
   }
 
   
   private JsonObject createJson(DefaultTemplate template) {
     JsonObject json = new JsonObject();
     JsonElement blocks = JsonUtils.GSON.toJsonTree(template.data);
     json.add("blocks", blocks);
     
     return json;
   }
   
   private enum DefaultTemplate
   {
     BRIDGE("Bridge", new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 1, 0, -1 }, { 0, 0, -1 }, { -1, 0, -1 }, { -1, 0, 0 }, { -1, 0, -2 }, { 0, 0, -2 }, { 1, 0, -2 }, { 1, 0, -3 }, { 0, 0, -3 }, { -1, 0, -3 }, { -1, 0, -4 }, { 0, 0, -4 }, { 1, 0, -4 }, { 1, 0, -5 }, { 0, 0, -5 }, { -1, 0, -5
 
         
         }
       
       }),
     FLOOR("Floor", new int[][] { { 0, 0, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 1, 0, 0 }, { 1, 0, -1 }, { 0, 0, -1 }, { -1, 0, -1 }, { -1, 0, 0 }, { -1, 0, 1 }, { -1, 0, 2 }, { 0, 0, 2 }, { 1, 0, 2 }, { 2, 0, 2 }, { 2, 0, 1 }, { 2, 0, 0 }, { 2, 0, -1 }, { 2, 0, -2 }, { 1, 0, -2 }, { 0, 0, -2 }, { -1, 0, -2 }, { -2, 0, -2 }, { -2, 0, -1 }, { -2, 0, 0 }, { -2, 0, 1 }, { -2, 0, 2 }, { -2, 0, 3 }, { -1, 0, 3 }, { 0, 0, 3 }, { 1, 0, 3 }, { 2, 0, 3 }, { 3, 0, 3 }, { 3, 0, 2 }, { 3, 0, 1 }, { 3, 0, 0 }, { 3, 0, -1 }, { 3, 0, -2 }, { 3, 0, -3 }, { 2, 0, -3 }, { 1, 0, -3 }, { 0, 0, -3 }, { -1, 0, -3 }, { -2, 0, -3 }, { -3, 0, -3 }, { -3, 0, -2 }, { -3, 0, -1 }, { -3, 0, 0 }, { -3, 0, 1 }, { -3, 0, 2 }, { -3, 0, 3
 
 
 
 
         
         }
 
 
 
       
       }),
     PILLAR("Pillar", new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 2, 0 }, { 0, 3, 0 }, { 0, 4, 0 }, { 0, 5, 0 }, { 0, 6, 0
         
         }
       }),
     WALL("Wall", new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 0 }, { -1, 1, 0 }, { -1, 0, 0 }, { -2, 0, 0 }, { -2, 1, 0 }, { -2, 2, 0 }, { -1, 2, 0 }, { 0, 2, 0 }, { 1, 2, 0 }, { 2, 2, 0 }, { 2, 1, 0 }, { 2, 0, 0 }, { 3, 0, 0 }, { 3, 1, 0 }, { 3, 2, 0 }, { 3, 3, 0 }, { 2, 3, 0 }, { 1, 3, 0 }, { 0, 3, 0 }, { -1, 3, 0 }, { -2, 3, 0 }, { -3, 3, 0 }, { -3, 2, 0 }, { -3, 1, 0 }, { -3, 0, 0 }, { -3, 4, 0 }, { -2, 4, 0 }, { -1, 4, 0 }, { 0, 4, 0 }, { 1, 4, 0 }, { 2, 4, 0 }, { 3, 4, 0 }, { 3, 5, 0 }, { 2, 5, 0 }, { 1, 5, 0 }, { 0, 5, 0 }, { -1, 5, 0 }, { -2, 5, 0 }, { -3, 5, 0 }, { -3, 6, 0 }, { -2, 6, 0 }, { -1, 6, 0 }, { 0, 6, 0 }, { 1, 6, 0 }, { 2, 6, 0 }, { 3, 6, 0
 
 
 
 
         
         }
 
 
 
       
       }),
     WURST("Wurst", new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 }, { 1, 1, 1 }, { 2, 1, 1 }, { 2, 1, 0 }, { 2, 0, 0 }, { 2, 1, -1 }, { 1, 1, -1 }, { 0, 1, -1 }, { -1, 1, -1 }, { -1, 1, 0 }, { -1, 0, 0 }, { -2, 0, 0 }, { -2, 1, 0 }, { -2, 1, 1 }, { -1, 1, 1 }, { -1, 2, 0 }, { 0, 2, 0 }, { 1, 2, 0 }, { 2, 2, 0 }, { 3, 1, 0 }, { -2, 1, -1 }, { -2, 2, 0 }, { -3, 1, 0 } });
 
 
     
     private final String name;
 
 
     
     private final int[][] data;
 
 
     
     DefaultTemplate(String name, int[][] data) {
       this.name = name;
       this.data = data;
     }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoBuildHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */