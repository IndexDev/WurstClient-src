 package net.wurstclient.analytics.dmurph;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class URIEncoder
 {
   private static String mark = "-_.!~*'()\"";
 
   
   public static String encodeURI(String argString) {
     StringBuffer uri = new StringBuffer();
     
     char[] chars = argString.toCharArray();
     for (char c : chars) {
       if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || mark
         .indexOf(c) != -1) {
         uri.append(c);
       } else {
         
         uri.append("%");
         uri.append(Integer.toHexString(c));
       } 
     }  return uri.toString();
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\dmurph\URIEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */