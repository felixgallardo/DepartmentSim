/**
 *
 * The ItemList class instantiates three objects; the linked list's head and the cursor,
 * all three of the ItemInfoNode type. As well as instantiating thes variables, it provides
 * multiple methods; the insertInfo, removeAllPurchased, moveItem, printAll, printByLocaiton,
 * cleanStore and checkout methods. These methods allow you to manipulate - by adding or removing nodes,
 * or printing the nodes for a visual representation of the list.
 *
 * */
public class ItemList {

    /**
     * Declaring the head and cursor objects of type ItemInfo Node.
     */
    private ItemInfoNode head;

    private ItemInfoNode cursor;
    //Counter's for testing reasons!
    int counter = 0;

    /**
     * toString headings to format the information in the list as a table.
     */
    String heading1 = "Item Name";
    String heading2 = "RFID";
    String heading3 = "Original Location";
    String heading4 = "Current Location";
    String heading5 = "Price";

    /**
     * The ItemList constructor sets the head and cursor to null, as the default
     * state of a list is empty.
     */
    public ItemList() {
        head = null;
        cursor = null;
    }

    /**
     *
     * The insertInfo method is O(n). If-statements don't affect time complexity and the
     * while loop runs n times, while the cursor != null. Therefore, the method is O(n) complexity.
     *
     * The insertInfo method takes in four variables; the name, rfidtag, price and the
     * initial position of the item being inserted. It then checks for three cases with 2
     * if-statements and a while loop.
     * Case 1: List is empty, therefore head = node.
     * Case 2: List only has one object, therefore we compare the item being inserted to the
     * single item in the list and choose whether it's placed in front or behind the object.
     * Case 3: Multiple objects in the list, so we loop through the list using a while loop,
     * comparing each object to the object being inserted. When we find the appropriate place,
     * we insert the item.
     *
     * @param name
     * @param rfidTag
     * @param price
     * @param initPosition
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {

        /* We need to create both objects - one that's a
        node and another object that's the info. Thus, we can create the node and
        refer the accessor/mutator methods in the ItemInfo class.
         */
        ItemInfo testInfo = new ItemInfo();
        ItemInfoNode testNode = new ItemInfoNode();

        ItemInfoNode cursor;
        ItemInfoNode temp;
        testInfo.setProdName(name);
        testInfo.setRfidTagNumber(rfidTag);
        testInfo.setProdPrice(price);
        testInfo.setOriginLocation(initPosition);
        testInfo.setCurrLocation(initPosition);

        testNode.setInfo(testInfo);
        cursor = head;

