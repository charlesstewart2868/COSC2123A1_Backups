package implementation;

public class Node {
    /** Stored Element of node. */
    protected Element mValue;
    /** Reference to next node. */
    protected Node mNext;

    public Node(Element type) {
        mValue = type;
        mNext = null;
    }

    public Element getElement() {
        return mValue;
    }

    public Node getNext() {
        return mNext;
    }

    public void setNext(Node next) {
        mNext = next;
    }
}
