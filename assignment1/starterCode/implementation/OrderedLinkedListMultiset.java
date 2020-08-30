package implementation;

import java.util.List;

/**
 * Ordered linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class OrderedLinkedListMultiset extends RmitMultiset
{

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
	
	protected LinkedNode mHead;
	protected int mLength;
	
	public OrderedLinkedListMultiset() {
		mHead = null;
		mLength = 0;
	}
	
    @Override
	public void add(String item) {
    	if (contains(item) == false) {
    		insert(item);
    	} else {
    		for (int i = 0; i < mLength; ++i) {
    			if (get(i).getType().equals(item)) {
    				get(i).incrementInstance();
    			}
    		}
    	}
    } // end of add()


    @Override
	public int search(String item) {
    	if (contains(item)) {
    		for (int i = 0; i < mLength; ++i) {
    			if (get(i).getType().equals(item)) {
    				return get(i).getInstances();
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
			if (get(i).getType().equals(item)) {
				return true;
			}
		}
        return false;
    } // end of contains()


    @Override
	public void removeOne(String item) {
		if (contains(item)) {
			for (int i = 0; i < mLength; i++) {
				if (get(i).getType().equals(item)) {
					if (get(i).getInstances() > 1) {
						get(i).decrementInstance();
						return;
					} else {
						LinkedNode currentNode = mHead;
						LinkedNode previousNode = null;
						if (i == 0) {
							mHead = currentNode.getNext();
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
    	LinkedNode currentNode = mHead;
    	
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
			while (get(i).getType().compareToIgnoreCase(lower) > 0 && get(i).getType().compareToIgnoreCase(upper) < 0 && i < mLength) {
				returnString = returnString + get(i).getType();
				i++;
			}
			
			return returnString;
		} else {
			return "Print failed";
		}
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
		RmitMultiset unionMultiset = other;
		
		for (int i = 0; i < mLength; ++i) {
			if (other.contains(get(i).getType()) == false) {
				unionMultiset.add(get(i).getType());
			}
		}
		
		return unionMultiset;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {
		RmitMultiset intersectionMultiset = new ArrayMultiset();
		
		for (int i = 0; i < mLength; ++i) {
			if (other.contains(get(i).getType()) == true) {
				intersectionMultiset.add(get(i).getType());
			}
		}
		
		return intersectionMultiset;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {
		RmitMultiset differenceMultiset = new ArrayMultiset();
		
		for (int i = 0; i < mLength; ++i) {
			if (other.contains(get(i).getType()) == false) {
				differenceMultiset.add(get(i).getType());
			}
		}

		return differenceMultiset;
    } // end of difference()
    
    public Element get(int index) throws IndexOutOfBoundsException {
    	if (index >= mLength || index < 0) {
    		throw new IndexOutOfBoundsException("Supplied index is not within required range");
    	}
    	
    	LinkedNode currentNode = mHead;
    	for (int i = 0; i < index; ++i) {
    		currentNode = currentNode.getNext();
    	}
    	

    	return currentNode.getElement();
    }
    
    public void insert(String type) {    	
    	Element newElement = new Element(type);
    	LinkedNode newNode = new LinkedNode(newElement);
    	
    	if (mHead == null) {
    		mHead = newNode;
    	} else {
    		int index = 0;
    		
    		for (int i = 0; i < mLength; ++i) {
    			if (get(i).getType().compareToIgnoreCase(type) < 0) {
    				index++;
    			} else {
    				break;
    			}
    		}
    		
	    	if (index == 0) {
	    		newNode.setNext(mHead);
	    		mHead = newNode;
	    	} else {
	    		LinkedNode currNode = mHead;
		    	for (int i = 0; i < index-1; i++) {
		    		currNode = currNode.getNext();
		    	}
		    	
		    	
		    	newNode.setNext(currNode.getNext());
		    	currNode.setNext(newNode);
	    	}
    	}
    	
    	mLength++;
    }

} // end of class OrderedLinkedListMultiset
