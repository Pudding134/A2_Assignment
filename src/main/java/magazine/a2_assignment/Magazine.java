package magazine.a2_assignment;

import java.util.ArrayList;
import java.io.*;

/**
 * @author  Willie Chong Wei Yi (williecwy134@gmail.com)
 * @version 1.0
 * @since   2022-Oct-17
 * @version 2.0
 * @since   2022-Oct-22
 */


public class Magazine implements Serializable{

    /**
     * String variable storing name of the magazine
     */
    private String nameOfMagazine;

    /**
     * Integer variable storing weekly cost of the magazine
     */
    private int weeklyCostOfMagazine;

    /**
     * Array list storing the list of supplement magazines available under this magazine
     */
    private ArrayList<Supplement> listOfSupplementMagazines = new ArrayList<Supplement>();

    /**
     * Array list storing the list of paying customer subscribed to this magazine
     */
    private ArrayList<PayingCustomer> listOfPayingCustomer = new ArrayList<PayingCustomer>();

    /**
     * Default Constructor
     */
    public Magazine() {
        this.nameOfMagazine = "";
        this.weeklyCostOfMagazine = 0;
    }

    /**
     * Constructor with name and weekly cost of magazine.
     * For usage if the list of supplement and paying customer wasn't available at instance of creation.
     *
     * @param nameOfMagazine  : String containing name of magazine
     * @param weeklyCostOfMagazine : Integer storing weekly cost of main magazine
     */
    public Magazine(String nameOfMagazine, int weeklyCostOfMagazine) {
        this.nameOfMagazine = nameOfMagazine;
        this.weeklyCostOfMagazine = weeklyCostOfMagazine;
    }

    /**
     * Constructor with name, weekly cost and list of supplement of magazine.
     * For usage in event that the list of paying customer wasn't available at instance of creation.
     *
     * @param nameOfMagazine  : String containing name of magazine
     * @param weeklyCostOfMagazine : Integer storing weekly cost of main magazine
     * @param listOfSupplementMagazines :List of supplement magazine under the main magazine.
     */
    public Magazine(String nameOfMagazine, int weeklyCostOfMagazine, ArrayList<Supplement> listOfSupplementMagazines) {
        this.nameOfMagazine = nameOfMagazine;
        this.weeklyCostOfMagazine = weeklyCostOfMagazine;
        this.listOfSupplementMagazines = listOfSupplementMagazines;
    }

    /**
     * Full Constructor, for usage when all information are ready at first instance of creation
     *
     * @param nameOfMagazine  : String containing name of magazine
     * @param weeklyCostOfMagazine : Integer storing weekly cost of magazine
     * @param listOfSupplementMagazines :List of supplement magazine under the main magazine.
     * @param listOfPayingCustomer :List of paying customer subscribed to the magazine
     */
    public Magazine(String nameOfMagazine, int weeklyCostOfMagazine, ArrayList<Supplement> listOfSupplementMagazines,ArrayList<PayingCustomer> listOfPayingCustomer) {
        this.nameOfMagazine = nameOfMagazine;
        this.weeklyCostOfMagazine = weeklyCostOfMagazine;
        this.listOfSupplementMagazines = listOfSupplementMagazines;
        this.listOfPayingCustomer = listOfPayingCustomer;
    }

    /**
     * Getter for name of magazine
     * @return nameOfMagazine = Name of Magazine
     */
    public String getNameOfMagazine() {
        return nameOfMagazine;
    }

    /**
     * Setter for name of magazine.
     * Magazine name variable will set/replaced by the string variable provided in parameter.
     * @param nameOfMagazine String containing name of magazine.
     */
    public void setNameOfMagazine(String nameOfMagazine) {
        this.nameOfMagazine = nameOfMagazine;
    }

    /**
     * Getter for weekly cost of magazine.
     * @return weeklyCostOfMagazine = Cost of magazine.
     */
    public int getWeeklyCostOfMagazine() {
        return weeklyCostOfMagazine;
    }

    /**
     * Setter for weekly cost of magazine.
     * Magazine weekly cost property set/replaced by the integer variable provided in parameter.
     * @param weeklyCostOfMagazine Integer storing weekly cost of magazine.
     */
    public void setWeeklyCostOfMagazine(int weeklyCostOfMagazine) {
        this.weeklyCostOfMagazine = weeklyCostOfMagazine;
    }

    /**
     * Getter for the list of supplement under this magazine.
     * @return listOfSupplementMagazines = List of supplements listed under this magazine.
     */
    public ArrayList<Supplement> getListOfSupplementMagazines() {
        return listOfSupplementMagazines;
    }

    /**
     * Setter for magazine List of Supplement.
     * @param listOfSupplementMagazines Array list containing list of supplement.
     */
    public void setListOfSupplementMagazines(ArrayList<Supplement> listOfSupplementMagazines) {
        this.listOfSupplementMagazines = listOfSupplementMagazines;
    }

    /**
     * Getter for the list of paying customer subscribed to the magazine.
     * @return listOfCustomer = List of paying customer subscribed to the magazine.
     */
    public ArrayList<PayingCustomer> getListOfPayingCustomer() {
        return listOfPayingCustomer;
    }

    /**
     * Setter for magazine List of paying customer subscribed.
     * @param listOfPayingCustomer Array list containing list of paying customer.
     */
    public void setListOfPayingCustomer(ArrayList<PayingCustomer> listOfPayingCustomer) {
        this.listOfPayingCustomer = listOfPayingCustomer;
    }

