module hundreddays {
    requires javafx.controls;
    requires javafx.fxml;

    opens hundreddays.controllers to javafx.fxml;
    
    exports hundreddays;
}
