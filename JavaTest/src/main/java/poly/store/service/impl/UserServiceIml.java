package poly.store.service.impl;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.store.DAO.UserDAO;
import poly.store.Entity.Users;
import poly.store.service.UserService;

@Service
public class UserServiceIml implements UserService {
	@Autowired
	UserDAO dUserDAO;
	@Autowired
	HttpServletRequest req;

	@Override
	public ResponseEntity<Users> findById(String id) {
		Optional<Users> account = dUserDAO.findById(id);
		if (!account.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(account.get());
	}

	@Override
	public ResponseEntity<List<Users>> findAll() {		
		List<Users> listUser = dUserDAO.findAll();
		return ResponseEntity.ok(listUser);
	}

	@Override
	public ResponseEntity<Users> create( Users user) {
		if (dUserDAO.existsById(user.getId())) {
			return ResponseEntity.badRequest().build();
		} else {
			dUserDAO.save(user);
			return ResponseEntity.ok(user);
		}
	}

	@Override
	public ResponseEntity<Users> update(Users user) {
		if (!dUserDAO.existsById(user.getId())) {
			return ResponseEntity.notFound().build();
		}
		dUserDAO.save(user);
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		if (!dUserDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			dUserDAO.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}

	@Override
	public Users Save(Users user) {
		// TODO Auto-generated method stub
		return dUserDAO.save(user);
	}

	@Override
	public List<Users> getAll() {
		
		return dUserDAO.findAll();
	}

	@Override
	public Users findByIda(String Id) {
		return dUserDAO.findById(Id).get();
	}

	@Override
	public void deletea(String id) {
		 dUserDAO.deleteById(id);
	}

}
