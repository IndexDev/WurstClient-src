 package net.wurstclient.util.json;
 
 import com.google.gson.JsonArray;
 import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 import java.util.ArrayList;
 import java.util.Objects;
 import java.util.stream.Collectors;
 import java.util.stream.StreamSupport;
 
 
 
 
 
 
 
 
 
 
 public final class WsonArray
 {
   private final JsonArray json;
   
   public WsonArray(JsonArray json) { this.json = (JsonArray)Objects.requireNonNull(json); }
 
 
   
   public ArrayList<String> getAllStrings() {
     return (ArrayList)StreamSupport.stream(this.json.spliterator(), false)
       .filter(JsonUtils::isString).map(JsonElement::getAsString)
       .collect(Collectors.toCollection(() -> new ArrayList()));
   }
 
   
   public ArrayList<WsonObject> getAllObjects() {
     return (ArrayList)StreamSupport.stream(this.json.spliterator(), false)
       .filter(JsonElement::isJsonObject).map(JsonElement::getAsJsonObject)
       .map(json -> new WsonObject(json))
       .collect(Collectors.toCollection(() -> new ArrayList()));
   }
 
 
   
   public JsonArray toJsonArray() { return this.json; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\json\WsonArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */