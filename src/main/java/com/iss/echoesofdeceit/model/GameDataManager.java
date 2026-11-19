package com.iss.echoesofdeceit.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameDataManager {
    private static final String DATA_FOLDER = "src/main/resources/com/iss/echoesofdeceit/data/";

    public GameDataManager() {
    }

    // Carica il quartiere specificato dal nome del quartiere dinamico
    public static DistrictModel loadQuartiere(String fileName, String quartiereName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Per una formattazione leggibile del JSON
        String filePath = DATA_FOLDER+fileName;
        // Legge l'intero file JSON come un nodo ad albero
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Naviga fino al nodo specificato dinamicamente dal nome del quartiere
        JsonNode quartiereNode = rootNode.path(quartiereName);

        // Verifica se il nodo del quartiere esiste
        if (quartiereNode.isMissingNode()) {
            throw new IllegalArgumentException("Nodo non trovato: " + quartiereName);
        }

        // Deserializza il nodo del quartiere in un oggetto DistrictModel
        DistrictModel districtModel = objectMapper.treeToValue(quartiereNode, DistrictModel.class);

        // Assicurati che il modello contenga la mappa delle zone
        JsonNode zonesNode = quartiereNode.path("zones");
        if (!zonesNode.isMissingNode()) {
            Map<String, ZoneModel> zones = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> zonesIterator = zonesNode.fields();
            while (zonesIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = zonesIterator.next();
                ZoneModel zoneModel = objectMapper.treeToValue(entry.getValue(), ZoneModel.class);
                zones.put(entry.getKey(), zoneModel);
            }
            districtModel.setZones(zones);
        }

        return districtModel;
    }

    // Salva il quartiere specificato dinamicamente nel file
    public static void saveQuartiere(String filePath, String quartiereName, DistrictModel districtModel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Per una formattazione leggibile del JSON
        // Legge l'intero file JSON
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Naviga fino al nodo del quartiere specificato dinamicamente
        JsonNode quartiereNode = rootNode.path(quartiereName);

        // Verifica se il nodo del quartiere esiste
        if (quartiereNode.isMissingNode() || !quartiereNode.isObject()) {
            throw new IllegalArgumentException("Struttura del file JSON non valida: manca il nodo '" + quartiereName + "'.");
        }

        // Aggiorna il quartiere specifico con il nuovo DistrictModel
        ((com.fasterxml.jackson.databind.node.ObjectNode) rootNode).set(quartiereName, objectMapper.valueToTree(districtModel));

        // Scrive il file aggiornato
        objectMapper.writeValue(new File(filePath), rootNode);
    }
}