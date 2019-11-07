/*
 * Copyright (C) 2019  C4
 *
 * This file is part of Caelus, a mod made for Minecraft.
 *
 * Caelus is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Caelus.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.caelus.common.network.client;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.caelus.api.CaelusAPI;

public class CPacketSetFlight {

  private final boolean withMotion;
  private final boolean withFirework;

  public CPacketSetFlight(boolean withMotion) {
    this(withMotion, false);
  }

  public CPacketSetFlight(boolean withMotion, boolean withFirework) {
    this.withMotion = withMotion;
    this.withFirework = withFirework;
  }

  public static void encode(CPacketSetFlight msg, PacketBuffer buf) {
    buf.writeBoolean(msg.withMotion);
    buf.writeBoolean(msg.withFirework);
  }

  public static CPacketSetFlight decode(PacketBuffer buf) {
    return new CPacketSetFlight(buf.readBoolean(), buf.readBoolean());
  }

  public static void handle(CPacketSetFlight msg, Supplier<NetworkEvent.Context> ctx) {

    ctx.get().enqueueWork(() -> {
      ServerPlayerEntity sender = ctx.get().getSender();

      if (sender == null) {
        return;
      }

      sender.clearElytraFlying();
      boolean isFalling = !msg.withMotion || sender.getMotion().y < 0.0D;

      if (!sender.onGround && isFalling && !sender.isElytraFlying() && !sender.isInWater()
          && CaelusAPI.canElytraFly(sender)) {
        sender.setElytraFlying();

        if (msg.withFirework) {
          PlayerInventory inventory = sender.inventory;
          ItemStack firework = ItemStack.EMPTY;

          if (inventory.getCurrentItem().getItem() instanceof FireworkRocketItem) {
            firework = inventory.getCurrentItem();
          } else {
            final List<NonNullList<ItemStack>> allInventories = ImmutableList
                .of(inventory.offHandInventory, inventory.mainInventory);

            for (List<ItemStack> list : allInventories) {

              for (ItemStack stack : list) {

                if (stack.getItem() instanceof FireworkRocketItem) {
                  firework = stack;
                  break;
                }
              }

              if (!firework.isEmpty()) {
                break;
              }
            }
          }

          if (!firework.isEmpty()) {
            sender.world.addEntity(new FireworkRocketEntity(sender.world, firework.copy(), sender));

            if (!sender.abilities.isCreativeMode) {
              firework.shrink(1);
            }
          }
        }
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
