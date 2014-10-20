package net.petercashel.PacasStuff.DIM_WOP;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class WorldProviderWOP extends WorldProvider {

	public void registerWorldChunkManager()
	{
		/** tells Minecraft to use our new WorldChunkManager **/
		this.worldChunkMgr = new WorldChunkMangerWOP(worldObj.getSeed(), terrainType);
		this.hasNoSky = false;
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName()
	{
		return "WOP";
	}
	
	@Override
	public String getSaveFolder() {
		return "DIM-WOP";
	}

	/** Welcome message **/
	@Override
	public String getWelcomeMessage()
	{
		return "Entering the WOP Dimension";
	}

	/** What chunk provider to use **/
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderWOP(worldObj, worldObj.getSeed(), true);
	}

	/** Can player re-spawn here **/
	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	/** Determines the dimension the player will be respawned in **/
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return 0;
	}

	/** Dimension Movement speed **/
	@Override
	public double getMovementFactor()
	{
		return 2.0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
		Vec3 sky = worldObj.getSkyColorBody(cameraEntity, partialTicks);
		//Vec3 sky = Vec3.createVectorHelper(0.46, 1.1, 3.0);
		
		if (!worldObj.isRaining() && !worldObj.isThundering()) {
			sky.addVector(0, 0.25, 0.5);
		}
		if (worldObj.isRaining() && !worldObj.isThundering()) {
			sky.addVector(0, 0.115, 0.25);
		}
		if (worldObj.isThundering()) {
		}
		return sky;
	}
	
	@Override
	public boolean isSkyColored()
	{
		 return true;
	}
	
	public boolean shouldMapSpin(String entity, double x, double y, double z)
    {
        return false;
    }

}
