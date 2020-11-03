package multiverseStructure;

import java.util.Random; 

public class Universe extends Realm{
	
	Galaxy[] galaxyArray;
	
	Multiverse parentMultiverse;
	
	public Universe(){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		galaxyArray = new Galaxy[0];
		parentMultiverse = new Multiverse(this);
	}
	
	public Universe(Multiverse multiverse){
		generateName();
		level = 9;
		numberOfSubrealms = 0;
		galaxyArray = new Galaxy[0];
		parentMultiverse = multiverse;
	}
	
	public Universe(Galaxy firstGalaxy){
		generateName();
		level = 9;
		numberOfSubrealms = 1;
		galaxyArray = new Galaxy[1];
		galaxyArray[0] = firstGalaxy;
		parentMultiverse = new Multiverse(this);
	}
	
	public void addGalaxy() {
		Galaxy[] newGalaxyArray = new Galaxy[numberOfSubrealms+1];
		for(int i=0; i < numberOfSubrealms; i++ ) {newGalaxyArray[i] = galaxyArray[i];}
		newGalaxyArray[numberOfSubrealms] = new Galaxy(this);
		numberOfSubrealms++;
		galaxyArray = newGalaxyArray;
	}
	
	private void generateName() {
		Random rand = new Random(); 
		int rand_int = rand.nextInt(999)+1;
		name = "u-" + rand_int;
	}
	
	public Galaxy[] getSubrealmArray() {
		return this.galaxyArray;
	}
	public void setSubrealmArray(Galaxy[] subrealmArray) {
		this.galaxyArray = subrealmArray;
	}
	public Multiverse getParentRealm() {
		return this.parentMultiverse;
	}
}
