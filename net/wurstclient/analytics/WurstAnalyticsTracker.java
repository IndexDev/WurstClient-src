 package net.wurstclient.analytics;
 
 import net.wurstclient.analytics.dmurph.JGoogleAnalyticsTracker;
 
 
 
 
 
 
 
 
 
 public final class WurstAnalyticsTracker
   extends JGoogleAnalyticsTracker
 {
   public WurstAnalyticsTracker(String trackingID) { super(new WurstAnalyticsConfigData(trackingID), JGoogleAnalyticsTracker.GoogleAnalyticsVersion.V_4_7_2); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\WurstAnalyticsTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */