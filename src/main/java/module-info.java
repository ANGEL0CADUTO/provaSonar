module com.example.demoproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demoproject to javafx.fxml;
   // exports com.example.demoproject;
    exports com.example.demoproject.adapter;
    opens com.example.demoproject.adapter to javafx.fxml;
    exports com.example.demoproject.view;
    opens com.example.demoproject.view to javafx.fxml;
    exports com.example.demoproject.controllerapplicativo;
    opens com.example.demoproject.controllerapplicativo to javafx.fxml;
    exports com.example.demoproject.model;
    opens com.example.demoproject.model to javafx.fxml;
    exports com.example.demoproject.bean;
    opens com.example.demoproject.bean to javafx.fxml;
//    exports com.example.demoproject.bean.utentebean;
//    opens com.example.demoproject.bean.utentebean to javafx.fxml;
//    exports com.example.demoproject.bean.mangabean;
//    opens com.example.demoproject.bean.mangabean to javafx.fxml;
    exports com.example.demoproject.dao;
    opens com.example.demoproject.dao to javafx.fxml;
}