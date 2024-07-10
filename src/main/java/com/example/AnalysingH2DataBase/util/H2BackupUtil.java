package com.example.AnalysingH2DataBase.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
public class H2BackupUtil {

    @Value("${spring.datasource.url}")
    private String h2Url;

    private static final String BACKUP_FILE_PATH = "C:/h2db/h2db_backup.zip";

    /*Post and Pre which is used here to defne method
     that should be executed after the bean has been created and before the bean is destroyed.*/
    @PostConstruct
    public void restoreDatabase() {
        try {
            if (Files.exists(Paths.get(BACKUP_FILE_PATH))) {//checks if the backup file exists
                String restoreCommand = String.format("java -cp %s org.h2.tools.RunScript -url %s -user sa -script %s -options compression zip",
                        getH2JarFilePath(), h2Url, BACKUP_FILE_PATH);
                //constructs a command to run the H2 RunScript tool to restore the database using the backup file.
                Runtime.getRuntime().exec(restoreCommand).waitFor();
                System.out.println("Database restored from backup.");
            } else {
                System.out.println("No backup file found.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void backupDatabase() {
        try {
            String backupCommand = String.format("java -cp %s org.h2.tools.Script -url %s -user sa -script %s -options compression zip",
                    getH2JarFilePath(), h2Url, BACKUP_FILE_PATH);
            Runtime.getRuntime().exec(backupCommand).waitFor();
            System.out.println("Database backup created.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getH2JarFilePath() {
        File h2JarFile = new File("./h2*.jar");
        if (h2JarFile.exists()) {
            System.out.println(h2JarFile);
            System.out.println(h2JarFile.getAbsolutePath());
            return h2JarFile.getAbsolutePath();
        } else {
            throw new RuntimeException("H2 jar file not found.");
        }
    }
}