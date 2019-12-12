 package net.wurstclient.util.json;
 
 import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 import java.util.LinkedHashMap;
 import java.util.Map;
 import java.util.Objects;
 
 
 
 
 
 
 
 
 
 
 
 public final class WsonObject
 {
   private final JsonObject json;
   
   public WsonObject(JsonObject json) { this.json = (JsonObject)Objects.requireNonNull(json); }
 
 
 
   
   public boolean getBoolean(String key) throws JsonException { return JsonUtils.getAsBoolean(this.json.get(key)); }
 
 
 
   
   public int getInt(String key) throws JsonException { return JsonUtils.getAsInt(this.json.get(key)); }
 
 
 
   
   public long getLong(String key) throws JsonException { return JsonUtils.getAsLong(this.json.get(key)); }
 
 
 
   
   public String getString(String key) throws JsonException { return JsonUtils.getAsString(this.json.get(key)); }
 
 
 
   
   public WsonArray getArray(String key) throws JsonException { return JsonUtils.getAsArray(this.json.get(key)); }
 
 
   
   public LinkedHashMap<String, String> getAllStrings() {
     LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
     
     for (Map.Entry<String, JsonElement> entry : this.json.entrySet()) {
       
       JsonElement value = (JsonElement)entry.getValue();
       if (!JsonUtils.isString(value)) {
         continue;
       }
       map.put(entry.getKey(), value.getAsString());
     } 
     
     return map;
   }
 
   
   public LinkedHashMap<String, Number> getAllNumbers() {
     LinkedHashMap<String, Number> map = new LinkedHashMap<String, Number>();
     
     for (Map.Entry<String, JsonElement> entry : this.json.entrySet()) {
       
       JsonElement value = (JsonElement)entry.getValue();
       if (!JsonUtils.isNumber(value)) {
         continue;
       }
       map.put(entry.getKey(), value.getAsNumber());
     } 
     
     return map;
   }
 
   
   public LinkedHashMap<String, JsonObject> getAllJsonObjects() {
     LinkedHashMap<String, JsonObject> map = new LinkedHashMap<String, JsonObject>();
     
     for (Map.Entry<String, JsonElement> entry : this.json.entrySet()) {
       
       JsonElement value = (JsonElement)entry.getValue();
       if (!value.isJsonObject()) {
         continue;
       }
       map.put(entry.getKey(), value.getAsJsonObject());
     } 
     
     return map;
   }
 
 
   
   public JsonObject toJsonObject() { return this.json; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\json\WsonObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */