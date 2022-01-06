package com.example.examplemod;

import java.util.HashSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber
public class HermesBoots extends Item implements ICurioItem {
  int accelCounter;
  double playerXLast5Tick;
  double playerZLast5Tick;
  int tickCounter;
  int tickBuffer = 7;

  public HermesBoots(Properties p_i48487_1_) {
    super(p_i48487_1_);
  }
  private boolean isEquipped = false;

  public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
    isEquipped = true;
  }

  public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
    isEquipped = false;
  }
  @SubscribeEvent
  public void onTickPlayerEvent(PlayerTickEvent event) {
    if (event.player != null) {
      if (isEquipped) {
        if (playerIsMoving(event.player) || tickCounter < tickBuffer) {
          if (accelCounter > 100) {
            event.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.12);
          }
          if (accelCounter >200) {
            event.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.14);
          }
          if (accelCounter > 300) {
            event.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.16);
          }
        } else {
          event.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.1);
          accelCounter = 0;
        }
        accelCounter++;
      } else {
        event.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.1);
        accelCounter = 0;
      }
    } assert event.player != null;
    if (tickCounter > tickBuffer) {
      playerXLast5Tick = event.player.position().x;
      playerZLast5Tick = event.player.position().z;
      tickCounter = 0;
    }
    tickCounter++;
    //System.out.println(accelCounter);
  }

  public boolean playerIsMoving(PlayerEntity player) {
    return (player.position().x != playerXLast5Tick || player.position().z != playerZLast5Tick);
  }
}
