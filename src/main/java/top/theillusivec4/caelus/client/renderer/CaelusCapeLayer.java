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
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.util.math.MathHelper;
import top.theillusivec4.caelus.core.CaelusHooks;

import javax.annotation.Nonnull;

public class CaelusCapeLayer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

    public CaelusCapeLayer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> playerRendererIn) {
        super(playerRendererIn);
    }

    public void render(@Nonnull AbstractClientPlayerEntity entityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        if (entityIn.hasPlayerInfo() && !entityIn.isInvisible() && entityIn.isWearing(PlayerModelPart.CAPE) && entityIn.getLocationCape() != null) {

            if (CaelusHooks.fireRenderCapeCheckEvent(entityIn)) {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.bindTexture(entityIn.getLocationCape());
                GlStateManager.pushMatrix();
                GlStateManager.translatef(0.0F, 0.0F, 0.125F);
                double d0 = MathHelper.lerp((double)partialTicks, entityIn.prevChasingPosX, entityIn.chasingPosX) - MathHelper.lerp((double)partialTicks, entityIn.prevPosX, entityIn.posX);
                double d1 = MathHelper.lerp((double)partialTicks, entityIn.prevChasingPosY, entityIn.chasingPosY) - MathHelper.lerp((double)partialTicks, entityIn.prevPosY, entityIn.posY);
                double d2 = MathHelper.lerp((double)partialTicks, entityIn.prevChasingPosZ, entityIn.chasingPosZ) - MathHelper.lerp((double)partialTicks, entityIn.prevPosZ, entityIn.posZ);
                float f = entityIn.prevRenderYawOffset + (entityIn.renderYawOffset - entityIn.prevRenderYawOffset);
                double d3 = (double) MathHelper.sin(f * ((float)Math.PI / 180F));
                double d4 = (double)(-MathHelper.cos(f * ((float)Math.PI / 180F)));
                float f1 = (float)d1 * 10.0F;
                f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
                float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
                f2 = MathHelper.clamp(f2, 0.0F, 150.0F);
                float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
                f3 = MathHelper.clamp(f3, -20.0F, 20.0F);

                if (f2 < 0.0F) {
                    f2 = 0.0F;
                }

                float f4 = MathHelper.lerp(partialTicks, entityIn.prevCameraYaw, entityIn.cameraYaw);
                f1 = f1 + MathHelper.sin((MathHelper.lerp(partialTicks, entityIn.prevDistanceWalkedModified, entityIn.distanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

                if (entityIn.isSneaking()) {
                    f1 += 25.0F;
                }
                GlStateManager.rotatef(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotatef(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                this.getEntityModel().renderCape(0.0625F);
                GlStateManager.popMatrix();
            }
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
