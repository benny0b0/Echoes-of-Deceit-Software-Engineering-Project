package com.iss.echoesofdeceit.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveDataManagerTest {

    private static final String BASE_PATH = "src/main/resources/com/iss/echoesofdeceit/saves/";
    private File userFolder;

    @AfterEach
    void cleanUp() {
        if (userFolder != null && userFolder.exists()) {
            deleteDirectory(userFolder);
        }
    }

    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    @Test
    void testCreateUserFolder() throws IOException {
        String username = "testUser";
        SaveDataManager.createUserFolder(username);
        userFolder = new File(BASE_PATH, username);
        assertTrue(userFolder.exists(), "La cartella utente dovrebbe essere creata.");
    }

    @Test
    void testInitializePlayer() throws IOException {
        String username = "testPlayer";
        SaveDataManager.createUserFolder(username);
        SaveDataManager.initializePlayer(username);
        userFolder = new File(BASE_PATH, username);

        List<String> saveFiles = SaveDataManager.getSaveFilesForUser(username);
        assertFalse(saveFiles.isEmpty(), "Il file di salvataggio dovrebbe essere stato creato.");
    }

    @Test
    void testSaveUserData() throws IOException {
        String username = "testPlayer";
        SaveDataManager.createUserFolder(username);
        SaveDataManager.initializePlayer(username);
        userFolder = new File(BASE_PATH, username);

        PlayerModel playerModel = mock(PlayerModel.class);
        when(playerModel.getName()).thenReturn(username);
        when(playerModel.getRemainingDays()).thenReturn(3);

        SaveDataManager.saveUserData(playerModel);

        List<String> saveFiles = SaveDataManager.getSaveFilesForUser(username);
        assertFalse(saveFiles.isEmpty(), "Il file di salvataggio del giocatore dovrebbe essere stato creato.");
    }

    @Test
    void testDeleteUser() throws IOException {
        String username = "testPlayer";
        SaveDataManager.createUserFolder(username);
        SaveDataManager.initializePlayer(username);
        userFolder = new File(BASE_PATH, username);

        assertTrue(userFolder.exists(), "La cartella utente dovrebbe esistere.");

        SaveDataManager.deleteUser(username);

        assertFalse(userFolder.exists(), "La cartella utente dovrebbe essere stata eliminata.");
    }

    @Test
    void testGetSavedUsers() throws IOException {
        String username = "testPlayer";
        SaveDataManager.createUserFolder(username);
        SaveDataManager.initializePlayer(username);
        userFolder = new File(BASE_PATH, username);

        assertTrue(SaveDataManager.getSavedUsers().contains(username), "L'utente dovrebbe essere presente nella lista degli utenti salvati.");
    }

    @Test
    void testDeleteSaveFileSpecific() throws IOException {
        String username = "testPlayer";
        SaveDataManager.createUserFolder(username);
        SaveDataManager.initializePlayer(username);
        userFolder = new File(BASE_PATH, username);

        List<String> saveFiles = SaveDataManager.getSaveFilesForUser(username);
        String fileName = saveFiles.get(0);

        SaveDataManager.deleteSaveFile(username, fileName);

        saveFiles = SaveDataManager.getSaveFilesForUser(username);
        assertFalse(saveFiles.contains(fileName), "Il file di salvataggio dovrebbe essere stato eliminato.");
    }
}
