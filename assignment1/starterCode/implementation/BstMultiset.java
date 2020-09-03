package implementation;

import java.util.List;

/**
 * BST implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class BstMultiset extends RmitMultiset
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
	- Union
	- Intersect
	- Difference
	*/
	
	protected BinaryNode root;
	protected int mLength;
	
	public BstMultiset() {
		root = null;
		mLength = 0;
	}
	
    @Override
	public void add(String item) {
        if (contains(item) == false) {
        	insert(item);
        	mLength++;
        } else {
        	get(root, item).getElement().incrementInstance();
        }
    } // end of add()


    @Override
	public int search(String item) {
    	if (contains(item)) {
    		return get(root, item).getElement().getInstances();
    	} else {
    		return searchFailed;
    	}
    } // end of search()


    @Override
	public List<String> searchByInstance(int instanceCount) {

        // Placeholder, please update.
        return null;
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {
        return containsNR(root, item);
    } // end of contains()


    @Override
	public void removeOne(String item) {
        if (contains(item) == true) {
        	if (get(root, item).getElement().getInstances() > 1) {
        		get(root, item).getElement().decrementInstance();
        	} else {
            	remove(item);
            	mLength--;
        	}
        } else {
        	System.out.println("There is no " + item + " to remove");
        }
    } // end of removeOne()


    // Non recursive printing of binary tree in alphabetical order
    @Override
	public String print() {
    	if (root == null) {
    		return "Nothing to print";
    	}
    	
    	String returnString = "";
    	BinaryNode currentNode = root;
    	BinaryNode preNode = null;
    	
    	while (currentNode != null) {
    		if (currentNode.getLeft() == null) {
    			returnString = returnString + currentNode.getElement().getType() + ": " + currentNode.getElement().getInstances() + "\n";
    			currentNode = currentNode.getRight();
    		} else {
    			preNode = currentNode.getLeft();
    			while (preNode.getRight() != null && preNode.getRight().getElement().getType().equals(currentNode.getElement().getType()) == false) {
    				preNode = preNode.getRight();
    			}
    			
    			if (preNode.getRight() == null) {
    				preNode.setRight(currentNode);
    				currentNode = currentNode.getLeft();
    			} else {
    				preNode.setRight(null);
        			returnString = returnString + currentNode.getElement().getType() + ": " + currentNode.getElement().getInstances() + "\n";
    				currentNode = currentNode.getRight();
    			}
    		}
    	}
    	
    	return returnString;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	if (root == null) {
    		return "Nothing to print";
    	}
    	
    	String returnString = "";
    	BinaryNode currentNode = root;
    	BinaryNode preNode = null;
    	
    	while (currentNode != null) {
    		if (currentNode.getLeft() == null) {
    			if (currentNode.getElement().getType().compareToIgnoreCase(lower) > 0 && currentNode.getElement().getType().compareToIgnoreCase(upper) < 0) {
    				returnString = returnString + currentNode.getElement().getType() + ": " + currentNode.getElement().getInstances() + "\n";
    			}
    			currentNode = currentNode.getRight();
    		} else {
    			preNode = currentNode.getLeft();
    			while (preNode.getRight() != null && preNode.getRight().getElement().getType().equals(currentNode.getElement().getType()) == false) {
    				preNode = preNode.getRight();
    			}
    			
    			if (preNode.getRight() == null) {
    				preNode.setRight(currentNode);
    				currentNode = currentNode.getLeft();
    			} else {
    				preNode.setRight(null);
        			if (currentNode.getElement().getType().compareToIgnoreCase(lower) > 0 && currentNode.getElement().getType().compareToIgnoreCase(upper) < 0) {
        				returnString = returnString + currentNode.getElement().getType() + ": " + currentNode.getElement().getInstances() + "\n";
        			}
    				currentNode = currentNode.getRight();
    			}
    		}
    	}
    	
    	return returnString;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
		RmitMultiset unionMultiset = other;
		
//		for (int i = 0; i < mLength; ++i) {
//			if (other.contains() == false) {
//				unionMultiset.add(get(i).getType());
//			}
//		}
		
		return unionMultiset;
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
    
    
    public BinaryNode get(BinaryNode currentNode, String type) {
    	if (currentNode == null) {
    		return null;
    	}
    	
    	while (currentNode != null) {
    		if (currentNode.getElement().getType().equals(type)) {
    			return currentNode;
    		} else if (currentNode.getElement().getType().compareToIgnoreCase(type) < 0) {
    			currentNode = currentNode.getRight();
    		} else {
    			currentNode = currentNode.getLeft();
    		}
    	}
    	
    	return null;
    }
    
  
	public boolean containsNR(BinaryNode currentNode, String type) {
	  	if (currentNode == null) {
	  		return false;
	  	}
	  	if (type.equals(currentNode.getElement().getType())) {
	  		return true;
	  	}
	  	if (type.compareToIgnoreCase(currentNode.getElement().getType()) < 0) {
	  		return containsNR(currentNode.getLeft(), type);
	  	} else {
	  		return containsNR(currentNode.getRight(), type);
	  	}
	}
    
    public void insert(String type) { // ||MEDIUM||
    	root = insertR(root, type);
    }
    
    private BinaryNode insertR(BinaryNode currentNode, String type) { // ||MEDIUM||
    	if (currentNode == null) {
    		Element newElement = new Element(type);
    		currentNode = new BinaryNode(null, null, newElement);
    		return currentNode;
    	}
    	
    	if (type.compareToIgnoreCase(currentNode.getElement().getType()) < 0) {
    		currentNode.setLeft(insertR(currentNode.getLeft(), type));
    	} else if (type.compareToIgnoreCase(currentNode.getElement().getType()) > 0) {
    		currentNode.setRight(insertR(currentNode.getRight(), type));
    	}
    	
    	return currentNode;
    }
    
    public void remove(String type) {
    	root = removeR(root, type);
    }
    
    private BinaryNode removeR(BinaryNode currentNode, String type) {
    	if (currentNode == null) {
    		return currentNode;
    	}
    	
    	if (type.compareToIgnoreCase(currentNode.getElement().getType()) < 0) {
    		currentNode.setLeft(removeR(currentNode.getLeft(), type));
    	} else if (type.compareToIgnoreCase(currentNode.getElement().getType()) > 0) {
    		currentNode.setRight(removeR(currentNode.getRight(), type));
    	} else {
    		if (currentNode.getLeft() == null) {
    			return currentNode.getRight();
    		} else if (currentNode.getRight() == null) {
    			return currentNode.getLeft();
    		}
    		
    		currentNode.getElement().setType(minString(currentNode.getRight()));
    		currentNode.setRight(removeR(currentNode.getRight(), currentNode.getElement().getType()));
    	}
    	
    	return currentNode;
    }
    
    private String minString(BinaryNode currentNode) {
    	String minValue = currentNode.getElement().getType();
    	
    	while (currentNode.getLeft() !=  null) {
    		minValue = currentNode.getLeft().getElement().getType();
    		currentNode = currentNode.getLeft();
    	}
    	
    	return minValue;
    }
    
    
    
//    public void createPrintString(BinaryNode node) {
//        if (root != null) {
//            createPrintString(node.getLeft());
//            System.out.println(node.getElement().getType() + ": " + node.getElement().getInstances() + "\n");
//            createPrintString(node.getRight());
//        }
//    }

    
//	  get(i).getType().compareToIgnoreCase(lower) > 0
//	  true if i is greater than lower

} // end of class BstMultiset
