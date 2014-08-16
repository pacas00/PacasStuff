package net.petercashel.PacasStuff.anvil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityPacasAnvilRenderer_Basic extends TileEntitySpecialRenderer {

	public ModelPacasAnvil_Basic model = new ModelPacasAnvil_Basic();
	public static final ResourceLocation texture = new ResourceLocation("pacas_stuff:textures/entity/anvil/PacasAnvilModel_Basic.png");
    

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		this.renderTileEntityPacasAnvil((TileEntityPacasAnvil_Basic) var1, var2, var4, var6, var8);
	}

	private void renderTileEntityPacasAnvil(TileEntityPacasAnvil_Basic t, double x, double y, double z, float partialTime)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		this.bindTexture(texture);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}
}
