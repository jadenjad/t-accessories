package com.example.examplemod;

import java.util.HashSet;
import java.util.Objects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber
public class CobaltShield extends Item implements ICurioItem {

  public CobaltShield(Properties p_i48487_1_) {
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
  public void playerTickEvent(PlayerTickEvent event) {
    if (isEquipped) {
      Objects.requireNonNull(event.player.getAttribute(Attributes.KNOCKBACK_RESISTANCE)).setBaseValue(1000);
    }
  }
}
