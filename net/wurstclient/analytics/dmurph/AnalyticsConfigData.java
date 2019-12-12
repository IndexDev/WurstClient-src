 package net.wurstclient.analytics.dmurph;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class AnalyticsConfigData
 {
   private final String trackingCode;
   private String encoding = "UTF-8";
   private String screenResolution = null;
   private String colorDepth = null;
   private String userLanguage = null;
   private String flashVersion = null;
   private String userAgent = null;
 
 
   
   private VisitorData visitorData;
 
 
 
   
   public AnalyticsConfigData(String argTrackingCode, VisitorData visitorData) {
     if (argTrackingCode == null)
       throw new RuntimeException("Tracking code cannot be null"); 
     this.trackingCode = argTrackingCode;
     this.visitorData = visitorData;
   }
 
 
 
 
 
   
   public String getColorDepth() { return this.colorDepth; }
 
 
 
 
 
 
   
   public String getEncoding() { return this.encoding; }
 
 
 
 
 
 
   
   public String getFlashVersion() { return this.flashVersion; }
 
 
 
 
 
 
   
   public String getScreenResolution() { return this.screenResolution; }
 
 
 
 
 
 
   
   public String getTrackingCode() { return this.trackingCode; }
 
 
 
 
 
 
   
   public String getUserLanguage() { return this.userLanguage; }
 
 
 
 
 
 
   
   public String getUserAgent() { return this.userAgent; }
 
 
 
 
 
 
   
   public VisitorData getVisitorData() { return this.visitorData; }
 
 
 
   
   public void setVisitorData(VisitorData visitorData) { this.visitorData = visitorData; }
 
 
 
 
 
 
 
 
   
   public void setColorDepth(String argColorDepth) { this.colorDepth = argColorDepth; }
 
 
 
 
 
 
 
 
 
   
   public void setEncoding(String argEncoding) { this.encoding = argEncoding; }
 
 
 
 
 
 
 
 
 
   
   public void setFlashVersion(String argFlashVersion) { this.flashVersion = argFlashVersion; }
 
 
 
 
 
 
 
 
 
   
   public void setScreenResolution(String argScreenResolution) { this.screenResolution = argScreenResolution; }
 
 
 
 
 
 
 
 
 
   
   public void setUserLanguage(String argUserLanguage) { this.userLanguage = argUserLanguage; }
 
 
 
   
   public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\dmurph\AnalyticsConfigData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */