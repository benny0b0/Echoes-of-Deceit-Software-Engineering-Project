package com.iss.echoesofdeceit.controller.command;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class SaveCommand implements Command {

    @Override
    public void execute() {
        // Percorso relativo al file system, fuori dal classpath
        String filePath = "src/main/resources/com/iss/echoesofdeceit/saves/log.json";  // Modifica il percorso in base alla tua struttura
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File JSON non trovato in: " + filePath);
            return;
        }

        try {
            // Leggi il contenuto del file JSON
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Crea un oggetto JSONObject dal contenuto letto
            JSONObject jsonObject = new JSONObject(content.toString());

            // Verifica se il valore "first_introduction" è false
            if (!jsonObject.getBoolean("first_introduction")) {
                // Imposta "first_introduction" a true
                jsonObject.put("first_introduction", true);

                // Scrivi il JSON modificato nel file
                FileWriter writer = new FileWriter(file);
                writer.write(jsonObject.toString(4));  // 4 per la formattazione leggibile
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
