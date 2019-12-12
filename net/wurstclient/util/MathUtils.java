 package net.wurstclient.util;
 
 
 
 
 
 
 
 
 
 
 
 
 public static enum MathUtils
 {
   public static boolean isInteger(String s) {
     try {
       Integer.parseInt(s);
       return true;
     }
     catch (NumberFormatException e) {
       
       return false;
     } 
   }
 
 
   
   public static boolean isDouble(String s) {
     try {
       Double.parseDouble(s);
       return true;
     }
     catch (NumberFormatException e) {
       
       return false;
     } 
   }
 
 
   
   public static int clamp(int num, int min, int max) { return (num < min) ? min : ((num > max) ? max : num); }
 
 
 
   
   public static float clamp(float num, float min, float max) { return (num < min) ? min : ((num > max) ? max : num); }
 
 
 
   
   public static double clamp(double num, double min, double max) { return (num < min) ? min : ((num > max) ? max : num); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\MathUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */