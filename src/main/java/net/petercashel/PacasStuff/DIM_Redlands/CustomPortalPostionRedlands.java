package net.petercashel.PacasStuff.DIM_Redlands;

import net.minecraft.util.ChunkCoordinates;

public class CustomPortalPostionRedlands extends ChunkCoordinates {
	
	public long field_85087_d;
	final RedlandsTeleporter field_85088_e;
	public CustomPortalPostionRedlands(RedlandsTeleporter RedlandsTeleporter, int par2, int par3, int par4, long par5)
	{
		super(par2, par3, par4);
		this.field_85088_e = RedlandsTeleporter;
		this.field_85087_d = par5;
	}

}
