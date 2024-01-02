package program1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Note {
    public static void noteFuc(String username) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (username == null) {
                System.out.println("请先登录！！");
                break;
            }

            // Display user's notes list
            System.out.println("用户：" + username + " 的笔记列表");
            displayUserNotes(username);

            // Show main menu
            System.out.println("请选择笔记（输入笔记编号）或操作：");
            System.out.println("0. 返回上级菜单");
            System.out.println("a. 新建笔记");

            int choice = 0;
            boolean flag = true;

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // 读取并忽略回车符
            } catch (InputMismatchException e) {
                String input = scanner.nextLine();
                if ("a".equalsIgnoreCase(input)) {
                    NoteStorage.storeNotes(username);
                    continue; // 跳过循环的其余部分并重新开始
                } else {
                    flag = false;
                    System.out.println("请输入正确的内容！！");
                }
            }

            // Rest of your existing code...
//            flag =false;
            if (choice == 0) {
                return; // 返回上一级菜单
            } else if (choice > 0 /*&& choice <= NoteStorage.getNoteCount(username)*/) {
                // 显示选定笔记的选项
                showNoteOptions(username, choice);
            } else {
                if (flag) {
                    System.out.println("请输入正确的选项！--n"+flag);
                }
            }
        }
    }


    private static void displayUserNotes(String username) {
        NoteViewer.listUserNotes(username);
    }

    private static void showNoteOptions(String username, int noteIndex) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display note options
            System.out.println("笔记：" + username + "-" + noteIndex);
            System.out.println("1. 查看笔记");
            System.out.println("2. 修改笔记");
            System.out.println("3. 删除笔记");
            System.out.print("请选择操作（输入相应的数字或操作符）：");

            int choice = 0;
            boolean flag = true;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                flag = false;
                System.out.println("请输入正确的内容！！");
            }
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    NoteViewer.viewNote(username);
                    break;
                case 2:
                    NoteStorage.modifyNote(username, choice);
                    break;
                case 3:
                    NoteStorage.deleteNote(username, noteIndex);
                    break;
                default:
                    if (flag) {
                        System.out.println("请输入正确的选项！");
                    }
            }
        }
    }
}
