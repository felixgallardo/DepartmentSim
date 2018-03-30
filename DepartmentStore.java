/**
 *
 * 
 * The DepartmentStore class shows the user a menu. Each menu pertains to a method from the ItemList class,
 * as well as a quit option. It prompts the user to choose an option. The option is converted to lowercase
 * and is compared to the available options. Once a match is found, the method within that option's
 * if-statement is found. If the method requires information, the user's prompted for that information
 * and it's then assigned to their respective variables. Once the information is collected, the variables
 * are passed to ItemList methods. The method is then ran, and the user is presented with the output
 * from the respective method.
 *
 * */
import java.util.*;
public class DepartmentStore {
    public static void main(String[] args) {
        /**
         * Here, we create a new scanner object for user input. As well as the creation of
         * the scanner object, we declare the input, name, rfidTag, price, initPosition,
         * currentPosition and cartNumber variables for later use when passing in arguments
         * to the methods in the ItemListClass.
         */
        Scanner userInput = new Scanner(System.in);
        String input;
        String name;
        String rfidTag;
        double price;
        String initPosition;
        String currentPosition;
        String cartNumber;

        /**
         * We create the new ItemList object which will house nodes created by the
         * insert methods.
         */
        ItemList newList = new ItemList();

        /**
         * The beginning of the while-loop which prompts the user to choose an option, and carries out
         * the user's selected option accordingly.
         */
        while (true) {
            System.out.println(

                    "\nWelcome!\n\n" +                                //Checklist
                                "I - Insert an item into the list\n" +  //Works
                                "M - Move an item in the store\n" +     //Works
                                "L - List by location\n" +              //Works
                                "P - Print all items in store\n" +      //Works
                                "O - Checkout\n" +                      //Works
                                "C - Clean store\n" +                   //Works
                                "U - Update inventory system\n" +       //Works
                                "R - Print by Rfid tag number\n" +      //Works
                                "Q - Exit the program\n");              //Works
            System.out.print("Select an operation: ");
            input = userInput.next();

            /**
             * If the user chooses "i", they're then prompted for the information pertaining
             * to the item they wish to insert into the linked list, and then inserts
             * that item into the linked list. As well as prompting them for
             * the information, the try-catch exceptions check to make sure the format of the
             * information the user is inputting is done so correctly.
             */
            if (input.toLowerCase().equals("i")) {

                System.out.println("What's the name of the item?: ");
                userInput.nextLine();
                name = userInput.nextLine();
                System.out.println("What's the Rfid tag of the item?: ");
                rfidTag = userInput.next();
                try {
                    if (rfidTag.length() != 9) {
                        throw new IllegalArgumentException("Rfid tag format is invalid; must have" +
                                " 9 characters.\n");
                    }
                    System.out.println("What's the price of the item?: ");
                    price = userInput.nextDouble();
                    if (price < 0) {
                        throw new IllegalArgumentException("The price can't be negative.\n");
                    }
                    System.out.println("What's the initial position of the item?: ");
                    userInput.nextLine();
                    initPosition = userInput.nextLine();
                    newList.insertInfo(name, rfidTag, price, initPosition);
                } catch (IllegalArgumentException fe) {
                    System.out.println(fe.getMessage());
                }
            }
            /**
             * If the user chooses m, they're asked for the rfid tag of the item they'd
             * like to move, as well as the position of the item they'd like to move. Afterwards,
             * these values are passed on to the moveItem method. It returns a boolean value
             * indicating whether the item containing the given rfid tag was found.
             */
            if (input.toLowerCase().equals("m")) {
                //    public boolean moveItem(String rfidTag, String source, String dest) {
                System.out.println("What's the Rfid tag of the item you'd like to move?: ");
                rfidTag = userInput.next();
                try {
                    if (rfidTag.length() != 9) {
                        throw new IllegalArgumentException("Rfid tag format is invalid; must have" +
                                " 9 characters.\n");
                    }
                    System.out.println("What's the original position of the item you'd like to move?: ");
                    userInput.nextLine();
                    initPosition = userInput.nextLine();
                    if (initPosition.equals("out")) {
                        throw new IllegalArgumentException("The item can't be moved, as it's checked out.");
                    }
                    System.out.println("Where would you like to move the item?: ");
                    currentPosition = userInput.next();
                    if ((currentPosition.charAt(0) == 'c' && currentPosition.length() == 4)
                            || (currentPosition.charAt(0) == 's' && currentPosition.length() == 6) ||
                            (currentPosition.equalsIgnoreCase("out"))) {
                        newList.moveItem(rfidTag, initPosition, currentPosition);
                    } else {
                        throw new IllegalArgumentException("The item can only be moved to a cart, a shelf or out. " +
                                "Carts are 4 characters long,\n starting with a C and shelves are 6 characters long," +
                                "starting with an S.");
                    }
                }
                     catch (IllegalArgumentException fe) {
                        System.out.println(fe.getMessage());
                    }

            }
            /**
             * If the user chooses l, they're asked for the position of an item. That value is then passed onto the
             * printByLocation method. It returns a formatted list of the items in the given location.
             */
            if (input.toLowerCase().equals("l")) {
                System.out.println("What's the location of the item(s) you'd like to see?: ");
                userInput.nextLine();
                currentPosition = userInput.nextLine();
                newList.printByLocation(currentPosition);
                //List by location
            }

            /**
             * If the user chooses p, the linked list, newList, is printed using the printAll method.
             * It returns all the items currently in the list.
             */
            if (input.toLowerCase().equals("p")) {
                //Print all
                newList.printAll();
            }
            /**
             * If the user chooses o, they're asked for their cart number.
             * Once provided, the value is passed on to the checkOut method.
             * As well as passing the value, this option has a try-catch method which
             * checks to see if the cart number the user entered is properly formatted. The method
             * returns a list of the items being checked out.
             */
            if (input.toLowerCase().equals("o")) {
                //Check out
                System.out.println("What's your cart number?");
                cartNumber = userInput.next();
                try {
                    if (cartNumber.length() != 4 || cartNumber.charAt(0) != 'c') {
                        throw new IllegalArgumentException("The cart number must be " +
                                "6 characters and start with a c.\n");
                    }
                    System.out.println("The total for cart " + cartNumber + " is: " + newList.checkOut(cartNumber));
                } catch (IllegalArgumentException fe) {
                    System.out.println(fe.getMessage());
                }
            }

            /**
             * If the user chooses c, the cleanStore method is called, which places items on
             * the wrong shelf back where they belong.
             */
                if (input.toLowerCase().equals("c")) {
                    //Clean store
                    newList.cleanStore();
                }

            /**
             * If the user presses u, the removeAllPurchased method is called, which returns
             * a table that displays the items removed by the removeAllPurchased method.
             */
                if (input.toLowerCase().equals("u")) {
                    //Update inventory system
                    newList.removeAllPurchased();
                }
            /**
             * If the user presses r, they're prompted for an rfid tag. This rfid tag is then
             * passed on to the printByRfid method. The try-catch block checks to see the rfid
             * is properly formatted, notifying the user if otherwise. The method returns a list
             * of items containing this rfid tag.
             */
                if (input.toLowerCase().equals("r")) {
                    System.out.println("What's the Rfid number of the item(s) you'd like to see?: ");
                    rfidTag = userInput.next();
                    try {
                        if (rfidTag.length() != 9) {
                            throw new IllegalArgumentException("Rfid tag format is invalid; must have" +
                                    " 9 characters.\n");
                        }
                        newList.printByRfid(rfidTag);
                    }
                    catch (IllegalArgumentException fe) {
                        System.out.println(fe.getMessage());
                    }

                }
            /**
             * If the user presses q, the program is terminated.
             */
                if (input.toLowerCase().equals("q")) {
                    //Exit the program
                    System.out.println("Program's closing.");
                    break;
                }


            }
        }
    }
