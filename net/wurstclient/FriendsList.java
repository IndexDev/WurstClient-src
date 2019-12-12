 package net.wurstclient;
 
 import com.google.gson.JsonArray;
 import java.io.IOException;
 import java.nio.file.NoSuchFileException;
 import java.nio.file.Path;
 import java.util.ArrayList;
 import java.util.TreeSet;
 import net.minecraft.class_1297;
 import net.wurstclient.commands.FriendsCmd;
 import net.wurstclient.settings.CheckboxSetting;
 import net.wurstclient.util.json.JsonException;
 import net.wurstclient.util.json.JsonUtils;
 
 
 
 
 
 
 
 public class FriendsList
 {
   private final TreeSet<String> friends;
   private Path path;
   
   public FriendsList(Path path) {
     this.friends = new TreeSet();
 
 
 
     
     this.path = path;
   }
 
   
   public void addAndSave(String name) {
     this.friends.add(name);
     save();
   }
 
   
   public void removeAndSave(String name) {
     this.friends.remove(name);
     save();
   }
 
   
   public void removeAllAndSave() {
     this.friends.clear();
     save();
   }
 
   
   public void middleClick(class_1297 entity) {
     if (entity == null || !(entity instanceof net.minecraft.class_1657)) {
       return;
     }
     FriendsCmd friendsCmd = (WurstClient.INSTANCE.getCmds()).friendsCmd;
     CheckboxSetting middleClickFriends = friendsCmd.getMiddleClickFriends();
     if (!middleClickFriends.isChecked()) {
       return;
     }
     String name = entity.method_5820();
     
     if (contains(name)) {
       removeAndSave(name);
     } else {
       addAndSave(name);
     } 
   }
 
   
   public boolean contains(String name) { return this.friends.contains(name); }
 
 
 
   
   public ArrayList<String> toList() { return new ArrayList(this.friends); }
 
 
 
   
   public void load() {
     try {
       this.friends.clear();
       this.friends.addAll(JsonUtils.parseFileToArray(this.path).getAllStrings());
     }
     catch (NoSuchFileException noSuchFileException) {
 
     
     }
     catch (IOException|JsonException e) {
       
       System.out.println("Couldn't load " + this.path.getFileName());
       e.printStackTrace();
     } 
     
     save();
   }
 
 
   
   private void save() {
     try {
       JsonUtils.toJson(createJson(), this.path);
     }
     catch (JsonException|IOException e) {
       
       System.out.println("Couldn't save " + this.path.getFileName());
       e.printStackTrace();
     } 
   }
 
   
   private JsonArray createJson() {
     JsonArray json = new JsonArray();
     this.friends.forEach(json::add);
     return json;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\FriendsList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */