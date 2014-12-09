package net.petercashel.PacasStuff.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import net.petercashel.PacasStuff.liquidDictDebug.DebugServerPacketSender;

public class DebugCommandSender extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "pacasdebug";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "pacasdebug";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length < 1) throw new WrongUsageException("/pacasdebug {int}");
		
		String str = args[0];
		int i = Integer.valueOf(str);
		System.out.println(i);
		
		if (i == 0) {
			DebugServerPacketSender.DumpLiquidDict(sender);
		}
		
	}

}
