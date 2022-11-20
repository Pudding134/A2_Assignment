package magazine.a2_assignment;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class Client extends Application {

    private static Magazine magazine = new Magazine();

    @Override
    public void start(Stage stage) throws Exception {
        //1) set up the stage
        stage.setTitle("Magazine Management System");
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setX(1052);
        stage.setY(650);

        //stage-program icon setting
        Image systemIcon = new Image("MagazineIcon.jpg");
        stage.getIcons().add(systemIcon);
        //initial loading scene setup
        Button loadSavedData = new Button("Load Existing Data");
        loadSavedData.setMinSize(100,100);
        Button newDataCreation = new Button("Create a new Magazine");
        newDataCreation.setMinSize(100,100);
        VBox initialScreenChoice = new VBox(loadSavedData, newDataCreation);
        initialScreenChoice.setAlignment(Pos.CENTER);
        initialScreenChoice.setSpacing(80);
        Scene loadingScene = new Scene(initialScreenChoice, 400,400, Color.DARKGOLDENROD);


        //2)Set up the scene and scene graph
        //set up a root node that store all the common to all 3 scene properties (reduce adding)
        Group root = new Group();
        //add all view scene exclusive item to this node
        Group viewSceneObj = new Group();
        viewSceneObj.getChildren().add(root);
        //create scene with root node
        Scene viewScene = new Scene(viewSceneObj,400,400, Color.DARKSEAGREEN);

        //set up and add menu bar to root
        Text header = new Text("Magazine Services");
        header.setX(80);
        header.setY(28);
        header.setFont(Font.font("Verdana", 25));
        header.setFill(Color.DARKCYAN);
        root.getChildren().add(header);

        //create a logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setOnMouseClicked(mouseEvent -> logout(stage));
        root.getChildren().add(logoutButton);

        //Creating a line to seperate header and scene chooser button
        Line line = new Line();
        line.setStartX(0.0);
        line.setStartY(40.0);
        line.setEndX(400.0);
        line.setEndY(40.0);
        line.setStrokeWidth(4);
        line.setStroke(Color.gray(0.3));
        line.setOpacity(0.5);
        root.getChildren().add(line);

        //HBox root = new HBox();
        //set up the top menu bar
        //create list of button to hold the 3 main features scene/screen
        ButtonBar sceneChooser = new ButtonBar();
        Button view = new Button("View");
        Button create = new Button("Create");
        Button edit = new Button("Edit");
        //add all button to button bar
        sceneChooser.getButtons().addAll(view,create,edit);
        sceneChooser.setLayoutX(60);
        sceneChooser.setLayoutY(50);
        root.getChildren().add(sceneChooser);
        //Creating a line object
        Line line2 = new Line();
        line2.setStartX(0.0);
        line2.setStartY(90);
        line2.setEndX(400.0);
        line2.setEndY(90);
        line2.setStrokeWidth(4);
        line2.setStroke(Color.gray(0.3));
        line2.setOpacity(0.5);
        root.getChildren().add(line2);

        //create the information panel and it's respective label
        VBox infoPanel = new VBox();
        Label infoPanelTitle = new Label("Information Panel:");
        infoPanelTitle.setFont(Font.font("Verdana", 16));
        infoPanelTitle.setUnderline(true);
        Label selectedItem1stProperty = new Label();
        Label selectedItem2ndProperty = new Label();
        Label selectedItem3rdProperty = new Label();
        Label selectedItem4thProperty = new Label();
        Label selectedItem5thProperty = new Label();
        Label selectedItem6thProperty = new Label();
        infoPanel.setAlignment(Pos.TOP_LEFT);
        infoPanel.setLayoutX(204);
        infoPanel.setLayoutY(100);

        //set up the List of supplement (Observable = it will update with changes - think of it as a pointer, reflecting with changes to original object)
        ObservableList<Supplement> supplementList = FXCollections.observableArrayList();
        ListView<Supplement> supplementListView = new ListView<>(supplementList);
        supplementListView.setCellFactory(param -> new ListCell<Supplement>() {
            @Override
            protected void updateItem(Supplement item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getNameOfSupplement() == null) {
                    setText(null);
                } else {
                    setText(item.getNameOfSupplement());
                }
            }
        });
        supplementListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplement>() {
            @Override
            public void changed(ObservableValue<? extends Supplement> observableValue, Supplement supplement, Supplement t1) {
                Supplement currentSupplement = supplementListView.getSelectionModel().getSelectedItem();
                selectedItem1stProperty.setText("Supplement Name = \n" + currentSupplement.getNameOfSupplement());
                selectedItem2ndProperty.setText("\nPrice of supplement = $" + currentSupplement.getWeeklyCostOfSupplement());
                selectedItem3rdProperty.setText("");
                selectedItem4thProperty.setText("");
                selectedItem5thProperty.setText("");
                selectedItem6thProperty.setText("");
            }});
        supplementListView.setPrefHeight(140);
        supplementListView.setPrefWidth(200);
        supplementListView.setLayoutX(0);
        supplementListView.setLayoutY(94);
        supplementListView.setOpacity(0.8);
        viewSceneObj.getChildren().add(supplementListView);
        infoPanel.getChildren().addAll(infoPanelTitle, selectedItem1stProperty,selectedItem2ndProperty,selectedItem3rdProperty,selectedItem4thProperty,selectedItem5thProperty,selectedItem6thProperty);
        viewSceneObj.getChildren().add(infoPanel);

        //Set up the list of customers
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        //load observable list of customer to the list view
        ListView<Customer> customersListView = new ListView<>(customersList);
        customersListView.setCellFactory(param -> new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getCustomerName() == null) {
                    setText(null);
                } else {
                    setText(item.getCustomerName());
                }
            }
        });
        customersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer t1) {
                Customer currentCustomer = customersListView.getSelectionModel().getSelectedItem();
                selectedItem1stProperty.setText("Customer Name = \n" + currentCustomer.getCustomerName());
                selectedItem2ndProperty.setText("\nCustomer Email = " + currentCustomer.getCustomerEmail());
                selectedItem3rdProperty.setText("\nCustomer Address = " + "\n" + (currentCustomer.getStreetNumber() + " " + currentCustomer.getStreetName() + " " + currentCustomer.getCountryState() + " " + currentCustomer.getPostalCode()));
                //list of subscription
                selectedItem4thProperty.setText("");
                //paying or associate customer
                if(currentCustomer instanceof PayingCustomer){
                    selectedItem5thProperty.setText("Paying Customer");
                }else{
                    selectedItem5thProperty.setText("Associate Customer");
                }
                selectedItem6thProperty.setText("");
            }});
        customersListView.setPrefHeight(140);
        customersListView.setPrefWidth(200);
        customersListView.setLayoutX(0);
        customersListView.setLayoutY(244);
        customersListView.setOpacity(0.8);
        viewSceneObj.getChildren().add(customersListView); //add to viewSceneExclusive node


        //set up line to separate both list
        Line line3 = new Line();
        line3.setStartX(0.0);
        line3.setStartY(240);
        line3.setEndX(200.0);
        line3.setEndY(240);
        line3.setStrokeWidth(4);
        line3.setStroke(Color.gray(0.3));
        line3.setOpacity(0.5);
        viewSceneObj.getChildren().add(line3);
        //set up vertical line to separate information panel
        Line line4 = new Line();
        line4.setStartX(200.0);
        line4.setStartY(94);
        line4.setEndX(200.0);
        line4.setEndY(400);
        line4.setStrokeWidth(4);
        line4.setStroke(Color.gray(0.3));
        line4.setOpacity(0.5);
        viewSceneObj.getChildren().add(line4);




        //setting up create scene
        //add all view scene exclusive item to this node
        Group createSceneObj = new Group();
        //create scene with root node
        Label labelToDisplayMsg = new Label();
        labelToDisplayMsg.setFont(Font.font("Verdana", 16));
        Label magazineName = new Label("Magazine Name");
        TextField magazineNameField = new TextField("");
        Label magazineCost = new Label("Magazine Weekly Cost");
        TextField magazineCostField = new TextField("");
        Button createSubmitButton = new Button("Submit");
        HBox magazineNameLevel = new HBox(magazineName,magazineNameField);
        magazineNameLevel.setSpacing(56);
        HBox magazineCostLevel = new HBox(magazineCost, magazineCostField);
        magazineCostLevel.setSpacing(20);

        VBox createSequence=new VBox(labelToDisplayMsg, magazineNameLevel,magazineCostLevel, createSubmitButton);
        createSequence.setSpacing(40);
        createSceneObj.getChildren().add(createSequence);
        Scene createScene = new Scene(createSceneObj,400,400, Color.DARKSEAGREEN);









        loadSavedData.setOnMouseClicked(event -> {
            Alert loadAlert = new Alert(Alert.AlertType.CONFIRMATION);
            loadAlert.setTitle("Load data");
            loadAlert.setHeaderText("Do you want to load save data?");
            //alert.setContentText("Do you want to save before exiting?");

            if (loadAlert.showAndWait().get() == ButtonType.OK){
                loadData();
                //saveData();
                extractSupplementList(supplementList);
                extractCustomerList(customersList);
                stage.setScene(viewScene); //switch scene once data loaded
                System.out.println("Name of magazine from top = " + magazine.getNameOfMagazine()); //testing purpose, to test if it got pass back to the calling function
            }else{
                event.consume();
            }
        });

        newDataCreation.setOnMouseClicked(event -> {
            Alert createAlert = new Alert(Alert.AlertType.CONFIRMATION);
            createAlert.setTitle("Confirmation to proceed");
            createAlert.setHeaderText("Proceed with creating a new magazine??");
            //alert.setContentText("Do you want to save before exiting?");

            if (createAlert.showAndWait().get() == ButtonType.OK){
                stage.setScene(createScene);
                System.out.println("Name of magazine from top = " + magazine.getNameOfMagazine()); //testing purpose, to test if it got pass back to the calling function
            }else{
                event.consume();
            }

        });

        view.setOnMouseClicked(event -> {
            stage.setScene(viewScene); //switch to "view" scene
        });

        create.setOnMouseClicked(event -> {
            stage.setScene(createScene); //switch to "create" scene
        });

        createSubmitButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createMagazineSubmit(labelToDisplayMsg, magazineNameField, magazineCostField);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created magazine name = " + magazine.getNameOfMagazine());
                System.out.println("Created magazine cost = " + magazine.getWeeklyCostOfMagazine());

                extractSupplementList(supplementList);
                extractCustomerList(customersList);
                stage.setScene(viewScene);
            }else{
                System.out.println("Proceed failed");
            }
        });

        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage);
        });

        stage.setScene(loadingScene);
        stage.show();
    }






        public boolean createMagazineSubmit(Label msgToDisplay, TextField magazineNameField, TextField magazineCostField) {
            int magazineCost;
            try {
                magazineCost = Integer.parseInt(magazineCostField.getText());

                if(magazineNameField.getText().trim().isEmpty()){
                    msgToDisplay.setText("Magazine Name cannot be empty");
                }
                if (magazineCost <= 0) {
                    msgToDisplay.setText("Cost of magazine must be more than $0");
                }
                if(!magazineNameField.getText().trim().isEmpty() && magazineCost > 0){
                    System.out.println("Create committed");
                    magazine.setNameOfMagazine(magazineNameField.getText());
                    magazine.setWeeklyCostOfMagazine(magazineCost);
                    return true;
                }
            } catch (NumberFormatException e) {
                msgToDisplay.setText("Enter only numbers in Magazine Cost Field");
            } catch (Exception e) {
                msgToDisplay.setText("error");
            }
            return false;
        }




    public static void main(String[] args) {
        launch();
    }


    /**
     * This method is conduct the deserialization of saved magazine and related data from previous instance
     * <p>
     * Precondition: Nil <br>
     * Postcondition: All previous magazine data are loaded into the magazine
     */
    public static void loadData() {
        //Testing out deserialization
        System.out.println("Initializing Data reading from existing save file....");
        try {
            //read file contain object byte stream data
            FileInputStream fileIn = new FileInputStream("MagazineSaveData.ser");
            //read object byte stream data in file
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            //load it into the temporary array list while casting the object type
            magazine = (Magazine) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Name of actual magazine read =" + magazine.getNameOfMagazine());
        } catch (IOException io) {
            System.out.println("Error in reading Save file");
        } catch (ClassNotFoundException CE) {
            System.out.println("Class Not Found Exception found");
        }

        System.out.println("Data reading from existing save file COMPLETED!");
        //System.out.println("Number of Magazine entries loaded = " + listOfMagazine.size());
        try {
            if (magazine.getNameOfMagazine().length() > 0) {
                System.out.println("Name of magazines read from the file: ");
                System.out.println();
            }
        }catch (NullPointerException NE){
            System.out.println("No data loaded from the save file");
            System.out.println();
        }
        System.out.println();
        //transfer the array list from the temporary to the main one use by the entire program
        //FinalListOfMagazine = listOfMagazine;
    }


    private static void extractSupplementList(ObservableList<Supplement> listOfSupplement){
        if(magazine.getListOfSupplementMagazines().size() > 0){
            for(Supplement supplement: magazine.getListOfSupplementMagazines()){
                listOfSupplement.addAll(supplement);
            }
        }
    }


    private static void extractCustomerList(ObservableList<Customer> listOfCustomer){
        if(magazine.getListOfPayingCustomer().size() > 0){
            for(Customer customer: magazine.getListOfPayingCustomer()){
                if(customer instanceof PayingCustomer){
                    listOfCustomer.add(customer);
                    System.out.println(customer.getCustomerName());
                }
                if(customer instanceof PayingCustomer && ((PayingCustomer) customer).getListOfAssociateCustomer().size()>0){
                    listOfCustomer.addAll(((PayingCustomer) customer).getListOfAssociateCustomer());
                }
            }
        }
    }


    /**
     * This method is conduct the serialization of all magazine and related data from current running instance
     * <p>
     * Precondition: Nil <br>
     * Postcondition: All current and latest magazine data in the array list are saved into the object file.
     */
    public static void saveData(){
        System.out.println("Initializing Data saving");
        try{
            FileOutputStream fileOut = new FileOutputStream("MagazineSaveData.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(magazine);
            objectOut.close();
            fileOut.close();
            System.out.println("Data saving completed");

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void logout(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully logged out");
            stage.close();
        }
    }


}
