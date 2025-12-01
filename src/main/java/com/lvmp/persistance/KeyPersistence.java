package com.lvmp.persistance;

import com.lvmp.config.Config;
import com.lvmp.utils.InputPrompt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class KeyPersistence {
    private static void storeApiKey(String key) throws IOException {
        Path configPath = Config.getConfigPath();
        Files.createDirectories(configPath.getParent());
        Files.writeString(configPath, key);
    }

    private static String loadApiKey() throws IOException {
        Path configPath = Config.getConfigPath();

        if(!Files.exists(configPath) || Files.readString(configPath, StandardCharsets.UTF_8).isBlank()) {
            return null;
        }

        return Files.readString(configPath, StandardCharsets.UTF_8);
    }

    public static String getApiKey() throws IOException {
        String apiKey = KeyPersistence.loadApiKey();

        if(apiKey == null) {
            apiKey = InputPrompt.getInput("Enter your Polygon api key: ");
            KeyPersistence.storeApiKey(apiKey);
        }

        return apiKey;
    }
}
