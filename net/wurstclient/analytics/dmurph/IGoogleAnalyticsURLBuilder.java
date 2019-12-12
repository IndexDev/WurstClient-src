package net.wurstclient.analytics.dmurph;

public interface IGoogleAnalyticsURLBuilder {
  void resetSession();
  
  String getGoogleAnalyticsVersion();
  
  String buildURL(AnalyticsRequestData paramAnalyticsRequestData);
}


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\dmurph\IGoogleAnalyticsURLBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */