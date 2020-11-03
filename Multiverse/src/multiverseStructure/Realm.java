package multiverseStructure;

public class Realm {
	
	String name = "Multiverse";
	
	int level;
	
	int numberOfSubrealms;
	
	boolean isFinite = false;
	
	//setters and getters
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLevel() {
		return this.level;
	}
	public void setlevel(int level) {
		this.level = level;
	}
	
	public int getNumberOfSubrealms() {
		return this.numberOfSubrealms;
	}
	public void setNumberOfSubrealms(int numberOfSubrealms) {
		this.numberOfSubrealms = numberOfSubrealms;
	}
	
	public boolean isFinite() {
		return this.isFinite;
	}
	public void setIsFinite(boolean isFinite) {
		this.isFinite = isFinite;
	}
}
