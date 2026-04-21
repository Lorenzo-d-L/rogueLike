module com.ldldevelopment.roguelike {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ldldevelopment.roguelike to javafx.fxml;
    exports com.ldldevelopment.roguelike;
}