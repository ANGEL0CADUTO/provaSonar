package controllerapplicativo;

import bean.AnnuncioBean;
import dao.AnnuncioDAO;
import dao.NotificheDAO;
import dao.UtenteDAO;

import dao.json.NotificheJSONDAO;
import model.AnnuncioModel;
import model.NotificaModel;



import java.io.FileInputStream;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CompraMangaControllerApplicativo {
    private static final String PATH = "src/main/resources/connection.properties";
    private static final Logger logger = Logger.getLogger(CompraMangaControllerApplicativo.class.getName());


    public List<AnnuncioModel> showAnnunce(int id, String name)  {
     AnnuncioDAO dao = new AnnuncioDAO();
     List<AnnuncioModel> array = dao.getAnnunci(id,name);
     UtenteDAO dao2 = new UtenteDAO();

     for(AnnuncioModel a : array){
         a.setVotoUtente(dao2.getVotoByUtenteID(a.getUtenteVenditoreID()));
     }
     return array;

    }

    public boolean saveNotifica(String notifica) {
        try (FileInputStream config = new FileInputStream(PATH)) {
            Properties properties = new Properties();
            properties.load(config);

            String persistenza = properties.getProperty("PERSISTENZA");
            if (persistenza.equals("JSON")) {
                saveToJsonFile(notifica);
            } else if (persistenza.equals("MYSQL")) {
                saveToMySQLDatabase(notifica);
            }
        } catch (IOException e) {
            logger.severe("Errore in CompraMangaControllerGrafico in saveNotifica: " + e.getMessage());
        }
        return false; // Aggiungi il valore di ritorno desiderato
    }


    public boolean saveToJsonFile(String notifica){
        NotificheJSONDAO dao = new NotificheJSONDAO();
        return dao.saveNotifica(notifica, "NotificaFile.json");


    }

    public boolean saveToMySQLDatabase(String notifica) {
        NotificheDAO dao = new NotificheDAO();


        Map<String, String> parsedData = parseNotifica(notifica);

        String utente = parsedData.get("utente");
        String volume = parsedData.get("volume");
        String venditore = parsedData.get("venditore");
        String manga = parsedData.get("manga");
        String prezzoOfferta = parsedData.get("prezzo_offerta");


        utente = parseUtente(notifica, utente);

        NotificaModel notificaModel = new NotificaModel();
        notificaModel.setUtente(utente);
        notificaModel.setVolume(Integer.parseInt(volume));
        notificaModel.setVenditore(venditore);
        notificaModel.setManga(manga);

        try {
            if (prezzoOfferta != null) {
                notificaModel.setPrezzoOfferta(Integer.parseInt(prezzoOfferta.trim().replace("\n", "")));
            }


        } catch (NumberFormatException e) {
            logger.severe("Errore durante il parsing: " + e.getMessage());
            return false;
        }

        return dao.saveNotificaInDatabase(notificaModel);
    }

    private Map<String, String> parseNotifica(String notifica) {
        Map<String, String> parsedData = new HashMap<>();
        Pattern pattern = Pattern.compile("\"([^\"]+)\": ?(?:\"([^\"]+)\"|([^,}]+))");

        try (Scanner scanner = new Scanner(notifica)) {
            while (scanner.findWithinHorizon(pattern, 0) != null) {
                MatchResult matchResult = scanner.match();
                String key = matchResult.group(1);
                String valueWithQuotes = matchResult.group(2);
                String valueWithoutQuotes = matchResult.group(3);

                parsedData.put(key, valueWithQuotes != null ? valueWithQuotes : valueWithoutQuotes);
            }
        }

        return parsedData;
    }

    private String parseUtente(String notifica, String currentUtente) {
        Pattern pattern = Pattern.compile("\"utente\"\\s*:\\s*\"([^\"]+)\"");

        try (Scanner scanner = new Scanner(notifica)) {
            while (scanner.findWithinHorizon(pattern, 0) != null) {
                MatchResult matchResult = scanner.match();
                currentUtente = matchResult.group(1);
            }
        }

        return currentUtente;
    }




    public AnnuncioBean getAnnuncioById(int idAnnuncio)  {
        AnnuncioDAO dao = new AnnuncioDAO();
        AnnuncioModel model = dao.getDatiAnnuncioByAnnuncioID(idAnnuncio);

        if (model != null) {
            AnnuncioBean bean = new AnnuncioBean();
            bean.setIdAnnuncio(model.getIdAnnuncio());
            bean.setNomeManga(model.getNomeManga());
            bean.setCopiaMangaID(model.getCopiaMangaID());
            bean.setUtenteVenditoreID(model.getUtenteVenditoreID());
            bean.setNomeUtente(model.getNomeUtente());
            bean.setVolume(model.getVolume());
            bean.setPrezzo(model.getPrezzo());
            bean.setDataAnnuncio(model.getDataAnnuncio());

            UtenteDAO dao2 = new UtenteDAO();
            bean.setVotoUtente(dao2.getVotoByUtenteID(model.getUtenteVenditoreID()));

            return bean;
        } else {
            return null;
        }
    }

}
