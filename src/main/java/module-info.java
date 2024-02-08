module logic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports adapter;
    opens adapter to javafx.fxml;

    exports view;
    opens view to javafx.fxml;

    exports controllerapplicativo;
    opens controllerapplicativo to javafx.fxml;

    exports model;
    opens model to javafx.fxml;

    exports bean;
    opens bean to javafx.fxml;

    // exports per i pacchetti specifici, se necessario
    // exports bean.utentebean;
    // opens bean.utentebean to javafx.fxml;
    // exports bean.mangabean;
    // opens bean.mangabean to javafx.fxml;

    exports dao;
    opens dao to javafx.fxml;


}
