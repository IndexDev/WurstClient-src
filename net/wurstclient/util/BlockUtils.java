 package net.wurstclient.util;
 
 import java.util.ArrayList;
 import net.minecraft.class_151;
 import net.minecraft.class_2246;
 import net.minecraft.class_2248;
 import net.minecraft.class_2338;
 import net.minecraft.class_2378;
 import net.minecraft.class_238;
 import net.minecraft.class_259;
 import net.minecraft.class_265;
 import net.minecraft.class_2680;
 import net.minecraft.class_2960;
 import net.minecraft.class_310;
 import net.wurstclient.WurstClient;
 
 
 
 
 
 
 
 
 public static enum BlockUtils
 {
   private static final class_310 MC;
   
   static  {
     MC = WurstClient.MC;
   }
 
   
   public static class_2680 getState(class_2338 pos) { return MC.field_1687.method_8320(pos); }
 
 
 
   
   public static class_2248 getBlock(class_2338 pos) { return getState(pos).method_11614(); }
 
 
 
   
   public static int getId(class_2338 pos) { return class_2248.method_9507(getState(pos)); }
 
 
 
   
   public static String getName(class_2338 pos) { return getName(getBlock(pos)); }
 
 
 
   
   public static String getName(class_2248 block) { return class_2378.field_11146.method_10221(block).toString(); }
 
 
 
   
   public static class_2248 getBlockFromName(String name) {
     try {
       return (class_2248)class_2378.field_11146.method_10223(new class_2960(name));
     }
     catch (class_151 e) {
       
       return class_2246.field_10124;
     } 
   }
 
 
   
   public static float getHardness(class_2338 pos) { return getState(pos).method_11589(MC.field_1724, MC.field_1687, pos); }
 
 
 
   
   private static class_265 getOutlineShape(class_2338 pos) { return getState(pos).method_17770(MC.field_1687, pos); }
 
 
 
   
   public static class_238 getBoundingBox(class_2338 pos) { return getOutlineShape(pos).method_1107().method_996(pos); }
 
 
 
   
   public static boolean canBeClicked(class_2338 pos) { return (getOutlineShape(pos) != class_259.method_1073()); }
 
 
   
   public static ArrayList<class_2338> getAllInBox(class_2338 min, class_2338 max) {
     ArrayList<class_2338> blocks = new ArrayList<class_2338>();
     
     for (int x = min.method_10263(); x <= max.method_10263(); x++) {
       for (int y = min.method_10264(); y <= max.method_10264(); y++) {
         for (int z = min.method_10260(); z <= max.method_10260(); z++)
           blocks.add(new class_2338(x, y, z)); 
       } 
     }  return blocks;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\BlockUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */