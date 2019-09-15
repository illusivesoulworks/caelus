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

package top.theillusivec4.caelus.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import javax.annotation.Nonnull;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import top.theillusivec4.caelus.core.CaelusHooks;

public class CaelusElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends
    ElytraLayer<T, M> {

  private static final ResourceLocation TEXTURE_ELYTRA =
      new ResourceLocation("textures/entity/elytra.png");

  private final ElytraModel<T> modelElytra = new ElytraModel<>();

  public CaelusElytraLayer(IEntityRenderer<T, M> renderer) {

    super(renderer);
  }

  @Override
  public void render(@Nonnull T entityIn, float limbSwing, float limbSwingAmount,
      float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    ItemStack itemstack = entityIn.getItemStackFromSlot(EquipmentSlotType.CHEST);

    if (itemstack.getItem() != Items.ELYTRA) {
      Tuple<Boolean, Boolean> evt = CaelusHooks.fireRenderElytraEvent(entityIn);

      if (evt.getA()) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        if (entityIn instanceof AbstractClientPlayerEntity) {
          AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityIn;
          boolean hasElytra = abstractclientplayerentity.isPlayerInfoSet()
              && abstractclientplayerentity.getLocationElytra() != null;
          boolean hasCape = abstractclientplayerentity.hasPlayerInfo()
              && abstractclientplayerentity.getLocationCape() != null
              && abstractclientplayerentity.isWearing(PlayerModelPart.CAPE);

          if (hasElytra) {
            this.bindTexture(abstractclientplayerentity.getLocationElytra());
          } else if (hasCape) {
            this.bindTexture(abstractclientplayerentity.getLocationCape());
          } else {
            this.bindTexture(TEXTURE_ELYTRA);
          }
        } else {
          this.bindTexture(TEXTURE_ELYTRA);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, 0.0F, 0.125F);
        this.modelElytra.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks,
            netHeadYaw, headPitch, scale);
        this.modelElytra.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
            headPitch, scale);

        if (evt.getB()) {
          ArmorLayer.func_215338_a(this::bindTexture, entityIn, this.modelElytra, limbSwing,
              limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch,
              scale);
        }

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
      }
    }
  }
}
