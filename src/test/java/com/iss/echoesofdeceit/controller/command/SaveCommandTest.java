package com.iss.echoesofdeceit.controller.command;

import com.iss.echoesofdeceit.controller.command.SaveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

public class SaveCommandTest {

    private SaveCommand saveCommand;
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        saveCommand = new SaveCommand();
    }

    @Test
    public void testExecute() throws Exception {
        Path tempFile = tempDir.resolve("test_log.json");
        Files.write(tempFile, "{\"first_introduction\": true}".getBytes());



        saveCommand.execute();

        String content = Files.readString(tempFile);
        assertTrue(content.contains("\"first_introduction\": true"));
    }
}