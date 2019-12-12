 package net.wurstclient.util;
 
 import net.minecraft.class_238;
 import net.minecraft.class_243;
 import net.minecraft.class_824;
 import net.wurstclient.WurstClient;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 public static enum RenderUtils
 {
   private static final class_238 DEFAULT_AABB;
   
   static  {
     DEFAULT_AABB = new class_238(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
   }
   
   public static void scissorBox(int startX, int startY, int endX, int endY) {
     int width = endX - startX;
     int height = endY - startY;
     int bottomY = WurstClient.MC.field_1755.height - endY;
     double factor = WurstClient.MC.field_1704.method_4495();
     
     int scissorX = (int)(startX * factor);
     int scissorY = (int)(bottomY * factor);
     int scissorWidth = (int)(width * factor);
     int scissorHeight = (int)(height * factor);
     GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
   }
 
 
   
   public static void applyRenderOffset() { GL11.glTranslated(-class_824.field_4343, -class_824.field_4341, -class_824.field_4340); }
 
 
 
 
 
   
   public static void drawSolidBox() { drawSolidBox(DEFAULT_AABB); }
 
 
   
   public static void drawSolidBox(class_238 bb) {
     GL11.glBegin(7);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     GL11.glEnd();
   }
 
 
   
   public static void drawOutlinedBox() { drawOutlinedBox(DEFAULT_AABB); }
 
 
   
   public static void drawOutlinedBox(class_238 bb) {
     GL11.glBegin(1);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     GL11.glEnd();
   }
 
 
   
   public static void drawCrossBox() { drawCrossBox(DEFAULT_AABB); }
 
 
   
   public static void drawCrossBox(class_238 bb) {
     GL11.glBegin(1);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1325, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1325, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1321);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1324);
     
     GL11.glVertex3d(bb.field_1320, bb.field_1322, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, bb.field_1322, bb.field_1321);
     GL11.glEnd();
   }
 
   
   public static void drawNode(class_238 bb) {
     double midX = (bb.field_1323 + bb.field_1320) / 2.0D;
     double midY = (bb.field_1322 + bb.field_1325) / 2.0D;
     double midZ = (bb.field_1321 + bb.field_1324) / 2.0D;
     
     GL11.glVertex3d(midX, midY, bb.field_1324);
     GL11.glVertex3d(bb.field_1323, midY, midZ);
     
     GL11.glVertex3d(bb.field_1323, midY, midZ);
     GL11.glVertex3d(midX, midY, bb.field_1321);
     
     GL11.glVertex3d(midX, midY, bb.field_1321);
     GL11.glVertex3d(bb.field_1320, midY, midZ);
     
     GL11.glVertex3d(bb.field_1320, midY, midZ);
     GL11.glVertex3d(midX, midY, bb.field_1324);
     
     GL11.glVertex3d(midX, bb.field_1325, midZ);
     GL11.glVertex3d(bb.field_1320, midY, midZ);
     
     GL11.glVertex3d(midX, bb.field_1325, midZ);
     GL11.glVertex3d(bb.field_1323, midY, midZ);
     
     GL11.glVertex3d(midX, bb.field_1325, midZ);
     GL11.glVertex3d(midX, midY, bb.field_1321);
     
     GL11.glVertex3d(midX, bb.field_1325, midZ);
     GL11.glVertex3d(midX, midY, bb.field_1324);
     
     GL11.glVertex3d(midX, bb.field_1322, midZ);
     GL11.glVertex3d(bb.field_1320, midY, midZ);
     
     GL11.glVertex3d(midX, bb.field_1322, midZ);
     GL11.glVertex3d(bb.field_1323, midY, midZ);
     
     GL11.glVertex3d(midX, bb.field_1322, midZ);
     GL11.glVertex3d(midX, midY, bb.field_1321);
     
     GL11.glVertex3d(midX, bb.field_1322, midZ);
     GL11.glVertex3d(midX, midY, bb.field_1324);
   }
 
   
   public static void drawArrow(class_243 from, class_243 to) {
     double startX = from.field_1352;
     double startY = from.field_1351;
     double startZ = from.field_1350;
     
     double endX = to.field_1352;
     double endY = to.field_1351;
     double endZ = to.field_1350;
     
     GL11.glPushMatrix();
     
     GL11.glBegin(1);
     GL11.glVertex3d(startX, startY, startZ);
     GL11.glVertex3d(endX, endY, endZ);
     GL11.glEnd();
     
     GL11.glTranslated(endX, endY, endZ);
     GL11.glScaled(0.1D, 0.1D, 0.1D);
     
     double angleX = Math.atan2(endY - startY, startZ - endZ);
     GL11.glRotated(Math.toDegrees(angleX) + 90.0D, 1.0D, 0.0D, 0.0D);
     
     double angleZ = Math.atan2(endX - startX, 
         Math.sqrt(Math.pow(endY - startY, 2.0D) + Math.pow(endZ - startZ, 2.0D)));
     GL11.glRotated(Math.toDegrees(angleZ), 0.0D, 0.0D, 1.0D);
     
     GL11.glBegin(1);
     GL11.glVertex3d(0.0D, 2.0D, 1.0D);
     GL11.glVertex3d(-1.0D, 2.0D, 0.0D);
     
     GL11.glVertex3d(-1.0D, 2.0D, 0.0D);
     GL11.glVertex3d(0.0D, 2.0D, -1.0D);
     
     GL11.glVertex3d(0.0D, 2.0D, -1.0D);
     GL11.glVertex3d(1.0D, 2.0D, 0.0D);
     
     GL11.glVertex3d(1.0D, 2.0D, 0.0D);
     GL11.glVertex3d(0.0D, 2.0D, 1.0D);
     
     GL11.glVertex3d(1.0D, 2.0D, 0.0D);
     GL11.glVertex3d(-1.0D, 2.0D, 0.0D);
     
     GL11.glVertex3d(0.0D, 2.0D, 1.0D);
     GL11.glVertex3d(0.0D, 2.0D, -1.0D);
     
     GL11.glVertex3d(0.0D, 0.0D, 0.0D);
     GL11.glVertex3d(1.0D, 2.0D, 0.0D);
     
     GL11.glVertex3d(0.0D, 0.0D, 0.0D);
     GL11.glVertex3d(-1.0D, 2.0D, 0.0D);
     
     GL11.glVertex3d(0.0D, 0.0D, 0.0D);
     GL11.glVertex3d(0.0D, 2.0D, -1.0D);
     
     GL11.glVertex3d(0.0D, 0.0D, 0.0D);
     GL11.glVertex3d(0.0D, 2.0D, 1.0D);
     GL11.glEnd();
     
     GL11.glPopMatrix();
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\RenderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */