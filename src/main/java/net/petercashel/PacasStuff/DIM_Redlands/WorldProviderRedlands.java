package net.petercashel.PacasStuff.DIM_Redlands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class WorldProviderRedlands extends WorldProvider {

	public void registerWorldChunkManager()
	{
		/** tells Minecraft to use our new WorldChunkManager **/
		this.worldChunkMgr = new WorldChunkMangerRedlands(worldObj.getSeed(), terrainType);
		this.hasNoSky = false;
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName()
	{
		return "Redlands";
	}
	
	@Override
	public String getSaveFolder() {
		return "DIM-Redlands";
	}

	/** Welcome message **/
	@Override
	public String getWelcomeMessage()
	{
		return "Entering the Redlands Dimension";
	}

	/** What chunk provider to use **/
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderRedlands(worldObj, worldObj.getSeed(), true);
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
		sky.addVector(0, 0.25, 0.5);
		
		if (worldObj.isRaining() && !worldObj.isThundering()) {
			sky.addVector(0, 0.115, 0.);
		}
		if (worldObj.isThundering()) {
			sky.addVector(0, 0.25, 0.5);
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
	
	@Override
	/** is this a surface world or an underworld */
	public boolean isSurfaceWorld()
	{
		return true;
	}

}
