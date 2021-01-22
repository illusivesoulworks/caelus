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

package top.theillusivec4.caelus.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.awt.Color;
import javax.annotation.Nonnull;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import top.theillusivec4.caelus.api.RenderElytraEvent;

public class CaelusElytraLayer<T extends PlayerEntity, M extends EntityModel<T>> extends
    ElytraLayer<T, M> {

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
      RenderElytraEvent renderElytraEvent = new RenderElytraEvent(entityIn);
      MinecraftForge.EVENT_BUS.post(renderElytraEvent);

      if (renderElytraEvent.canRender()) {
        ResourceLocation resourcelocation;

        if (entityIn instanceof AbstractClientPlayerEntity) {
          AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityIn;
          boolean hasElytra = abstractclientplayerentity.isPlayerInfoSet()
              && abstractclientplayerentity.getLocationElytra() != null;
          boolean hasCape = abstractclientplayerentity.hasPlayerInfo()
              && abstractclientplayerentity.getLocationCape() != null && abstractclientplayerentity
              .isWearing(PlayerModelPart.CAPE);

          if (hasElytra) {
            resourcelocation = abstractclientplayerentity.getLocationElytra();
          } else if (hasCape) {
            resourcelocation = abstractclientplayerentity.getLocationCape();
          } else {
            resourcelocation = renderElytraEvent.getResourceLocation();
          }
        } else {
          resourcelocation = renderElytraEvent.getResourceLocation();
        }
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.0D, 0.125D);
        this.getEntityModel().copyModelAttributesTo(this.modelElytra);
        this.modelElytra
            .setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
                headPitch);
        IVertexBuilder ivertexbuilder = ItemRenderer
            .getArmorVertexBuilder(bufferIn, RenderType.getArmorCutoutNoCull(resourcelocation), false,
                renderElytraEvent.isEnchanted());
        Color color = renderElytraEvent.getColor();
        this.modelElytra
            .render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        matrixStackIn.pop();
      }
    }
  }
}
