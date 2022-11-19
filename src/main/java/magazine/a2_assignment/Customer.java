package magazine.a2_assignment;

import java.util.ArrayList;
import java.io.*;

/**
 * @author  Willie Chong Wei Yi (williecwy134@gmail.com)
 * @version 1.0
 * @since   2022-Oct-17
 */


public class Customer implements Serializable {

    /**
     * The string holding customer name
     */
    protected String customerName;

    /**
     * The string holding customer email address
     */
    protected String customerEmail;

    /**
     * The integer holding customer address-street number
     */
    protected Integer streetNumber;

    /**
     * The string holding customer address-street name
     */
    protected String streetName;

    /**
     * The string holding customer address-state
     */
    protected String countryState;

    /**
     * The integer holding customer address-postal code
     */
    protected Integer postalCode;

    /**
     * The arraylist holding the list of supplement customer interested in.
     */
    protected ArrayList<Supplement> customerInterestedSupplement = new ArrayList<Supplement>();

    /**
     * Default Constructor
     * Post-condition: Customer object constructed
     */
    public Customer() {
        this.customerName = "";
        this.customerEmail = "";
        this.streetNumber = 0;
        this.streetName = "";
        this.countryState = "";
        this.postalCode = 0;
    }

    /**
     * Constructor, for usage in event that the supplement wasn't available at first instance of creation
     * @param customerName : Valid string containing Customer name
     * @param customerEmail : Valid string containing customer email address
     * @param streetNumber : Valid integer containing the customer street number;
     * @param streetName : Valid string containing the customer street name;
     * @param countryState : Valid string containing the customer address country state;
     * @param postalCode : Valid integer containing the customer address postal code;
     * Postcondition: Customer object constructed
     */
    public Customer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.countryState = countryState;
        this.postalCode = postalCode;
    }

    /**
     * Full Constructor
     * @param customerName : Valid string containing Customer name
     * @param customerEmail : Valid string containing customer email address
     * @param streetNumber : Valid integer containing the customer street number
     * @param streetName : Valid string containing the customer street name
     * @param countryState : Valid string containing the customer address country state
     * @param postalCode : Valid integer containing the customer address postal code
     * @param customerInterestedSupplement : Valid Array list containing customer interested supplement
     * Postcondition: Customer object constructed
     */
    public Customer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, ArrayList<Supplement> customerInterestedSupplement) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.countryState = countryState;
        this.postalCode = postalCode;
        this.customerInterestedSupplement = customerInterestedSupplement;
    }

    /**
     * Getter for Customer Name
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for Customer Name
     * @param customerName String containing customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for Customer Email
     * @return customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Setter for Customer Email
     * @param customerEmail String containing customer email address
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Getter for Customer address-Street Number
     * @return Integer customer address-streetNumber
     */
    public Integer getStreetNumber() {
        return streetNumber;
    }

    /**
     * Setter for Customer address-Street number
     */
    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Getter for Customer address-Street name
     * @return String Customer address-street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Setter for Customer address-Street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Getter for Customer address-country state
     * @return String Customer address-country state
     */
    public String getCountryState() {
        return countryState;
    }

    /**
     * Setter for Customer address-country state
     */
    public void setCountryState(String countryState) {
        this.countryState = countryState;
    }

    /**
     * Getter for Customer address-Postal Code
     * @return Integer customer address-Postal Code
     */
    public Integer getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for Customer address-Postal Code
     */
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for Customer List of interested Supplement
     * @return customerInterestedSupplement
     */
    public ArrayList<Supplement> getCustomerInterestedSupplement() {
        return customerInterestedSupplement;
    }

    /**
     * Setter for Customer List of interested Supplement
     * @param customerInterestedSupplement Array list containing customer interested list of supplement
     */
    public void setCustomerInterestedSupplement(ArrayList<Supplement> customerInterestedSupplement) {
        this.customerInterestedSupplement = customerInterestedSupplement;
    }

    /**
     * This method is print on-screen info of customer
     * <p>
     * Postcondition: Crafted message printed out on screen.
     */
    public void display (){
        //1) print out customer details
        System.out.println("Name of Customer = " + customerName);
        //System.out.println("Email of Customer = " + customerEmail);
        System.out.println("Address of Customer = " + streetNumber + " " + streetName + " " + countryState + " " + postalCode);

        //2) print out the customer supplement list if 1 or more supplement is in list
        if(customerInterestedSupplement.size() > 0){
            printSupplementList();
        }
    }

    /**
     * This method is print on-screen info of all supplement customer interested in
     * <p>
     * Postcondition: Crafted message printed out on screen.
     */
    private void printSupplementList(){
        System.out.println("List of supplement Customer subscribed to: ");
        //use regular for loop as wanted to display the position and number of supplement in the list
        for (int i=0; i<customerInterestedSupplement.size(); i++){
            Supplement tempCustomerSupplement = customerInterestedSupplement.get(i);
            System.out.println("Name of supplement magazine " + (i+1) + " = " + tempCustomerSupplement.getNameOfSupplement());
            System.out.println("Cost of supplement magazine " + (i+1) + " = " + tempCustomerSupplement.getWeeklyCostOfSupplement());
        }
    }

    /**
     * Unit Testing for Customer Class
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


        //creating a temporary array list
        System.out.println("Testing: Adding both supplement into temporary array list");
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


        //creating a new customer with arraylist
        System.out.println("Testing 1: Creating a customer object with the new temporary array list");
        Customer customerA = new Customer("Willie", "williecwy134@gmail.com",91 , " Jones Street" , "Sydney",2060, tempListOfSupplementMagazines);
        //display the new magazine content
        System.out.println("Displaying all the content of the customer object");
        customerA.display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();

        //creating a new customer without arraylist
        System.out.println("Testing 2: Creating a customer object without array list");
        Customer customerB = new Customer("Jon", "Jonathan@gmail.com",17,"Philip Street", "Sydney", 3018);
        //display the new magazine content
        System.out.println("Displaying all the content of the customer object");
        customerB.display();
        System.out.println("----------------------------------------------------------------");
    }
}
