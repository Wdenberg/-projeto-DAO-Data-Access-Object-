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

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

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
        System.out.println("\n=== Teste 3: Seller Insert ====");
        Seller newSeller = new Seller(null, "Wdenberg", "wdenberg@gmail.com", new Date(), 7000.00, department);
        sellerDao.insert(newSeller);
        System.out.printf("Inserted new ID = "+newSeller);

    }
}