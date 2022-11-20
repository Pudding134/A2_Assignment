package magazine.a2_assignment;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Client extends Application {

    private static Magazine magazine = new Magazine();

    @Override
    public void start(Stage stage) throws Exception {
        //1) set up the stage
        stage.setTitle("Magazine Management System");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setX(1052);
        stage.setY(100);
        stage.setResizable(false);


        //stage-program icon setting
        Image systemIcon = new Image("MagazineIcon.jpg");
        stage.getIcons().add(systemIcon);
        //initial loading scene setup
        Button loadSavedData = new Button("Load Existing Data");
        loadSavedData.setMinSize(200,200);
        Button newDataCreation = new Button("Create a new Magazine");
        newDataCreation.setMinSize(200,200);
        VBox initialScreenChoice = new VBox(loadSavedData, newDataCreation);
        initialScreenChoice.setAlignment(Pos.CENTER);
        initialScreenChoice.setSpacing(80);
        Scene loadingScene = new Scene(initialScreenChoice, Color.DARKSEAGREEN);


        //2)Set up the scene and scene graph
        //set up a root node that store all the common to all 3 scene properties (reduce adding)
        Group root = new Group();
        //add all view scene exclusive item to this node
        Group viewSceneObj = new Group();
        viewSceneObj.getChildren().add(root);
        //create scene with root node
        Scene viewScene = new Scene(viewSceneObj, Color.DARKSEAGREEN);

        //set up and add menu bar to root
        Text header = new Text("Magazine Services");
        header.setX(280);
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
        line.setEndX(800.0);
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
        sceneChooser.setButtonMinWidth(200);
        root.getChildren().add(sceneChooser);
        //Creating a line object
        Line line2 = new Line();
        line2.setStartX(0.0);
        line2.setStartY(90);
        line2.setEndX(800.0);
        line2.setEndY(90);
        line2.setStrokeWidth(4);
        line2.setStroke(Color.gray(0.3));
        line2.setOpacity(0.5);
        root.getChildren().add(line2);

        //create the information panel and it's respective label
        VBox infoPanel = new VBox();
        Label infoPanelTitle = new Label("Information Panel:");
        infoPanelTitle.setFont(Font.font("Verdana", 26));
        infoPanelTitle.setUnderline(true);
        Label selectedItem1stProperty = new Label();
        Label selectedItem2ndProperty = new Label();
        Label selectedItem3rdProperty = new Label();
        Label selectedItem4thProperty = new Label();
        Label selectedItem5thProperty = new Label();
        Label selectedItem6thProperty = new Label();
        Label selectedItemProperty7 = new Label();
        Label selectedItemProperty8 = new Label();

        selectedItem1stProperty.setFont(Font.font("Verdana", 18));
        selectedItem2ndProperty.setFont(Font.font("Verdana", 18));
        selectedItem3rdProperty.setFont(Font.font("Verdana", 18));
        selectedItem4thProperty.setFont(Font.font("Verdana", 18));
        selectedItem5thProperty.setFont(Font.font("Verdana", 18));
        selectedItem6thProperty.setFont(Font.font("Verdana", 18));
        selectedItemProperty7.setFont(Font.font("Verdana", 18));
        selectedItemProperty8.setFont(Font.font("Verdana", 18));
        infoPanel.setAlignment(Pos.TOP_LEFT);
        infoPanel.setLayoutX(312);
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
                //selectedItemProperty7.setText("");
                //selectedItemProperty8.setText("");

            }});
        supplementListView.setPrefHeight(348);
        supplementListView.setPrefWidth(308);
        supplementListView.setLayoutX(0);
        supplementListView.setLayoutY(94);
        supplementListView.setOpacity(0.8);
        viewSceneObj.getChildren().add(supplementListView);
        infoPanel.getChildren().addAll(infoPanelTitle, selectedItem1stProperty,selectedItem2ndProperty,selectedItem3rdProperty,selectedItem4thProperty,selectedItem5thProperty,selectedItem6thProperty, selectedItemProperty7, selectedItemProperty8);
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
                selectedItem6thProperty.setText("aa");
                //selectedItemProperty7.setText("Empty");
                //selectedItemProperty8.setText("Empty");
                if(currentCustomer.getCustomerInterestedSupplement().size()==1){
                    selectedItemProperty7.setText(currentCustomer.getCustomerInterestedSupplement().get(0).getNameOfSupplement());
                }if(currentCustomer.getCustomerInterestedSupplement().size()==2){
                    selectedItemProperty8.setText(currentCustomer.getCustomerInterestedSupplement().get(1).getNameOfSupplement());
                }else{
                    selectedItemProperty7.setText("Empty");
                    selectedItemProperty8.setText("Empty");
                }

            }});
        customersListView.setPrefHeight(348);
        customersListView.setPrefWidth(308);
        customersListView.setLayoutX(0);
        customersListView.setLayoutY(446);
        customersListView.setOpacity(0.8);
        viewSceneObj.getChildren().add(customersListView); //add to viewSceneExclusive node


        //set up line to separate both list
        Line line3 = new Line();
        line3.setStartX(0.0);
        line3.setStartY(444);
        line3.setEndX(308);
        line3.setEndY(444);
        line3.setStrokeWidth(4);
        line3.setStroke(Color.gray(0.3));
        line3.setOpacity(0.5);
        viewSceneObj.getChildren().add(line3);
        //set up vertical line to separate information panel
        Line line4 = new Line();
        line4.setStartX(309);
        line4.setStartY(94);
        line4.setEndX(309);
        line4.setEndY(800);
        line4.setStrokeWidth(4);
        line4.setStroke(Color.gray(0.3));
        line4.setOpacity(0.5);
        viewSceneObj.getChildren().add(line4);




        //setting up create magazine scene
        Group createMagazineSceneObj = new Group();
        //create scene with root node
        Label labelToDisplayMsg = new Label();
        labelToDisplayMsg.setFont(Font.font("Verdana", 16));
        Label magazineName = new Label("Magazine Name");
        TextField magazineNameField = new TextField("");
        Label magazineCost = new Label("Magazine Weekly Cost");
        TextField magazineCostField = new TextField();
        Button createMagazineSubmitButton = new Button("Submit");
        Button backToLoadingScreenButton = new Button("Back");
        HBox magazineNameLevel = new HBox(magazineName,magazineNameField);
        magazineNameLevel.setSpacing(56);
        HBox magazineCostLevel = new HBox(magazineCost, magazineCostField);
        magazineCostLevel.setSpacing(20);

        VBox createSequence=new VBox(labelToDisplayMsg, magazineNameLevel,magazineCostLevel, createMagazineSubmitButton,backToLoadingScreenButton);
        createSequence.setSpacing(40);
        createMagazineSceneObj.getChildren().add(createSequence);
        Scene createMagazineScene = new Scene(createMagazineSceneObj, Color.DARKSEAGREEN);


        //setting up create supplement scene
        Group createSupplementSceneObj = new Group();
        //create scene with root node
        Label supplementCreationMsg = new Label();
        supplementCreationMsg.setFont(Font.font("Verdana", 16));
        Label supplementName = new Label("Supplement Name");
        TextField supplementNameField = new TextField("");
        Label supplementCost = new Label("Supplement Weekly Cost");
        TextField supplementCostField = new TextField();
        Button supplementSubmitAndAddButton = new Button("Submit and Add ");
        Button supplementSubmitAndCompleteButton = new Button("Submit and Complete Addition");
        Button endSupplementAddition = new Button("End Supplement Addition");
        HBox supplementNameLevel = new HBox(supplementName,supplementNameField);
        supplementNameLevel.setSpacing(56);
        HBox supplementCostLevel = new HBox(supplementCost, supplementCostField);
        supplementCostLevel.setSpacing(20);

        VBox supplementSequence=new VBox(supplementCreationMsg, supplementNameLevel,supplementCostLevel, supplementSubmitAndAddButton,supplementSubmitAndCompleteButton,endSupplementAddition);
        supplementSequence.setSpacing(40);
        createSupplementSceneObj.getChildren().add(supplementSequence);
        Scene createSupplementScene = new Scene(createSupplementSceneObj,400,400, Color.DARKSEAGREEN);




        //setting up create customer scene
        Group createCustomerSceneObj = new Group();
        //create scene with root node
        Label customerCreationMsg = new Label();
        customerCreationMsg.setFont(Font.font("Verdana", 16));
        Label CustomerName = new Label("Customer Name");
        TextField customerNameField = new TextField("");
        Label CustomerAddress = new Label("Customer Address:");
        Label CustomerStreetNumber = new Label("Street Number");
        TextField CustomerStreetNumberField = new TextField("");
        Label customerStreetName = new Label("Street Name");
        TextField customerStreetNameField = new TextField("");
        Label customerStateName = new Label("State Name");
        TextField customerStateNameField = new TextField("");
        Label customerPostalCode = new Label("Postal Code");
        TextField customerPostalCodeField = new TextField("");
        Label customerEmail = new Label("Email Address");
        TextField customerEmailField = new TextField("");
        //radio button for paying / associate
        //if paying then set visible for field of credit or debit? -> Another radio button


        Button customerSubmitAndAddButton = new Button("Submit and Add ");
        Button customerSubmitAndCompleteButton = new Button("Submit and Complete Addition");
        Button endCustomerAddition = new Button("End Customer Addition");

        HBox customerNameLevel = new HBox(CustomerName,customerNameField);
        customerNameLevel.setSpacing(30);
        HBox StreetNumberLevel = new HBox(CustomerStreetNumber,CustomerStreetNumberField);
        StreetNumberLevel.setSpacing(40);
        HBox streetNameLevel = new HBox(customerStreetName,customerStreetNameField);
        streetNameLevel.setSpacing(52);
        HBox stateNameLevel = new HBox(customerStateName,customerStateNameField);
        stateNameLevel.setSpacing(55);
        HBox postalCodeLevel = new HBox(customerPostalCode,customerPostalCodeField);
        postalCodeLevel.setSpacing(55);
        HBox customerEmailLevel = new HBox(customerEmail,customerEmailField);
        customerEmailLevel.setSpacing(44);

        Label choiceOfSupplement = new Label("Supplement customer subscribed to:");
        ChoiceBox<Supplement> supplementChoiceBox1 = new ChoiceBox<>(supplementList);
        ChoiceBox<Supplement> supplementChoiceBox2 = new ChoiceBox<>(supplementList);
        ChoiceBox<Supplement> supplementChoiceBox3 = new ChoiceBox<>(supplementList);
        ChoiceBox<Supplement> supplementChoiceBox4 = new ChoiceBox<>(supplementList);
        ChoiceBox<Supplement> supplementChoiceBox5 = new ChoiceBox<>(supplementList);
        VBox supplementChoiceOptions = new VBox(supplementChoiceBox1, supplementChoiceBox2, supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5);



        VBox customerSequence=new VBox(customerCreationMsg, customerNameLevel,CustomerAddress,  StreetNumberLevel, streetNameLevel, stateNameLevel, postalCodeLevel, customerEmailLevel, choiceOfSupplement,supplementChoiceOptions, customerSubmitAndAddButton,customerSubmitAndCompleteButton,endCustomerAddition);
        customerSequence.setSpacing(25);
        createCustomerSceneObj.getChildren().add(customerSequence);
        Scene createCustomerScene = new Scene(createCustomerSceneObj,400,400, Color.DARKSEAGREEN);





        //create option screen (for use if user already create magazine - allow user to only choose create supplement or customer
        Button createSupplement = new Button("Create Supplement");
        createSupplement.setMinSize(200,200);
        Button createCustomer = new Button("Create Customer");
        createCustomer.setMinSize(200,200);
        VBox createScreenChoice = new VBox(createSupplement, createCustomer);
        createScreenChoice.setAlignment(Pos.CENTER);
        createScreenChoice.setSpacing(80);
        Scene createSupCustScene = new Scene(createScreenChoice, Color.DARKSEAGREEN);








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
                stage.setScene(createMagazineScene);
                System.out.println("Name of magazine from top = " + magazine.getNameOfMagazine()); //testing purpose, to test if it got pass back to the calling function
            }else{
                event.consume();
            }

        });

        view.setOnMouseClicked(event -> {
            stage.setScene(viewScene); //switch to "view" scene
        });

        create.setOnMouseClicked(event -> {
            if(!magazine.getNameOfMagazine().isEmpty()){
                stage.setScene(createSupCustScene);
            }else{
                stage.setScene(createMagazineScene); //switch to "create" scene
            }
        });

        createMagazineSubmitButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createMagazineSubmit(labelToDisplayMsg, magazineNameField, magazineCostField);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created magazine name = " + magazine.getNameOfMagazine());
                System.out.println("Created magazine cost = " + magazine.getWeeklyCostOfMagazine());

                extractSupplementList(supplementList);
                extractCustomerList(customersList);
                stage.setScene(createSupplementScene);
            }else{
                System.out.println("Proceed failed");
            }
        });

        backToLoadingScreenButton.setOnMouseClicked(event -> {
                stage.setScene(loadingScene);
        });


        supplementSubmitAndAddButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createSupplementSubmit(supplementCreationMsg, supplementNameField, supplementCostField);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created supplement name = " + supplementNameField.getText());
                System.out.println("Created supplement name = " + supplementCostField.getText());
                extractSupplementList(supplementList);
                supplementNameField.setText("");
                supplementCostField.setText("");
                supplementCreationMsg.setText("Supplement added to list");
            }else{
                System.out.println("Proceed failed");
            }
        });

        supplementSubmitAndCompleteButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createSupplementSubmit(supplementCreationMsg, supplementNameField, supplementCostField);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created supplement name = " + supplementNameField.getText());
                System.out.println("Created supplement name = " + supplementCostField.getText());

                extractSupplementList(supplementList);
                //stage.setScene(createCustomerScene);
                stage.setScene(viewScene); //switch to "view" scene
            }else{
                System.out.println("Proceed failed");
            }
        });

        endSupplementAddition.setOnMouseClicked(event -> {
            //stage.setScene(createCustomerScene); //switch to "create" scene
            stage.setScene(viewScene); //switch to "view" scene
        });

        endCustomerAddition.setOnMouseClicked(event -> {
            stage.setScene(viewScene); //switch to "view" scene
        });

        customerSubmitAndAddButton.setOnMouseClicked(event -> {
            //System.out.println("Choice box 1 empty? = " + supplementChoiceBox1.getSelectionModel().isEmpty());
            //System.out.println("Choice box 2 empty? = " + supplementChoiceBox2.getSelectionModel().isEmpty());
            //System.out.println("Choice box 3 empty? = " + supplementChoiceBox3.getSelectionModel().isEmpty());
            //System.out.println("Choice box 4 empty? = " + supplementChoiceBox4.getSelectionModel().isEmpty());
            //System.out.println("Choice box 5 empty? = " + supplementChoiceBox5.getSelectionModel().isEmpty());

            boolean toProceed = false;
            toProceed = createCustomerSubmit(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                     customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                     supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created Customer name = " + customerNameField.getText());
                System.out.println("Created Customer email = " + customerEmailField.getText());
                extractCustomerList(customersList);
                customerCreationMsg.setText("");
                customerNameField.setText("");
                CustomerStreetNumberField.setText("");
                customerStreetNameField.setText("");
                customerStateNameField.setText("");
                customerPostalCodeField.setText("");
                customerEmailField.setText("");
                customerCreationMsg.setText("Customer added to list");
                //supplementChoiceBox1.valueProperty().set(null);
                //supplementChoiceBox2.valueProperty().set(null);
                //supplementChoiceBox3.valueProperty().set(null);
                //supplementChoiceBox4.valueProperty().set(null);
                //supplementChoiceBox5.valueProperty().set(null);
            }else{
                System.out.println("Proceed failed");
            }
        });

        customerSubmitAndCompleteButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createCustomerSubmit(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                    customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                    supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created Customer name = " + customerNameField.getText());
                System.out.println("Created Customer email = " + customerEmailField.getText());
                extractCustomerList(customersList);
                customerCreationMsg.setText("");
                customerNameField.setText("");
                CustomerStreetNumberField.setText("");
                customerStreetNameField.setText("");
                customerStateNameField.setText("");
                customerPostalCodeField.setText("");
                customerEmailField.setText("");
                stage.setScene(viewScene);
            }else{
                System.out.println("Proceed failed");
            }
        });

        createSupplement.setOnMouseClicked(event -> {
            stage.setScene(createSupplementScene); //switch to "create supplement" scene
        });

        createCustomer.setOnMouseClicked(event -> {
            stage.setScene(createCustomerScene); //switch to "create customer" scene
        });

        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage);
        });

        stage.setScene(loadingScene);
        stage.show();
    }




    public boolean createCustomerSubmit (Label msgToDisplay, TextField customerNameField, TextField CustomerStreetNumberField,
                                         TextField customerStreetNameField, TextField customerStateNameField,
                                         TextField customerPostalCodeField, TextField customerEmailField, ChoiceBox<Supplement> supplementChoiceBox1,
                                         ChoiceBox<Supplement> supplementChoiceBox2, ChoiceBox<Supplement> supplementChoiceBox3,
                                         ChoiceBox<Supplement> supplementChoiceBox4, ChoiceBox<Supplement> supplementChoiceBox5) {
        int streetNumber;
        int postalCode;

        try {
            streetNumber = Integer.parseInt(CustomerStreetNumberField.getText());
            postalCode = Integer.parseInt(customerPostalCodeField.getText());

            if(customerNameField.getText().trim().isEmpty()){
                msgToDisplay.setText("Customer Name cannot be empty");
            }
            else if(CustomerStreetNumberField.getText().trim().isEmpty() || streetNumber <= 0){
                msgToDisplay.setText("Street Number cannot be empty or less than 1");
            }
            else if(customerStreetNameField.getText().trim().isEmpty()){
                msgToDisplay.setText("Street Name cannot be empty");
            }
            else if(customerStateNameField.getText().trim().isEmpty()){
                msgToDisplay.setText("State Name cannot be empty");
            }
            else if(customerPostalCodeField.getText().trim().isEmpty() || postalCode <= 0){
                msgToDisplay.setText("Postal Code cannot be empty or less than 1");
            }
            else if(customerEmailField.getText().trim().isEmpty()){
                msgToDisplay.setText("Email Address cannot be empty");
            }
            else{
                //public PayingCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, String paymentMethod) {
                System.out.println("Customer Create committed");
                ArrayList<Supplement> tempSupplementList = new ArrayList<>();
                System.out.println("Choice box 1 empty? = " + supplementChoiceBox1.getSelectionModel().isEmpty());
                System.out.println("Choice box 2 empty? = " + supplementChoiceBox2.getSelectionModel().isEmpty());
                System.out.println("Choice box 3 empty? = " + supplementChoiceBox3.getSelectionModel().isEmpty());
                System.out.println("Choice box 4 empty? = " + supplementChoiceBox4.getSelectionModel().isEmpty());
                System.out.println("Choice box 5 empty? = " + supplementChoiceBox5.getSelectionModel().isEmpty());
                if(!supplementChoiceBox1.getSelectionModel().isEmpty()){
                    tempSupplementList.add(supplementChoiceBox1.getValue());
                    System.out.println("Name of supplement added = " + supplementChoiceBox1.getValue().getNameOfSupplement());
                    magazine.getListOfPayingCustomer().add(new PayingCustomer(customerNameField.getText().trim(),
                            customerEmailField.getText().trim(),
                            streetNumber,
                            customerStreetNameField.getText().trim(),
                            customerStateNameField.getText().trim(),
                            postalCode,
                            new String("Paying"),tempSupplementList
                    ));
                }
                else{
                    magazine.getListOfPayingCustomer().add(new PayingCustomer(customerNameField.getText().trim(),
                            customerEmailField.getText().trim(),
                            streetNumber,
                            customerStreetNameField.getText().trim(),
                            customerStateNameField.getText().trim(),
                            postalCode,
                            new String("Paying")
                    ));
                }


                return true;
            }
        } catch (NumberFormatException e) {
            msgToDisplay.setText("Enter only number in Street number & Postal code");
        } catch (NullPointerException e){
            System.out.println("End of list adding");
        } catch (Exception e) {
            msgToDisplay.setText("error");
        }
        return false;
    }



    public boolean createSupplementSubmit (Label msgToDisplay, TextField supplementNameField, TextField supplementCostField) {
        int supplementCost;
        try {
            supplementCost = Integer.parseInt(supplementCostField.getText());

            if(supplementNameField.getText().trim().isEmpty()){
                msgToDisplay.setText("Supplement Name cannot be empty");
            }
            if (supplementCost <= 0) {
                msgToDisplay.setText("Cost of supplement must be more than $0");
            }
            if(!supplementNameField.getText().trim().isEmpty() && supplementCost > 0){
                System.out.println("Create committed");
                //magazine.setNameOfMagazine(supplementNameField.getText());
                //magazine.setWeeklyCostOfMagazine(supplementCost);
                magazine.getListOfSupplementMagazines().add(new Supplement(supplementNameField.getText().trim(), supplementCost));
                return true;
            }
        } catch (NumberFormatException e) {
            msgToDisplay.setText("Enter only number in Magazine Cost Field");
        } catch (NullPointerException e){
            System.out.println("End of list adding");
        } catch (Exception e) {
            msgToDisplay.setText("error");
        }
        return false;
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
                System.out.println("Name of magazines read from the file: " + magazine.getNameOfMagazine());
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
            //empty the list first to avoid duplicate
            listOfSupplement.clear();
            for(Supplement supplement: magazine.getListOfSupplementMagazines()){
                listOfSupplement.addAll(supplement);
            }
        }
    }


    private static void extractCustomerList(ObservableList<Customer> listOfCustomer){
        if(magazine.getListOfPayingCustomer().size() > 0){
            //empty the list first
            System.out.println("Clearing ObservableList");
            listOfCustomer.clear();
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
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Logout");
        closeAlert.setHeaderText("You're about to logout! Do you want to proceed");
        //closeAlert.setContentText("Do you want to save before exiting?");



        if (closeAlert.showAndWait().get() == ButtonType.OK){
            Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
            saveAlert.setTitle("Save Data");
            saveAlert.setHeaderText("Do you want to save before exiting?");
            //saveAlert.setContentText("Do you want to save before exiting?");
            if (saveAlert.showAndWait().get() == ButtonType.OK){
                saveData();
            }
            System.out.println("You successfully logged out");
            stage.close();
        }
    }


}
