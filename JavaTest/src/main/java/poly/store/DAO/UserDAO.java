package poly.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.Entity.Users;

public interface UserDAO extends JpaRepository<Users, String>{
}
