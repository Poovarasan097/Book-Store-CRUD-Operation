package com.Bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Bookstore.Service.MyBookListService;

@Controller
public class MyBookListController
{
	@Autowired
	private MyBookListService service;
	
					
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable(value="id") int id)
	{
		service.deleteById(id);
		
		return "redirect:/my_books";
	}
}
