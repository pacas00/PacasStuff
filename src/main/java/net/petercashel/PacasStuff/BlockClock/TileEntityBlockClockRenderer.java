package net.petercashel.PacasStuff.BlockClock;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL21;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityBlockClockRenderer extends TileEntitySpecialRenderer {

	public ModelBlockClock model = new ModelBlockClock();
	public static final ResourceLocation texture = new ResourceLocation("pacas_stuff:textures/entity/clock/blockclock.png");
	ItemStack stack = new ItemStack(Items.clock, 1, 0);
	EntityItem entItem = null;
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		this.renderTileEntityPacasAnvil((TileEntityBlockClock) var1, var2, var4, var6, var8);
	}

	private void renderTileEntityPacasAnvil(TileEntityBlockClock t, double x, double y, double z, float partialTime)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		this.bindTexture(texture);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		//GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
		
		String TextTime = "";
		if (t.hasWorldObj()) {
			World w = t.getWorldObj();
			long time = w.getWorldInfo().getWorldTime();
			double tmp = Math.floor((time/24000));
			time = (long) (time - (tmp*24000));
			double hour = Math.floor(time/1000);
			double min = time - (hour*1000 );
			String minStr = "";
			minStr = String.valueOf((int)((min/1000)*60));
			//this.func_147498_b().drawString("Test", 1, 1, 1);
			if (minStr.length() == 1) {
				TextTime = HourSwitch((int)hour) + ":0" + minStr;
			} else {
				TextTime = HourSwitch((int)hour) + ":" + minStr;
			}
		}
		if (t.hasWorldObj()) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.72F, (float)y + 0.615F, (float)z - 0.002F);
		
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glScalef(0.032F, 0.032F, 0.032F);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_LIGHTING);

        if (TextTime.length() == 4) {
        	this.func_147498_b().drawString(TextTime, 0, 0, 0xCCCCCC);
        } else {
        	this.func_147498_b().drawString(TextTime, -6, 0, 0xCCCCCC);
        }
        
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
		GL11.glTranslatef((float)x - 0.002F, (float)y + 0.615F, (float)z + 0.276F);
		
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);

        GL11.glRotatef(270, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(0.032F, 0.032F, 0.032F);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_LIGHTING);

        if (TextTime.length() == 4) {
        	this.func_147498_b().drawString(TextTime, 0, 0, 0xCCCCCC);
        } else {
        	this.func_147498_b().drawString(TextTime, -6, 0, 0xCCCCCC);
        }
        
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.276F, (float)y + 0.615F, (float)z + 1.02F);
		
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(0.032F, 0.032F, 0.032F);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_LIGHTING);

        if (TextTime.length() == 4) {
        	this.func_147498_b().drawString(TextTime, 0, 0, 0xCCCCCC);
        } else {
        	this.func_147498_b().drawString(TextTime, -6, 0, 0xCCCCCC);
        }
        
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 1.002F, (float)y + 0.615F, (float)z + 0.72F);
		
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(0.032F, 0.032F, 0.032F);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_LIGHTING);

        if (TextTime.length() == 4) {
        	this.func_147498_b().drawString(TextTime, 0, 0, 0xCCCCCC);
        } else {
        	this.func_147498_b().drawString(TextTime, -6, 0, 0xCCCCCC);
        }
        
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
		}
       
	}
	
	private static int HourSwitch(int hours) {
		switch(hours) {
		case 6: return 12;
		case 7: return 1;
		case 8: return 2;
		case 9: return 3;
		case 10: return 4;
		case 11: return 5;
		case 12: return 6;
		case 13: return 7;
		case 14: return 8;
		case 15: return 9;
		case 16: return 10;
		case 17: return 11;
		case 18: return 12;
		case 19: return 1;
		case 20: return 2;
		case 21: return 3;
		case 22: return 4;
		case 23: return 5;
		case 0: return 6;
		case 1: return 7;
		case 2: return 8;
		case 3: return 9;
		case 4: return 10;
		case 5: return 11;
		}
		return 0;
	}
}
