package implementation;

/**
 * Class created to hold elements required in multisets.
 * Each instance has a element (String) and the number of instances (int) for that element.
 * 
 * @author Charles Stewart, August 2020
 */

public class Element {
	String type;
	int instances;
	
	public Element(String type) {
		this.type = type;
		this.instances = 1;
	}
	
	public void setType(String newType) {
		type = newType;
	}
	
	public String getType() {
		return type;
	}
	
	public char getChar() {
		return type.charAt(0);
	}
	
	public void incrementInstance() {
		instances++;
	}
	
	public void decrementInstance() {
		instances--;
	}
	
	public int getInstances() {
		return instances;
	}
}
