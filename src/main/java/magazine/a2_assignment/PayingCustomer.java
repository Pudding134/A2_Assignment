package magazine.a2_assignment;

import java.util.ArrayList;
import java.io.*;

/**
 * @author  Willie Chong Wei Yi (williecwy134@gmail.com)
 * @version 1.0
 * @since   2022-Oct-17
 */


public class PayingCustomer extends Customer implements Serializable{

    /**
     * The string holding payment method
     */
    private String paymentMethod;

    /**
     * The arraylist holding the list of associate customer paid by this paying customer
     */
    private ArrayList<associateCustomer> listOfAssociateCustomer = new ArrayList<associateCustomer>();

    /**
     * Default Constructor
     * Postcondition: Customer object constructed
     */
    public PayingCustomer(){
        super();
    }

    /**
     * Constructor, for usage in event that the supplement and associate customer wasn't available at first instance of creation
     *
     * @param customerName  : Valid string containing Customer name
     * @param customerEmail : Valid string containing customer email address
     * @param streetNumber : Valid integer containing the customer street number;
     * @param streetName : Valid string containing the customer street name;
     * @param countryState : Valid string containing the customer address country state;
     * @param postalCode : Valid integer containing the customer address postal code;
     * @param paymentMethod : String containing payment method
     * Postcondition: Customer object constructed
     */
    public PayingCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, String paymentMethod) {
        super(customerName, customerEmail,streetNumber, streetName, countryState, postalCode);
        this.paymentMethod = paymentMethod;
    }


    /**
     * Constructor, for usage in event that the supplement available but associate customer not available
     *
     * @param customerName  : Valid string containing Customer name
     * @param customerEmail : Valid string containing customer email address
     * @param streetNumber : Valid integer containing the customer street number;
     * @param streetName : Valid string containing the customer street name;
     * @param countryState : Valid string containing the customer address country state;
     * @param postalCode : Valid integer containing the customer address postal code;
     * @param paymentMethod : String containing payment method
     * @param customerInterestedSupplement : Array list containing list of associate customer this customer is paying for
     * Postcondition: Customer object constructed
     */
    public PayingCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, String paymentMethod, ArrayList<Supplement> customerInterestedSupplement) {
        super(customerName, customerEmail,streetNumber, streetName, countryState, postalCode,customerInterestedSupplement);
        this.paymentMethod = paymentMethod;
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
     * @param paymentMethod : String containing payment method
     * @param listOfAssociateCustomer : Array list containing list of associate customer this customer is paying for
     * Postcondition: Customer object constructed
     */
    public PayingCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, String paymentMethod, ArrayList<Supplement> customerInterestedSupplement, ArrayList<associateCustomer> listOfAssociateCustomer) {
        super(customerName, customerEmail,streetNumber, streetName, countryState, postalCode,customerInterestedSupplement);
        this.paymentMethod = paymentMethod;
        this.listOfAssociateCustomer = listOfAssociateCustomer;
    }

    /**
     * Getter for Customer payment method
     * @return paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Setter for Customer payment method
     * @param paymentMethod String containing customer payment method
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Getter for list of associate customer this paying customer pay for
     * @return listOfAssociateCustomer
     */
    public ArrayList<associateCustomer> getListOfAssociateCustomer() {
        return listOfAssociateCustomer;
    }

    /**
     * Setter for list of associate customer this paying customer pay for
     * @param listOfAssociateCustomer Array list containing list of associate customer this paying customer pay for
     */
    public void setListOfAssociateCustomer(ArrayList<associateCustomer> listOfAssociateCustomer) {
        this.listOfAssociateCustomer = listOfAssociateCustomer;
    }


    /**
     * This method is to overwrite print on-screen info of customer class
     * Paying Customer details will be displayed.
     * Associate customer under this paying customer will be displayed as well.
     * <p>
     * Precondition: Nil <br>
     * Postcondition: Crafted message printed out on screen.
     */
    public void display (){
        super.display();
        System.out.println("Payment method = " + paymentMethod);
        System.out.println(); //formatting purpose

        //print out if there is any associate customer this customer is printing for
        //use normal loop as wanted the position number of the associate customer in the list
        if(listOfAssociateCustomer.size()>0){
            System.out.println("List of associate customer under " + customerName + ":");
            for (int i=0; i<listOfAssociateCustomer.size(); i++){
                System.out.println("Associate Customer " + (i+1));
                listOfAssociateCustomer.get(i).display();
                System.out.println(); //formatting purpose
            }
        }
        System.out.println(); //formatting purpose
    }
    @Override
    public String toString() {
        return this.getCustomerName();
    }



    /**
     * Unit Testing for Paying Customer Class
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

        //creating 2 associate customer for testing
        System.out.println("Testing: Creating 2 testing associate customer");
        associateCustomer tempAssociateA = new associateCustomer("Stella", "Stella@Gmail.com",91 ," Jones Street" , "Sydney",2081);
        tempAssociateA.display();
        associateCustomer tempAssociateB = new associateCustomer("Eunice", "Eunice@Gmail.com",91, " Jones Street" , "Sydney",2081);
        tempAssociateB.display();
        tempAssociateA.customerInterestedSupplement.add(supplementA);
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        //creating a temporary array list
        System.out.println("Testing: Adding all 2 associate customer into temporary array list");
        ArrayList<associateCustomer> tempListofAssociateCustomer = new ArrayList<associateCustomer>();
        //add the 2 associate customer into the temporary array list
        tempListofAssociateCustomer.add(tempAssociateA);
        tempListofAssociateCustomer.add(tempAssociateB);
        System.out.println("Testing: Displaying content of temporary array list");
        tempListofAssociateCustomer.get(0).display();
        tempListofAssociateCustomer.get(1).display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        //creating a new MINIMAL Paying customer without anything
        System.out.println("Testing 1: Creating a MINIMAL paying customer object without ANYTHING");
        PayingCustomer customerA = new PayingCustomer("Willie", "williecwy134@gmail.com",91 , " Jones Street" , "Sydney",2081, "Debit");
        //display the new magazine content
        System.out.println("Displaying all the content of the paying customer object");
        customerA.display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();



        //creating a new Paying customer with associate customer list
        System.out.println("Testing 2: Creating a paying customer object with the new temporary supplement array list");
        PayingCustomer customerB = new PayingCustomer("Unhomer", "Unhomer@gmail.com",13 , " Jones Street" , "Sydney",2020,"credit card" ,tempListOfSupplementMagazines);
        //display the new magazine content
        System.out.println("Displaying all the content of the paying customer object");
        customerB.display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        //creating a new paying customer with EVERYTHING
        System.out.println("Testing 2: Creating a customer object with both associate customer and supplement array list");
        PayingCustomer customerC = new PayingCustomer("Jon", "Jonathan@gmail.com", 1 , " Jones Street" , "Sydney",2053,"credit card", tempListOfSupplementMagazines, tempListofAssociateCustomer);
        //display the new magazine content
        System.out.println("Displaying all the content of the customer object");
        customerC.display();
        System.out.println("----------------------------------------------------------------");
    }
}
