package implementation;

public class LinkedNode {
    /** Stored Element of linkedNode. */
    protected Element linkedValue;
    /** Reference to next linkedNode. */
    protected LinkedNode linkedNext;

    public LinkedNode(Element type) {
        this.linkedValue = type;
        this.linkedNext = null;
    }

    public Element getElement() {
        return this.linkedValue;
    }

    public LinkedNode getNext() {
        return this.linkedNext;
    }

    public void setNext(LinkedNode next) {
        this.linkedNext = next;
    }
}
