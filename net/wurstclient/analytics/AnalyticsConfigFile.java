 package net.wurstclient.analytics;
 
 import com.google.gson.JsonObject;
 import java.io.IOException;
 import java.nio.file.NoSuchFileException;
 import java.nio.file.Path;
 import net.wurstclient.analytics.dmurph.VisitorData;
 import net.wurstclient.util.json.JsonException;
 import net.wurstclient.util.json.JsonUtils;
 import net.wurstclient.util.json.WsonObject;
 
 
 
 
 
 
 
 
 
 
 
 
 public final class AnalyticsConfigFile
 {
   private final Path path;
   
   public AnalyticsConfigFile(Path path) { this.path = path; }
 
 
 
   
   public void load(WurstAnalyticsTracker tracker) {
     try {
       WsonObject wson = JsonUtils.parseFileToObject(this.path);
       tracker.setEnabled(wson.getBoolean("enabled"));
       tracker.getConfigData().setVisitorData(readVisitorData(wson));
     }
     catch (NoSuchFileException noSuchFileException) {
 
     
     }
     catch (IOException|JsonException e) {
       
       System.out.println("Couldn't load " + this.path.getFileName());
       e.printStackTrace();
     } 
     
     save(tracker);
   }
 
   
   private VisitorData readVisitorData(WsonObject wson) throws JsonException {
     int visitorID = wson.getInt("id");
     long firstLaunch = wson.getLong("first_launch");
     long lastLaunch = wson.getLong("last_launch");
     int launches = wson.getInt("launches");
     
     return VisitorData.newSession(visitorID, firstLaunch, lastLaunch, launches);
   }
 
 
   
   public void save(WurstAnalyticsTracker tracker) {
     JsonObject json = createJson(tracker);
 
     
     try {
       JsonUtils.toJson(json, this.path);
     }
     catch (IOException|JsonException e) {
       
       System.out.println("Couldn't save " + this.path.getFileName());
       e.printStackTrace();
     } 
   }
 
   
   private JsonObject createJson(WurstAnalyticsTracker tracker) {
     JsonObject json = new JsonObject();
     json.addProperty("enabled", Boolean.valueOf(tracker.isEnabled()));
     
     VisitorData visitorData = tracker.getConfigData().getVisitorData();
     json.addProperty("id", Integer.valueOf(visitorData.getVisitorId()));
     json.addProperty("first_launch", Long.valueOf(visitorData.getTimestampFirst()));
     json.addProperty("last_launch", Long.valueOf(visitorData.getTimestampCurrent()));
     json.addProperty("launches", Integer.valueOf(visitorData.getVisits()));
     
     return json;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\AnalyticsConfigFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */