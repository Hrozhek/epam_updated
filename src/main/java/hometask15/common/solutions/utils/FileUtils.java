package hometask15.common.solutions.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public final class FileUtils {

    private FileUtils(){

    }

    public static File createFileFromResource(String fileNamePrefix, String fileNameSuffix, String resourcePath) throws IOException {
        try(InputStream inputStream = new FileInputStream(new File(resourcePath))) {
            Path tempFile = Files.createTempFile(fileNamePrefix, fileNameSuffix);
            Files.copy(inputStream, tempFile, REPLACE_EXISTING);
            return tempFile.toFile();
        }
    }

}
