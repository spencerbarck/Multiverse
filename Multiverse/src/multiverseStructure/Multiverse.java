package multiverseStructure;

public class Multiverse extends Realm{

	Universe[] universeArray;
	
	public Multiverse(){
		level = 10;
		numberOfSubrealms = 0;
		universeArray = new Universe[0];
	}
	
	public Multiverse(Universe firstUniverse){
		level = 10;
		numberOfSubrealms = 1;
		universeArray = new Universe[1];
		universeArray[0] = firstUniverse;
	}
	
	public void addUniverse() {
		Universe[] newUniverseArray = new Universe[numberOfSubrealms+1];
		for(int i=0; i < numberOfSubrealms; i++ ) {newUniverseArray[i] = universeArray[i];}
		newUniverseArray[numberOfSubrealms] = new Universe(this);
		numberOfSubrealms++;
		universeArray = newUniverseArray;
	}
	
	public Universe[] getSubrealmArray() {
		return this.universeArray;
	}
	
	public void setSubrealmArray(Universe[] subrealmArray) {
		this.universeArray = subrealmArray;
	}
}
