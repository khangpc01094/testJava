package poly.store.RestController;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import poly.store.Entity.Users;
import poly.store.service.UploadService;
import poly.store.service.UserService;

@CrossOrigin("*")
@RestController
public class UserRestController {
	@Autowired
	UserService accountService;
	
	@Autowired
	UploadService sUploadService;
	
	@PreAuthorize("hasRole('user')")
	@GetMapping("/getall")
	public ResponseEntity<List<Users>>findAll(){
		return accountService.findAll();
	}
	
	@PostMapping("/upload/images/{path}")
	   public JsonNode upload(@PathVariable("file") MultipartFile file) {
		   File savedFile = sUploadService.save(file, "images");
		   
		   ObjectMapper mapper = new ObjectMapper();
		   ObjectNode node = mapper.createObjectNode();
		   node.put("name",savedFile.getName());
		   node.put("size",savedFile.length());
		   return node;
	   }
	
	@PostMapping("/user")
	public ResponseEntity<Users> create(@Valid @RequestBody Users user) {
			return accountService.create(user);
	
		
	}

	@PreAuthorize("hasRole('user')")
	@PutMapping("/user")
	public ResponseEntity<Users> update(@Valid @RequestBody Users user) {
		return accountService.update(user);
	}

	@PreAuthorize("hasRole('user')")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return accountService.delete(id);
	}

}
