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

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import javax.annotation.Nonnull;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.caelus.api.CaelusAPI;
import top.theillusivec4.caelus.api.capability.IRenderElytra.State;

public class CaelusElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends
    ElytraLayer<T, M> {

  private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation(
      "textures/entity/elytra.png");

  private final ElytraModel<T> modelElytra = new ElytraModel<>();

  public CaelusElytraLayer(IEntityRenderer<T, M> renderer) {
    super(renderer);
  }

  @Override
  public void render(@Nonnull MatrixStack matrixStackIn, @Nonnull IRenderTypeBuffer bufferIn,
      int packedLightIn, T entityIn, float limbSwing, float limbSwingAmount, float partialTicks,
      float ageInTicks, float netHeadYaw, float headPitch) {
    ItemStack itemstack = entityIn.getItemStackFromSlot(EquipmentSlotType.CHEST);

    if (itemstack.getItem() != Items.ELYTRA) {
      CaelusAPI.getRenderElytra(entityIn).ifPresent(render -> {

        if (render.getRenderState() != State.NONE) {
          ResourceLocation resourcelocation;
          if (entityIn instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityIn;
            boolean hasElytra = abstractclientplayerentity.isPlayerInfoSet()
                && abstractclientplayerentity.getLocationElytra() != null;
            boolean hasCape = abstractclientplayerentity.hasPlayerInfo()
                && abstractclientplayerentity.getLocationCape() != null
                && abstractclientplayerentity.isWearing(PlayerModelPart.CAPE);

            if (hasElytra) {
              resourcelocation = abstractclientplayerentity.getLocationElytra();
            } else if (hasCape) {
              resourcelocation = abstractclientplayerentity.getLocationCape();
            } else {
              resourcelocation = TEXTURE_ELYTRA;
            }
          } else {
            resourcelocation = TEXTURE_ELYTRA;
          }

          matrixStackIn.push();
          matrixStackIn.translate(0.0D, 0.0D, 0.125D);
          this.getEntityModel().setModelAttributes(this.modelElytra);
          this.modelElytra
              .render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
          IVertexBuilder ivertexbuilder = ItemRenderer
              .getBuffer(bufferIn, this.modelElytra.getRenderType(resourcelocation), false,
                  render.getRenderState() == State.ENCHANTED);
          this.modelElytra
              .render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.DEFAULT_LIGHT,
                  1.0F, 1.0F, 1.0F, 1.0F);
          matrixStackIn.pop();
        }
      });
    }
  }
}
