 package net.wurstclient.util;
 
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ThreadFactory;
 import java.util.concurrent.atomic.AtomicInteger;
 
 
 
 
 
 
 
 public class MinPriorityThreadFactory
   implements ThreadFactory
 {
   private static final AtomicInteger poolNumber = new AtomicInteger(1);
   private final ThreadGroup group;
   private final AtomicInteger threadNumber = new AtomicInteger(1);
   
   private final String namePrefix;
   
   public MinPriorityThreadFactory() {
     SecurityManager s = System.getSecurityManager();
     this
       .group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
     this.namePrefix = "pool-min-" + poolNumber.getAndIncrement() + "-thread-";
   }
 
 
 
   
   public Thread newThread(Runnable r) {
     Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
     if (t.isDaemon())
       t.setDaemon(false); 
     if (t.getPriority() != 1)
       t.setPriority(1); 
     return t;
   }
 
   
   public static ExecutorService newFixedThreadPool() {
     return Executors.newFixedThreadPool(
         Runtime.getRuntime().availableProcessors(), new MinPriorityThreadFactory());
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\MinPriorityThreadFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */