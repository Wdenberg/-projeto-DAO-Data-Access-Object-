package org.example;

import db.DB.DB;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Department department = new Department(1, "Developer");
        Seller seller = new Seller(21,"Wdenberg","wdenberg@gmail.com", new Date(), 5000.00, department );

        System.out.println(seller);
    }
}