package implementation;

import java.util.List;

/**
 * Dual linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class DualLinkedListMultiset extends RmitMultiset
{

	/*
	Items to complete;
	- Initialiser  		||COMPLETE||
	- Add				||COMPLETE||
	- Search  			||COMPLETE||
	- SearchByInstance
	- Contains  		||COMPLETE||
	- RemoveOne			||COMPLETE||
	- Print				||COMPLETE||
	- PrintRange		||COMPLETE||
	- Union
	- Intersect
	- Difference
	*/
	
	protected LinkedNode ascendingHead;
	protected LinkedNode instanceHead;
	protected int mLength;
	
	public DualLinkedListMultiset() {
		ascendingHead = null;
		instanceHead = null;
		mLength = 0;
	}
	
    @Override
	public void add(String item) {
    	if (contains(item) == false) {
    		insert(item);
    	} else {
    		for (int i = 0; i < mLength; ++i) {
    			if (ascendingGet(i).getType().equals(item)) {
    				ascendingGet(i).incrementInstance();
    			}
    		}
    		
    		for (int i = 0; i < mLength; ++i) {
    			if (instanceGet(i).getType().equals(item)) {
    				instanceGet(i).incrementInstance();
    			}
    			
    		}
    	}
    } // end of add()


    @Override
	public int search(String item) {
    	if (contains(item)) {
    		for (int i = 0; i < mLength; ++i) {
    			if (ascendingGet(i).getType().equals(item)) {
    				return ascendingGet(i).getInstances();
    			}
    		}
    	}
    	
    	return 0;
    } // end of search()


    @Override
	public List<String> searchByInstance(int instanceCount) {

        // Placeholder, please update.
        return null;
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {
		for (int i = 0; i < mLength; ++i) {
			if (ascendingGet(i).getType().equals(item)) {
				return true;
			}
		}
        return false;
    } // end of contains()


    @Override
	public void removeOne(String item) {
		if (contains(item)) {
			for (int i = 0; i < mLength; i++) {
				if (ascendingGet(i).getType().equals(item)) {
					if (ascendingGet(i).getInstances() > 1) {
						ascendingGet(i).decrementInstance();
						return;
					} else {
						LinkedNode currentNode = ascendingHead;
						LinkedNode previousNode = null;
						if (i == 0) {
							ascendingHead = currentNode.getNext();
							mLength--;
							return;
						} else {
							previousNode = currentNode;
							currentNode = currentNode.getNext();
							previousNode.setNext(currentNode.getNext());
							currentNode = null;
							mLength--;
							return;
						}
					}
				}
				
				if (instanceGet(i).getType().equals(item)) {
					if (instanceGet(i).getInstances() > 1) {
						instanceGet(i).decrementInstance();
						return;
					} else {
						LinkedNode currentNode = instanceHead;
						LinkedNode previousNode = null;
						if (i == 0) {
							instanceHead = currentNode.getNext();
							mLength--;
							return;
						} else {
							previousNode = currentNode;
							currentNode = currentNode.getNext();
							previousNode.setNext(currentNode.getNext());
							currentNode = null;
							mLength--;
							return;
						}
					}
				}	
			}
		} else {
			System.out.println("This array does not contaim the element; " + item);
		}
    } // end of removeOne()


    @Override
	public String print() {
    	LinkedNode currentNode = ascendingHead;
    	
    	String returnString = "";
    	
    	while (currentNode != null) {
    		returnString = returnString + currentNode.getElement().getType() + ": " + currentNode.getElement().getInstances() + "\n";
    		currentNode = currentNode.getNext();
    	}
    	
    	return returnString;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	if (mLength > 0) {
			String returnString = "";
			
			int i = 0;
			while (ascendingGet(i).getType().compareToIgnoreCase(lower) > 0 && ascendingGet(i).getType().compareToIgnoreCase(upper) < 0 && i < mLength) {
				returnString = returnString + ascendingGet(i).getType();
				i++;
			}
			
			return returnString;
		} else {
			return "Print failed";
		}
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of difference()
    
    public Element ascendingGet(int index) throws IndexOutOfBoundsException {
    	if (index >= mLength || index < 0) {
    		throw new IndexOutOfBoundsException("Supplied index is not within required range");
    	}
    	
    	LinkedNode currentNode = ascendingHead;
    	for (int i = 0; i < index; ++i) {
    		currentNode = currentNode.getNext();
    	}
    	

    	return currentNode.getElement();
    }
    
    public Element instanceGet(int index) throws IndexOutOfBoundsException {
    	if (index >= mLength || index < 0) {
    		throw new IndexOutOfBoundsException("Supplied index is not within required range");
    	}
    	
    	LinkedNode currentNode = instanceHead;
    	for (int i = 0; i < index; ++i) {
    		currentNode = currentNode.getNext();
    	}
    	

    	return currentNode.getElement();
    }
    
    public void insert(String type) {    	
    	Element newElement = new Element(type);
    	LinkedNode newAscendingNode = new LinkedNode(newElement);
    	LinkedNode newInstanceNode = new LinkedNode(newElement);
    	
    	
    	if (mLength == 0) {
    		ascendingHead = newAscendingNode;
    		instanceHead = newInstanceNode;
    	} else {
    		int index = 0;
    		
    		// update ascending linked list
    		for (int i = 0; i < mLength; ++i) {
    			if (ascendingGet(i).getType().compareToIgnoreCase(type) < 0) {
    				index++;
    			} else {
    				break;
    			}
    		}
    		
	    	if (index == 0) {
	    		newAscendingNode.setNext(ascendingHead);
	    		ascendingHead = newAscendingNode;
	    	} else {
	    		LinkedNode currNode = ascendingHead;
		    	for (int i = 0; i < index-1; i++) {
		    		currNode = currNode.getNext();
		    	}
		    	
		    	newAscendingNode.setNext(currNode.getNext());
		    	currNode.setNext(newAscendingNode);
	    	}
	    	
	    	// update instance linked list
	    	newInstanceNode.setNext(instanceHead);
	    	instanceHead = newInstanceNode;
	    	
    	}
    	
    	mLength++;
    }

} // end of class DualLinkedListMultiset
