module lk.ijse.layardproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.layardproject to javafx.fxml;
    exports lk.ijse.layardproject;
}