        //If head is null, list is empty so head = testNode.
        if (head == null) {
            head = testNode;
            counter++;
        } else if (cursor.getNext() == null) {
            //Case 2
            if (cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) >= 0) {
                cursor.setPrev(testNode);
                testNode.setNext(cursor);
                head = testNode;
            } else {
                cursor.setNext(testNode);
                testNode.setPrev(cursor);
            }
            counter++;
        }
        //While the next cursor is empty (to check the entire list for the proper position
        else {
            //Case 3
            while (cursor != null) {
                //Starting at the head, i f the node we're checking returns a negative or 0, then we know
                //that we have to entire testNode after the node that we're checking.

                if (cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) >= 0) {
                    if (cursor.getPrev() == null) {
                        cursor.setPrev(testNode);
                            testNode.setNext(cursor);
                        head = testNode;
                    } else {
                        temp = cursor.getPrev();
                        temp.setNext(testNode);
                        testNode.setPrev(temp);
                        testNode.setNext(cursor);
                        cursor.setPrev(testNode);
                    }
                    counter++;
                    break;
                } else {
                    if (cursor.getNext() == null) {
                        cursor.setNext(testNode);
                        testNode.setPrev(cursor);
                        break;
                    } else {
                        cursor = cursor.getNext();
                    }
                }
                counter++;
            }
        }
        // System.out.println("counter: " + counter);
    }

    /**
     *
     * Time complexity of removeAllPurchased is O(n), as if-statements don't affect complexity and
     * the while loop is dependent on the number of nodes, n. It runs while the cursor != null.
     *
     * The remove all purchased method checks first if the list is empty. If it's not, it loops
     * through the nodes checking their current location. If their current location is set to
     * "out", the item is removed from the linked list. It's removed by linking the previous
     * node and the node after the item, thus leaving garbage collection to remove the middle node.
     */
    public void removeAllPurchased() {
        cursor = head;
        boolean isEmpty = true;
        StringBuilder stringDesc = new StringBuilder(String.format("\n%-20s %-20s %-20s %-20s%-20s\n",
                heading1, heading2, heading3, heading4, heading5) +
                String.format("%-20s %-20s %-20s %-20s %-20s",
                        "---------", "----", "-----------------", "----------------", "----"));
        System.out.println(stringDesc.toString());

        if (head == null) {
            System.out.println("The list is empty. \n");
        } else while (cursor != null) {
            //If the cursor's current location is "out", it should be removed
            if (cursor.getInfo().getCurrLocation().equals("out")) {
                System.out.println(cursor);
                isEmpty = false;
                //We connect the node we set to null's previous node and next node to restructure the link
                if (cursor.getPrev() != null && cursor.getNext() != null) {
                    cursor.getPrev().setNext(cursor.getNext());
                    cursor.getNext().setPrev(cursor.getPrev());
                }
                else if (cursor.getPrev() != null && cursor.getNext() == null) {
                    cursor.getPrev().setNext(null);
                }
                else if (cursor.getPrev() == null) {
                    cursor.getNext().setPrev(null);
                }
                //We restructure the link and set the cursor to next, thus continuing the while loop
                System.out.println("We've removed all your purchased items.");
                cursor = cursor.getNext();
            } else {
                cursor = cursor.getNext();
            }
        }
        if (isEmpty) {
            System.out.println("There's nothing to remove.");
        }
    }

    /**
     *
     * The moveItem method is of time complexity O(n). The while loop loops through the list
     * of nodes n times, while the cursor != null. As such, as we've to iterate through the
     * entire list and there's no nested for/while loops, the time complexity is O(n).
     *
     * The moveItem method checked if the list is empty. If it is, it notifies the user. If it's not,
     * it method takes the rfidTag of an item to identify the item. It also takes the
     * item's source location to know where it came from as well as the destination - where the user
     * wants to move it. Once we loop through the list, we find the item(s) with the matching rfid
     * tag, set their current location to the given location and re-establish the links between
     * the nodes.
     *
     * @param rfidTag
     * @param source
     * @param dest
     * @return
     */
    public boolean moveItem(String rfidTag, String source, String dest) {
        cursor = head;
        boolean isEmpty = true;

        if (head == null) {
            System.out.println("The list is empty. \n");
        } else while (cursor != null) {

            if (cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(rfidTag) == 0 &&
                    cursor.getInfo().getOriginLocation().equalsIgnoreCase(source)) {
                cursor.getInfo().setCurrLocation(dest);
                cursor = cursor.getNext();
                System.out.println("Item has been moved! \n");
                return isEmpty = false;
            } else {
                cursor = cursor.getNext();
            }
        }
        if (isEmpty) {
            System.out.println("Item wasn't moved.");
        }
        return isEmpty;
    }

    /**
     * The printAll method is of time complexity O(n), as it loops through the list printing out
     * every node, while the cursor != null, as directed by the toString method.
     *
     * The printAll method prints out every node currently in the list.
     */
    public void printAll() {
        System.out.println(this);
    }

    /**
     * The printByLocation method is of time complexity O(n), as it loops through the list n times, while
     * cursor != null. As it's accessing every single value, one by one, the time complexity will be O(n).
     *
     *
     * The printByLocation takes in a location. It then checks if the list is empty. If it is, it
     * notifies the user. If it's not, we loop through the list, printing out every object
     * we encounter.
     *
     * @param location
     */
    public void printByLocation(String location) {
        cursor = head;
        boolean isEmpty = true;
        StringBuilder stringDesc = new StringBuilder(String.format("\n%-20s %-20s %-20s %-20s%-20s\n",
                heading1, heading2, heading3, heading4, heading5) +
                String.format( "%-20s %-20s %-20s %-20s %-20s",
                        "---------", "----", "-----------------", "----------------","----"));
        System.out.println(stringDesc.toString());

        if (cursor == null) {
            System.out.println("The list is empty. \n");
        }
        while (cursor != null) {
            if (cursor.getInfo().getCurrLocation().equalsIgnoreCase(location)) {
                System.out.println(cursor);
                cursor = cursor.getNext();
                isEmpty = false;
            } else {
                cursor = cursor.getNext();
            }
        }
        if (isEmpty){
            System.out.println("There's no item with the location " + location + ".");
        }
    }

    /**
     * cleanStore's time complexity is equal to O(n). Like the other methods, cleanStore loops
     * through the list as long as the cursor isn't equal to null. As the condition of looping is
     * based on whether there's a value or not, and there may be n values, thus complexity is O(n).
     *
     *
     * The cleanStore method checks if the list is empty. If it's empty, it notifies the user. Otherwise,
     * we loop through the items, checking if any items exist with mismatching current/original locations,
     * and make sure their current locations isn't equal to out. If any item eets this criteria, we set
     * the item's current location to its original location, thus putting it in its proper place.
     */
    public void cleanStore() {
        cursor = head;
        boolean isEmpty = true;
        StringBuilder stringDesc = new StringBuilder(String.format("\n%-20s %-20s %-20s %-20s%-20s\n",
                heading1, heading2, heading3, heading4, heading5) +
                String.format( "%-20s %-20s %-20s %-20s %-20s",
                        "---------", "----", "-----------------", "----------------","----"));
        System.out.println(stringDesc.toString());

        if (cursor == null) {
            System.out.println("The list is empty. \n");
        }
        while (cursor != null) {
            if (!cursor.getInfo().getCurrLocation().equalsIgnoreCase(cursor.getInfo().getOriginLocation()) &&
                    !cursor.getInfo().getCurrLocation().equalsIgnoreCase("out") &&
                    cursor.getInfo().getCurrLocation().charAt(0) != 'c') {
                //cursor.getPrev().steNext(cursor.getNext());
                cursor.getInfo().setCurrLocation(cursor.getInfo().getOriginLocation());
                //reconstruct the links from the new location
                System.out.println(cursor);
                cursor = cursor.getNext();
                System.out.println("Items have been moved back to their shelves.");
                isEmpty = false;
            } else {
                cursor = cursor.getNext();
            }
        }
        if (isEmpty){
            System.out.println("No items to be cleaned!");
        }
    }

    /**
     * The checkOut method's time complexity is O(n). The while loop keeps
     * looping through all of the nodes while the cursor is not equal to null. Because
     * it loops through all the given nodes in a list, the complexity is O(n).
     *
     * The checkOut method searches the cart provided by the user. It iterates through
     * all the nodes, matching their location to the provided cart number. Those with a
     * matching cart number then have their location set to "out".
     * @param cartNumber
     * @return
     */
    public double checkOut(String cartNumber) {
        double total = 0;
        cursor = head;
        boolean isEmpty = true;
        StringBuilder stringDesc = new StringBuilder(String.format("\n%-20s %-20s %-20s %-20s%-20s\n",
                heading1, heading2, heading3, heading4, heading5) +
                String.format( "%-20s %-20s %-20s %-20s %-20s",
                        "---------", "----", "-----------------", "----------------","----"));
        System.out.println(stringDesc.toString());

        if (cursor == null) {
            System.out.println("The list is empty. \n");
        }

        while (cursor != null) {
            if (cursor.getInfo().getCurrLocation().equalsIgnoreCase(cartNumber)) {
                total += cursor.getInfo().getProdPrice();
                cursor.getInfo().setCurrLocation("out");
                System.out.println(cursor);
                if (cursor.getPrev() != null) {
                    cursor.getPrev().setNext(cursor.getNext());
                }
                if (cursor.getNext() != null) {
                    cursor.getNext().setPrev(cursor.getPrev());
                }
                isEmpty = false;
                cursor = cursor.getNext();
                System.out.println("Items have been checked out.");
            } else {
                cursor = cursor.getNext();
            }
        }
        if (isEmpty){
            System.out.println("There are no items to check out!");
        }
        return total;
    }

    /**
     * The printByRfid method is of time complexity O(n), as the while loop ensures the
     * method will loop through the list based on the number of nodes, which may
     * potentially be n nodes, thus O(n).
     *
     * The printByrfid method checks if the list is null. If it is, it notifies the user.
     * If it's not, it loops through the list while the cursor isn't empty. While it's looping,
     * it compares the given rfid to the cursor object's rfid. If the rfid tags match, the item
     * is printed. Otherwise, we keep looping until cursor.getNext() = null;.
     *
     * @param rfid
     */
    public void printByRfid(String rfid) {
        boolean isEmpty = true;
        StringBuilder stringDesc = new StringBuilder(String.format("\n%-20s %-20s %-20s %-20s%-20s\n",
                heading1, heading2, heading3, heading4, heading5) +
                String.format( "%-20s %-20s %-20s %-20s %-20s",
                        "---------", "----", "-----------------", "----------------","----"));
        System.out.println(stringDesc.toString());
        cursor = head;
        if (cursor == null) {
            System.out.println("The list is empty. \n");
        }
        while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfid)) {
                System.out.println(cursor);
                cursor = cursor.getNext();
                isEmpty = false;
            } else {
                cursor = cursor.getNext();

            }
        }
        if (isEmpty){
            System.out.println("There are no items matching the Rfid " + rfid + ".");
        }
    }


    /**
     * The toString method provides us with a way to properly format the information we'd
     * like to display to the user.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringDesc = new StringBuilder(String.format("\n%-20s %-20s %-20s %-20s%-20s\n",
                                        heading1, heading2, heading3, heading4, heading5) +
                                        String.format( "%-20s %-20s %-20s %-20s %-20s\n",
                                        "---------", "----", "-----------------", "----------------","-----"));
        cursor = head;
        while (true) {
            if (cursor != null) {
                stringDesc.append(String.format("%-20s %-20s %-20s %-20s %-20s\n", cursor.getInfo().getProdName(),
                        cursor.getInfo().getRfidTagNumber(), cursor.getInfo().getOriginLocation(),
                        cursor.getInfo().getCurrLocation(), cursor.getInfo().getProdPrice()));
                cursor = cursor.getNext();

            } else {
                break;
            }

        }
        return stringDesc.toString();
    }
}