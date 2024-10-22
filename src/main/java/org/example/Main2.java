package org.example;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("===== Teste 1 Department findById ========");
        Department department = departmentDao.findById(1);
        System.out.println(department);
        System.out.println("===== Teste 1 Department findAll ========");

        List<Department> list = new ArrayList<>();
        list = departmentDao.findAll();
        for (Department obj : list){
            System.out.println(obj);
        }

        System.out.println("===== Teste 1 Department Insert ========");
        Department newDepartment = new Department(null, "SmartPhone");
        departmentDao.insert(newDepartment);
        System.out.println("Insert new Department");
        System.out.println(newDepartment);

        System.out.println("===== Teste 1 Department UPDATE ========");
        department = departmentDao.findById(6);
        department.setName("Teste");
        departmentDao.update(department);

        System.out.println("===== Teste 1 Department DELETE ========");
        System.out.println("Enter Id for Delete Test: ");
        int id = sc.nextInt();
        departmentDao.deletByID(id);
        System.out.println("Delete Completed");
        sc.close();


    }
}
