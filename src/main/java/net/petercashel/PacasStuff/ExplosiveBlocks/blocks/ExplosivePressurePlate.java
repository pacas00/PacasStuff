package net.petercashel.PacasStuff.ExplosiveBlocks.blocks;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.petercashel.PacasStuff.mod_PacasStuff;
import net.petercashel.PacasStuff.interfaces.IPlayerUUID;

public class ExplosivePressurePlate extends BlockPressurePlate implements IPlayerUUID {

	public ExplosivePressurePlate(String p_i45418_1_, Material p_i45418_2_,
			Sensitivity p_i45418_3_) {
		super(p_i45418_1_, p_i45418_2_, p_i45418_3_);
		this.setCreativeTab(mod_PacasStuff.tabPacasExplosions);
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
	{

		if (!p_149670_1_.isRemote)
		{
			if (this.cooldown == 0) {
				int l = this.func_150060_c(p_149670_1_.getBlockMetadata(p_149670_2_, p_149670_3_, p_149670_4_));

				if (l == 0)
				{
					this.func_150062_a(p_149670_1_, p_149670_2_, p_149670_3_, p_149670_4_, l);
				}

				if (p_149670_5_ instanceof EntityPlayer) {
					if (id != null) {
						int i = id.compareTo(((EntityPlayer)p_149670_5_).getGameProfile().getId());
						if (i == 0) {
							p_149670_1_.playSoundAtEntity(p_149670_5_, "game.tnt.primed", 1.0F, 1.0F);
							EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(p_149670_1_, (double)((float)p_149670_2_ + 0.5F), (double)((float)p_149670_3_ + 0.5F), (double)((float)p_149670_4_ + 0.5F), (EntityLivingBase)p_149670_5_);
				            entitytntprimed.fuse = 1;
				            p_149670_1_.spawnEntityInWorld(entitytntprimed);
				            
							p_149670_1_.setBlock(p_149670_2_, p_149670_3_ - 2, p_149670_4_, Blocks.redstone_block);
							p_149670_1_.setBlock(p_149670_2_, p_149670_3_ - 3, p_149670_4_, Blocks.redstone_block);
						}
					} else {
						Random rand = new Random();
						int i = rand.nextInt(2);
						if (i == 1) {
							p_149670_1_.playSoundAtEntity(p_149670_5_, "game.tnt.primed", 1.0F, 1.0F);
							
							EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(p_149670_1_, (double)((float)p_149670_2_ + 0.5F), (double)((float)p_149670_3_ + 0.5F), (double)((float)p_149670_4_ + 0.5F), (EntityLivingBase)p_149670_5_);
				            entitytntprimed.fuse = 1;
				            p_149670_1_.spawnEntityInWorld(entitytntprimed);
				            
							p_149670_1_.setBlock(p_149670_2_, p_149670_3_ - 2, p_149670_4_, Blocks.redstone_block);
							p_149670_1_.setBlock(p_149670_2_, p_149670_3_ - 3, p_149670_4_, Blocks.redstone_block);
						}
					}
				}
				this.cooldown = 18;
			}
			System.out.println(this.cooldown);
			if (this.cooldown > 0) this.cooldown--;
			if (this.cooldown < 0) this.cooldown = 0;
		}
	}

	private UUID id = null;
	private int cooldown = 0;

	@Override
	public void SetPlayerUUID(UUID id) {
		this.id = id;

	}

	@Override
	public void ClearPlayerUUID() {
		this.id = null;

	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World p_149738_1_)
	{
		return 20;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		if (!p_149674_1_.isRemote)
        {
            int l = this.func_150060_c(p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_));

            if (l > 0)
            {
                this.func_150062_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, l);
            }
        }
	}

	/**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        
    }

    /**
     * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
     */
    protected void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
    {
        
    }


}
