package implementation;

public class BinaryNode {
    /** Stored Element of binaryNode. */
    protected Element binaryValue;
    /** Reference to next linkedNode. */
    protected BinaryNode binaryLeft;
    protected BinaryNode binaryRight;

    public BinaryNode(Element type) {
        binaryValue = type;
        binaryLeft = null;
        binaryRight = null;
    }
    
    public Element getElement() {
        return binaryValue;
    }

    public BinaryNode getLeft() {
        return binaryLeft;
    }
    
    public BinaryNode getRight() {
    	return binaryRight;
    }

    public void setLeft(BinaryNode left) {
        binaryLeft = left;
    }
    
    public void setRight(BinaryNode right) {
    	binaryRight = right;
    }
}
