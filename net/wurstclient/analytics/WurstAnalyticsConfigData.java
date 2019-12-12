 package net.wurstclient.analytics;
 
 import net.wurstclient.analytics.dmurph.AnalyticsConfigData;
 import net.wurstclient.analytics.dmurph.VisitorData;
 
 
 
 
 
 
 
 
 
 public final class WurstAnalyticsConfigData
   extends AnalyticsConfigData
 {
   public WurstAnalyticsConfigData(String argTrackingCode) { super(argTrackingCode, VisitorData.newVisitor()); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\WurstAnalyticsConfigData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */