package com.iss.echoesofdeceit.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class SaveDataManager {
    private static final String SAVES_FOLDER = "src/main/resources/com/iss/echoesofdeceit/saves/";

    // Crea una cartella per l'utente
    public static void createUserFolder(String name) throws IOException {
        File userDir = new File(SAVES_FOLDER, name);
        if (!userDir.exists()) {
            boolean created = userDir.mkdir();
            if (!created) {
                System.out.println("Errore nella creazione della cartella utente.");
            }
        } else {
            System.out.println("La cartella dell'utente esiste già: " + userDir.getPath());
        }
    }

    // Inizializza il file dell'utente con un salvataggio iniziale
    public static void initializePlayer(String name) throws IOException {
        // Crea una mappa con il nome del giocatore come chiave
        Map<String, Map<String, Object>> playerData = new HashMap<>();

        // Popola la struttura dei dati come richiesta
        Map<String, Object> playerDetails = new HashMap<>();
        Map<String, Object> districtData = new HashMap<>();

        // Aggiorna il currentDistrict con i dati
        districtData.put("DistrictName", "historicCenter");
        districtData.put("ZoneName", "1"); // Settalo a 0 come indicato nel JSON

        // Aggiungi currentDistrict direttamente a playerDetails
        playerDetails.put("currentDistrict", districtData);

        // Set notebook data
        Map<String, Object> notebookData = new HashMap<>();
        notebookData.put("diary", "CENTRO STORICO\n" +
                "Sono arrivato a Odill con un invito che non si poteva rifiutare: ammirare il quadro simbolo della citta', quell'opera leggendaria di cui avevo sentito parlare a lungo. Ma ora, davanti a me, c'e' qualcosa di diverso, qualcosa che non torna. Il dipinto esposto e' un falso, non c'e' alcun dubbio.\n" +
                "Accanto al quadro, una lettera mi attende. Non c'e' bisogno di pensarci troppo: qualunque cosa contenga, potrebbe essere la chiave per svelare cosa sta accadendo davvero.\n");
        // Set inventory
        Map<String, Object> inventoryData = new HashMap<>();
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemArrayList.add(new Item("Mappa di Odill", "Rappresenta la citta' di Odill che e' suddivisa in sei distinti quartieri: il Centro storico, cuore antico della citta'; i Sobborghi, zone periferiche e residenziali; il Quartiere popolare, area densamente abitata da classi meno abbienti; le Accademie, sede di istituzioni culturali e universitarie; il Mercato, centro commerciale e di scambio; e il Quartiere nobiliare, residenza della nobilta' e della ricca elita' cittadina.", "MappaFinale.png"));
        inventoryData.put("items", itemArrayList);

        // Set completed puzzles
        Map<String, Boolean> completedPuzzles = new HashMap<>();
        completedPuzzles.put("1", false);
        completedPuzzles.put("2", false);
        completedPuzzles.put("3", false);


        // Popola i dettagli dell'utente
        playerDetails.put("name",name);
        playerDetails.put("remainingDays", 4);
        playerDetails.put("inventory", inventoryData);
        playerDetails.put("localItemList", new ArrayList<>());
        playerDetails.put("completedPuzzles", completedPuzzles);
        playerDetails.put("notebook", notebookData);

        // Aggiungi i dettagli del giocatore alla mappa generale
        playerData.put(name, playerDetails);

        // Usa ObjectMapper per serializzare l'oggetto in formato JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String playerJson = objectMapper.writeValueAsString(playerData);

        // Salva il file JSON nella cartella dell'utente
        SaveDataManager.saveGame(playerJson, "startGame1", name);
    }

    public static void saveUserData(PlayerModel playerModel) throws IOException {
        // Crea una mappa con il nome del giocatore come chiave
        Map<String, Map<String, Object>> playerData = new HashMap<>();

        // Popola la struttura dei dati come richiesta
        Map<String, Object> playerDetails = new HashMap<>();

        playerDetails.put("currentDistrict", playerModel.getCurrentDistrict());
        playerDetails.put("remainingDays", playerModel.getRemainingDays());
        playerDetails.put("inventory", playerModel.getInventory());
        playerDetails.put("localItemList", playerModel.getLocalItemList());
        playerDetails.put("completedPuzzles", playerModel.getCompletedPuzzles());
        playerDetails.put("notebook", playerModel.getNotebook());
        // Aggiungi i dettagli del giocatore alla mappa generale
        playerData.put(playerModel.getName(), playerDetails);

        // Usa ObjectMapper per serializzare l'oggetto in formato JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String playerJson = objectMapper.writeValueAsString(playerData);

        // Salva il file JSON nella cartella dell'utente
        SaveDataManager.saveGame(playerJson, playerModel.getCurrentDistrict().get("DistrictName")+playerModel.getCurrentDistrict().get("ZoneName"), playerModel.getName());
    }

    // Metodo per eliminare un file JSON specifico di un utente
    public static void deleteSaveFile(String userName, String fileName) throws IOException {
        File userDir = new File(SAVES_FOLDER, userName);
        if (!userDir.exists()) {
            throw new IOException("La cartella dell'utente non esiste!");
        }

        File saveFile = new File(userDir,  fileName + ".json");
        if (saveFile.exists()) {
            boolean deleted = saveFile.delete();
            if (!deleted) {
                throw new IOException("Impossibile eliminare il file: " + saveFile.getPath());
            }
        } else {
            throw new IOException("Il file specificato non esiste: " + saveFile.getPath());
        }
    }


    private static void saveGame(String saveData, String fileName, String name) throws IOException {
        File userDir = new File(SAVES_FOLDER, name);
        if (!userDir.exists()) {
            throw new IOException("La cartella utente non esiste!");
        }

        // Cerca un file che contiene il nome specificato
        File[] matchingFiles = userDir.listFiles((dir, currentFileName) -> currentFileName.contains(fileName + ".json"));

        Path saveFile;
        if (matchingFiles != null && matchingFiles.length > 0) {
            // Usa il file esistente
            saveFile = matchingFiles[0].toPath();
        }
        else{
            // Utilizzo la dimensione della lista di salvataggi dell'utente [name] come contatore per mantenere l'ordine dei salvataggi
            saveFile = Path.of(userDir.getPath(), SaveDataManager.getSaveFilesForUser(name).size() + "-" + fileName + ".json");
        }

        try {
            // Usa ObjectMapper per formattare la stringa JSON
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

            // Converte il JSON string in uno formattato
            Object jsonObject = objectMapper.readValue(saveData, Object.class);
            String formattedJson = writer.writeValueAsString(jsonObject);

            // Scrivi il JSON formattato nel file
            Files.writeString(saveFile, formattedJson, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del file JSON: " + e.getMessage());
            throw e;
        }
    }

    // Restituisce una lista dei nomi delle cartelle utente
    public static List<String> getExistingUsers() {
        File savesDir = new File(SAVES_FOLDER);
        File[] userFolders = savesDir.listFiles(File::isDirectory);

        List<String> userNames = new ArrayList<>();
        if (userFolders != null) {
            for (File folder : userFolders) {
                userNames.add(folder.getName());
            }
        }
        return userNames;
    }

    // Elimina una cartella utente
    public static void deleteUser(String userName) throws IOException {
        File userDir = new File(SAVES_FOLDER, userName);
        if (userDir.exists()) {
            for (File file : userDir.listFiles()) {
                file.delete();
            }
            userDir.delete();
        } else {
            throw new IOException("La cartella dell'utente non esiste!");
        }
    }

    public static List<String> getSavedUsers() {
        List<String> userFolders = new ArrayList<>();
        File savesFolder = new File("src/main/resources/com/iss/echoesofdeceit/saves");
        if (savesFolder.exists() && savesFolder.isDirectory()) {
            File[] userDirs = savesFolder.listFiles(File::isDirectory);
            if (userDirs != null) {
                for (File userDir : userDirs) {
                    userFolders.add(userDir.getName());
                }
            }
        }
        return userFolders;
    }

    public static List<String> getSaveFilesForUser(String username) {
        List<String> saveFiles = new ArrayList<>();
        File userFolder = new File(SAVES_FOLDER + username);
        if (userFolder.exists() && userFolder.isDirectory()) {
            File[] files = userFolder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    saveFiles.add(file.getName().replace(".json", ""));  // Rimuove ".json" dal nome
                }
            }
        }
        return saveFiles;
    }

    public static void loadPlayer(String fileName, String playerName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Per una formattazione leggibile del JSON
        String filePath = SAVES_FOLDER+playerName+"/"+fileName;
        // Legge l'intero file JSON come un nodo ad albero
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Naviga fino al nodo specificato dinamicamente dal nome del giocatore
        JsonNode playerNode = rootNode.path(playerName);

        // Verifica se il nodo del giocatore esiste
        if (playerNode.isMissingNode()) {
            throw new IllegalArgumentException("Nodo non trovato: " + playerName);
        }

        // Deserializza il nodo del giocatore in un oggetto PlayerModel
        PlayerModel playerModel = PlayerModel.getInstance();

        // Imposta il nome del giocatore
        playerModel.setName(playerName);

        // Imposta i giorni rimanenti
        playerModel.setRemainingDays(playerNode.path("remainingDays").asInt());

        // Imposta il completamento dei puzzle
        JsonNode puzzlesNode = playerNode.path("completedPuzzles");
        if (!puzzlesNode.isMissingNode()) {
            Map<String, Boolean> completedPuzzles = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> puzzlesIterator = puzzlesNode.fields();
            while (puzzlesIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = puzzlesIterator.next();
                completedPuzzles.put(entry.getKey(), entry.getValue().asBoolean());
            }
            playerModel.setCompletedPuzzles(completedPuzzles);
        }

        // Imposta l'inventario
        JsonNode inventoryNode = playerNode.path("inventory");
        if (!inventoryNode.isMissingNode()) {
            InventoryModel inventoryModel = new InventoryModel();
            JsonNode itemsNode = inventoryNode.path("items");
            if (!itemsNode.isMissingNode()) {
                List<Item> items = objectMapper.readValue(
                        itemsNode.toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class)
                );
                inventoryModel.setItems(items);
            }

            playerModel.setInventory(inventoryModel);
        }

        //imposta item locali di una zona
        // Imposta la lista degli oggetti locali (localItemList)
        JsonNode localItemListNode = playerNode.path("localItemList");
        if (!localItemListNode.isMissingNode()) {
            List<Item> localItemList = objectMapper.readValue(localItemListNode.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
            playerModel.setLocalItemList(localItemList);
        }

        // Imposta il distretto e la zona attuali
        JsonNode currentDistrictNode = playerNode.path("currentDistrict");
        if (!currentDistrictNode.isMissingNode()) {
            Map<String, String> currentDistrict = new HashMap<>();
            currentDistrict.put("DistrictName", currentDistrictNode.path("DistrictName").asText());
            currentDistrict.put("ZoneName", currentDistrictNode.path("ZoneName").asText());
            playerModel.setCurrentDistrict(currentDistrict);
        }

        // Imposta il quaderno (notebook)
        JsonNode notebookNode = playerNode.path("notebook");
        if (!notebookNode.isMissingNode()) {
            NotebookModel notebookModel = new NotebookModel();

            notebookModel.setDiary(notebookNode.path("diary").asText());
            playerModel.setNotebook(notebookModel);
        }
    }

}
