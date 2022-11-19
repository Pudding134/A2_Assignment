module magazine.a2_assignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens magazine.a2_assignment to javafx.fxml;
    exports magazine.a2_assignment;
}