// NoteViewer.java
package program1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class NoteViewer {

    private static final String NOTES_FOLDER = "notes";

    public static void listUserNotes(String username) {
        try {
            Path folderPath = Paths.get(NOTES_FOLDER);
            if (Files.exists(folderPath)) {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath, username + "_*.txt");

                int index = 1;
                for (Path filePath : directoryStream) {
                    String fileName = filePath.getFileName().toString();
                    String[] parts = fileName.split("_");

                    if (parts.length == 3) {
                        String timestamp = parts[1] + "_" + parts[2].substring(0, parts[2].lastIndexOf(".txt"));
                        System.out.println(index + ". " + username + "-" + timestamp);
                        index++;
                    }
                }

                directoryStream.close();
            } else {
                System.out.println("没有找到笔记文件夹。可能是因为您还没有存储任何笔记。");
            }
        } catch (IOException e) {
            System.out.println("无法加载笔记文件夹。");
        }
    }
    public static void listUserNotes(String username, int choice) {
        try {
            Path folderPath = Paths.get(NOTES_FOLDER);
            if (Files.exists(folderPath)) {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath, username + "-*.txt");

                int index = 1;
                for (Path filePath : directoryStream) {
                    if (index == choice) {
                        // Implement logic to show options for the selected note
                        System.out.println("选择了笔记：" + filePath.getFileName().toString());
                        // Add your logic here
                        break;
                    }
                    index++;
                }

                directoryStream.close();
            } else {
                System.out.println("没有找到笔记文件夹。可能是因为您还没有存储任何笔记。");
            }
        } catch (IOException e) {
            System.out.println("无法加载笔记文件夹。");
        }
    }



    public static void viewNote(String username) {
        try {
            Path folderPath = Paths.get(NOTES_FOLDER);
            if (Files.exists(folderPath)) {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath, username + "-*.txt");

                int index = 1;
                for (Path filePath : directoryStream) {
                    String fileName = filePath.getFileName().toString();
                    System.out.println(index + ". " + fileName);
                    index++;
                }

                directoryStream.close();
            } else {
                System.out.println("没有找到笔记文件夹。可能是因为您还没有存储任何笔记。");
            }
        } catch (IOException e) {
            System.out.println("无法加载笔记文件夹。");
        }
    }
}
