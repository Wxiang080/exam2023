package program1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataDeleter {

    private static final String USER_DATA_FILE = "userdata.txt";

    public static void deleteUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {

        } catch (IOException e) {
            System.out.println("无法删除用户数据文件。");
        }
    }
}
