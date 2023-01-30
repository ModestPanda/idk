package com.lby.dao_.test;

import com.lby.dao_.damain.Students;
import com.lby.dao_.dao.StudentsDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Humble
 * @version 1.0
 */
public class TestStudents {
    public static void main(String[] args) throws SQLException {

        StudentsDAO studentsDAO = new StudentsDAO();



        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== 欢迎进入学生管理系统 =====\n");
            System.out.println("      请选择你要进行的操作     ");
            System.out.println("      1.添加学生     ");
            System.out.println("      2.删除学生     ");
            System.out.println("      3.修改学生     ");
            System.out.println("      4.查看学生     ");
            System.out.println("      5.退出系统     ");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    System.out.println("请添加学生学号");
                    String id = sc.next();
                    System.out.println("请添加学生名字");
                    String name = sc.next();
                    System.out.println("请添加学生班级");
                    int classname = sc.nextInt();
                    System.out.println("请添加学生宿舍");
                    int dormitory = sc.nextInt();
                    int update = studentsDAO.update("insert into Students values(?,?,?,?)", id, name, classname, dormitory);
                    if(update>0){
                        System.out.println("添加成功");
                        break;
                    }
                case 2:
                    System.out.println("请输入要删除的学生学号");
                    String id2 = sc.next();
                    int update1 = studentsDAO.update("delete from Students where id = ?", id2);
                    if(update1>0){
                        System.out.println("删除成功");
                        break;
                    }else
                        System.out.println("没有找到该学生");
                    break;
                case 3:
                    System.out.println("请输入要修改的学生学号");
                    String id3 = sc.next();
                    System.out.println("请修改学生姓名");
                    String name2 = sc.next();
                    System.out.println("请修改学生班级");
                    int classname2 = sc.nextInt();
                    System.out.println("请修改学生宿舍");
                    int dormitory2  =sc.nextInt();
                    int update2 = studentsDAO.update("update Students set name = ?,classname = ?,dormitory = ?", name2, classname2, dormitory2);
                    if(update2>0){
                        System.out.println("修改成功");
                        break;
                    }else
                        System.out.println("修改失败");
                    break;
                case 4:
                    List<Students> students =
                            studentsDAO.queryMulti("select * from Students where id > ?", Students.class, 1);
            for (Students students1:students){
                System.out.println(students1);
            }
            break;
                case 5:
                    System.out.println("操作完成，退出系统");
                    System.exit(0);
            }
        }
    }
}
