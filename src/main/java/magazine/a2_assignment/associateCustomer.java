package magazine.a2_assignment;

import java.util.ArrayList;
import java.io.*;

/**
 * @author  Willie Chong Wei Yi (williecwy134@gmail.com)
 * @version 1.0
 * @since   2022-Oct-17
 */

public class associateCustomer extends Customer implements Serializable{

    /**
     * Default Constructor, for usage in event that the supplement wasn't available at first instance of creation
     * Postcondition: Customer object constructed
     */
    public associateCustomer() {
        super();
    }

    /**
     * Constructor, for usage in event that the supplement wasn't available at first instance of creation
     *
     * @param customerName  : Valid string containing Customer name
     * @param customerEmail : Valid string containing customer email address
     * @param streetNumber : Valid integer containing the customer street number;
     * @param streetName : Valid string containing the customer street name;
     * @param countryState : Valid string containing the customer address country state;
     * @param postalCode : Valid integer containing the customer address postal code;
     * Postcondition: Customer object constructed
     */
    public associateCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode) {
        super(customerName, customerEmail,streetNumber, streetName, countryState, postalCode);
    }

    /**
     * Full Constructor
     *
     * @param customerName  : Valid string containing Customer name
     * @param customerEmail : Valid string containing customer email address
     * @param streetNumber : Valid integer containing the customer street number;
     * @param streetName : Valid string containing the customer street name;
     * @param countryState : Valid string containing the customer address country state;
     * @param postalCode : Valid integer containing the customer address postal code;
     * @param customerInterestedSupplement : Array list containing list of associate customer this customer is paying for
     * Postcondition: Customer object constructed
     */
    public associateCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, ArrayList<Supplement> customerInterestedSupplement) {
        super(customerName, customerEmail,streetNumber, streetName, countryState, postalCode,customerInterestedSupplement);
    }

    /**
     * Unit Testing for associate Customer Class
     */
    public static void main(String[] args) {
        //creating 2 supplement for testing
        System.out.println("Testing: Creating 2 testing supplement");
        Supplement supplementA = new Supplement("Earth Geography", 2);
        supplementA.display();
        Supplement supplementB = new Supplement("Animal Geography", 5);
        supplementB.display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        //creating a temporary array list (supplement)
        System.out.println("Testing: Adding all 2 supplement into temporary array list");
        ArrayList<Supplement> tempListOfSupplementMagazines = new ArrayList<Supplement>();
        //add the 2 supplement into the temporary array list
        tempListOfSupplementMagazines.add(supplementA);
        tempListOfSupplementMagazines.add(supplementB);
        System.out.println("Testing: Displaying content of temporary array list");
        tempListOfSupplementMagazines.get(0).display();
        tempListOfSupplementMagazines.get(1).display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();

        //creating a new MINIMAL associate customer without supplement
        System.out.println("Testing 1: Creating a testing associate customer without supplement");
        associateCustomer tempAssociateA = new associateCustomer("Stella", "Stella@Gmail.com",91 ," Jones Street" , "Sydney",2081);
        tempAssociateA.display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        //creating a new associate customer with list of supplement
        System.out.println("Testing 2: Creating a testing associate customer object with supplement array list");
        associateCustomer tempAssociateB = new associateCustomer("Eunice", "Eunice@Gmail.com",91, " Jones Street" , "Sydney",2081, tempListOfSupplementMagazines);
        tempAssociateB.display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
    }
}
