package net.petercashel.PacasStuff.anvil.AE_Enabled;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.petercashel.PacasStuff.mod_PacasStuff;
import appeng.api.config.Actionable;
import appeng.api.implementations.tiles.IColorableTile;
import appeng.api.networking.GridFlags;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.api.networking.events.MENetworkChannelsChanged;
import appeng.api.networking.events.MENetworkEventSubscribe;
import appeng.api.networking.events.MENetworkPowerStatusChange;
import appeng.api.networking.security.MachineSource;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import appeng.tile.TileEvent;
import appeng.tile.events.AETileEventHandler;
import appeng.tile.events.TileEventType;
import appeng.tile.grid.AENetworkTile;
import appeng.tile.networking.TileCableBus;
import appeng.util.item.AEItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AnvilAENetBaseTile extends AENetworkTile implements IColorableTile {
	
	
	public AnvilAENetBaseTile() {
		
	}
	
	protected static final String NBTKEY_COLOR = "TEColor";
	protected static final String NBTKEY_ATTACHMENT = "TEAttachSide";
	protected static final String NBTKEY_ISCOLORFORCED = "ColorForced";
	public static final AEColor[] AE_COLOR = AEColor.values();
	
	protected boolean isColorForced = false;

	protected int attachmentSide;

	protected IMEMonitor<IAEItemStack> monitor = null;

	protected boolean isActive;

	/**
	 * Configures the provider based on the specified
	 * attachment side.
	 * @param attachmentSide
	 */
	public void setupProvider( int attachmentSide )
	{
		// Ignored on client side
		if( FMLCommonHandler.instance().getEffectiveSide().isServer() )
		{
			// Set which side we are attached to
			this.attachmentSide = attachmentSide;

			// Set that we require a channel
			this.gridProxy.setFlags( GridFlags.REQUIRE_CHANNEL );

			// Set the idle power usage
			this.gridProxy.setIdlePowerUsage( this.getIdlePowerusage() );
		}
	}

	@Override
	public void onReady()
	{
		super.onReady();
		this.checkGridConnectionColor();
	}

	@Override
	protected ItemStack getItemFromTile( Object obj )
	{
		// Return the itemstack the visually represents this tile
		return new ItemStack( mod_PacasStuff.PacasAnvil, 1 );

	}
	
	protected double getIdlePowerusage()
	{
		return 5.0;
	}

	@Override
	public AECableType getCableConnectionType( ForgeDirection direction )
	{
		return AECableType.SMART;
	}

	public AEColor getGridColor()
	{
		return this.gridProxy.getGridColor();
	}

	public void setGridColor( AEColor gridColor )
	{
		// Set our color to match
		this.gridProxy.myColor = gridColor;

		// Are we server side?
		if( FMLCommonHandler.instance().getEffectiveSide().isServer() )
		{
			// Get the grid node
			IGridNode gridNode = this.gridProxy.getNode();

			// Do we have a grid node?
			if( gridNode != null )
			{
				// Update the grid node
				this.gridProxy.getNode().updateState();
			}

			// Mark the tile as needing updates and to be saved
			this.markForUpdate();
			this.saveChanges();
		}
	}

	public void checkGridConnectionColor()
	{
		// Are we server side and with a world?
		if( FMLCommonHandler.instance().getEffectiveSide().isClient() || ( this.worldObj == null ) )
		{
			// Nothing to do
			return;
		}

		// Get the colors of our neighbors
		AEColor[] sideColors = AnvilAENetBaseTile.getNeighborCableColors( this.worldObj, this.xCoord, this.yCoord, this.zCoord );

		// Get our current color
		AEColor currentColor = this.gridProxy.myColor;

		// Are we attached to a side?
		if( this.attachmentSide != ForgeDirection.UNKNOWN.ordinal() )
		{
			// Does our attached side still exist
			if( sideColors[this.attachmentSide] != null )
			{
				// Do we match it's color?
				if( sideColors[this.attachmentSide] == currentColor )
				{
					// Nothing to change
					return;
				}

				// Set our color to match
				this.setGridColor( sideColors[this.attachmentSide] );

				return;
			}
		}

		// Are any of the other sides the same color?
		for( int index = 0; index < 6; index++ )
		{
			if( sideColors[index] != null )
			{
				// Does the current side match our color?
				if( sideColors[index] == currentColor )
				{
					// Found another cable with the same color, lets attach to it
					this.attachmentSide = index;

					// Mark for a save
					this.saveChanges();

					return;
				}
				// Are we transparent?
				else if( currentColor == AEColor.Transparent )
				{
					// Attach to this cable
					this.attachmentSide = index;

					// Take on its color
					this.setGridColor( sideColors[index] );

					return;
				}
			}
		}

		// No cables match our color, set attachment to unknown
		this.attachmentSide = ForgeDirection.UNKNOWN.ordinal();

		// Set color to transparent
		this.setGridColor( AEColor.Transparent );

	}

	@MENetworkEventSubscribe
	public void channelEvent( MENetworkChannelsChanged event )
	{
		this.channelUpdated();
	}

	@MENetworkEventSubscribe
	public final void powerEvent( MENetworkPowerStatusChange event )
	{
		this.markForUpdate();
	}

	protected void channelUpdated()
	{
		// Check that our color is still valid
		this.checkGridConnectionColor();
	}
	
	protected boolean getItemMonitor()
	{
		// Get the grid node
		IGridNode node = this.gridProxy.getNode();

		// Ensure we have the node
		if( node == null )
		{
			return false;
		}

		// Get the grid that node is connected to
		IGrid grid = node.getGrid();

		// Is there a grid?
		if( grid == null )
		{
			return false;
		}

		// Access the storage grid
		IStorageGrid storageGrid = (IStorageGrid)grid.getCache( IStorageGrid.class );

		// Set our monitor
		this.monitor = storageGrid.getItemInventory();

		return( this.monitor != null );
	}
	
	private static AEColor[] getNeighborCableColors( IBlockAccess world, int x, int y, int z )
	{
		AEColor[] sideColors = new AEColor[6];

		for( ForgeDirection side : ForgeDirection.VALID_DIRECTIONS )
		{
			// Get the tile entity on the current side
			TileEntity tileEntity = world.getTileEntity( x + side.offsetX, y + side.offsetY, z + side.offsetZ );

			// Did we get an entity?
			if( tileEntity == null )
			{
				continue;
			}

			// Is that entity a cable?
			if( tileEntity instanceof TileCableBus )
			{
				// Set the color
				sideColors[side.ordinal()] = ( (TileCableBus)tileEntity ).getColor();
			}

		}

		return sideColors;
	}
	
	protected boolean extractItemFromNetwork( ItemStack item, World world, EntityPlayer player)
	{
		if( this.getItemMonitor() )
		{
			IAEItemStack request = AEItemStack.create(item);
			
			// Simulate the extraction
			IAEItemStack ItemStack = this.monitor.extractItems( request, Actionable.SIMULATE, new MachineSource( this ) );

			// Were we able to extract any?
			if( ItemStack == null )
			{
				return false;
			}
			
			// Take from the network
			IAEItemStack result = this.monitor.extractItems( request, Actionable.MODULATE, new MachineSource( this ) );

			if (result.getStackSize() != 0) {
				return true;	
			}
		}
		
		return false;

	}
	
	public boolean isActive()
	{
		// Are we server side?
		if( FMLCommonHandler.instance().getEffectiveSide().isServer() )
		{
			// Do we have a proxy and grid node?
			if( ( this.gridProxy != null ) && ( this.gridProxy.getNode() != null ) )
			{
				// Get the grid node activity
				this.isActive = this.gridProxy.getNode().isActive();
			}
		}

		return this.isActive;
	}
	
	@TileEvent(TileEventType.WORLD_NBT_READ)
	public void onLoadNBT( final NBTTagCompound data )
	{
		int attachmentSideFromNBT = ForgeDirection.UNKNOWN.ordinal();

		// Do we have the forced key?
		if( data.hasKey( AnvilAENetBaseTile.NBTKEY_ISCOLORFORCED ) )
		{
			this.isColorForced = data.getBoolean( AnvilAENetBaseTile.NBTKEY_ISCOLORFORCED );
		}

		// Do we have the color key?
		if( data.hasKey( AnvilAENetBaseTile.NBTKEY_COLOR ) )
		{
			// Read the color from the tag
			this.setProviderColor( AE_COLOR[data.getInteger( AnvilAENetBaseTile.NBTKEY_COLOR )] );
		}

		// Do we have the attachment key?
		if( data.hasKey( AnvilAENetBaseTile.NBTKEY_ATTACHMENT ) )
		{
			// Read the attachment side
			attachmentSideFromNBT = data.getInteger( AnvilAENetBaseTile.NBTKEY_ATTACHMENT );
		}

		// Setup the tile
		this.setupProvider( attachmentSideFromNBT );
	}
	
	/**
	 * Sets the color of the provider.
	 * This does not set the isColorForced flag to true.
	 * 
	 * @param gridColor
	 */
	protected void setProviderColor( final AEColor gridColor )
	{
		// Set our color to match
		this.gridProxy.myColor = gridColor;

		// Are we server side?
		if( FMLCommonHandler.instance().getEffectiveSide().isServer() )
		{
			// Get the grid node
			IGridNode gridNode = this.gridProxy.getNode();

			// Do we have a grid node?
			if( gridNode != null )
			{
				// Update the grid node
				this.gridProxy.getNode().updateState();
			}

			// Mark the tile as needing updates and to be saved
			this.markForUpdate();
			this.saveChanges();
		}
	}

	/**
	 * Get's the color of the provider
	 */
	@Override
	public AEColor getColor()
	{
		return this.gridProxy.myColor;
	}

	/**
	 * Forces a color change for the provider.
	 * Called when the provider's color is changed via the ColorApplicator item.
	 */
	@Override
	public boolean recolourBlock( final ForgeDirection side, final AEColor color, final EntityPlayer player )
	{
		// Mark our color as forced
		this.isColorForced = true;

		// Set our color
		this.setProviderColor( color );

		return true;
	}
	
		@TileEvent(TileEventType.NETWORK_READ)
		@SideOnly(Side.CLIENT)
		public boolean onReceiveNetworkData( final ByteBuf data )
		{
			// Read the color from the stream
			this.setProviderColor( AE_COLOR[data.readInt()] );
	
			// Read the activity
			this.isActive = data.readBoolean();
	
			return true;
		}
	
		@TileEvent(TileEventType.WORLD_NBT_WRITE)
		public void onSaveNBT( final NBTTagCompound data )
		{
			// Write our color to the tag
			data.setInteger( AnvilAENetBaseTile.NBTKEY_COLOR, this.getGridColor().ordinal() );
	
			// Write the attachment side to the tag
			data.setInteger( AnvilAENetBaseTile.NBTKEY_ATTACHMENT, this.attachmentSide );
	
			// Write the forced color flag
			data.setBoolean( AnvilAENetBaseTile.NBTKEY_ISCOLORFORCED, this.isColorForced );
		}
	
		@TileEvent(TileEventType.NETWORK_WRITE)
		public void onSendNetworkData( final ByteBuf data ) throws IOException
		{
			// Write the color data to the stream
			data.writeInt( this.getGridColor().ordinal() );
	
			// Write the activity to the stream
			data.writeBoolean( this.isActive() );
		}
	

}
