package dao.json;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.NotificaModel;

import java.io.*;
import java.util.logging.Logger;



public class NotificheJSONDAO {
    private static final Logger logger = Logger.getLogger(NotificheJSONDAO.class.getName());

    // Modifica il tipo di ritorno da String a NotificheModel




    public NotificaModel readNotificaFromFile(String filePath) {
        StringBuilder jsonContent = new StringBuilder();
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line).append("\n");
            }

            // Converti la stringa JSON in un oggetto JSON
            JsonObject jsonObject = JsonParser.parseString(jsonContent.toString()).getAsJsonObject();


            // Ottieni l'oggetto "notifica" dal tuo JSON
            JsonObject notificaObject = jsonObject.getAsJsonObject("notifica");

            // Utilizza Gson per deserializzare il JSON in un oggetto NotificaModel
            Gson gson = new Gson();
            return gson.fromJson(notificaObject, NotificaModel.class);

        } catch (IOException e) {
            logger.severe("Errore in NotificheJSONDAO in readNotificaFromFile: " + e.getMessage());
            // Gestisci eventuali eccezioni o restituisci un valore predefinito in caso di errore
            return null;
        }
    }

    public boolean saveNotifica(String notifica, String filePath){
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(notifica);
        } catch (IOException e) {
            logger.severe("Errore durante il salvataggio del file JSON: " + e.getMessage());
        }
        return true;
    }


}
