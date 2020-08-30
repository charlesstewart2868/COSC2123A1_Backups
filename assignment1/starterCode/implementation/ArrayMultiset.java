package implementation;

import java.util.List;

/**
 * Array implementation of a multiset. See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */

public class ArrayMultiset extends RmitMultiset {
	
	/*
	Items to complete;
	- Initialiser  		||COMPLETE||
	- Add				||COMPLETE||
	- Search			||COMPLETE||
	- SearchByInstance
	- Contains			||COMPLETE||
	- RemoveOne			||COMPLETE||
	- Print				||COMPLETE||
	- PrintRange		||COMPLETE||
	- Union				||COMPLETE||
	- Intersect			||COMPLETE||
	- Difference		||COMPLETE||
	*/

	protected Element[] arrayMultiset;
	protected int arraySize;
	protected static final int startingArraySize = 2;

	/**
	 * Constructor method for ArrayMultiset class
	 */
	public ArrayMultiset() {
		arrayMultiset = new Element[startingArraySize]; // array created with array size double
												// what is initially required
		arraySize = 0;
	} // end of ArrayMultiset()

	@Override
	public void add(String elem) {
		// Implement me!
		if (contains(elem) == true) {
			for (int i = 0; i < arraySize; ++i) {
				if (arrayMultiset[i].getType().equals(elem)) {
					arrayMultiset[i].incrementInstance();
					break;
				}
			}
		} else {
			if (arraySize == arrayMultiset.length) {
				int newSize = arraySize * 2;
				Element newArray[] = new Element[newSize];
	
				for (int i = 0; i < arrayMultiset.length; ++i) {
					newArray[i] = arrayMultiset[i];
				}
				newArray[arraySize] = new Element(elem);
				arrayMultiset = newArray;
			} else {
				arrayMultiset[arraySize] = new Element(elem);
			}
			arraySize++;
		}
	} // end of add()

	@Override
	public int search(String elem) {
		// Implement me!
		if (arrayMultiset != null) {
			for (int i = 0; i < arraySize; i++) {
				if (arrayMultiset[i].getType().equals(elem)) {
					return arrayMultiset[i].getInstances();
				}
			}
		}

		// Placeholder, please update.
		// Following return is only required if no elem is present
		// Contains could be used, however only one for loop required
		return 0;
	} // end of search()

	@Override
	public List<String> searchByInstance(int instanceCount) {
		// Implement me!

		// Placeholder, please update.
		// Java.lang.utils not required for this method
		return null;
	} // end of searchByInstance

	@Override
	public boolean contains(String elem) { // ||COMPLETE||
		// Implement me!

		for (int i = 0; i < arraySize; i++) {
			if (arrayMultiset[i].getType().equals(elem)) {
				return true;
			}
		}
		// Placeholder, please update.
		return false;
	} // end of contains()

	@Override
	public void removeOne(String elem) { // Add functionality for when last instance is removed
		
		if (contains(elem)) {
			for (int i = 0; i < arraySize; i++) {
				if (arrayMultiset[i].getType().equals(elem)) {
					if (arrayMultiset[i].getInstances() > 1) {
						arrayMultiset[i].decrementInstance();
						return;
					} else {
						if (arraySize > 1 && i != arraySize - 1) {
							for (;i < arraySize - 1; ++i) {
								arrayMultiset[i] = arrayMultiset[i+1];
								arrayMultiset[i+1] = null;
							}
						} else {
							arrayMultiset[i] = null;
						}
						arraySize--;
						return;
					}
				}
			}
		} else {
			System.out.println("This array does not contaim the element; " + elem);
		}

		
		return;
		// Implement me!
	} // end of removeOne()

	@Override
	public String print() { // ||COMPLETE||
		// selection sort will be used
		int highestElement = 0;
		Element tempArray[] = arrayMultiset;
		Element swapper;
		
		if (arraySize > 1) {
			for (int i = arraySize; i > 1; --i) {
				for (int k = 1; k < i; ++k) {
					if (tempArray[highestElement].getInstances() < tempArray[k].getInstances()) {
						highestElement = k;
					}
				}
				
				swapper = tempArray[i-1];
				tempArray[i-1] = tempArray[highestElement];
				tempArray[highestElement] = swapper;
				highestElement = 0;
			}
			
			String returnString = "";
			
			for (int i = 0; i < arraySize; ++i) {
				if (tempArray[i] != null) {
					returnString = returnString + tempArray[i].getType() + ": " + tempArray[i].getInstances() + "\n";
				}
			}
			
			return returnString;
		} else if (arraySize == 1) {
			return arrayMultiset[0].getType() + ": " + arrayMultiset[0].getInstances() + "\n";
		} else {
			return "Print failed";
		}

		// Placeholder, please update.
		// return returnString;
	} // end of OrderedPrint

	@Override
	public String printRange(String lower, String upper) {
		if (arraySize > 1) {
			
			String returnString = "";
			
			for (int i = 0; i < arraySize; ++i) {
				if (arrayMultiset[i].getType().compareToIgnoreCase(lower) > 0 && arrayMultiset[i].getType().compareToIgnoreCase(upper) < 0) {
					returnString = returnString + arrayMultiset[i].getType() + ": " + arrayMultiset[i].getInstances() + "\n";	
				}
			}
			
			return returnString;
		} else if (arraySize == 1 && (arrayMultiset[0].getType().compareToIgnoreCase(lower) > 0 && arrayMultiset[0].getType().compareToIgnoreCase(upper) < 0)) {
			return arrayMultiset[0].getType() + ": " + arrayMultiset[0].getInstances() + "\n";
		} else {
			return "Print failed";
		}
	} // end of printRange()

	@Override
	public RmitMultiset union(RmitMultiset other) {
		RmitMultiset unionMultiset = other;
		
		for (int i = 0; i < arraySize; ++i) {
			if (other.contains(arrayMultiset[i].getType()) == false) {
				unionMultiset.add(arrayMultiset[i].getType());
			}
		}
		
		return unionMultiset;
	} // end of union()

	@Override
	public RmitMultiset intersect(RmitMultiset other) {
		RmitMultiset intersectionMultiset = new ArrayMultiset();
		
		for (int i = 0; i < arraySize; ++i) {
			if (other.contains(arrayMultiset[i].getType()) == true) {
				intersectionMultiset.add(arrayMultiset[i].getType());
			}
		}
		
		return intersectionMultiset;
	} // end of intersect()

	@Override
	public RmitMultiset difference(RmitMultiset other) {
		RmitMultiset differenceMultiset = new ArrayMultiset();
		
		for (int i = 0; i < arraySize; ++i) {
			if (other.contains(arrayMultiset[i].getType()) == false) {
				differenceMultiset.add(arrayMultiset[i].getType());
			}
		}

		return differenceMultiset;
	} // end of difference() 

} // end of class ArrayMultiset
