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

public class CPacketUseFirework {

  public static void encode(CPacketUseFirework msg, PacketBuffer buf) {

  }

  public static CPacketUseFirework decode(PacketBuffer buf) {
    return new CPacketUseFirework();
  }

  public static void handle(CPacketUseFirework msg, Supplier<NetworkEvent.Context> ctx) {

    ctx.get().enqueueWork(() -> {
      ServerPlayerEntity sender = ctx.get().getSender();

      if (sender == null) {
        return;
      }

      if (sender.isElytraFlying() && CaelusAPI.canElytraFly(sender)) {
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
    });
    ctx.get().setPacketHandled(true);
  }
}

