package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NotificheControllerGrafico {

    @FXML
    private Label notificationLabel;


    public void initialize() {
        // Leggi il contenuto del file JSON
        String notifica = readFile("NotificaFile.json");

        // Aggiorna l'etichetta con la notifica
        notificationLabel.setText(notifica);
    }

    private String readFile(String filePath) {
        StringBuilder jsonContent = new StringBuilder();
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return jsonContent.toString();
    }

}
