package com.Bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.Bookstore.Entity.Book;
import com.Bookstore.Entity.MyBookList;
import com.Bookstore.Service.BookService;
import com.Bookstore.Service.MyBookListService;

import ch.qos.logback.core.model.Model;

@Controller
public class BookController
{
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home ()
	{
		return "home";
	}
	@GetMapping("/book_register")
	public String bookRegister()
	{
		return "bookRegister";
	}
	@GetMapping("/available_books")
	public ModelAndView getAllBook()
	{
		List<Book>list=service.getAllBook();
		//ModelAndView m=new ModelAndView();
		//m.setViewName("bookList");
		//m.addObject(addBook(null),list);
		return new ModelAndView("bookList","book",list);
	}
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b)
	{
		service.save(b);
		return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(org.springframework.ui.Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books"; 
	}
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id")int id,org.springframework.ui.Model model)
	{
		Book b=service.getBookById(id);
		
		model.addAttribute("book", b);
		return "bookEdit";		
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		service.deleteById(id);
		return "redirect:/available_books";
	}

}
