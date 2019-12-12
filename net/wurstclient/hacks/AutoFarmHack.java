 package net.wurstclient.hacks;
 
 import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;
 import net.minecraft.class_1268;
 import net.minecraft.class_1792;
 import net.minecraft.class_1799;
 import net.minecraft.class_1802;
 import net.minecraft.class_2246;
 import net.minecraft.class_2248;
 import net.minecraft.class_2302;
 import net.minecraft.class_2338;
 import net.minecraft.class_2350;
 import net.minecraft.class_238;
 import net.minecraft.class_2421;
 import net.minecraft.class_243;
 import net.minecraft.class_265;
 import net.minecraft.class_2680;
 import net.minecraft.class_2879;
 import net.minecraft.class_746;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.RenderListener;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.settings.SliderSetting;
 import net.wurstclient.util.BlockBreaker;
 import net.wurstclient.util.BlockUtils;
 import net.wurstclient.util.RenderUtils;
 import net.wurstclient.util.RotationUtils;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 @SearchTags({"auto farm", "AutoHarvest", "auto harvest"})
 public final class AutoFarmHack
   extends Hack
   implements UpdateListener, RenderListener
 {
   private final SliderSetting range = new SliderSetting("Range", 5.0D, 1.0D, 6.0D, 0.05D, SliderSetting.ValueDisplay.DECIMAL);
 
   
   private final HashMap<class_2338, class_1792> plants = new HashMap();
   
   private final ArrayDeque<Set<class_2338>> prevBlocks = new ArrayDeque();
   
   private class_2338 currentBlock;
   
   private float progress;
   private float prevProgress;
   private int displayList;
   private int box;
   private int node;
   
   public AutoFarmHack() {
     super("AutoFarm", "Harvests and re-plants crops automatically.\nWorks with wheat, carrots, potatoes, beetroots,\npumpkins, melons, cacti, sugar canes, kelp and\nnether warts.");
 
 
 
     
     setCategory(Category.BLOCKS);
     addSetting(this.range);
   }
 
 
   
   public void onEnable() {
     this.plants.clear();
     
     EVENTS.add(UpdateListener.class, this);
     EVENTS.add(RenderListener.class, this);
     
     this.displayList = GL11.glGenLists(1);
     this.box = GL11.glGenLists(1);
     this.node = GL11.glGenLists(1);
     
     GL11.glNewList(this.box, 4864);
     class_238 box = new class_238(0.0625D, 0.0625D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
     
     RenderUtils.drawOutlinedBox(box);
     GL11.glEndList();
     
     GL11.glNewList(this.node, 4864);
     class_238 node = new class_238(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
     GL11.glBegin(1);
     RenderUtils.drawNode(node);
     GL11.glEnd();
     GL11.glEndList();
   }
 
 
   
   public void onDisable() {
     EVENTS.remove(UpdateListener.class, this);
     EVENTS.remove(RenderListener.class, this);
     
     if (this.currentBlock != null) {
       
       IMC.getInteractionManager().setBreakingBlock(true);
       MC.field_1761.method_2925();
       this.currentBlock = null;
     } 
     
     this.prevBlocks.clear();
     GL11.glDeleteLists(this.displayList, 1);
     GL11.glDeleteLists(this.box, 1);
     GL11.glDeleteLists(this.node, 1);
   }
 
 
 
   
   public void onUpdate() {
     this.currentBlock = null;
     class_243 eyesVec = RotationUtils.getEyesPos().method_1023(0.5D, 0.5D, 0.5D);
     class_2338 eyesBlock = new class_2338(RotationUtils.getEyesPos());
     double rangeSq = Math.pow(this.range.getValue(), 2.0D);
     int blockRange = (int)Math.ceil(this.range.getValue());
 
 
 
     
     List<class_2338> blocks = (List)getBlockStream(eyesBlock, blockRange).filter(pos -> (eyesVec.method_1025(new class_243(pos)) <= rangeSq)).filter(pos -> BlockUtils.canBeClicked(pos)).collect(Collectors.toList());
     
     registerPlants(blocks);
     
     List<class_2338> blocksToHarvest = new ArrayList<class_2338>();
     List<class_2338> blocksToReplant = new ArrayList<class_2338>();
     
     if (!(WURST.getHax()).freecamHack.isEnabled()) {
 
 
 
 
       
       blocksToHarvest = (List)blocks.parallelStream().filter(this::shouldBeHarvested).sorted(Comparator.comparingDouble(pos -> eyesVec.method_1025(new class_243(pos)))).collect(Collectors.toList());
 
 
 
 
 
 
 
 
 
       
       blocksToReplant = (List)getBlockStream(eyesBlock, blockRange).filter(pos -> (eyesVec.method_1025(new class_243(pos)) <= rangeSq)).filter(pos -> BlockUtils.getState(pos).method_11620().method_15800()).filter(pos -> this.plants.containsKey(pos)).filter(this::canBeReplanted).sorted(Comparator.comparingDouble(pos -> eyesVec.method_1025(new class_243(pos)))).collect(Collectors.toList());
     } 
     
     while (!blocksToReplant.isEmpty()) {
       
       class_2338 pos = (class_2338)blocksToReplant.get(0);
       class_1792 neededItem = (class_1792)this.plants.get(pos);
       if (tryToReplant(pos, neededItem)) {
         break;
       }
       blocksToReplant.removeIf(p -> (this.plants.get(p) == neededItem));
     } 
     
     if (blocksToReplant.isEmpty()) {
       harvest(blocksToHarvest);
     }
     updateDisplayList(blocksToHarvest, blocksToReplant);
   }
 
 
 
   
   public void onRender(float partialTicks) {
     GL11.glEnable(3042);
     GL11.glBlendFunc(770, 771);
     GL11.glEnable(2848);
     GL11.glLineWidth(2.0F);
     GL11.glDisable(3553);
     GL11.glEnable(2884);
     GL11.glDisable(2929);
     
     GL11.glPushMatrix();
     RenderUtils.applyRenderOffset();
     
     GL11.glCallList(this.displayList);
     
     if (this.currentBlock != null) {
       
       GL11.glPushMatrix();
       
       class_238 box = new class_238(class_2338.field_10980);
       float p = this.prevProgress + (this.progress - this.prevProgress) * partialTicks;
       float red = p * 2.0F;
       float green = 2.0F - red;
       
       GL11.glTranslated(this.currentBlock.method_10263(), this.currentBlock.method_10264(), this.currentBlock
           .method_10260());
       if (p < 1.0F) {
         
         GL11.glTranslated(0.5D, 0.5D, 0.5D);
         GL11.glScaled(p, p, p);
         GL11.glTranslated(-0.5D, -0.5D, -0.5D);
       } 
       
       GL11.glColor4f(red, green, 0.0F, 0.25F);
       RenderUtils.drawSolidBox(box);
       
       GL11.glColor4f(red, green, 0.0F, 0.5F);
       RenderUtils.drawOutlinedBox(box);
       
       GL11.glPopMatrix();
     } 
     
     GL11.glPopMatrix();
 
     
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(2929);
     GL11.glEnable(3553);
     GL11.glDisable(3042);
     GL11.glDisable(2848);
   }
 
   
   private Stream<class_2338> getBlockStream(class_2338 center, int range) {
     class_2338 min = center.method_10069(-range, -range, -range);
     class_2338 max = center.method_10069(range, range, range);
     
     return BlockUtils.getAllInBox(min, max).stream();
   }
 
   
   private boolean shouldBeHarvested(class_2338 pos) {
     class_2248 block = BlockUtils.getBlock(pos);
     class_2680 state = BlockUtils.getState(pos);
     
     if (block instanceof class_2302)
       return ((class_2302)block).method_9825(state); 
     if (block instanceof net.minecraft.class_2511)
       return true; 
     if (block instanceof net.minecraft.class_2523)
       return (BlockUtils.getBlock(pos.method_10074()) instanceof net.minecraft.class_2523 && 
         
         !(BlockUtils.getBlock(pos.method_10087(2)) instanceof net.minecraft.class_2523)); 
     if (block instanceof net.minecraft.class_2266)
       return (BlockUtils.getBlock(pos.method_10074()) instanceof net.minecraft.class_2266 && 
         !(BlockUtils.getBlock(pos.method_10087(2)) instanceof net.minecraft.class_2266)); 
     if (block instanceof net.minecraft.class_2391)
       return (BlockUtils.getBlock(pos.method_10074()) instanceof net.minecraft.class_2391 && 
         
         !(BlockUtils.getBlock(pos.method_10087(2)) instanceof net.minecraft.class_2391)); 
     if (block instanceof class_2421) {
       return (((Integer)state.method_11654(class_2421.field_11306)).intValue() >= 3);
     }
     return false;
   }
 
   
   private void registerPlants(List<class_2338> blocks) {
     HashMap<class_2248, class_1792> seeds = new HashMap<class_2248, class_1792>();
     seeds.put(class_2246.field_10293, class_1802.field_8317);
     seeds.put(class_2246.field_10609, class_1802.field_8179);
     seeds.put(class_2246.field_10247, class_1802.field_8567);
     seeds.put(class_2246.field_10341, class_1802.field_8309);
     seeds.put(class_2246.field_9984, class_1802.field_8706);
     seeds.put(class_2246.field_10168, class_1802.field_8188);
     seeds.put(class_2246.field_9974, class_1802.field_8790);
     
     this.plants.putAll((Map)blocks.parallelStream()
         .filter(pos -> seeds.containsKey(BlockUtils.getBlock(pos)))
         .collect(Collectors.toMap(pos -> pos, pos -> 
             (class_1792)seeds.get(BlockUtils.getBlock(pos)))));
   }
 
   
   private boolean canBeReplanted(class_2338 pos) {
     class_1792 item = (class_1792)this.plants.get(pos);
     
     if (item == class_1802.field_8317 || item == class_1802.field_8179 || item == class_1802.field_8567 || item == class_1802.field_8309 || item == class_1802.field_8706 || item == class_1802.field_8188)
     {
       
       return BlockUtils.getBlock(pos.method_10074()) instanceof net.minecraft.class_2344;
     }
     if (item == class_1802.field_8790) {
       return BlockUtils.getBlock(pos.method_10074()) instanceof net.minecraft.class_2492;
     }
     return false;
   }
 
   
   private boolean tryToReplant(class_2338 pos, class_1792 neededItem) {
     class_746 player = MC.field_1724;
     class_1799 heldItem = player.method_6047();
     
     if (!heldItem.method_7960() && heldItem.method_7909() == neededItem) {
       
       placeBlockSimple(pos);
       return true;
     } 
     
     for (int slot = 0; slot < 36; slot++) {
       
       if (slot != player.field_7514.field_7545) {
 
         
         class_1799 stack = player.field_7514.method_5438(slot);
         if (!stack.method_7960() && stack.method_7909() == neededItem) {
 
           
           if (slot < 9) {
             player.field_7514.field_7545 = slot;
           } else if (player.field_7514.method_7376() < 9) {
             IMC.getInteractionManager().windowClick_QUICK_MOVE(slot);
           } else if (player.field_7514.method_7376() != -1) {
             
             IMC.getInteractionManager()
               .windowClick_QUICK_MOVE(player.field_7514.field_7545 + 36);
             IMC.getInteractionManager().windowClick_QUICK_MOVE(slot);
           } else {
             
             IMC.getInteractionManager()
               .windowClick_PICKUP(player.field_7514.field_7545 + 36);
             IMC.getInteractionManager().windowClick_PICKUP(slot);
             IMC.getInteractionManager()
               .windowClick_PICKUP(player.field_7514.field_7545 + 36);
           } 
           
           return true;
         } 
       } 
     }  return false;
   }
 
   
   private void placeBlockSimple(class_2338 pos) {
     class_2350 side = null;
     class_2350[] sides = class_2350.values();
     
     class_243 eyesPos = RotationUtils.getEyesPos();
     class_243 posVec = (new class_243(pos)).method_1031(0.5D, 0.5D, 0.5D);
     double distanceSqPosVec = eyesPos.method_1025(posVec);
     
     class_243[] hitVecs = new class_243[sides.length];
     for (int i = 0; i < sides.length; i++) {
       hitVecs[i] = posVec
         .method_1019((new class_243(sides[i].method_10163())).method_1021(0.5D));
     }
     for (int i = 0; i < sides.length; i++) {
 
       
       class_2338 neighbor = pos.method_10093(sides[i]);
       if (BlockUtils.canBeClicked(neighbor)) {
 
 
         
         class_2680 neighborState = BlockUtils.getState(neighbor);
         
         class_265 neighborShape = neighborState.method_17770(MC.field_1687, neighbor);
         if (MC.field_1687.method_17745(eyesPos, hitVecs[i], neighbor, neighborShape, neighborState) == null) {
 
 
           
           side = sides[i]; break;
         } 
       } 
     } 
     if (side == null)
       for (int i = 0; i < sides.length; i++) {
 
         
         if (BlockUtils.canBeClicked(pos.method_10093(sides[i])))
         {
 
           
           if (distanceSqPosVec <= eyesPos.method_1025(hitVecs[i])) {
 
             
             side = sides[i];
             break;
           }  } 
       }  
     if (side == null) {
       return;
     }
     class_243 hitVec = hitVecs[side.ordinal()];
 
     
     WURST.getRotationFaker().faceVectorPacket(hitVec);
     if (RotationUtils.getAngleToLastReportedLookVec(hitVec) > 1.0D) {
       return;
     }
     
     if (IMC.getItemUseCooldown() > 0) {
       return;
     }
     
     IMC.getInteractionManager().rightClickBlock(pos.method_10093(side), side
         .method_10153(), hitVec);
 
     
     MC.field_1724.field_3944
       .method_2883(new class_2879(class_1268.field_5808));
 
     
     IMC.setItemUseCooldown(4);
   }
 
   
   private void harvest(List<class_2338> blocksToHarvest) {
     if (MC.field_1724.field_7503.field_7477) {
       
       Stream<class_2338> stream3 = blocksToHarvest.parallelStream();
       for (Set<class_2338> set : this.prevBlocks) {
         stream3 = stream3.filter(pos -> !set.contains(pos));
       }
       List<class_2338> blocksToHarvest2 = (List)stream3.collect(Collectors.toList());
       
       this.prevBlocks.addLast(new HashSet(blocksToHarvest2));
       while (this.prevBlocks.size() > 5) {
         this.prevBlocks.removeFirst();
       }
       if (!blocksToHarvest2.isEmpty()) {
         this.currentBlock = (class_2338)blocksToHarvest2.get(0);
       }
       MC.field_1761.method_2925();
       this.progress = 1.0F;
       this.prevProgress = 1.0F;
       BlockBreaker.breakBlocksWithPacketSpam(blocksToHarvest2);
       
       return;
     } 
     for (class_2338 pos : blocksToHarvest) {
       if (BlockBreaker.breakOneBlock(pos)) {
         
         this.currentBlock = pos;
         break;
       } 
     } 
     if (this.currentBlock == null) {
       MC.field_1761.method_2925();
     }
     if (this.currentBlock != null && BlockUtils.getHardness(this.currentBlock) < 1.0F) {
       
       this.prevProgress = this.progress;
       this.progress = IMC.getInteractionManager().getCurrentBreakingProgress();
       
       if (this.progress < this.prevProgress) {
         this.prevProgress = this.progress;
       }
     } else {
       
       this.progress = 1.0F;
       this.prevProgress = 1.0F;
     } 
   }
 
 
   
   private void updateDisplayList(List<class_2338> blocksToHarvest, List<class_2338> blocksToReplant) {
     GL11.glNewList(this.displayList, 4864);
     GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.5F);
     for (class_2338 pos : blocksToHarvest) {
       
       GL11.glPushMatrix();
       GL11.glTranslated(pos.method_10263(), pos.method_10264(), pos.method_10260());
       GL11.glCallList(this.box);
       GL11.glPopMatrix();
     } 
     GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
     for (class_2338 pos : this.plants.keySet()) {
       
       GL11.glPushMatrix();
       GL11.glTranslated(pos.method_10263(), pos.method_10264(), pos.method_10260());
       GL11.glCallList(this.node);
       GL11.glPopMatrix();
     } 
     GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
     for (class_2338 pos : blocksToReplant) {
       
       GL11.glPushMatrix();
       GL11.glTranslated(pos.method_10263(), pos.method_10264(), pos.method_10260());
       GL11.glCallList(this.box);
       GL11.glPopMatrix();
     } 
     GL11.glEndList();
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoFarmHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */