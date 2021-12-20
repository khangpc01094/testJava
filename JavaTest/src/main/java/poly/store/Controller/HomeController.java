package poly.store.Controller;


import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.store.Entity.Users;
import poly.store.service.UploadService;
import poly.store.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService sUsersService;

	@Autowired
	UploadService sUploadService;

	@GetMapping("/signup")
	public String signup( Users users) {
		return "/views/signup";
	}

	@PostMapping("/signup")
	public String Signup(@Valid Users users, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "vui lòng chọn file ảnh");
			return "/views/signup";
		} 
			// users.setEmoji("user.png");
			File s = sUploadService.save(file, "images");
			users.setEmoji(s.getName());
			sUsersService.Save(users);
			
			return "views/login";
	}

	@ModelAttribute
	public Users getObject(Model model) {
		model.addAttribute("users", new Users());
		List<Users> list = sUsersService.getAll();
		model.addAttribute("list", list);
		return null;
	}

	@PreAuthorize("hasRole('user')")
	@GetMapping("/detail")
	public String getForm(Model model) {

		return "/views/detail";
	}

	@GetMapping("/detail/{id}")
	public String getById(Model model, @PathVariable("id") String id) {
		Users user = sUsersService.findByIda(id);
		model.addAttribute("users", user);
		return "/views/detail";
	}

	@PostMapping("detail")
	public String SignupAd(Model model, @RequestParam("file") MultipartFile file, @Valid Users users,	BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/views/detail";
		} else {
				File s = sUploadService.save(file, "images");
				users.setEmoji(s.getName());
				sUsersService.Save(users);
				model.addAttribute("users", new Users());
			return "/views/detail";
		}
			
	}


	@RequestMapping("/detaild/{id}")
	public String Delete(@PathVariable("id") String id, Model model) {
		sUsersService.delete(id);
		List<Users> list = sUsersService.getAll();
		model.addAttribute("list", list);
		return "/views/detail";
	}

}
