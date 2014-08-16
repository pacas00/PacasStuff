package net.petercashel.PacasStuff.anvil;

import com.google.gson.annotations.SerializedName;

public class anvilConfigData {
	
	@SerializedName("modId")
	public String modId;
	
	@SerializedName("name")
	public String name;
	
	@SerializedName("metadata")
	public int metadata;
	
	@SerializedName("RepairMat_modId")
	public String RepairMat_modId;
	
	@SerializedName("RepairMat_name")
	public String RepairMat_name;
	
	@SerializedName("RepairMat_metadata")
	public int RepairMat_metadata;
	
	@SerializedName("RepairMat_Divider")
	public int RepairMat_Divider;
}
