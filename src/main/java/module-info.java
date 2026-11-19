module com.iss.echoesofdeceit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.iss.echoesofdeceit to javafx.fxml;
    exports com.iss.echoesofdeceit;
    exports com.iss.echoesofdeceit.controller;
    opens com.iss.echoesofdeceit.controller to javafx.fxml;
    exports com.iss.echoesofdeceit.model;
    opens com.iss.echoesofdeceit.model to javafx.fxml;
}