package org.example;

import db.DB.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== Teste 1: Seller findById ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println("\n=== Teste 2: FindByDepartment ====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller ojb : list){
            System.out.println(ojb);
        }

        System.out.println("\n=== Teste 3: Seller FindAll ====");

        list = sellerDao.findAll();
        for (Seller ojb : list){
            System.out.println(ojb);
        }
        /*System.out.println("\n=== Teste 4: Seller Insert ====");
        Seller newSeller = new Seller(null, "Wdenberg", "wdenberg@gmail.com", new Date(), 7000.00, department);
        sellerDao.insert(newSeller);
        System.out.printf("Inserted new ID = ");
        System.out.println(newSeller);*/

        System.out.println("\n=== Teste 5: Seller UPDATE ====");
        seller = sellerDao.findById(1);
        seller.setName("João Lima");
        sellerDao.update(seller);
        System.out.println("Update Completed" );
        System.out.println(seller);

        System.out.println("\n=== Teste 5: Seller Delete ====");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deletByID(id);
        System.out.println("Delete Completed");
        sc.close();




    }
}