package com.example.examplemod;

import java.util.HashSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber
public class LuckyHorseshoe extends Item implements ICurioItem {

  private boolean isEquipped = false;

  public LuckyHorseshoe(Properties p_i48487_1_) {
    super(p_i48487_1_);
  }

  public void curiosTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
    isEquipped = true;
  }

  public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
    isEquipped = true;
  }

  public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
    isEquipped = false;
  }

  @SubscribeEvent
  public void livingFallEvent (LivingFallEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) event.getEntityLiving();
      if (isEquipped) {
        event.setDistance(0F);
      }
    }
  }
}
