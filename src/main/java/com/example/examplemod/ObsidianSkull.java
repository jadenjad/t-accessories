package com.example.examplemod;

import com.sun.corba.se.impl.ior.IORTemplateImpl;
import java.util.HashSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber
  public class ObsidianSkull extends Item implements ICurioItem {

  public ObsidianSkull(Properties p_i48487_1_) {
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
      event.player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 3));
    }
  }
}
