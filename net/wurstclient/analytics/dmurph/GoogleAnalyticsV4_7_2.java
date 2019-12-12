 package net.wurstclient.analytics.dmurph;
 
 import java.util.Random;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class GoogleAnalyticsV4_7_2
   implements IGoogleAnalyticsURLBuilder
 {
   public static final String URL_PREFIX = "http://www.google-analytics.com/__utm.gif";
   private AnalyticsConfigData config;
   private Random random;
   
   public GoogleAnalyticsV4_7_2(AnalyticsConfigData argConfig) {
     this.random = new Random((long)(Math.random() * 9.223372036854776E18D));
 
 
     
     this.config = argConfig;
   }
 
 
 
 
 
 
   
   public String getGoogleAnalyticsVersion() { return "4.7.2"; }
 
 
 
 
 
 
   
   public String buildURL(AnalyticsRequestData argData) {
     StringBuilder sb = new StringBuilder();
     sb.append("http://www.google-analytics.com/__utm.gif");
     
     System.currentTimeMillis();
     
     sb.append("?utmwv=" + getGoogleAnalyticsVersion());
     sb.append("&utmn=" + this.random.nextInt());
     
     if (argData.getHostName() != null) {
       sb.append("&utmhn=" + getURIString(argData.getHostName()));
     }
     if (argData.getEventAction() != null && argData
       .getEventCategory() != null) {
       
       sb.append("&utmt=event");
       String category = getURIString(argData.getEventCategory());
       String action = getURIString(argData.getEventAction());
       
       sb.append("&utme=5(" + category + "*" + action);
       
       if (argData.getEventLabel() != null)
         sb.append("*" + getURIString(argData.getEventLabel())); 
       sb.append(")");
       
       if (argData.getEventValue() != null)
         sb.append("(" + argData.getEventValue() + ")"); 
     } else if (argData.getEventAction() != null || argData
       .getEventCategory() != null) {
       throw new IllegalArgumentException("Event tracking must have both a category and an action");
     } 
     
     if (this.config.getEncoding() != null) {
       sb.append("&utmcs=" + getURIString(this.config.getEncoding()));
     } else {
       sb.append("&utmcs=-");
     }  if (this.config.getScreenResolution() != null) {
       sb.append("&utmsr=" + getURIString(this.config.getScreenResolution()));
     }
     if (this.config.getColorDepth() != null) {
       sb.append("&utmsc=" + getURIString(this.config.getColorDepth()));
     }
     if (this.config.getUserLanguage() != null)
       sb.append("&utmul=" + getURIString(this.config.getUserLanguage())); 
     sb.append("&utmje=1");
     
     if (this.config.getFlashVersion() != null) {
       sb.append("&utmfl=" + getURIString(this.config.getFlashVersion()));
     }
     
     if (argData.getPageTitle() != null) {
       sb.append("&utmdt=" + getURIString(argData.getPageTitle()));
     }
     
     sb.append("&utmhid=" + this.random.nextInt());
     
     if (argData.getPageURL() != null) {
       sb.append("&utmp=" + getURIString(argData.getPageURL()));
     }
     
     sb.append("&utmac=" + this.config.getTrackingCode());
 
 
     
     String utmcsr = getURIString(argData.getUtmcsr());
     String utmccn = getURIString(argData.getUtmccn());
     String utmctr = getURIString(argData.getUtmctr());
     String utmcmd = getURIString(argData.getUtmcmd());
     String utmcct = getURIString(argData.getUtmcct());
 
     
     int hostnameHash = hostnameHash(argData.getHostName());
     int visitorId = this.config.getVisitorData().getVisitorId();
     long timestampFirst = this.config.getVisitorData().getTimestampFirst();
     long timestampPrevious = this.config.getVisitorData().getTimestampPrevious();
     long timestampCurrent = this.config.getVisitorData().getTimestampCurrent();
     int visits = this.config.getVisitorData().getVisits();
     
     sb.append("&utmcc=__utma%3D" + hostnameHash + "." + visitorId + "." + timestampFirst + "." + timestampPrevious + "." + timestampCurrent + "." + visits + "%3B%2B__utmz%3D" + hostnameHash + "." + timestampCurrent + ".1.1.utmcsr%3D" + utmcsr + "%7Cutmccn%3D" + utmccn + "%7Cutmcmd%3D" + utmcmd + ((utmctr != null) ? ("%7Cutmctr%3D" + utmctr) : "") + ((utmcct != null) ? ("%7Cutmcct%3D" + utmcct) : "") + "%3B&gaq=1");
 
 
 
 
 
     
     return sb.toString();
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   private String getURIString(String argString) {
     if (argString == null)
       return null; 
     return URIEncoder.encodeURI(argString);
   }
 
 
   
   private int hostnameHash(String hostname) { return 999; }
 
 
 
 
 
 
 
   
   public void resetSession() { this.config.getVisitorData().resetSession(); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\dmurph\GoogleAnalyticsV4_7_2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */