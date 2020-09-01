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
        // Implement me!
    } // end of removeOne()


    @Override
	public String print() {
    	return "Printed";
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

        // Placeholder, please update.
        return new String();
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
    
    
    public BinaryNode get(BinaryNode root, String type) {
    	if (root == null) {
    		return null;
    	}
    	
    	while (root != null) {
    		if (root.getElement().getType().equals(type)) {
    			return root;
    		} else if (root.getElement().getType().compareToIgnoreCase(type) < 0) {
    			root = root.getRight();
    		} else {
    			root = root.getLeft();
    		}
    	}
    	
    	return null;
    }
    
  
	public boolean containsNR(BinaryNode node, String type) {
	  	if (node == null) {
	  		return false;
	  	}
	  	if (type.equals(node.getElement().getType())) {
	  		return true;
	  	}
	  	if (type.compareToIgnoreCase(node.getElement().getType()) < 0) {
	  		return containsNR(node.getLeft(), type);
	  	} else {
	  		return containsNR(node.getRight(), type);
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
    
    public void romove(String type) {
    	root = removeR(root, type);
    }
    
    private BinaryNode removeR(BinaryNode currentNode, String type) {
    	if (currentNode == null) {
    		return currentNode;
    	}
    	
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
