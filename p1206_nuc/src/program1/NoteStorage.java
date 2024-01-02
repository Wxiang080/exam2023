// NoteStorage.java
package program1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteStorage {

    private static final String NOTES_FOLDER = "notes";



    // 在 NoteStorage 类中的 storeNotes 方法中进行修改
    public static void storeNotes(String username) {
        try {
            createNotesFolderIfNeeded(); // 创建 notes 文件夹（如果文件夹不存在的话）

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());

            // 根据现有笔记数量动态计算 noteIndex
            int noteIndex = getNoteCount(username) + 1;

            String noteFileName = username + "-" + timestamp + "-" + noteIndex + ".txt"; // 笔记文件名

            Path filePath = Paths.get(NOTES_FOLDER, noteFileName); // 笔记文件的路径
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile())); // 创建写入器

            Scanner scanner = new Scanner(System.in); // 创建扫描器，用于读取用户输入

            System.out.println("请输入要存储的笔记内容（输入exit结束并不保存，输入save保存并结束）："); // 提示用户输入笔记内容
            while (true) {
                String note = scanner.nextLine(); // 读取用户输入的笔记
                if (note.equals("exit")) { // 用户输入:q
                    break; // 退出循环
                } else if (note.equals("save")) { // 用户输入:wq
                    writer.close(); // 关闭写入器
                    System.out.println("笔记存储成功！"); // 提示用户笔记存储成功
                    return; // 结束函数
                }
                writer.write(note); // 将笔记写入文件
                writer.newLine(); // 在新的一行写入换行符
            }

            writer.close(); // 关闭写入器
            System.out.println("笔记存储成功！"); // 提示用户笔记存储成功
        } catch (IOException e) {
            System.out.println("无法保存笔记文件。"); // 如果发生 IO 异常，提示用户无法保存笔记文件
        }
    }




    /**
     * 获取指定用户的笔记数量
     */
    public static int getNoteCount(String username) {
        try {
            // 获取笔记文件夹路径
            Path folderPath = Paths.get(NOTES_FOLDER);
            if (Files.exists(folderPath)) {
                // 创建目录流以遍历 笔记文件夹路径 下的指定用户名开头的文本文件
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath, username + "-*.txt");
                int count = 0;
                // 遍历目录流中的文件路径
                for (Path filePath : directoryStream) {
                    count++; // 统计笔记数量
                }
                directoryStream.close(); // 关闭目录流
                return count; // 返回笔记数量
            } else {
                return 0; // 若笔记文件夹不存在，则返回0
            }
        } catch (IOException e) {
            System.out.println("无法加载笔记文件夹。");
            return 0; // 若发生IO异常，则打印错误信息并返回0
        }
    }


    public static void modifyNote(String username, int noteIndex) {
        try {
            Path filePath = Paths.get(NOTES_FOLDER, username + "-" + noteIndex + ".txt");
            if (Files.exists(filePath)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("当前笔记内容：");
                NoteViewer.viewNote(username);
                System.out.println("请输入新的笔记内容（输入:q结束并不保存，输入:wq保存并结束）：");

                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
                while (true) {
                    String modifiedNote = scanner.nextLine();
                    if (modifiedNote.equals(":q")) {
                        break;
                    } else if (modifiedNote.equals(":wq")) {
                        writer.close();
                        System.out.println("笔记修改成功！");
                        return;
                    }
                    writer.write(modifiedNote);
                    writer.newLine();
                }

                writer.close();
                System.out.println("笔记修改成功！");
            } else {
                System.out.println("找不到要修改的笔记文件。");
            }
        } catch (IOException e) {
            System.out.println("无法修改笔记文件。");
        }
    }


    public static void deleteNote(String username, int noteIndex) {
        try {
            Path filePath = Paths.get(NOTES_FOLDER, username + "-" + noteIndex + ".txt");
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("笔记删除成功！");
            } else {
                System.out.println("找不到要删除的笔记文件。");
            }
        } catch (IOException e) {
            System.out.println("无法删除笔记文件。");
        }
    }


    private static void createNotesFolderIfNeeded() throws IOException {
        Path folderPath = Paths.get(NOTES_FOLDER);
        if (Files.notExists(folderPath)) {
            Files.createDirectories(folderPath);
        }
    }
}
