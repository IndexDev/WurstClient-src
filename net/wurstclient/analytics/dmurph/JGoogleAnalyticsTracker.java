 package net.wurstclient.analytics.dmurph;
 
 import java.net.HttpURLConnection;
 import java.net.InetSocketAddress;
 import java.net.Proxy;
 import java.net.SocketAddress;
 import java.net.URL;
 import java.util.LinkedList;
 import java.util.Scanner;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import java.util.regex.MatchResult;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class JGoogleAnalyticsTracker
 {
   public enum DispatchMode
   {
     SYNCHRONOUS,
 
 
     
     MULTI_THREAD,
 
 
 
     
     SINGLE_THREAD;
   }
 
   
   private static Logger logger = Logger.getLogger(JGoogleAnalyticsTracker.class.getName());
   private static final ThreadGroup asyncThreadGroup = new ThreadGroup("Async Google Analytics Threads");
   
   private static long asyncThreadsRunning = 0L;
   private static Proxy proxy = Proxy.NO_PROXY;
   private static LinkedList<String> fifo = new LinkedList();
   
   private static boolean backgroundThreadMayRun = false;
   
   private GoogleAnalyticsVersion gaVersion;
   private AnalyticsConfigData configData;
   
   static  {
     asyncThreadGroup.setMaxPriority(1);
     asyncThreadGroup.setDaemon(true);
     setProxy(System.getenv("http_proxy"));
   }
   private IGoogleAnalyticsURLBuilder builder; private DispatchMode mode;
   private boolean enabled;
   
   public enum GoogleAnalyticsVersion { V_4_7_2; }
 
 
 
 
 
 
 
 
 
 
   
   public JGoogleAnalyticsTracker(AnalyticsConfigData argConfigData, GoogleAnalyticsVersion argVersion) { this(argConfigData, argVersion, DispatchMode.SINGLE_THREAD); }
 
 
 
   
   public JGoogleAnalyticsTracker(AnalyticsConfigData argConfigData, GoogleAnalyticsVersion argVersion, DispatchMode argMode) {
     this.gaVersion = argVersion;
     this.configData = argConfigData;
     createBuilder();
     this.enabled = true;
     setDispatchMode(argMode);
   }
 
 
 
 
 
 
 
 
 
 
   
   public void setDispatchMode(DispatchMode argMode) {
     if (argMode == null)
       argMode = DispatchMode.SINGLE_THREAD; 
     if (argMode == DispatchMode.SINGLE_THREAD)
       startBackgroundThread(); 
     this.mode = argMode;
   }
 
 
 
 
 
 
 
 
 
   
   public DispatchMode getDispatchMode() { return this.mode; }
 
 
 
 
 
 
 
 
   
   public boolean isSynchronous() { return (this.mode == DispatchMode.SYNCHRONOUS); }
 
 
 
 
 
 
 
 
   
   public boolean isSingleThreaded() { return (this.mode == DispatchMode.SINGLE_THREAD); }
 
 
 
 
 
 
 
 
   
   public boolean isMultiThreaded() { return (this.mode == DispatchMode.MULTI_THREAD); }
 
 
 
 
 
 
   
   public void resetSession() { this.builder.resetSession(); }
 
 
 
 
 
 
 
 
   
   public void setEnabled(boolean argEnabled) { this.enabled = argEnabled; }
 
 
 
 
 
 
 
 
   
   public boolean isEnabled() { return this.enabled; }
 
 
 
 
 
 
 
 
 
 
 
   
   public static void setProxy(Proxy argProxy) { proxy = (argProxy != null) ? argProxy : Proxy.NO_PROXY; }
 
 
 
 
 
 
 
 
 
 
 
   
   public static void setProxy(String proxyAddr) {
     if (proxyAddr != null) {
       
       s = new Scanner(proxyAddr);
 
       
       proxyAddr = null;
       int proxyPort = 8080;
       
       try {
         s.findInLine("(http://|)([^:/]+)(:|)([0-9]*)(/|)");
         MatchResult m = s.match();
         
         if (m.groupCount() >= 2) {
           proxyAddr = m.group(2);
         }
         if (m.groupCount() >= 4 && m.group(4).length() != 0) {
           proxyPort = Integer.parseInt(m.group(4));
         }
       } finally {
         s.close();
       } 
       
       if (proxyAddr != null) {
         
         SocketAddress sa = new InetSocketAddress(proxyAddr, proxyPort);
         setProxy(new Proxy(Proxy.Type.HTTP, sa));
       } 
     } 
   }
 
 
 
 
 
 
 
 
 
 
   
   public static void completeBackgroundTasks(long timeoutMillis) {
     boolean fifoEmpty = false;
     boolean asyncThreadsCompleted = false;
     
     long absTimeout = System.currentTimeMillis() + timeoutMillis;
     while (System.currentTimeMillis() < absTimeout) {
       
       synchronized (fifo) {
         
         fifoEmpty = (fifo.size() == 0);
       } 
       
       synchronized (JGoogleAnalyticsTracker.class) {
         
         asyncThreadsCompleted = (asyncThreadsRunning == 0L);
       } 
       
       if (fifoEmpty && asyncThreadsCompleted) {
         break;
       }
       
       try {
         Thread.sleep(100L);
       } catch (InterruptedException e) {
         break;
       } 
     } 
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public void trackPageView(String argPageURL, String argPageTitle, String argHostName) {
     if (argPageURL == null) {
       throw new IllegalArgumentException("Page URL cannot be null, Google will not track the data.");
     }
     AnalyticsRequestData data = new AnalyticsRequestData();
     data.setHostName(argHostName);
     data.setPageTitle(argPageTitle);
     data.setPageURL(argPageURL);
     makeCustomRequest(data);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public void trackPageViewFromReferrer(String argPageURL, String argPageTitle, String argHostName, String argReferrerSite, String argReferrerPage) {
     if (argPageURL == null) {
       throw new IllegalArgumentException("Page URL cannot be null, Google will not track the data.");
     }
     AnalyticsRequestData data = new AnalyticsRequestData();
     data.setHostName(argHostName);
     data.setPageTitle(argPageTitle);
     data.setPageURL(argPageURL);
     data.setReferrer(argReferrerSite, argReferrerPage);
     makeCustomRequest(data);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public void trackPageViewFromSearch(String argPageURL, String argPageTitle, String argHostName, String argSearchSource, String argSearchKeywords) {
     if (argPageURL == null) {
       throw new IllegalArgumentException("Page URL cannot be null, Google will not track the data.");
     }
     AnalyticsRequestData data = new AnalyticsRequestData();
     data.setHostName(argHostName);
     data.setPageTitle(argPageTitle);
     data.setPageURL(argPageURL);
     data.setSearchReferrer(argSearchSource, argSearchKeywords);
     makeCustomRequest(data);
   }
 
 
 
 
 
 
 
 
 
   
   public void trackEvent(String argCategory, String argAction) { trackEvent(argCategory, argAction, null, null); }
 
 
 
 
 
 
 
 
 
 
 
 
   
   public void trackEvent(String argCategory, String argAction, String argLabel) { trackEvent(argCategory, argAction, argLabel, null); }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public void trackEvent(String argCategory, String argAction, String argLabel, Integer argValue) {
     AnalyticsRequestData data = new AnalyticsRequestData();
     data.setEventCategory(argCategory);
     data.setEventAction(argAction);
     data.setEventLabel(argLabel);
     data.setEventValue(argValue);
     
     makeCustomRequest(data);
   }
 
 
 
 
 
 
 
   
   public void makeCustomRequest(AnalyticsRequestData argData) {
     Thread t;
     if (!this.enabled) {
       
       logger.log(Level.CONFIG, "Ignoring tracking request, enabled is false");
       
       return;
     } 
     if (argData == null)
       throw new NullPointerException("Data cannot be null"); 
     if (this.builder == null)
       throw new NullPointerException("Class was not initialized"); 
     final String url = this.builder.buildURL(argData);
     final String userAgent = this.configData.getUserAgent();
     
     switch (this.mode) {
 
       
       case V_4_7_2:
         t = new Thread(asyncThreadGroup, "AnalyticsThread-" + asyncThreadGroup.activeCount())
           {
             
             public void run()
             {
               synchronized (JGoogleAnalyticsTracker.class) {
                 asyncThreadsRunning++;
               } 
 
               
               try {
                 JGoogleAnalyticsTracker.dispatchRequest(url, userAgent);
               } finally {
                 
                 synchronized (JGoogleAnalyticsTracker.class) {
                   asyncThreadsRunning--;
                 } 
               } 
             }
           };
         
         t.setDaemon(true);
         t.start();
         return;
       case null:
         dispatchRequest(url, userAgent);
         return;
     } 
     synchronized (fifo) {
       
       fifo.addLast(url);
       fifo.notify();
     } 
     if (!backgroundThreadMayRun) {
       logger.log(Level.SEVERE, "A tracker request has been added to the queue but the background thread isn't running.", url);
     }
   }
 
 
 
 
 
   
   private static void dispatchRequest(String argURL, String userAgent) {
     try {
       URL url = new URL(argURL);
       
       HttpURLConnection connection = (HttpURLConnection)url.openConnection(proxy);
       connection.setRequestMethod("GET");
       connection.setInstanceFollowRedirects(true);
       if (userAgent != null)
         connection.addRequestProperty("User-Agent", userAgent); 
       connection.connect();
       int responseCode = connection.getResponseCode();
       if (responseCode != 200) {
         logger.log(Level.SEVERE, "JGoogleAnalyticsTracker: Error requesting url '" + argURL + "', received response code " + responseCode);
       }
       else {
         
         logger.log(Level.CONFIG, "JGoogleAnalyticsTracker: Tracking success for url '" + argURL + "'");
       }
     
     } catch (Exception e) {
       
       logger.log(Level.SEVERE, "Error making tracking request", e);
     } 
   }
 
   
   private void createBuilder() {
     switch (this.gaVersion) {
       
       case V_4_7_2:
         this.builder = new GoogleAnalyticsV4_7_2(this.configData);
         return;
     } 
     this.builder = new GoogleAnalyticsV4_7_2(this.configData);
   }
 
 
 
 
 
 
   
   private void startBackgroundThread() {
     if (backgroundThread == null) {
       
       backgroundThreadMayRun = true;
       backgroundThread = new Thread(asyncThreadGroup, "AnalyticsBackgroundThread")
         {
 
           
           public void run()
           {
             logger.log(Level.CONFIG, "AnalyticsBackgroundThread started");
             
             while (backgroundThreadMayRun) {
               
               try {
                 String url = null;
                 
                 synchronized (fifo) {
                   
                   if (fifo.isEmpty()) {
                     fifo.wait();
                   }
                   if (!fifo.isEmpty())
                   {
 
 
 
                     
                     url = (String)fifo.getFirst();
                   }
                 } 
                 if (url != null)
                   
                   try {
                     JGoogleAnalyticsTracker.dispatchRequest(url, JGoogleAnalyticsTracker.this
                         .configData.getUserAgent());
 
                   
                   }
                   finally {
 
                     
                     synchronized (fifo) {
                       
                       fifo.removeFirst();
                     } 
                   }  
               } catch (Exception e) {
                 
                 logger.log(Level.SEVERE, "Got exception from dispatch thread", e);
               } 
             } 
           }
         };
 
 
 
       
       backgroundThread.setDaemon(true);
       
       backgroundThread.start();
     } 
   }
 
 
 
 
 
 
 
 
 
 
 
   
   public static void stopBackgroundThread(long timeoutMillis) {
     backgroundThreadMayRun = false;
     fifo.notify();
     if (backgroundThread != null && timeoutMillis > 0L) {
 
       
       try {
         backgroundThread.join(timeoutMillis);
       } catch (InterruptedException interruptedException) {}
       
       backgroundThread = null;
     } 
   }
 
 
   
   public AnalyticsConfigData getConfigData() { return this.configData; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\analytics\dmurph\JGoogleAnalyticsTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */