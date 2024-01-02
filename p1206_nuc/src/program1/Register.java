package program1;

import java.io.*;
import java.util.*;

public class Register{
    String username;

    private static final String USER_DATA_FILE = "userdata.txt";
    private static final Map<String, String> userData = new HashMap<>();
    private static String currentUsername;

    public static void Registration() {
        loadDataFromFile();// 加载用户数据文件到内存

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出登录/注册程序");// 打印菜单

            System.out.print("请选择操作（输入相应的数字）： ");
            int choice = 0;
            boolean flag = true;
            try {
                choice = scanner.nextInt();
            }catch(InputMismatchException e){
                flag=false;
                System.out.println("请输入正确的内容！！");
            }
            scanner.nextLine();//回车

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    saveDataToFile();//保存数据
                    System.out.println("退出登录/注册程序。");//回到上一层
                    return;
                default:
                    if (flag)
                        System.out.println("请输入正确的选项！");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("请输入用户名： ");
        String username = scanner.nextLine();

        if (userData.containsKey(username)) {//利用hashmap判断键
            System.out.println("用户名已存在，请选择其他用户名。");
            return;
        }

        System.out.print("请输入密码： ");
        String password = scanner.nextLine();

        userData.put(username, password);//添加到映射
        System.out.println("注册成功！");
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("请输入用户名： ");
        String username = scanner.nextLine();

        if (!userData.containsKey(username)) {//利用hashmap判断是否存在
            System.out.println("用户不存在，请先注册。");
            return;
        }

        System.out.print("请输入密码： ");
        String password = scanner.nextLine();

        if (password.equals(userData.get(username))) {
            System.out.println("登录成功！");
            currentUsername = username;

        } else {
            System.out.println("密码错误，请重新登录。");
        }
    }

    private static void loadDataFromFile() { //从文件中加载用户数据
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;//读取的每一行数据的字符串
            while ((line = reader.readLine()) != null) {// 到文件末尾时结束
                String[] parts = line.split(",");//以‘,’分割每一行为数组
                userData.put(parts[0], parts[1]);//存储
            }
        } catch (IOException e) {//ioexception时返回错误内容
            System.out.println("无法加载用户数据文件。");
        }
    }

    private static void saveDataToFile() {//从文件中写入用户数据
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (Map.Entry<String, String> entry : userData.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("无法保存用户数据文件。");
        }
    }
    public String getCurrentUsername() {
        return currentUsername;
    }
}
