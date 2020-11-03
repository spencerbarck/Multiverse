package multiverseStructure;

import java.util.Random; 

public class Galaxy extends Realm{
	
	SolarSystem[] solarSystemArray;
	
	Universe parentUniverse;
	
	public Galaxy(){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		solarSystemArray = new SolarSystem[0];
		parentUniverse = new Universe(this);
	}
	
	public Galaxy(Universe universe){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		solarSystemArray = new SolarSystem[0];
		parentUniverse = universe;
	}
	
	public Galaxy(SolarSystem firstSolarSystem){
		generateName();
		level = 9;
		numberOfSubrealms = 1;
		solarSystemArray = new SolarSystem[1];
		solarSystemArray[0] = firstSolarSystem;
		parentUniverse = new Universe(this);
	}
	
	public void addSolarSystem() {
		SolarSystem[] newSolarSystemArray = new SolarSystem[numberOfSubrealms+1];
		for(int i=0; i < numberOfSubrealms; i++ ) {newSolarSystemArray[i] = solarSystemArray[i];}
		newSolarSystemArray[numberOfSubrealms] = new SolarSystem(this);
		numberOfSubrealms++;
		solarSystemArray = newSolarSystemArray;
	}
	
	private void generateName() {
		Random rand = new Random(); 
		int rand_int = rand.nextInt(999)+1;
		name = "g-" + rand_int;
	}
	
	public SolarSystem[] getSubrealmArray() {
		return this.solarSystemArray;
	}
	public void setSubrealmArray(SolarSystem[] subrealmArray) {
		this.solarSystemArray = subrealmArray;
	}
	public Universe getParentRealm() {
		return this.parentUniverse;
	}
}