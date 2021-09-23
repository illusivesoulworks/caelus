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

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.awt.Color;
import javax.annotation.Nonnull;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import top.theillusivec4.caelus.api.RenderElytraEvent;

public class CaelusElytraLayer<T extends Player, M extends EntityModel<T>> extends
    ElytraLayer<T, M> {

  private final ElytraModel<T> modelElytra = new ElytraModel<>();

  public CaelusElytraLayer(RenderLayerParent<T, M> renderer) {
    super(renderer);
  }

  @Override
  public void render(@Nonnull PoseStack matrixStackIn, @Nonnull MultiBufferSource bufferIn,
                     int packedLightIn, T entityIn, float limbSwing, float limbSwingAmount,
                     float partialTicks,
                     float ageInTicks, float netHeadYaw, float headPitch) {
    ItemStack itemstack = entityIn.getItemBySlot(EquipmentSlot.CHEST);

    if (itemstack.getItem() != Items.ELYTRA) {
      RenderElytraEvent renderElytraEvent = new RenderElytraEvent(entityIn);
      MinecraftForge.EVENT_BUS.post(renderElytraEvent);

      if (renderElytraEvent.canRender()) {
        ResourceLocation resourcelocation;

        if (entityIn instanceof AbstractClientPlayer) {
          AbstractClientPlayer abstractclientplayerentity =
              (AbstractClientPlayer) entityIn;
          boolean hasElytra = abstractclientplayerentity.isElytraLoaded()
              && abstractclientplayerentity.getElytraTextureLocation() != null;
          boolean hasCape = abstractclientplayerentity.isCapeLoaded()
              && abstractclientplayerentity.getCloakTextureLocation() != null && abstractclientplayerentity
              .isModelPartShown(PlayerModelPart.CAPE);

          if (hasElytra) {
            resourcelocation = abstractclientplayerentity.getElytraTextureLocation();
          } else if (hasCape) {
            resourcelocation = abstractclientplayerentity.getCloakTextureLocation();
          } else {
            resourcelocation = renderElytraEvent.getResourceLocation();
          }
        } else {
          resourcelocation = renderElytraEvent.getResourceLocation();
        }
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.0D, 0.125D);
        this.getParentModel().copyPropertiesTo(this.modelElytra);
        this.modelElytra
            .setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
                headPitch);
        VertexConsumer ivertexbuilder = ItemRenderer
            .getArmorFoilBuffer(bufferIn, this.modelElytra.renderType(resourcelocation),
                false,
                renderElytraEvent.isEnchanted());
        Color color = renderElytraEvent.getColor();
        float red = color.getRed() / 255.0F;
        float green = color.getGreen() / 255.0F;
        float blue = color.getBlue() / 255.0F;
        float alpha = color.getAlpha() / 255.0F;
        this.modelElytra
            .renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, red,
                green, blue, alpha);
        matrixStackIn.popPose();
      }
    }
  }
}