    /**
     * Function to print on-screen, info of all variables of magazine.
     * <p>Name, weekly cost of magazine.</p>
     * <p>All details of all supplement under the magazine.</p>
     * <p>All details of all paying customer subscribed to the magazine.</p>
     */
    public void display(){
        //print out core magazine info
        System.out.println("Name of magazine = " + nameOfMagazine);
        System.out.println("Weekly Cost of magazine = " + weeklyCostOfMagazine);

        //print out list of supplement
        /*
        if(listOfSupplementMagazines.size() > 0){
            for (int i=0; i<listOfSupplementMagazines.size(); i++){
                Supplement tempSupplement = listOfSupplementMagazines.get(i);
                System.out.println("Name of supplement magazine " + (i+1) + " = " + tempSupplement.getNameOfSupplement());
                System.out.println("Cost of supplement magazine " + (i+1) + " = " + tempSupplement.getWeeklyCostOfSupplement());
            }
        }*/
        if(listOfSupplementMagazines.size()>0){
            for (Supplement supplement: listOfSupplementMagazines) {
                supplement.display();
            }
        }

        //print out list of paying customer
        if(listOfPayingCustomer.size()>0){
            for (int i = 0; i < listOfPayingCustomer.size(); i ++ ) {
                //System.out.println();//formatting
                //System.out.println();//formatting
                System.out.println(); //formatting purpose
                System.out.println("Paying Customer " + (i+1));
                listOfPayingCustomer.get(i).display();
            }
        }
    }


    /**
     * function to generate and prepare the weekly email content of magazine
     */
    public void weeklyEmailMessageGeneration (){
        //check if magazine contain more than 1 customer
        if(listOfPayingCustomer.size()>0){
            for (int i = 0; i < listOfPayingCustomer.size(); i ++ ) {
                System.out.println();//formatting
                PayingCustomer tempPayingCust = listOfPayingCustomer.get(i);
                monthlyEmailPrinting(tempPayingCust);
                System.out.println();//formatting

                //check if paying customer contain associate customer under their account
                if(tempPayingCust.getListOfAssociateCustomer().size() > 0) {
                    //if yes, loop through the list and print out the email for associate customer as well
                    for (int j = 0; j < tempPayingCust.getListOfAssociateCustomer().size(); j++) {
                        System.out.println();//formatting
                        monthlyEmailPrinting(tempPayingCust.getListOfAssociateCustomer().get(j));
                    }
                }
            }
            System.out.println();//formatting
            System.out.println();//formatting
        }
    }

    /**
     * function to print on-screen the weekly email customer and supplement list of magazine
     * @param customerToPrint The customer object to extract the details and print on screen
     */
    private void monthlyEmailPrinting(Customer customerToPrint){

        System.out.println("Dear " + customerToPrint.getCustomerName() + ",");
        System.out.println("Your weekly subscription of magazine is ready for reading.");

        if(customerToPrint.getCustomerInterestedSupplement().size() > 0) {
            System.out.println("The supplement you are subscribe to are: ");
            for (int j = 0; j < customerToPrint.getCustomerInterestedSupplement().size(); j++) {
                Supplement tempCustomerSupplement = customerToPrint.getCustomerInterestedSupplement().get(j);
                String tempSupplement = tempCustomerSupplement.getNameOfSupplement();
                System.out.println("Name of supplement magazine " + (j + 1) + " = " + tempSupplement);
            }
        }else{
            System.out.println("You are currently not subscribe to any supplement.");
        }
    }


    /**
     * Unit testing for magazine class
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


        //creating a new magazine
        System.out.println("Testing 1: Creating a magazine object with the new temporary array list");
        Magazine magazineA = new Magazine("National Geography", 10, tempListOfSupplementMagazines);
        //display the new magazine content
        System.out.println("Displaying all the content of the magazine object");
        magazineA.display();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        System.out.println("Testing 2: Creating a magazine object with the new temporary customer array list");
        Magazine magazineB = new Magazine("Science Geography", 20);
        //display the new magazine content
        System.out.println("Displaying all the content of the magazine object");
        magazineB.display();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        //creating 2 paying customer for testing
        System.out.println("Testing: Creating 2 paying customer");
        associateCustomer tempAssociateCust = new associateCustomer("Stella", "Stella@Gmail.com",91 ," Jones Street" , "Sydney",2081);
        ArrayList<associateCustomer> tempAssociateList = new ArrayList<associateCustomer>();
        tempAssociateList.add(tempAssociateCust);

        PayingCustomer tempPayingA = new PayingCustomer("Willie", "williecwy134@gmail.com",91 , " Jones Street" , "Sydney",2081, "Debit" ,tempListOfSupplementMagazines);
        PayingCustomer tempPayingB = new PayingCustomer("Unhomer", "Unhomer@gmail.com",13 , " Jones Street" , "Sydney",2020,"credit card" ,tempListOfSupplementMagazines);
        //creating a temporary array list
        System.out.println("Testing : Adding all 2 paying customer into temporary array list");
        ArrayList<PayingCustomer> tempListofCustomer = new ArrayList<PayingCustomer>();
        //add the 2 associate customer into the temporary array list
        tempListofCustomer.add(tempPayingA);
        tempListofCustomer.add(tempPayingB);
        System.out.println("Testing: Displaying content of temporary array list");
        tempListofCustomer.get(0).display();
        tempListofCustomer.get(1).display();
        //formatting purpose
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();


        System.out.println("Testing 3: Creating a magazine object with the new temporary array list");
        Magazine magazineC = new Magazine("Galaxy Geography", 20,tempListOfSupplementMagazines, tempListofCustomer);
        //display the new magazine content
        System.out.println("Displaying all the content of the magazine object");
        magazineC.display();
        //System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();



        System.out.println("Testing 4: Creating a magazine object with the new temporary array list");
        //display the new magazine content
        System.out.println("Printing the email content of the magazine subscription");
        magazineC.weeklyEmailMessageGeneration();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }



}
