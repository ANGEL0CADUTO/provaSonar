module logic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;


    exports view;
    opens view to javafx.fxml;

    exports controllerapplicativo;
    opens controllerapplicativo to javafx.fxml;

    exports model;
    opens model to javafx.fxml;



    exports dao;
    opens dao to javafx.fxml;
    exports bean;
    opens bean to javafx.fxml;

    exports  exceptions;



}
