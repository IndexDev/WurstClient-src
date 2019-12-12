 package net.wurstclient.other_feature;
 
 import java.lang.reflect.Field;
 import java.util.Collection;
 import java.util.TreeMap;
 import net.minecraft.class_128;
 import net.minecraft.class_148;
 import net.wurstclient.other_features.HackListOtf;
 import net.wurstclient.other_features.WurstLogoOtf;
 import net.wurstclient.other_features.ZoomOtf;
 
 
 
 
 
 
 public final class OtfList
 {
   public final HackListOtf hackListOtf;
   public final WurstLogoOtf wurstLogoOtf;
   public final ZoomOtf zoomOtf;
   private final TreeMap<String, OtherFeature> otfs;
   
   public OtfList() {
     this.hackListOtf = new HackListOtf();
 
 
     
     this.wurstLogoOtf = new WurstLogoOtf();
     
     this.zoomOtf = new ZoomOtf();
     
     this.otfs = new TreeMap((o1, o2) -> 
         o1.compareToIgnoreCase(o2));
 
 
 
     
     try {
       for (Field field : OtfList.class.getDeclaredFields()) {
         
         if (field.getName().endsWith("Otf")) {
 
           
           OtherFeature otf = (OtherFeature)field.get(this);
           this.otfs.put(otf.getName(), otf);
         } 
       } 
     } catch (Exception e) {
       
       String message = "Initializing other Wurst features";
       class_128 report = class_128.method_560(e, message);
       throw new class_148(report);
     } 
   }
 
 
   
   public OtherFeature getOtfByName(String name) { return (OtherFeature)this.otfs.get(name); }
 
 
 
   
   public Collection<OtherFeature> getAllOtfs() { return this.otfs.values(); }
 
 
 
   
   public int countOtfs() { return this.otfs.size(); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\other_feature\OtfList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */