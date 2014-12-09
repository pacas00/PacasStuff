package net.petercashel.PacasStuff.liquidDictDebug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.google.common.collect.Maps;

import io.netty.buffer.ByteBufInputStream;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class DebugClientPacketHandler {

	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
		
		int type = -1;
		try {
			type = bbis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (type == 0) {
			
			BufferedWriter writer = null;
			File dump = new File("LiquidDictDump-Client.txt");
			dump.delete();
			dump = new File("LiquidDictDump-Client.txt");
			try {
				writer = new BufferedWriter(new FileWriter(dump));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Dump Liquid Dict
			System.out.println("Client Fluid Dict");
			HashMap<String, Fluid> map = Maps.newHashMap();
			map.putAll(FluidRegistry.getRegisteredFluids());
			Iterator i = map.keySet().iterator();
			while (i.hasNext()) {
				String str = (String) i.next();
				try {
					writer.write(str);
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i.remove();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
