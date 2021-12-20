package poly.store.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.SecurityConfig;

@Controller
public class LoginController {
    
	@Autowired
	SecurityConfig cSecurityConfig;

    @GetMapping("/login")
	public String login() {
		return "/views/login";
	}

	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "/views/login";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "/views/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		return "/views/detail";
	}
	

    @GetMapping("/menu")
	public String menu() {
		return "/layout/menu";
	}
}
