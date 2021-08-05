package com.dell.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dell.webapp.bean.EProduct;
import com.dell.webapp.dao.EProductDAO;

@Controller
public class MainController {
	
	@Autowired
	EProductDAO eProductDAO;
	
	@RequestMapping(value="/list-product", method=RequestMethod.GET)
	public String listProduct(ModelMap map) {
		List<EProduct> list = eProductDAO.getProducts();
		map.addAttribute("list",list);
		return "list-product";
	}
	
	@RequestMapping(value="/open-product", method=RequestMethod.GET)
	public String openProduct(ModelMap map) {
		EProduct eProduct = new EProduct();
		map.addAttribute("eProduct",eProduct);
		return "open-product";
	}
	
	@RequestMapping(value="/add-product", method=RequestMethod.POST)
	public String addProductData(ModelMap map, @ModelAttribute("eProduct") EProduct eProduct) {
		int response = eProductDAO.addProduct(eProduct);
		if(response >0)
			return "success";
		return "failure";
	}

}
