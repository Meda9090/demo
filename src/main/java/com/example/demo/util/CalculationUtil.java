package com.example.demo.util;

import com.example.demo.model.Product;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public interface CalculationUtil { // расчет полезности
    public static void bzlogic(Product p) {

        Double cost = p.getProdCost(); //Стоимость продукта
        p.setProdSkidka(cost * 12 / 100);
        p.setProdGst(cost * 18 / 100);

        List<Integer> l = new ArrayList<Integer>();
    }
}
