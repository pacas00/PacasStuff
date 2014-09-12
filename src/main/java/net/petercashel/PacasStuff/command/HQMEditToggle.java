package net.petercashel.PacasStuff.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class HQMEditToggle extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "HQMEditToggle";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "HQMEditToggle";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] p_71515_2_) {
		if (hardcorequesting.quests.Quest.isEditing) {
			hardcorequesting.quests.Quest.isEditing = false;
			sender.addChatMessage(new ChatComponentText("HQM Quest Editing is off"));
		} else {
			hardcorequesting.quests.Quest.isEditing = true;
			sender.addChatMessage(new ChatComponentText("HQM Quest Editing is on"));
		}

	}

}
