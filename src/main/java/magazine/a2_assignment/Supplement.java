package magazine.a2_assignment;

import java.io.*;

/**
 * @author  Willie Chong Wei Yi (williecwy134@gmail.com)
 * @version 1.0
 * @since   2022-Oct-17
 */

public class Supplement implements Serializable{

    /**
     * String variable storing name of the supplement magazine
     */
    private String nameOfSupplement;

    /**
     * Integer variable storing cost of the supplement magazine
     */
    private int weeklyCostOfSupplement;

    /**
     * Constructor
     *
     * @param nameOfSupplement  : String containing name of supplement magazine
     * @param weeklyCostOfSupplement : Integer storing weekly cost of supplement magazine
     * Postcondition: Magazine object constructed
     */
    //constructor
    public Supplement(String nameOfSupplement, int weeklyCostOfSupplement) {
        this.nameOfSupplement = nameOfSupplement;
        this.weeklyCostOfSupplement = weeklyCostOfSupplement;
    }


    /**
     * Getter for name of supplement magazine
     * @return nameOfSupplement
     */
    public String getNameOfSupplement() {
        return nameOfSupplement;
    }

    /**
     * Setter for name of supplement magazine
     * @param nameOfSupplement String containing name of supplement
     */
    public void setNameOfSupplement(String nameOfSupplement) {
        this.nameOfSupplement = nameOfSupplement;
    }

    /**
     * Getter for cost of supplement magazine
     * @return weeklyCostOfSupplement
     */
    public int getWeeklyCostOfSupplement() {
        return weeklyCostOfSupplement;
    }

    /**
     * Setter for name of supplement magazine
     * @param weeklyCostOfSupplement Integer containing weekly cost of supplement
     */
    public void setWeeklyCostOfSupplement(int weeklyCostOfSupplement) {
        this.weeklyCostOfSupplement = weeklyCostOfSupplement;
    }

    /**
     * function to print on-screen info of all variables of Supplement magazine
     */
    public void display (){
        System.out.println("Name of supplement = " + nameOfSupplement);
        System.out.println("Weekly Cost of supplement = " + weeklyCostOfSupplement);
    }

    /**
     * Unit testing for Supplement class
     */
    public static void main(String[] args){
        Supplement supplementA = new Supplement("Earth Geography", 2);
        supplementA.display();
        Supplement supplementB = new Supplement("Animal Geography", 5);
        supplementB.display();
    }

}
