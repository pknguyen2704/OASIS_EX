import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static String readContentFromFile(String path) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeContentToFile(String path, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendContentToFile(String path, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File findFileByName(String folderPath, String fileName) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    return file;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Andrew\\Documents\\dev\\w9\\src\\main\\resources\\input.txt";
        String contentToWrite = "Hello, this is a test content.";

        String contentRead = readContentFromFile(filePath);
        writeContentToFile(filePath, contentToWrite);
        appendContentToFile(filePath, "\nThis is additional content.");

        String folderPath = "C:\\Users\\Andrew\\Documents\\dev\\w9\\src\\main\\resources\\";
        String fileNameToFind = "input.txt";
        File foundFile = findFileByName(folderPath, fileNameToFind);
        if (foundFile != null) {
            System.out.println("File found: " + foundFile.getAbsolutePath());
        } else {
            System.out.println("File not found.");
        }
    }
}