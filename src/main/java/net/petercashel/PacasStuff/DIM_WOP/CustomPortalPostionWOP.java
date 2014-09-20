package net.petercashel.PacasStuff.DIM_WOP;

import net.minecraft.util.ChunkCoordinates;

public class CustomPortalPostionWOP extends ChunkCoordinates {
	
	public long field_85087_d;
	final WOPTeleporter field_85088_e;
	public CustomPortalPostionWOP(WOPTeleporter wopTeleporter, int par2, int par3, int par4, long par5)
	{
		super(par2, par3, par4);
		this.field_85088_e = wopTeleporter;
		this.field_85087_d = par5;
	}

}
