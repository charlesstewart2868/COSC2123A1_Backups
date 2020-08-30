package implementation;

public class LinkedNode {
    /** Stored Element of linkedNode. */
    protected Element linkedValue;
    /** Reference to next linkedNode. */
    protected LinkedNode linkedNext;

    public LinkedNode(Element type) {
        linkedValue = type;
        linkedNext = null;
    }

    public Element getElement() {
        return linkedValue;
    }

    public LinkedNode getNext() {
        return linkedNext;
    }

    public void setNext(LinkedNode next) {
        linkedNext = next;
    }
}
