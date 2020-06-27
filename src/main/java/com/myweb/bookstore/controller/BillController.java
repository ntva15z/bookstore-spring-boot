package com.myweb.bookstore.controller;

import com.myweb.bookstore.entity.Bill;
import com.myweb.bookstore.entity.BillDetail;
import com.myweb.bookstore.entity.Product;
import com.myweb.bookstore.repository.BillReponsitory;
import com.myweb.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/bill")
public class BillController {
    @Autowired
    private BillReponsitory billReponsitory;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String viewBill(ModelMap model){
        List<Bill> list = billReponsitory.findAll();
        model.addAttribute("bill",list);
        return "admin/bill/list";
    }

    @GetMapping("/update/{id}")
    public String updateBillActive( @PathVariable(name = "id")Long id){
        Optional<Bill> bill = billReponsitory.findById(id);

        if(bill.isPresent()){
            bill.get().setStatus(2);
            bill.get().setActivedate(Calendar.getInstance().getTime());

            for (BillDetail bdt: bill.get().getBillDetailList()){
                Optional<Product> opt = productService.findById(bdt.getProduct().getId());
                if(opt.isPresent()){
                    opt.get().setQuantity(opt.get().getQuantity()-bdt.getQuantity());
                    productService.save(opt.get());
                }
            }
            billReponsitory.save(bill.get());
        }
        return "redirect:/admin/bill/list";
    }

}
