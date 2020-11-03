package multiverseStructure;

import java.util.Random; 

public class SolarSystem extends Realm{
	
	Planet[] planetArray;
	
	Galaxy parentGalaxy;
	
	public SolarSystem(){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		planetArray = new Planet[0];
		parentGalaxy = new Galaxy(this);
	}
	
	public SolarSystem(Galaxy galaxy){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		planetArray = new Planet[0];
		parentGalaxy = galaxy;
	}
	
	public SolarSystem(Planet firstPlanet){
		generateName();
		level = 9;
		numberOfSubrealms = 1;
		planetArray = new Planet[1];
		planetArray[0] = firstPlanet;
		parentGalaxy = new Galaxy(this);
	}
	
	public void addPlanet() {
		Planet[] newPlanetArray = new Planet[numberOfSubrealms+1];
		for(int i=0; i < numberOfSubrealms; i++ ) {newPlanetArray[i] = planetArray[i];}
		newPlanetArray[numberOfSubrealms] = new Planet(this);
		numberOfSubrealms++;
		planetArray = newPlanetArray;
	}
	
	private void generateName() {
		Random rand = new Random(); 
		int rand_char = rand.nextInt(26)+65;
		int rand_int = rand.nextInt(1000);
		name = (char)rand_char +""+ rand_int + " System";
	}
	
	public Planet[] getSubrealmArray() {
		return this.planetArray;
	}
	public void setSubrealmArray(Planet[] subrealmArray) {
		this.planetArray = subrealmArray;
	}
	public Galaxy getParentRealm() {
		return this.parentGalaxy;
	}
}