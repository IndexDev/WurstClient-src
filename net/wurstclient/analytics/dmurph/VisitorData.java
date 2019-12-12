 package net.wurstclient.analytics.dmurph;
 
 import java.security.SecureRandom;
 
 
 
 public class VisitorData
 {
   private int visitorId;
   private long timestampFirst;
   private long timestampPrevious;
   private long timestampCurrent;
   private int visits;
   
   private VisitorData(int visitorId, long timestampFirst, long timestampPrevious, long timestampCurrent, int visits) {
     this.visitorId = visitorId;
     this.timestampFirst = timestampFirst;
     this.timestampPrevious = timestampPrevious;
     this.timestampCurrent = timestampCurrent;
     this.visits = visits;
   }
 
 
 
 
   
   public static VisitorData newVisitor() {
     visitorId = (new SecureRandom()).nextInt() & 0x7FFFFFFF;
     long now = now();
     return new VisitorData(visitorId, now, now, now, 1);
   }
 
 
 
   
   public static VisitorData newSession(int visitorId, long timestampfirst, long timestamplast, int visits) { return new VisitorData(visitorId, timestampfirst, timestamplast, now(), visits + 1); }
 
 
 
   
   public void resetSession() {
     long now = now();
     this.timestampPrevious = this.timestampCurrent;
     this.timestampCurrent = now;
     this.visits++;
   }
 
 
   
   private static long now() { return System.currentTimeMillis() / 1000L; }
 
 
 
   
   public int getVisitorId() { return this.visitorId; }
 
 
 
   
   public long getTimestampFirst() { return this.timestampFirst; }
 
 
 
   
   public long getTimestampPrevious() { return this.timestampPrevious; }
 
 
 
   
   public long getTimestampCurrent() { return this.timestampCurrent; }
 
 
 
   
   public int getVisits() { return this.visits; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\dmurph\VisitorData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */