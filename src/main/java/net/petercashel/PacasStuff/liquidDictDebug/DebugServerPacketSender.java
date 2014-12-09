package net.petercashel.PacasStuff.liquidDictDebug;

import static io.netty.buffer.Unpooled.buffer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.petercashel.PacasStuff.mod_PacasStuff;

import com.google.common.collect.Maps;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class DebugServerPacketSender {
	
	public static void DumpLiquidDict(ICommandSender ISender) {
		ByteBuf bb = buffer(128);
		bb.clear();
		bb.writeInt(0);
		FMLProxyPacket pkt = new FMLProxyPacket(bb, "PacasDebugChannel");
		System.out.println("Player is " + ISender.getCommandSenderName());
		mod_PacasStuff.PacasDebugChannel.sendTo(pkt, (EntityPlayerMP) MinecraftServer.getServer().getEntityWorld().getPlayerEntityByName(ISender.getCommandSenderName()));
		
		BufferedWriter writer = null;
		File dump = new File("LiquidDictDump-Server.txt");
		dump.delete();
		dump = new File("LiquidDictDump-Server.txt");
		try {
			writer = new BufferedWriter(new FileWriter(dump));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Dump Liquid Dict
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
