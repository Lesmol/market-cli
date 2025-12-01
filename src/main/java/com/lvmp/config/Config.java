package com.lvmp.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final String APP_NAME_DIR = ".market";
    private static final String API_KEY_FILE = "key.txt";

    public static Path getConfigPath() {
        String homeDir = System.getProperty("user.home");
        Path appDir = Paths.get(homeDir, APP_NAME_DIR);

        return appDir.resolve(API_KEY_FILE);
    }
}
