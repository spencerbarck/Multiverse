package multiverseStructure;

import java.util.Random; 

public class Planet extends Realm{
	
	SolarSystem parentSolarSystem;
	
	public Planet(){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		parentSolarSystem = new SolarSystem(this);
	}
	
	public Planet(SolarSystem solarSystem){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		parentSolarSystem = solarSystem;
	}
	
	private void generateName() {
		Random rand = new Random(); 
		int rand_char_upper = rand.nextInt(26)+65;
		int lower1 = rand.nextInt(26)+97;
		int lower2 = rand.nextInt(26)+97;
		int lower3 = rand.nextInt(26)+97;
		String lower = (char)lower1 + "" + (char)lower2 + "" + (char)lower3;
		name = "Planet " + (char)rand_char_upper +"" + lower;
	}
	
	public SolarSystem getParentRealm() {
		return this.parentSolarSystem;
	}
}
