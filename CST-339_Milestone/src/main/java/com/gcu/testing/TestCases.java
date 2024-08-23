package com.gcu.testing;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.gcu.model.LoginModel;
import com.gcu.model.ProductModel;
import com.gcu.model.RegistrationModel;
import com.gcu.service.LoginService;
import com.gcu.service.ProductService;
import com.gcu.service.RegistrationDataService;

@Component
public class TestCases
{
	// Test Data
	String[] characterArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	Random rnd = new Random();
	
	// Test Services
	
	@Autowired
	ProductService productService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegistrationDataService registrationService;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	// Test Cases
	
	public int TestProductService(Model model)
	{
		int failures = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<String> passes = (ArrayList<String>) model.getAttribute("passes");
		@SuppressWarnings("unchecked")
		ArrayList<String> fails = (ArrayList<String>) model.getAttribute("fails");
		
		ProductModel productModel = new ProductModel(RandomText(20), RandomNumbers(14), RandomText(18), 1 + rnd.nextInt(1000));
		
		// Create Test
		
		try
		{
			productService.addProduct(productModel);
			
			System.out.println("Test: Product Service: Creation = Success");
			passes.add("Test: Product Service: Creation");
		}
		catch (Exception e)
		{
			System.out.println("Test: Product Service: Creation = Failure");
			fails.add("Test: Product Service: Creation");
			e.printStackTrace();
			failures++;
		}
		
		// Read Test

		failures += TestProductServiceRead(productModel, model);
		
		// Update Test
		
		productModel.setAuthorName(RandomText(18));
		productModel.setBookName(RandomText(20));
		productModel.setStock(1 + rnd.nextInt(1000));
		try
		{
			productService.updateProduct(productModel);
			
			System.out.println("Test: Product Service: Update = Success");
			passes.add("Test: Product Service: Update");
		}
		catch (Exception e)
		{
			System.out.println("Test: Product Service: Update = Failure");
			fails.add("Test: Product Service: Update");
			e.printStackTrace();
			failures++;
		}

		failures += TestProductServiceRead(productModel, model);
		
		// Delete Test
		
		try
		{
			productService.deleteProduct(productModel.getIsbn());
			productModel = productService.getBookByIsbn(productModel.getIsbn());
			
			if (productModel == null)
			{
				System.out.println("Test: Product Service: Delete = Success");
				passes.add("Test: Product Service: Delete");
			}
			else
			{
				System.out.println("Test: Product Service: Delete = Failure");
				fails.add("Test: Product Service: Delete");
				failures++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Test: Product Service: Delete = Failure");
			fails.add("Test: Product Service: Delete");
			e.printStackTrace();
			failures++;
		}
		
		return failures;
	}
	
	private int TestProductServiceRead(ProductModel productModel, Model model)
	{
		int failures = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<String> passes = (ArrayList<String>) model.getAttribute("passes");
		@SuppressWarnings("unchecked")
		ArrayList<String> fails = (ArrayList<String>) model.getAttribute("fails");
		
		try
		{
			ProductModel retrievedModel = productService.getBookByIsbn(productModel.getIsbn());
			
			if (retrievedModel.getAuthorName().equals(productModel.getAuthorName()))
			{
				System.out.println("Test: Product Service: Read.AuthorName = Success");
				passes.add("Test: Product Service: Read.AuthorName");
			}
			else
			{
				System.out.println("Test: Product Service: Read.AuthorName = Failure");
				fails.add("Test: Product Service: Read.AuthorName");
				failures++;
			}
			
			if (retrievedModel.getBookName().equals(productModel.getBookName()))
			{
				System.out.println("Test: Product Service: Read.BookName = Success");
				passes.add("Test: Product Service: Read.BookName");
			}
			else
			{
				System.out.println("Test: Product Service: Read.BookName = Failure");
				fails.add("Test: Product Service: Read.BookName");
				failures++;
			}
			
			if (retrievedModel.getIsbn().equals(productModel.getIsbn()))
			{
				System.out.println("Test: Product Service: Read.ISBN = Success");
				passes.add("Test: Product Service: Read.ISBN");
			}
			else
			{
				System.out.println("Test: Product Service: Read.ISBN = Failure");
				fails.add("Test: Product Service: Read.ISBN");
				failures++;
			}
			
			if (retrievedModel.getStock() == productModel.getStock())
			{
				System.out.println("Test: Product Service: Read.Stock = Success");
				passes.add("Test: Product Service: Read.Stock");
			}
			else
			{
				System.out.println("Test: Product Service: Read.Stock = Failure");
				fails.add("Test: Product Service: Read.Stock");
				failures++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Test: Product Service: Read = Failure");
			fails.add("Test: Product Service: Read");
			e.printStackTrace();
			failures++;
		}
		
		return failures;
	}
	
	public int TestUserService(Model model)
	{
		int failures = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<String> passes = (ArrayList<String>) model.getAttribute("passes");
		@SuppressWarnings("unchecked")
		ArrayList<String> fails = (ArrayList<String>) model.getAttribute("fails");
		
		RegistrationModel registrationModel =
			new RegistrationModel(
				RandomText(16),
				RandomText(16),
				(RandomText(16) + "@mail.com"),
				RandomNumbers(10),
				RandomText(16),
				RandomText(20),
				RandomText(16),
				RandomText(16),
				RandomText(16),
				RandomNumbers(5)
			);
		
		// Create Test
		
		try
		{
			while (registrationService.isEmailUsed(registrationModel.getEmail()))
				registrationModel.setEmail(RandomText(16) + "@mail.com");
			while (registrationService.isUsernameTaken(registrationModel.getUsername()))
				registrationModel.setUsername(RandomText(16));
			if (registrationService.createUser(registrationModel))
			{
				System.out.println("Test: Registration Service: Creation = Success");
				passes.add("Test: Registration Service: Creation");
			}
			else
			{
				System.out.println("Test: Registration Service: Creation = Failure");
				fails.add("Test: Registration Service: Creation");
			}
		}
		catch (Exception e)
		{
			System.out.println("Test: Registration Service: Creation = Failure");
			fails.add("Test: Registration Service: Creation");
			e.printStackTrace();
		}
		
		// Read Test
		
		try
		{
			boolean isEmailTaken = registrationService.isEmailUsed(registrationModel.getEmail()),
					isUsernameTaken = registrationService.isUsernameTaken(registrationModel.getUsername());

			if (isEmailTaken)
			{
				System.out.println("Test: Registration Service: Taken Email = Success");
				passes.add("Test: Registration Service: Taken Email");
			}
			else
			{
				System.out.println("Test: Registration Service: Taken Email = Failure");
				fails.add("Test: Registration Service: Taken Email");
			}
			
			if (isUsernameTaken)
			{
				System.out.println("Test: Registration Service: Taken Username = Success");
				passes.add("Test: Registration Service: Taken Username");
			}
			else
			{
				System.out.println("Test: Registration Service: Taken Username = Failure");
				fails.add("Test: Registration Service: Taken Username");
			}
		}
		catch (Exception e)
		{
			System.out.println("Test: Registration Service: Taken Email/Username = Failure");
			fails.add("Test: Registration Service: Taken Email/Username");
			e.printStackTrace();
		}
		
		try
		{
			UserDetails userDetails = loginService.loadUserByUsername(registrationModel.getUsername());
			
			if (userDetails.getUsername().equals(registrationModel.getUsername()))
			{
				System.out.println("Test: Login Service: Authentication.Username = Success");
				passes.add("Test: Login Service: Authentication.Username");
			}
			else
			{
				System.out.println("Test: Login Service: Authentication.Username = Failure");
				fails.add("Test: Login Service: Authentication.Username");
			}
			
			if (encoder.matches(registrationModel.getPassword(), userDetails.getPassword()))
			{
				System.out.println("Test: Login Service: Authentication.Password = Success");
				passes.add("Test: Login Service: Authentication.Password");
			}
			else
			{
				System.out.println("Test: Login Service: Authentication.Password = Failure");
				fails.add("Test: Login Service: Authentication.Password");
			}
		}
		catch (Exception e)
		{
			System.out.println("Test: Login Service: Authentication = Failure");
			fails.add("Test: Login Service: Authentication");
			e.printStackTrace();
		}
		
		return failures;
	}
	
	// Test Functions
	
	private String RandomText(int length)
	{
		String text = "";
		
		for (int i = 1; i <= length; i++)
			text += text.length() == 0 ? characterArray[rnd.nextInt(characterArray.length)].toUpperCase() : characterArray[rnd.nextInt(characterArray.length)];
		
		return text;
	}
	
	private String RandomNumbers(int length)
	{
		String text = "";
		
		for (int i = 1; i <= length; i++)
			text += text.length() == 0 ? 1 + rnd.nextInt(9) : rnd.nextInt(10);
		
		return text;
	}
}
