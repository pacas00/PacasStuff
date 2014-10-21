package net.petercashel.PacasStuff.DIM_Common.Render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.petercashel.PacasStuff.mod_PacasStuff;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class Redlands_Grass_Renderer implements ISimpleBlockRenderingHandler {
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	    Tessellator tessellator = Tessellator.instance;
	    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, -1.0F, 0.0F);
	    renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 0.0F, -1.0F);
	    renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 0.0F, 1.0F);
	    renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
	    renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(1.0F, 0.0F, 0.0F);
	    renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
	    tessellator.draw();
	    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		return this.renderStandardBlock(block, x, y, z, world, modelId, renderer);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		return mod_PacasStuff.Redlands_GrassRendererID;
	}
	
	/**
     * Renders a standard cube block at the given coordinates
     */
    public boolean renderStandardBlock(Block block, int x, int y, int z, IBlockAccess world, int modelId, RenderBlocks renderer)
    {
        int l = block.colorMultiplier(world, x, y, z);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        return this.renderStandardBlockWithColorMultiplier(block, x, y, z, f, f1, f2, world, l, renderer);
    }
    
    /**
     * Renders a standard cube block at the given coordinates, with a given color ratio.  Args: block, x, y, z, r, g, b
     */
    public boolean renderStandardBlockWithColorMultiplier(Block block, int x, int y, int z, float r, float g, float b, IBlockAccess world, int modelId, RenderBlocks renderer)
    {
    	//renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * r;
        float f8 = f4 * g;
        float f9 = f4 * b;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;

        if (block != mod_PacasStuff.Redlands_Grass)
        {
            f10 = f3 * r;
            f11 = f5 * r;
            f12 = f6 * r;
            f13 = f3 * g;
            f14 = f5 * g;
            f15 = f6 * g;
            f16 = f3 * b;
            f17 = f5 * b;
            f18 = f6 * b;
        }

        int l = block.getMixedBrightnessForBlock(world, x, y, z);

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y - 1, z, 0))
        {
            tessellator.setBrightness(renderer.renderMinY > 0.0D ? l : block.getMixedBrightnessForBlock(world, x, y - 1, z));
            tessellator.setColorOpaque_F(f10, f13, f16);
            renderer.renderFaceYNeg(block, (double)x, (double)y, (double)z, renderer.getBlockIcon(block, world, x, y, z, 0));
            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y + 1, z, 1))
        {
            tessellator.setBrightness(renderer.renderMaxY < 1.0D ? l : block.getMixedBrightnessForBlock(world, x, y + 1, z));
            tessellator.setColorOpaque_F(f7, f8, f9);
            renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, renderer.getBlockIcon(block, world, x, y, z, 1));
            flag = true;
        }

        IIcon iicon;

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y, z - 1, 2))
        {
            tessellator.setBrightness(renderer.renderMinZ > 0.0D ? l : block.getMixedBrightnessForBlock(world, x, y, z - 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 2);
            renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, iicon);

            if (renderer.fancyGrass && iicon.getIconName().equals("pacas_stuff:grass_side") && !renderer.hasOverrideBlockTexture())
            {
                tessellator.setColorOpaque_F(f11 * r, f14 * g, f17 * b);
                renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y, z + 1, 3))
        {
            tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? l : block.getMixedBrightnessForBlock(world, x, y, z + 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 3);
            renderer.renderFaceZPos(block, (double)x, (double)y, (double)z, iicon);

            if (renderer.fancyGrass && iicon.getIconName().equals("pacas_stuff:grass_side") && !renderer.hasOverrideBlockTexture())
            {
                tessellator.setColorOpaque_F(f11 * r, f14 * g, f17 * b);
                renderer.renderFaceZPos(block, (double)x, (double)y, (double)z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x - 1, y, z, 4))
        {
            tessellator.setBrightness(renderer.renderMinX > 0.0D ? l : block.getMixedBrightnessForBlock(world, x - 1, y, z));
            tessellator.setColorOpaque_F(f12, f15, f18);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 4);
            renderer.renderFaceXNeg(block, (double)x, (double)y, (double)z, iicon);

            if (renderer.fancyGrass && iicon.getIconName().equals("pacas_stuff:grass_side") && !renderer.hasOverrideBlockTexture())
            {
                tessellator.setColorOpaque_F(f12 * r, f15 * g, f18 * b);
                renderer.renderFaceXNeg(block, (double)x, (double)y, (double)z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x + 1, y, z, 5))
        {
            tessellator.setBrightness(renderer.renderMaxX < 1.0D ? l : block.getMixedBrightnessForBlock(world, x + 1, y, z));
            tessellator.setColorOpaque_F(f12, f15, f18);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 5);
            renderer.renderFaceXPos(block, (double)x, (double)y, (double)z, iicon);

            if (renderer.fancyGrass && iicon.getIconName().equals("pacas_stuff:grass_side") && !renderer.hasOverrideBlockTexture())
            {
                tessellator.setColorOpaque_F(f12 * r, f15 * g, f18 * b);
                renderer.renderFaceXPos(block, (double)x, (double)y, (double)z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        return flag;
    }
}
