package com.assignment.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assignment.model.ActiveLinkMenu;
import com.assignment.model.Item;
import com.assignment.model.User;
import com.assignment.repository.ItemRepository;
import com.assignment.repository.UserRepository;




@Controller
public class AssignmentController {
	
	
	public AssignmentController() {
		
	}

	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ItemRepository itemRepo;
	
	Logger logger = LoggerFactory.getLogger(AssignmentController.class);
	
	
	
	
	@GetMapping("/")
	public String rootUrl(Model model) {

		return "redirect:/login"; 
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
	    
		return "login";
	}
	// Dashboard
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute ActiveLinkMenu menu, Model model, Principal principal) {
		
		 try {
		        String userName = principal.getName();
		        User user = userRepository.getUserByUserName(userName);
		      
		        
		        model.addAttribute("user", user);
		        menu.setDashboardLink("active");
		        model.addAttribute("menu", menu);
		        
		        return "dashboard";
		        
		    } catch (Exception e) {
		        logger.error("An error occurred while loading the dashboard for user: {}", principal.getName(), e);
		        return "error"; 
		    }
	}
	
	//********************Crud Opretion****************
	// Show Add Form Method
	
	@GetMapping("/add")
	public String addOpretion(@ModelAttribute ActiveLinkMenu menu, @ModelAttribute Item item , Model model, Principal principal) {
		
		try {
	        String userName = principal.getName();
	        User user = userRepository.getUserByUserName(userName);
	        
	        model.addAttribute("user", user);
	        menu.setUserLink("active");
	        model.addAttribute("menu", menu);
	        if (item == null) {
	           
	        	return "add-form";
	        }
	        
	        return "add-form";
	    } catch (Exception e) {
	        // Log the exception
	    	logger.error("An error occurred in addOperation method: {}", e.getMessage(), e);
	        
	        return "error-page";
	    }
	}
	
	//SAVE OPRETION
	@PostMapping("/save-item-form")
	public String saveItem(@ModelAttribute ActiveLinkMenu menu,
			@ModelAttribute("item") Item item, BindingResult result, Model model, Principal principal,
			RedirectAttributes redirectAttrs) {

		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
			
		try {
			
			item.setUser(user);
		    itemRepo.save(item);
		    
		    // Set appropriate success message
		    if (item != null) {
		        redirectAttrs.addFlashAttribute("messageSuccess", "Success.");
	    }  else if (item == null) {
	    	
	    	  redirectAttrs.addFlashAttribute("messageSuccess2", "Updated.");
		}
		     
		} catch (Exception e) {
		    // Log the error
		    logger.error("An error occurred in saveItem method: {}", e.getMessage(), e);
		    
		    // Set error message
		    redirectAttrs.addFlashAttribute("messageError", "Error while saving item.");
		}

		// Redirect to item list page
		return "redirect:/item-list";

	}
	
	
	// READ LIST
	@GetMapping("/item-list")
	public String viewItemList(@ModelAttribute ActiveLinkMenu menu, Model model, Principal principal,
			@ModelAttribute Item item) {
		try {
			String userName = principal.getName();
			User user = userRepository.getUserByUserName(userName);

			List<Item> itemList = itemRepo.findAllByUserId(user.getId());

			model.addAttribute("itemList", itemList);
			menu.setListLink("active");
			model.addAttribute("menu", menu);
			model.addAttribute("user", user);

			return "item-list";
		} catch (Exception e) {
			// Log the exception
			logger.error("An error occurred in viewItemList method: {}", e.getMessage(), e);

			return "error-page";
		}
	}
	
	// DELETE OPERATION
	@GetMapping("/delete-item/{itemId}")
	public String deleteItem(@PathVariable int itemId, RedirectAttributes redirectAttrs) {
		try {
			itemRepo.deleteById(itemId);
			redirectAttrs.addFlashAttribute("messageSuccess1", "Item deleted successfully.");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("messageError", "Error occurred while deleting the item.");
			logger.error("An error occurred in deleteItem method: {}", e.getMessage(), e);
		}
		return "redirect:/item-list";
	}
	
		
	@GetMapping("/edit-item/{itemId}")
	public String editUser(@ModelAttribute ActiveLinkMenu menu, Model model, Principal principal,
			RedirectAttributes redirectAttrs, @PathVariable int itemId) {
		try {
			String userName = principal.getName();
			User user = userRepository.getUserByUserName(userName);

			Item itms = itemRepo.getById(itemId);

			model.addAttribute("user", user);
			model.addAttribute("itms", itms);
			menu.setListLink("active");

			model.addAttribute("menu", menu);

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("messageError", "Error occurred while edit the item.");
			logger.error("An error occurred in editITEm method: {}", e.getMessage(), e);
		}

		return "update-form";
	}
	 
	 
	@GetMapping("/user-registration")
	public String userRegistration(Model model, RedirectAttributes redirectAttrs) {
		try {
			redirectAttrs.addFlashAttribute("messageSuccess", "Registration Successfully");
		} catch (Exception e) {
			// Handle the exception and log it properly.
			redirectAttrs.addFlashAttribute("messageError", "An error occurred during registration.");
			logger.error("An error occurred in the userRegistration method: {}", e.getMessage(), e);
		}
		return "user-registration"; // Return the view name for user registration.
	}
	 



}
