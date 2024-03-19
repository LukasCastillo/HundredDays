module hundreddays {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.collections4;

    opens hundreddays.controllers to javafx.fxml;
    
    exports hundreddays;
}
