package com.hbm.render.item.weapon.sedna;

import org.lwjgl.opengl.GL11;
import com.hbm.render.util.RenderItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.hbm.lib.RefStrings;
import com.hbm.render.model.ModelZOMG;
import com.hbm.render.anim.HbmAnimations;

/**
 * Custom item renderer for gun_pppop.
 * Integrates with ItemRenderWeaponBase for alignment and animation.
 */
public class ItemRenderPPPOP extends ItemRenderWeaponBase {

	protected ModelZOMG swordModel = new ModelZOMG();

	public ItemRenderPPPOP() { }

	@Override
	protected float getTurnMagnitude(ItemStack stack) {
		return -0.5F;
	}

	@Override
	public float getViewFOV(ItemStack stack, float fov) {
		return fov;
	}

	@Override
	public void setupFirstPerson(ItemStack stack) {
		GL11.glTranslated(0, 0, 0.875);
		float offset = 0.8F;
		standardAimingTransform(stack,
				-1.75F * offset, -0.5F * offset, 1.75F * offset,
				0, -10 / 8D, 1.25);
	}

	@Override
	public void renderFirstPerson(ItemStack stack) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RefStrings.MODID + ":textures/models/ModelZOMG.png"));
		double scale = 0.75D;
		GL11.glScaled(scale, scale, scale);
		
		double[] equip = HbmAnimations.getRelevantTransformation("EQUIP");
		double[] recoil = HbmAnimations.getRelevantTransformation("RECOIL");
		double[] jolt = HbmAnimations.getRelevantTransformation("JOLT");
		
		GL11.glRotated(180, 1, 0, 0);
		GL11.glRotated(-90, 0, 1, 0);
		GL11.glTranslatef(0.0F, -0.4F, 0.6F);
		
		GL11.glTranslated(0, 0, recoil[2] * 0.15D);
		GL11.glTranslated(jolt[0], jolt[1], jolt[2]);
		GL11.glTranslated(0, -equip[0] * 0.02D, -equip[0] * 0.05D);

		GL11.glShadeModel(GL11.GL_SMOOTH);
		swordModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glShadeModel(GL11.GL_FLAT);
	}

	@Override
	public void setupThirdPerson(ItemStack stack) { }

	@Override
	public void setupEntity(ItemStack stack) { }

	@Override
	public void setupInv(ItemStack stack) { }

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.INVENTORY) {
			GL11.glPushMatrix();
			float angle = (float) ((System.currentTimeMillis() % 2000) * 360.0 / 2000.0);
			GL11.glTranslatef(8.0F, 8.0F, 0.0F);
			GL11.glRotatef(angle, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-8.0F, -8.0F, 0.0F);
			RenderItemStack.renderItemStackNoEffect(0, 0, 0, item);
			GL11.glPopMatrix();
			return;
		}
		super.renderItem(type, item, data);
	}

	@Override
	public void renderOther(ItemStack stack, ItemRenderType type, Object... data) {
		GL11.glEnable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RefStrings.MODID + ":textures/models/ModelZOMG.png"));

		if (type == ItemRenderType.EQUIPPED) {
			GL11.glPushMatrix();
			GL11.glRotatef(-200.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(75.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.0F, -0.2F, -0.5F);
			GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.5F, -0.2F, 0.0F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glTranslatef(-0.4F, -0.1F, -0.1F);
			
			GL11.glShadeModel(GL11.GL_SMOOTH);
			swordModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glPopMatrix();
		} else if (type == ItemRenderType.ENTITY) {
			GL11.glPushMatrix();
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glTranslatef(0F, -0.5F, 0F);
			
			GL11.glShadeModel(GL11.GL_SMOOTH);
			swordModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void setupModTable(ItemStack stack) { }
}
