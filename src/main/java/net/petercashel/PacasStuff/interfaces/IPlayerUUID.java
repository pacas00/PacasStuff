package net.petercashel.PacasStuff.interfaces;

import java.util.UUID;

public interface IPlayerUUID {
	
	public UUID id = null;
	
	public void SetPlayerUUID(UUID id);
	
	public void ClearPlayerUUID();
	
}
