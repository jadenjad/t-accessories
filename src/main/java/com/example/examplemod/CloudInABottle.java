package com.example.examplemod;

import java.util.HashSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber
public class CloudInABottle extends Item implements ICurioItem {
  boolean haveDoubleJumped = false;

  public CloudInABottle(Properties p_i48487_1_) {
    super(p_i48487_1_);
  }
//  private boolean isEquipped = false;
  @Override
  public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
    Vector3d currentVelocity = livingEntity.getDeltaMovement();
    if ((Math.round(currentVelocity.y * 100000.0)/100000.0) != -0.0784) {
      if (TAccessories.cloudKey.isDown() && !haveDoubleJumped ) {
        livingEntity.setDeltaMovement(currentVelocity.x, .56F, currentVelocity.z);
        haveDoubleJumped = true;
        System.out.print(1);
      } else {
        System.out.print(0);
      }
    } else {
      haveDoubleJumped = false;
    }
  }
}
