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
        // Leggi il contenuto del file di testo
        String notifica = readFile("NotificaFile.txt");


        notificationLabel.setText(notifica);
    }

    private String readFile(String fileNotifica) {
        StringBuilder notifica = new StringBuilder();
        try {
            File file = new File(fileNotifica);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                notifica.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notifica.toString();
    }

}
