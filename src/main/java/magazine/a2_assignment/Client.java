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
import javafx.scene.text.TextAlignment;
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
        Text header = new Text("Magazine Services" + magazine.getNameOfMagazine());
        header.setX(220);
        header.setY(16);
        header.setFont(Font.font("Verdana", 15));
        header.setFill(Color.DARKCYAN);
        header.setTextAlignment(TextAlignment.CENTER);
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
        Label selectedItemProperty1 = new Label();
        Label selectedItemProperty2 = new Label();
        Label selectedItemProperty3 = new Label();
        Label selectedItemProperty4 = new Label();
        Label selectedItemProperty5 = new Label();
        Label selectedItemProperty6 = new Label();
        Label selectedItemProperty7 = new Label();
        Label selectedItemProperty8 = new Label();
        Label selectedItemProperty9 = new Label();
        Label selectedItemProperty10 = new Label();


        selectedItemProperty1.setFont(Font.font("Verdana", 18));
        selectedItemProperty2.setFont(Font.font("Verdana", 18));
        selectedItemProperty3.setFont(Font.font("Verdana", 18));
        selectedItemProperty4.setFont(Font.font("Verdana", 18));
        selectedItemProperty5.setFont(Font.font("Verdana", 18));
        selectedItemProperty6.setFont(Font.font("Verdana", 18));
        selectedItemProperty7.setFont(Font.font("Verdana", 18));
        selectedItemProperty8.setFont(Font.font("Verdana", 18));
        selectedItemProperty9.setFont(Font.font("Verdana", 18));
        selectedItemProperty10.setFont(Font.font("Verdana", 18));
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
                try{
                    Supplement currentSupplement = supplementListView.getSelectionModel().getSelectedItem();
                    selectedItemProperty1.setText("Supplement Name = \n" + currentSupplement.getNameOfSupplement());
                    selectedItemProperty2.setText("\nPrice of supplement = $" + currentSupplement.getWeeklyCostOfSupplement());
                    selectedItemProperty3.setText("");
                    selectedItemProperty4.setText("");
                    selectedItemProperty5.setText("");
                    selectedItemProperty6.setText("");
                    selectedItemProperty7.setText("");
                    selectedItemProperty8.setText("");
                    selectedItemProperty9.setText("");
                    selectedItemProperty10.setText("");
                }catch(NullPointerException e){
                    System.out.println("Null Pointer exception caught at supplment change.");
                }

            }});
        supplementListView.setPrefHeight(348);
        supplementListView.setPrefWidth(308);
        supplementListView.setLayoutX(0);
        supplementListView.setLayoutY(94);
        supplementListView.setOpacity(0.8);
        viewSceneObj.getChildren().add(supplementListView);
        infoPanel.getChildren().addAll(infoPanelTitle, selectedItemProperty1,selectedItemProperty2,selectedItemProperty3,selectedItemProperty4,selectedItemProperty5,selectedItemProperty6, selectedItemProperty7, selectedItemProperty8,selectedItemProperty9,selectedItemProperty10);
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
                try{
                    Customer currentCustomer = customersListView.getSelectionModel().getSelectedItem();
                    selectedItemProperty1.setText("Customer Name = \n" + currentCustomer.getCustomerName());
                    selectedItemProperty2.setText("\nCustomer Email = " + currentCustomer.getCustomerEmail());
                    selectedItemProperty3.setText("\nCustomer Address = " + "\n" + (currentCustomer.getStreetNumber() + " " + currentCustomer.getStreetName() + " " + currentCustomer.getCountryState() + " " + currentCustomer.getPostalCode()));
                    //if(customer != null || t1 !=null){
                    //paying or associate customer
                    if(currentCustomer instanceof PayingCustomer){
                        selectedItemProperty4.setText("Paying Customer");
                    }else{
                        selectedItemProperty4.setText("Associate Customer");
                    }
                    //list of subscription
                    selectedItemProperty5.setText("List of Subscribed Supplement Magazine:");
                    selectedItemProperty6.setText("");
                    selectedItemProperty7.setText("");
                    selectedItemProperty8.setText("");
                    selectedItemProperty9.setText("");
                    selectedItemProperty10.setText("");
                    if(currentCustomer.getCustomerInterestedSupplement().size()==1){
                        selectedItemProperty6.setText(currentCustomer.getCustomerInterestedSupplement().get(0).getNameOfSupplement());
                    }else if(currentCustomer.getCustomerInterestedSupplement().size()==2){
                        selectedItemProperty6.setText(currentCustomer.getCustomerInterestedSupplement().get(0).getNameOfSupplement());
                        selectedItemProperty7.setText(currentCustomer.getCustomerInterestedSupplement().get(1).getNameOfSupplement());
                    }else if(currentCustomer.getCustomerInterestedSupplement().size()==3){
                        selectedItemProperty6.setText(currentCustomer.getCustomerInterestedSupplement().get(0).getNameOfSupplement());
                        selectedItemProperty7.setText(currentCustomer.getCustomerInterestedSupplement().get(1).getNameOfSupplement());
                        selectedItemProperty8.setText(currentCustomer.getCustomerInterestedSupplement().get(2).getNameOfSupplement());
                    }else if(currentCustomer.getCustomerInterestedSupplement().size()==4){
                        selectedItemProperty6.setText(currentCustomer.getCustomerInterestedSupplement().get(0).getNameOfSupplement());
                        selectedItemProperty7.setText(currentCustomer.getCustomerInterestedSupplement().get(1).getNameOfSupplement());
                        selectedItemProperty8.setText(currentCustomer.getCustomerInterestedSupplement().get(2).getNameOfSupplement());
                        selectedItemProperty9.setText(currentCustomer.getCustomerInterestedSupplement().get(3).getNameOfSupplement());
                    }else if(currentCustomer.getCustomerInterestedSupplement().size()==5){
                        selectedItemProperty6.setText(currentCustomer.getCustomerInterestedSupplement().get(0).getNameOfSupplement());
                        selectedItemProperty7.setText(currentCustomer.getCustomerInterestedSupplement().get(1).getNameOfSupplement());
                        selectedItemProperty8.setText(currentCustomer.getCustomerInterestedSupplement().get(2).getNameOfSupplement());
                        selectedItemProperty9.setText(currentCustomer.getCustomerInterestedSupplement().get(3).getNameOfSupplement());
                        selectedItemProperty10.setText(currentCustomer.getCustomerInterestedSupplement().get(4).getNameOfSupplement());
                    }else{
                        System.out.println("nothing to change! No supplement at all");
                        System.out.println("Number of supplement under this customer = " + currentCustomer.getCustomerInterestedSupplement().size());
                    }
                }catch(NullPointerException e){
                    System.out.println("Nullpointer exception caught at customer change");
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
        ToggleGroup customerTypeGroup = new ToggleGroup();
        RadioButton payingCustButton = new RadioButton("Paying Customer");
        payingCustButton.setToggleGroup(customerTypeGroup);
        payingCustButton.setSelected(true);
        RadioButton associateCustButton = new RadioButton("Associate Customer");
        associateCustButton.setToggleGroup(customerTypeGroup);
        HBox customerType = new HBox(payingCustButton, associateCustButton);
        //if paying then set visible for field of credit or debit? -> Another radio button
        ObservableList<PayingCustomer> payingCustomersList = FXCollections.observableArrayList();
        ChoiceBox<PayingCustomer> choiceOfPayingCustomerToAdd = new ChoiceBox<>(payingCustomersList);
        Label payingCustomerToAddUnder = new Label("Paying Customer a/c (Associate Customer addition only)");
        Button refreshSelection = new Button("Reset Paying Customer Choice");

        HBox payingCustomerAdditionLevel = new HBox(payingCustomerToAddUnder, choiceOfPayingCustomerToAdd, refreshSelection);
        payingCustomerAdditionLevel.setSpacing(15);

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



        VBox customerSequence=new VBox(customerCreationMsg, customerNameLevel,CustomerAddress,  StreetNumberLevel, streetNameLevel, stateNameLevel, postalCodeLevel, customerEmailLevel, choiceOfSupplement,supplementChoiceOptions, customerType, payingCustomerAdditionLevel, customerSubmitAndAddButton,customerSubmitAndCompleteButton,endCustomerAddition);
        customerSequence.setSpacing(20);
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




        //create edit scene
        //create option scene to choose between editing - Magazine / supplement / Customer
        //create option screen (for use if user already create magazine - allow user to only choose create supplement or customer
        Button editMagazine = new Button("Magazine Edit");
        editMagazine.setMinSize(200,200);
        Button editSupplement = new Button("Supplement Edit");
        editSupplement.setMinSize(200,200);
        Button editCustomer = new Button("Customer Edit");
        editCustomer.setMinSize(200,200);

        HBox editScreenChoice = new HBox(editMagazine, editSupplement, editCustomer);
        editScreenChoice.setAlignment(Pos.CENTER);
        editScreenChoice.setSpacing(70);
        Scene editOptionScene = new Scene(editScreenChoice, Color.DARKSEAGREEN);

        //create edit magazine scene
        Label editMagazineName = new Label("Magazine Name:");
        editMagazineName.setFont(Font.font("Verdana", 16));
        TextField editMagazineNameField = new TextField();
        Label editMagazineCost = new Label("Magazine Weekly Cost:");
        editMagazineCost.setFont(Font.font("Verdana", 16));
        TextField editMagazineCostField = new TextField();
        HBox editMagazienNameLevel = new HBox(editMagazineName,editMagazineNameField);
        editMagazienNameLevel.setSpacing(70);
        HBox editMagazieCostLevel = new HBox(editMagazineCost,editMagazineCostField);
        editMagazieCostLevel.setSpacing(30);
        Button saveMagzineChanges = new Button("Save Changes");
        saveMagzineChanges.setMinSize(100,100);
        Button exitMagazineChanges = new Button("Exit to main screen");
        exitMagazineChanges.setMinSize(100,100);
        HBox editMagazineButtonLevel = new HBox(saveMagzineChanges,exitMagazineChanges);
        editMagazineButtonLevel.setSpacing(30);
        editMagazineButtonLevel.setAlignment(Pos.CENTER_LEFT);
        VBox editMagazineRoot = new VBox(editMagazienNameLevel, editMagazieCostLevel, editMagazineButtonLevel);
        editMagazineRoot.setSpacing(30);
        editMagazineRoot.setAlignment(Pos.CENTER_LEFT);
        Scene editMagazineScene = new Scene(editMagazineRoot);


        //create edit supplement scene
        Label supplementEditMsg = new Label("Supplement Edit Screen");
        supplementEditMsg.setFont(Font.font("Verdana", 20));

        ChoiceBox<Supplement> supplementEditChoice = new ChoiceBox<>(supplementList);
        Button loadSupplementChoice = new Button("Load Supplement Data");
        loadSupplementChoice.setMinSize(100,100);
        HBox supplementChoiceLevel = new HBox(supplementEditChoice, loadSupplementChoice);
        supplementChoiceLevel.setSpacing(30);

        Label editSupplementName = new Label("Supplement Name:");
        editSupplementName.setFont(Font.font("Verdana", 16));
        TextField editSupplementNameField = new TextField();
        Label editSupplementCost = new Label("Supplement Weekly Cost:");
        editSupplementCost.setFont(Font.font("Verdana", 16));
        TextField editSupplementCostField = new TextField();
        HBox editSupplementNameLevel = new HBox(editSupplementName,editSupplementNameField);
        editSupplementNameLevel.setSpacing(70);
        HBox editSupplementCostLevel = new HBox(editSupplementCost,editSupplementCostField);
        editSupplementCostLevel.setSpacing(30);

        Button saveSupplementChanges = new Button("Save Changes");
        saveSupplementChanges.setMinSize(100,100);
        Button deleteSupplement = new Button("Delete Supplement Selected");
        deleteSupplement.setMinSize(100,100);
        Button exitSupplementChanges = new Button("Exit to main screen");
        exitSupplementChanges.setMinSize(100,100);
        HBox editSupplementButtonLevel = new HBox(saveSupplementChanges,deleteSupplement, exitSupplementChanges);
        editSupplementButtonLevel.setSpacing(50);
        editSupplementButtonLevel.setAlignment(Pos.CENTER_LEFT);


        VBox editSupplementRoot = new VBox(supplementEditMsg, supplementChoiceLevel, editSupplementNameLevel, editSupplementCostLevel, editSupplementButtonLevel);
        editSupplementRoot.setSpacing(30);
        editSupplementRoot.setAlignment(Pos.CENTER_LEFT);
        Scene editSupplementScene = new Scene(editSupplementRoot);


        //create edit customer scene
        Label customerEditMsg = new Label("Customer Edit Screen");
        customerEditMsg.setFont(Font.font("Verdana", 20));

        ChoiceBox<Customer> customerEditChoice = new ChoiceBox<>(customersList);
        Button loadCustomerChoice = new Button("Load Customer Data");
        loadCustomerChoice.setMinSize(100,100);
        HBox customerChoiceLEvel = new HBox(customerEditChoice, loadCustomerChoice);
        customerChoiceLEvel.setSpacing(30);

        Label editCustomerName = new Label("Customer Name");
        TextField editCustomerNameField = new TextField("");
        Label editCustomerAddress = new Label("Customer Address:");
        Label editCustomerStreetNumber = new Label("Street Number");
        TextField editCustomerStreetNumberField = new TextField("");
        Label editCustomerStreetName = new Label("Street Name");
        TextField editCustomerStreetNameField = new TextField("");
        Label editCustomerStateName = new Label("State Name");
        TextField editCustomerStateNameField = new TextField("");
        Label editCustomerPostalCode = new Label("Postal Code");
        TextField editCustomerPostalCodeField = new TextField("");
        Label editCustomerEmail = new Label("Email Address");
        TextField editCustomerEmailField = new TextField("");
        Label editCustomerType = new Label("Customer Type = ");

        Button editCustomerSaveButton = new Button("Save Changes");
        Button editCustomerDeleteButton = new Button("Delete Customer Selected");
        Button editCustomerExitButton = new Button("Back to Main Screen");
        HBox editCustomerButtonLevel = new HBox(editCustomerSaveButton,editCustomerDeleteButton, editCustomerExitButton);
        editCustomerButtonLevel.setSpacing(50);
        editCustomerButtonLevel.setAlignment(Pos.CENTER_LEFT);

        HBox editCustomerNameLevel = new HBox(editCustomerName,editCustomerNameField);
        editCustomerNameLevel.setSpacing(30);
        HBox editStreetNumberLevel = new HBox(editCustomerStreetNumber,editCustomerStreetNumberField);
        editStreetNumberLevel.setSpacing(40);
        HBox editStreetNameLevel = new HBox(editCustomerStreetName,editCustomerStreetNameField);
        editStreetNameLevel.setSpacing(52);
        HBox editStateNameLevel = new HBox(editCustomerStateName,editCustomerStateNameField);
        editStateNameLevel.setSpacing(55);
        HBox editPostalCodeLevel = new HBox(editCustomerPostalCode,editCustomerPostalCodeField);
        editPostalCodeLevel.setSpacing(55);
        HBox editCustomerEmailLevel = new HBox(editCustomerEmail,editCustomerEmailField);
        editCustomerEmailLevel.setSpacing(44);



        VBox editCustomerSequence=new VBox(customerEditMsg,customerChoiceLEvel, editCustomerNameLevel, editCustomerAddress, editStreetNumberLevel, editStreetNameLevel,
                editStateNameLevel,  editPostalCodeLevel, editCustomerEmailLevel, editCustomerType, editCustomerButtonLevel);
        editCustomerSequence.setSpacing(20);
        //editCustomerSequence.getChildren().add(customerSequence);
        Scene editCustomerScene = new Scene(editCustomerSequence,400,400, Color.DARKSEAGREEN);


        loadCustomerChoice.setOnMouseClicked(event -> {
            loadCustomerEditData(customerEditChoice, customerEditMsg, editCustomerNameField, editCustomerStreetNumberField,
                    editCustomerStreetNameField, editCustomerStateNameField, editCustomerPostalCodeField, editCustomerEmailField, editCustomerType);
        });

        editCustomerSaveButton.setOnMouseClicked(event -> {
            saveCustomerData(customerEditChoice, customerEditMsg, editCustomerNameField, editCustomerStreetNumberField,
                    editCustomerStreetNameField, editCustomerStateNameField, editCustomerPostalCodeField, editCustomerEmailField, editCustomerType);
        });

        editCustomerDeleteButton.setOnMouseClicked(event -> {
            Alert deleteCustomerAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteCustomerAlert.setTitle("Delete Customer data");
            deleteCustomerAlert.setHeaderText("Do you want to delete selected customer data?");
            deleteCustomerAlert.setContentText("Note: If paying customer deleted, all associate customer under this account will be deleted too.");

            if (deleteCustomerAlert.showAndWait().get() == ButtonType.OK) {
                deleteCustomer(customerEditChoice);
                clearEditCustomerData(customerEditChoice, customerEditMsg, editCustomerNameField, editCustomerStreetNumberField,
                        editCustomerStreetNameField, editCustomerStateNameField, editCustomerPostalCodeField, editCustomerEmailField, editCustomerType);
                extractCustomerList(customersList, payingCustomersList); //reload the list everytime there is addition
            } else {
                event.consume();
                customerEditMsg.setText("Deletion did not occur");
            }
        });

        editCustomerExitButton.setOnMouseClicked(event -> {
            clearEditCustomerData(customerEditChoice, customerEditMsg, editCustomerNameField, editCustomerStreetNumberField,
                    editCustomerStreetNameField, editCustomerStateNameField, editCustomerPostalCodeField, editCustomerEmailField, editCustomerType);
            stage.setScene(viewScene);
        });


        editCustomer.setOnMouseClicked(event -> {
            stage.setScene(editCustomerScene);
        });

        editSupplement.setOnMouseClicked(event -> {
            stage.setScene(editSupplementScene);
        });

        loadSupplementChoice.setOnMouseClicked(event -> {
            for(int i = 0; i<magazine.getListOfSupplementMagazines().size(); i++) {
                if (magazine.getListOfSupplementMagazines().get(i) == supplementEditChoice.getValue()) {
                    System.out.println("Supplement entry found");
                    System.out.println("Supplement current name = " + magazine.getListOfSupplementMagazines().get(i).getNameOfSupplement());
                    System.out.println("Supplement current cost = " + magazine.getListOfSupplementMagazines().get(i).getWeeklyCostOfSupplement());
                    editSupplementNameField.setText(magazine.getListOfSupplementMagazines().get(i).getNameOfSupplement());
                    editSupplementCostField.setText("" + magazine.getListOfSupplementMagazines().get(i).getWeeklyCostOfSupplement());
                    System.out.println("Supplement loaded");
                    supplementEditMsg.setText("Supplement Data Loaded");
                }
            }
        });

        saveSupplementChanges.setOnMouseClicked(event -> {
            int result = findSupplement(editSupplementNameField, editSupplementCostField,  supplementEditChoice);
            if(result>-1){
                saveSupplementData(result, editSupplementNameField, editSupplementCostField, supplementEditChoice);
                supplementEditMsg.setText("Supplement Changes Saved");
            }else{
                event.consume();
            }
        });

        deleteSupplement.setOnMouseClicked(event -> {
            Alert deleteSupplementAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteSupplementAlert.setTitle("Delete Supplement data");
            deleteSupplementAlert.setHeaderText("Do you want to delete selected supplement data?");
            //alert.setContentText("Do you want to save before exiting?");

            if (deleteSupplementAlert.showAndWait().get() == ButtonType.OK){
                int result = findSupplement(editSupplementNameField, editSupplementCostField,  supplementEditChoice);
                if(result>-1){
                    deleteSupplementData(result, editSupplementNameField, editSupplementCostField, supplementEditChoice);
                    clearSupplementEditField(editSupplementNameField, editSupplementCostField, supplementEditChoice);
                    extractSupplementList(supplementList);
                    supplementEditMsg.setText("Supplement Deleted");
                }else{
                    event.consume();
                    supplementEditMsg.setText("Deletion did not occur");
                }
            }else{
                supplementEditMsg.setText("Deletion Cancelled");
                event.consume();
            }
        });

        exitSupplementChanges.setOnMouseClicked(event -> {
            clearSupplementEditField(editSupplementNameField, editSupplementCostField, supplementEditChoice);
            stage.setScene(viewScene);
        });

        saveMagzineChanges.setOnMouseClicked(event -> {
            magazine.setNameOfMagazine(editMagazineNameField.getText());
            magazine.setWeeklyCostOfMagazine(Integer.parseInt(editMagazineCostField.getText()));
            header.setText("Magazine Name = " + magazine.getNameOfMagazine() + ".\nWeekly cost of magazine = " + magazine.getWeeklyCostOfMagazine());
            //stage.setScene(editMagazineScene);
        });

        exitMagazineChanges.setOnMouseClicked(event -> {
            stage.setScene(viewScene);
        });

        editMagazine.setOnMouseClicked(event -> {
            editMagazineNameField.setText(magazine.getNameOfMagazine());
            editMagazineCostField.setText(String.valueOf(magazine.getWeeklyCostOfMagazine()));
            stage.setScene(editMagazineScene);
        });

        loadSavedData.setOnMouseClicked(event -> {
            Alert loadAlert = new Alert(Alert.AlertType.CONFIRMATION);
            loadAlert.setTitle("Load data");
            loadAlert.setHeaderText("Do you want to load save data?");
            //alert.setContentText("Do you want to save before exiting?");

            if (loadAlert.showAndWait().get() == ButtonType.OK){
                loadData();
                //saveData();
                extractSupplementList(supplementList);
                extractCustomerList(customersList,payingCustomersList);
                stage.setScene(viewScene); //switch scene once data loaded
                System.out.println("Name of magazine from top = " + magazine.getNameOfMagazine()); //testing purpose, to test if it got pass back to the calling function
            }else{
                event.consume();
            }
            header.setText("Magazine Name = " + magazine.getNameOfMagazine() + ".\nWeekly cost of magazine = " + magazine.getWeeklyCostOfMagazine());
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

        edit.setOnMouseClicked(event -> {
                stage.setScene(editOptionScene); //switch to "edit option" scene
        });

        createMagazineSubmitButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createMagazineSubmit(labelToDisplayMsg, magazineNameField, magazineCostField);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created magazine name = " + magazine.getNameOfMagazine());
                System.out.println("Created magazine cost = " + magazine.getWeeklyCostOfMagazine());

                extractSupplementList(supplementList);
                extractCustomerList(customersList,payingCustomersList);
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
                clearSupplementField(supplementCreationMsg, supplementNameField, supplementCostField);
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
                clearSupplementField(supplementCreationMsg, supplementNameField, supplementCostField);
                stage.setScene(viewScene); //switch to "view" scene
            }else{
                System.out.println("Proceed failed");
            }
        });

        endSupplementAddition.setOnMouseClicked(event -> {
            clearSupplementField(supplementCreationMsg, supplementNameField, supplementCostField);
            stage.setScene(viewScene); //switch to "view" scene
        });

        endCustomerAddition.setOnMouseClicked(event -> {
            clearCustomerField(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                    customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                    supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5,choiceOfPayingCustomerToAdd);
            stage.setScene(viewScene); //switch to "view" scene
        });

        customerSubmitAndAddButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createCustomerSubmit(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                     customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                     supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5, payingCustButton, associateCustButton,choiceOfPayingCustomerToAdd);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created Customer name = " + customerNameField.getText());
                System.out.println("Created Customer email = " + customerEmailField.getText());
                extractCustomerList(customersList,payingCustomersList); //reload the list everytime there is addition
                clearCustomerField(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                        customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                        supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5,choiceOfPayingCustomerToAdd);
            }else{
                System.out.println("Proceed failed");
            }
        });

        customerSubmitAndCompleteButton.setOnMouseClicked(event -> {
            boolean toProceed = false;
            toProceed = createCustomerSubmit(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                    customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                    supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5, payingCustButton, associateCustButton,choiceOfPayingCustomerToAdd);
            if(toProceed){
                System.out.println("Proceed successful");
                System.out.println("Created Customer name = " + customerNameField.getText());
                System.out.println("Created Customer email = " + customerEmailField.getText());
                extractCustomerList(customersList,payingCustomersList); //reload the list everytime a customer added
                clearCustomerField(customerCreationMsg, customerNameField, CustomerStreetNumberField,
                        customerStreetNameField,  customerStateNameField, customerPostalCodeField,  customerEmailField, supplementChoiceBox1,
                        supplementChoiceBox2,  supplementChoiceBox3, supplementChoiceBox4, supplementChoiceBox5,choiceOfPayingCustomerToAdd);
                stage.setScene(viewScene);
            }else{
                System.out.println("Proceed failed");
            }
        });

        refreshSelection.setOnMouseClicked(event -> {
            choiceOfPayingCustomerToAdd.setValue(null);
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




    private void deleteCustomer (ChoiceBox<Customer> customerEditChoice){
        for(int i = 0; i<magazine.getListOfPayingCustomer().size(); i++) {
            if (magazine.getListOfPayingCustomer().get(i) == customerEditChoice.getValue()) {
                System.out.println("Customer entry found");
                magazine.getListOfPayingCustomer().remove(i);
                System.out.println("Paying Customer entry deleted");
                return;
            }
            else{
                for(int j=0; j<magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().size(); j++){
                    if(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j) == customerEditChoice.getValue()){
                        System.out.println("Customer entry found");
                        magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().remove(j);
                        System.out.println("Associate Customer entry deleted");
                        return;
                    }
                }
            }
        }
    }

    private void clearEditCustomerData(ChoiceBox<Customer> customerEditChoice, Label customerEditMsg, TextField editCustomerNameField, TextField editCustomerStreetNumberField,
                                  TextField editCustomerStreetNameField, TextField editCustomerStateNameField,
                                  TextField editCustomerPostalCodeField, TextField editCustomerEmailField, Label editCustomerType){
        customerEditChoice.setValue(null);
        customerEditMsg.setText("");
        editCustomerNameField.setText("");
        editCustomerStreetNumberField.setText("");
        editCustomerStreetNameField.setText("");
        editCustomerStateNameField.setText("");
        editCustomerPostalCodeField.setText("");
        editCustomerEmailField.setText("");
        editCustomerType.setText("Customer Type = ");
        System.out.println("All Edit Field cleared");
    }

    private void saveCustomerData(ChoiceBox<Customer> customerEditChoice, Label customerEditMsg, TextField editCustomerNameField, TextField editCustomerStreetNumberField,
                                  TextField editCustomerStreetNameField, TextField editCustomerStateNameField,
                                  TextField editCustomerPostalCodeField, TextField editCustomerEmailField, Label editCustomerType){

        int streetNumber;
        int postalCode;

        try {
            streetNumber = Integer.parseInt(editCustomerStreetNumberField.getText());
            postalCode = Integer.parseInt(editCustomerPostalCodeField.getText());

            if(editCustomerNameField.getText().trim().isEmpty()){
                customerEditMsg.setText("Customer Name cannot be empty");
            }
            else if(editCustomerStreetNumberField.getText().trim().isEmpty() || streetNumber <= 0){
                customerEditMsg.setText("Street Number cannot be empty or less than 1");
            }
            else if(editCustomerStreetNameField.getText().trim().isEmpty()){
                customerEditMsg.setText("Street Name cannot be empty");
            }
            else if(editCustomerStateNameField.getText().trim().isEmpty()){
                customerEditMsg.setText("State Name cannot be empty");
            }
            else if(editCustomerPostalCodeField.getText().trim().isEmpty() || postalCode <= 0){
                customerEditMsg.setText("Postal Code cannot be empty or less than 1");
            }
            else if(editCustomerEmailField.getText().trim().isEmpty()){
                customerEditMsg.setText("Email Address cannot be empty");
            }else{
                for(int i = 0; i<magazine.getListOfPayingCustomer().size(); i++) {
                    if (magazine.getListOfPayingCustomer().get(i) == customerEditChoice.getValue()) {
                        System.out.println("Customer entry found");
                        customerEditMsg.setText("Customer Data Changes Saved");
                        magazine.getListOfPayingCustomer().get(i).setCustomerName(editCustomerNameField.getText());
                        magazine.getListOfPayingCustomer().get(i).setStreetNumber(Integer.parseInt(editCustomerStreetNumberField.getText()));
                        magazine.getListOfPayingCustomer().get(i).setStreetName(editCustomerStreetNameField.getText());
                        magazine.getListOfPayingCustomer().get(i).setCountryState(editCustomerStateNameField.getText());
                        magazine.getListOfPayingCustomer().get(i).setPostalCode(Integer.parseInt(editCustomerPostalCodeField.getText()));
                        magazine.getListOfPayingCustomer().get(i).setCustomerEmail(editCustomerEmailField.getText());
                        editCustomerType.setText("Customer Type = Paying Customer");
                        System.out.println("Paying Customer entry Updated");
                        return;
                    }
                    else{
                        for(int j=0; j<magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().size(); j++){
                            if(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j) == customerEditChoice.getValue()){
                                customerEditMsg.setText("Customer Data Changes Saved");
                                magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).setCustomerName(editCustomerNameField.getText());
                                magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).setStreetNumber(Integer.parseInt(editCustomerStreetNumberField.getText()));
                                magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).setStreetName(editCustomerStreetNameField.getText());
                                magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).setCountryState(editCustomerStateNameField.getText());
                                magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).setPostalCode(Integer.parseInt(editCustomerPostalCodeField.getText()));
                                magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).setCustomerEmail(editCustomerEmailField.getText());
                                editCustomerType.setText("Customer Type = Associate Customer");
                                System.out.println("Associate Customer entry Updated");
                                return;
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            customerEditMsg.setText("Enter only number in Street number & Postal code");
        } catch (NullPointerException ex){
            System.out.println("End of list adding");
        } catch (Exception e) {
            customerEditMsg.setText("error");
        }



    }


    private void loadCustomerEditData(ChoiceBox<Customer> customerEditChoice, Label customerEditMsg, TextField editCustomerNameField, TextField editCustomerStreetNumberField,
                                      TextField editCustomerStreetNameField, TextField editCustomerStateNameField,
                                      TextField editCustomerPostalCodeField, TextField editCustomerEmailField, Label editCustomerType){

        for(int i = 0; i<magazine.getListOfPayingCustomer().size(); i++) {
            if (magazine.getListOfPayingCustomer().get(i) == customerEditChoice.getValue()) {
                System.out.println("Customer entry found");
                customerEditMsg.setText("Paying Customer Data Loaded");
                editCustomerNameField.setText(magazine.getListOfPayingCustomer().get(i).getCustomerName());
                editCustomerStreetNumberField.setText(""+magazine.getListOfPayingCustomer().get(i).getStreetNumber());
                editCustomerStreetNameField.setText(magazine.getListOfPayingCustomer().get(i).getStreetName());
                editCustomerStateNameField.setText(magazine.getListOfPayingCustomer().get(i).getCountryState());
                editCustomerPostalCodeField.setText(""+magazine.getListOfPayingCustomer().get(i).getPostalCode());
                editCustomerEmailField.setText(magazine.getListOfPayingCustomer().get(i).getCustomerEmail());
                editCustomerType.setText("Customer Type = Paying Customer");
                System.out.println("Customer loaded");
                return;
            }
            else{
                for(int j=0; j<magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().size(); j++){
                    if(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j) == customerEditChoice.getValue()){
                        System.out.println("Customer entry found");
                        customerEditMsg.setText("Associate Customer Data Loaded");
                        editCustomerNameField.setText(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).getCustomerName());
                        editCustomerStreetNumberField.setText(""+magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).getStreetNumber());
                        editCustomerStreetNameField.setText(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).getStreetName());
                        editCustomerStateNameField.setText(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).getCountryState());
                        editCustomerPostalCodeField.setText(""+magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).getPostalCode());
                        editCustomerEmailField.setText(magazine.getListOfPayingCustomer().get(i).getListOfAssociateCustomer().get(j).getCustomerEmail());
                        editCustomerType.setText("Customer Type = Associate Customer");
                        System.out.println("Customer loaded");
                        return;
                    }
                }
            }
        }
    }



    private int findSupplement (TextField editSupplementNameField, TextField editSupplementCostField, ChoiceBox<Supplement> supplementEditChoice){
        int positionOfSupplementFound = -1;
        for(int i = 0; i<magazine.getListOfSupplementMagazines().size(); i++){
            if(magazine.getListOfSupplementMagazines().get(i) == supplementEditChoice.getValue()){
                positionOfSupplementFound = i;

                System.out.println("Supplement change match found");
            }
        }
        return positionOfSupplementFound;
    }

    private void saveSupplementData(int positionOfSupplementFound,TextField editSupplementNameField, TextField editSupplementCostField, ChoiceBox<Supplement> supplementEditChoice){
        System.out.println("Supplement old name = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getNameOfSupplement());
        System.out.println("Supplement old cost = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getWeeklyCostOfSupplement());
        magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).setNameOfSupplement(editSupplementNameField.getText());
        magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).setWeeklyCostOfSupplement(Integer.parseInt(editSupplementCostField.getText()));
        System.out.println("Supplement change saved");
        System.out.println("Supplement new name = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getNameOfSupplement());
        System.out.println("Supplement new cost = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getWeeklyCostOfSupplement());
    }

    private void deleteSupplementData(int positionOfSupplementFound, TextField editSupplementNameField, TextField editSupplementCostField, ChoiceBox<Supplement> supplementEditChoice){
        System.out.println("Supplement old name = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getNameOfSupplement());
        System.out.println("Supplement old cost = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getWeeklyCostOfSupplement());
        magazine.getListOfSupplementMagazines().remove(positionOfSupplementFound);
        System.out.println("Supplement change removed");
        try{
            System.out.println("Supplement new name = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getNameOfSupplement());
            System.out.println("Supplement new cost = " + magazine.getListOfSupplementMagazines().get(positionOfSupplementFound).getWeeklyCostOfSupplement());
        }catch (IndexOutOfBoundsException e){
            System.out.println("No more item at this position, deleted item was at end of the line");
        }
    }


    private void clearSupplementEditField (TextField editSupplementNameField, TextField editSupplementCostField, ChoiceBox<Supplement> supplementEditChoice) {
        editSupplementNameField.setText("");
        editSupplementCostField.setText("");
        supplementEditChoice.setValue(null);
    }

    private void clearCustomerField (Label msgToDisplay, TextField customerNameField, TextField CustomerStreetNumberField,
                                     TextField customerStreetNameField, TextField customerStateNameField,
                                     TextField customerPostalCodeField, TextField customerEmailField, ChoiceBox<Supplement> supplementChoiceBox1,
                                     ChoiceBox<Supplement> supplementChoiceBox2, ChoiceBox<Supplement> supplementChoiceBox3,
                                     ChoiceBox<Supplement> supplementChoiceBox4, ChoiceBox<Supplement> supplementChoiceBox5,ChoiceBox<PayingCustomer> choiceOfPayingCustomerToAdd) {
        msgToDisplay.setText("");
        customerNameField.setText("");
        CustomerStreetNumberField.setText("");
        customerStreetNameField.setText("");
        customerStateNameField.setText("");
        customerPostalCodeField.setText("");
        customerEmailField.setText("");
        msgToDisplay.setText("");
        supplementChoiceBox1.setValue(null);
        supplementChoiceBox2.setValue(null);
        supplementChoiceBox3.setValue(null);
        supplementChoiceBox4.setValue(null);
        supplementChoiceBox5.setValue(null);
        choiceOfPayingCustomerToAdd.setValue(null);
    }

    public boolean createCustomerSubmit (Label msgToDisplay, TextField customerNameField, TextField CustomerStreetNumberField,
                                         TextField customerStreetNameField, TextField customerStateNameField,
                                         TextField customerPostalCodeField, TextField customerEmailField, ChoiceBox<Supplement> supplementChoiceBox1,
                                         ChoiceBox<Supplement> supplementChoiceBox2, ChoiceBox<Supplement> supplementChoiceBox3,
                                         ChoiceBox<Supplement> supplementChoiceBox4, ChoiceBox<Supplement> supplementChoiceBox5,
                                         RadioButton payingCustButton, RadioButton associateCustButton, ChoiceBox<PayingCustomer> choiceOfPayingCustomerToAdd) {
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
            }else if(associateCustButton.isSelected() && choiceOfPayingCustomerToAdd.getValue() == null){
                msgToDisplay.setText("Paying Customer Selection cannot be empty when adding Associate Customer");
            }else if(payingCustButton.isSelected() && choiceOfPayingCustomerToAdd.getValue() != null){
                msgToDisplay.setText("Paying Customer Selection not required when adding new Paying Customer");
            }
            else{
                //public PayingCustomer(String customerName, String customerEmail, Integer streetNumber, String streetName, String countryState, Integer postalCode, String paymentMethod) {
                System.out.println("Customer Create committed");
                ArrayList<Supplement> tempSupplementList = new ArrayList<>();

                if(!supplementChoiceBox1.getSelectionModel().isEmpty() || !supplementChoiceBox2.getSelectionModel().isEmpty() || !supplementChoiceBox3.getSelectionModel().isEmpty()
                        || !supplementChoiceBox4.getSelectionModel().isEmpty() || !supplementChoiceBox5.getSelectionModel().isEmpty()){

                    if(!supplementChoiceBox1.getSelectionModel().isEmpty()) {
                        tempSupplementList.add(supplementChoiceBox1.getValue());
                        System.out.println("Name of supplement 1 added = " + supplementChoiceBox1.getValue().getNameOfSupplement());
                    }if(!supplementChoiceBox2.getSelectionModel().isEmpty()){
                        tempSupplementList.add(supplementChoiceBox2.getValue());
                        System.out.println("Name of supplement 2 added = " + supplementChoiceBox2.getValue().getNameOfSupplement());
                    }if(!supplementChoiceBox3.getSelectionModel().isEmpty()){
                        tempSupplementList.add(supplementChoiceBox3.getValue());
                        System.out.println("Name of supplement 3 added = " + supplementChoiceBox3.getValue().getNameOfSupplement());
                    }if(!supplementChoiceBox4.getSelectionModel().isEmpty()){
                        tempSupplementList.add(supplementChoiceBox4.getValue());
                        System.out.println("Name of supplement 4 added = " + supplementChoiceBox4.getValue().getNameOfSupplement());
                    }if(!supplementChoiceBox5.getSelectionModel().isEmpty()) {
                        tempSupplementList.add(supplementChoiceBox5.getValue());
                        System.out.println("Name of supplement 5 added = " + supplementChoiceBox5.getValue().getNameOfSupplement());
                    }
                    if(payingCustButton.isSelected()){
                        magazine.getListOfPayingCustomer().add(new PayingCustomer(customerNameField.getText().trim(),
                                customerEmailField.getText().trim(),
                                streetNumber,
                                customerStreetNameField.getText().trim(),
                                customerStateNameField.getText().trim(),
                                postalCode,
                                new String("Paying"),tempSupplementList
                        ));
                    }else if(associateCustButton.isSelected()){
                        for(int i = 0; i<magazine.getListOfPayingCustomer().size(); i++){
                            if(magazine.getListOfPayingCustomer().get(i) == choiceOfPayingCustomerToAdd.getValue()){
                                System.out.println("Paying Customer Match found");
                                magazine.getListOfPayingCustomer().get(0).getListOfAssociateCustomer().add(new associateCustomer(customerNameField.getText().trim(),
                                        customerEmailField.getText().trim(),
                                        streetNumber,
                                        customerStreetNameField.getText().trim(),
                                        customerStateNameField.getText().trim(),
                                        postalCode, tempSupplementList
                                ));
                            }
                        }
                    }
                }
                else{
                    if(payingCustButton.isSelected()){
                        magazine.getListOfPayingCustomer().add(new PayingCustomer(customerNameField.getText().trim(),
                                customerEmailField.getText().trim(),
                                streetNumber,
                                customerStreetNameField.getText().trim(),
                                customerStateNameField.getText().trim(),
                                postalCode,
                                new String("Paying")
                        ));
                    }else if(associateCustButton.isSelected()){
                        for(int i = 0; i<magazine.getListOfPayingCustomer().size(); i++){
                            if(magazine.getListOfPayingCustomer().get(i) == choiceOfPayingCustomerToAdd.getValue()){
                                magazine.getListOfPayingCustomer().get(0).getListOfAssociateCustomer().add(new associateCustomer(customerNameField.getText().trim(),
                                        customerEmailField.getText().trim(),
                                        streetNumber,
                                        customerStreetNameField.getText().trim(),
                                        customerStateNameField.getText().trim(),
                                        postalCode
                                ));
                            }
                        }
                    }
                }
                return true;
            }
        } catch (NumberFormatException e) {
            msgToDisplay.setText("Enter only number in Street number & Postal code");
        } catch (NullPointerException ex){
            System.out.println("End of list adding");
        } catch (Exception e) {
            msgToDisplay.setText("error");
        }
        return false;
    }

    private void clearSupplementField (Label msgToDisplay, TextField supplementNameField, TextField supplementCostField) {
        supplementNameField.setText("");
        supplementCostField.setText("");
        msgToDisplay.setText("");
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


    private static void extractCustomerList(ObservableList<Customer> listOfCustomer, ObservableList<PayingCustomer> payingCustomersList){
        if(magazine.getListOfPayingCustomer().size() > 0){
            //empty the list first
            System.out.println("Clearing Customer ObservableList");
            listOfCustomer.clear();
            payingCustomersList.clear();
            for(Customer customer: magazine.getListOfPayingCustomer()){
                if(customer != null){
                    if(customer instanceof PayingCustomer){
                        listOfCustomer.add(customer);
                        payingCustomersList.add((PayingCustomer) customer);
                        System.out.println(customer.getCustomerName());
                    }
                    if(customer instanceof PayingCustomer && ((PayingCustomer) customer).getListOfAssociateCustomer().size()>0){
                        listOfCustomer.addAll(((PayingCustomer) customer).getListOfAssociateCustomer());
                    }
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
