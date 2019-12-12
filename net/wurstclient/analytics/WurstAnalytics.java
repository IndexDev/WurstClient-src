 package net.wurstclient.analytics;
 
 import java.nio.file.Path;
 import net.wurstclient.analytics.dmurph.AnalyticsRequestData;
 
 
 
 
 
 
 
 
 
 
 public final class WurstAnalytics
 {
   private final String hostname;
   private final WurstAnalyticsTracker tracker;
   private final AnalyticsConfigFile configFile;
   
   public WurstAnalytics(String trackingID, String hostname, Path configFile) {
     this.tracker = new WurstAnalyticsTracker(trackingID);
     this.hostname = hostname;
     this.configFile = new AnalyticsConfigFile(configFile);
     this.configFile.load(this.tracker);
   }
 
 
   
   public boolean isEnabled() { return this.tracker.isEnabled(); }
 
 
   
   public void setEnabled(boolean enabled) {
     if (!enabled) {
       trackEvent("options", "analytics", "disable");
     }
     this.tracker.setEnabled(enabled);
     this.configFile.save(this.tracker);
     
     if (enabled) {
       trackEvent("options", "analytics", "enable");
     }
   }
 
   
   public void trackPageView(String url, String title) { this.tracker.trackPageView(url, title, this.hostname); }
 
 
 
 
   
   public void trackPageViewFromReferrer(String url, String title, String referrerSite, String referrerPage) { this.tracker.trackPageViewFromReferrer(url, title, this.hostname, referrerSite, referrerPage); }
 
 
 
 
 
   
   public void trackPageViewFromSearch(String url, String title, String searchSource, String keywords) { this.tracker.trackPageViewFromSearch(url, title, this.hostname, searchSource, keywords); }
 
 
 
 
   
   public void trackEvent(String category, String action) { this.tracker.trackEvent(category, action); }
 
 
 
   
   public void trackEvent(String category, String action, String label) { this.tracker.trackEvent(category, action, label); }
 
 
 
 
   
   public void trackEvent(String category, String action, String label, Integer value) { this.tracker.trackEvent(category, action, label, value); }
 
 
 
   
   public void makeCustomRequest(AnalyticsRequestData data) { this.tracker.makeCustomRequest(data); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\WurstAnalytics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */