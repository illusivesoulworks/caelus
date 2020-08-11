/*
 * Copyright (c) 2019-2020 C4
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

package top.theillusivec4.caelus.core.client;

import java.awt.Color;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import top.theillusivec4.caelus.api.RenderElytraInfo;
import top.theillusivec4.caelus.api.event.RenderElytraCallback;

public class CaelusElytraRenderer<T extends PlayerEntity, M extends EntityModel<T>> extends
    ElytraFeatureRenderer<T, M> {

  private static final Identifier TEXTURE_ELYTRA = new Identifier("textures/entity/elytra.png");

  private final ElytraEntityModel<T> modelElytra = new ElytraEntityModel<>();

  public CaelusElytraRenderer(FeatureRendererContext<T, M> renderer) {
    super(renderer);
  }

  @Override
  public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn,
      T entityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
      float netHeadYaw, float headPitch) {
    ItemStack itemstack = entityIn.getEquippedStack(EquipmentSlot.CHEST);

    if (itemstack.getItem() != Items.ELYTRA) {
      RenderElytraInfo renderElytraEvent = new RenderElytraInfoImpl(entityIn);
      RenderElytraCallback.EVENT.invoker().process(entityIn, renderElytraEvent);

      if (renderElytraEvent.canRender()) {
        Identifier identifier;

        if (entityIn instanceof AbstractClientPlayerEntity) {
          AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityIn;
          boolean hasTextureOverride = renderElytraEvent.getTextureOverride() != null;
          boolean hasElytra = abstractclientplayerentity.canRenderElytraTexture()
              && abstractclientplayerentity.getElytraTexture() != null;
          boolean hasCape = abstractclientplayerentity.canRenderCapeTexture()
              && abstractclientplayerentity.getCapeTexture() != null && abstractclientplayerentity
              .isPartVisible(PlayerModelPart.CAPE);

          if (hasElytra) {
            identifier = abstractclientplayerentity.getElytraTexture();
          } else if (hasCape) {
            identifier = abstractclientplayerentity.getCapeTexture();
          } else if (hasTextureOverride) {
            identifier = renderElytraEvent.getTextureOverride();
          } else {
            identifier = TEXTURE_ELYTRA;
          }
        } else {
          identifier = TEXTURE_ELYTRA;
        }
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.0D, 0.125D);
        this.getContextModel().copyStateTo(this.modelElytra);
        this.modelElytra
            .setAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        VertexConsumer vertices = ItemRenderer
            .getGlintVertexConsumer(bufferIn, this.modelElytra.getLayer(identifier), false,
                renderElytraEvent.isGlowing());
        Color color = renderElytraEvent.getColor();
        float red = color.getRed() / 255.0F;
        float green = color.getGreen() / 255.0F;
        float blue = color.getBlue() / 255.0F;
        float alpha = color.getAlpha() / 255.0F;
        this.modelElytra
            .render(matrixStackIn, vertices, packedLightIn, OverlayTexture.DEFAULT_UV, red, green,
                blue, alpha);
        matrixStackIn.pop();
      }
    }
  }
}
