/**
 *
 *
 * As the ItemInfo class creates an object which holds the information, the
 * ItemInfoNode class creates an object in the form of a node which holds the
 * ItemInfo information. It instantiates three variables; the infoNode of the type
 * ItemInfo, as well as the prevNode and nextNode, which are type ItemInfoNode. These
 * instantiated variables allow us to access the previous and next nodes in the list, thus
 * allowing us to traverse the list. These instantiated variables are accompanied by their
 * appropriate accessor and mutator methods.
 *
 *
 * */

 public class ItemInfoNode {

    //When creating an object, originalLoc = currentPoc.

    /**
     * We declare the infoNode, prevNode and nextNode and their respective types.
     */
    private ItemInfo infoNode;
    private ItemInfoNode prevNode;
    private ItemInfoNode nextNode;
    String heading1 = "Item Name";
    String heading2 = "RFID";
    String heading3 = "Original Location";
    String heading4 = "Current Location";
    String heading5 = "Price";

    /**
     * Using the ItemInfoNode method, we create an ItemInfo object named infoNode.
     */
    public ItemInfoNode() {
        infoNode = new ItemInfo();
    }

    /**
     * The setInfo method takes in a value; info, and sets the infoNode equal
     * to the given variable.
     * @param info
     */
    public void setInfo(ItemInfo info) {
      infoNode = info;
    }

    /**
     * The getInfo method returns the previously set infoNode.
     * @return
     */
    public ItemInfo getInfo() {
        return infoNode;
    }

    /**
     * The setNext method takes in a node, and sets the current node's next node equal
     * to the given node. It allows us to properly establish a link between nodes.
     * @param node
     */
    //Shouldn't have been calling the setter and getter methods in themselves
    public void setNext(ItemInfoNode node) {
        nextNode = node;
    }

    /**
     * * The setPrev method takes in a node, and sets the current node's previous node equal
     * to the given node. It allows us to properly establish a link between nodes.
     * @param node
     */
    public void setPrev(ItemInfoNode node) {
        prevNode = node;
    }

    /**
     * The getNext method returns the current node's next node, as was established by the
     * setNext method.
     * @return
     */
    public ItemInfoNode getNext() {
        return nextNode;
    }

    /**
     * The getPrev method returns the current node's previous node, as was established by the
     * setPrevious method.
     * @return
     */
    public ItemInfoNode getPrev() {
        return prevNode;
    }


    @Override
    public String toString() {

        StringBuilder stringDesc = new StringBuilder(
                String.format("%-20s %-20s %-20s %-20s %-20s", getInfo().getProdName(),
                getInfo().getRfidTagNumber(), getInfo().getOriginLocation(),
                getInfo().getCurrLocation(),getInfo().getProdPrice()));


        return stringDesc.toString();
    }
    }


