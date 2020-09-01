package implementation;

public class BinaryNode {
    /** Stored Element of binaryNode. */
    protected Element binaryValue;
    /** Reference to next linkedNode. */
    protected BinaryNode binaryLeft;
    protected BinaryNode binaryRight;

    public BinaryNode(BinaryNode left, BinaryNode right, Element type) {
        this.binaryValue = type;
        this.binaryLeft = left;
        this.binaryRight = right;
    }
    
    public Element getElement() {
        return this.binaryValue;
    }

    public BinaryNode getLeft() {
        return this.binaryLeft;
    }
    
    public BinaryNode getRight() {
    	return this.binaryRight;
    }

    public void setLeft(BinaryNode left) {
        this.binaryLeft = left;
    }
    
    public void setRight(BinaryNode right) {
    	this.binaryRight = right;
    }
}
