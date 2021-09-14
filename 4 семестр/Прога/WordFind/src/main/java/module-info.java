module org.maxim {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.maxim.controllers to javafx.fxml;
    exports org.maxim;
}