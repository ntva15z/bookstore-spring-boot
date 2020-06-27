package com.myweb.bookstore.controller;

import com.myweb.bookstore.repository.BillReponsitory;
import com.myweb.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Chart {

    @Autowired
    private BillReponsitory billReponsitory;

    @GetMapping("/admin/bill/chart")
    public String chartByDate(ModelMap model){
        List<Object[]> result = billReponsitory.totalByDate();
        Map<String,Double> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Object[] object:result){
            String date = (String)format.format(object[0]);
            Double total = (Double)object[1];
            map.put(date,total);
            model.addAttribute("datechart",map);
        }
        model.addAttribute("datechart",map);
        return "admin/bill/chart";
    }

}
