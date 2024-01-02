package program1;

import java.util.Objects;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Register run=new Register();
        Scanner cho=new Scanner(System.in);
        while (true){
            boolean flag=true;
            int choice;
            System.out.println("1. 注册/登录");
            System.out.println("2. 笔记功能");// 打印菜单
            if (Objects.equals(run.getCurrentUsername(), "admin")) {
                System.out.println("*****管理员选项*****");
                System.out.println("3. 打开笔记存储位置");
                System.out.println("******************");
            }
            System.out.println("10.退出");
            System.out.print("请选择操作（输入相应的数字）： ");
            choice = cho.nextInt();
            switch (choice){
                case 1:
                    Register.Registration();
                    break;
                case 2:
                    Note.noteFuc(run.getCurrentUsername());
                    break;
                case 3:
                    OpenFolder.openFolder();
                    break;
                default:
                    System.out.println("请输入正确的选项！--t");//选项校错
                    break;
                case 10:
                    System.exit(0);
            }

        }

    }
}
