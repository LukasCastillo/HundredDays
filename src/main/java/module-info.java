module hundreddays {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.collections4;
    requires com.google.gson;

    opens hundreddays.controllers to javafx.fxml;
    opens hundreddays.game to com.google.gson;
    opens hundreddays.enums to com.google.gson;

    
    exports hundreddays;
}
