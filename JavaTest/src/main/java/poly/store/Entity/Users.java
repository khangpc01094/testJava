package poly.store.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@NotBlank( message = "Không để trống Id")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "id ít nhất 8 kí tự bao gồm chữ in hoa chữ thường và số")
	public String id;

	@NotBlank( message = "Không để trống password")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "It nhất 8 kí tự bao gồm kí tự in hoa, Số, in thường và kí tự đạt biệt")
	public String password;
	
	@NotBlank( message = "Không để trống name")
	public String name;

	@NotBlank ( message = "Không để trống số điện thoại")
	@Pattern(regexp = "^\\(?([0-9]{3})\\)?[-]?([0-9]{4})[-]?([0-9]{4})$", message = "vi du: 098-5555-0000")
	public String phonenumber;

	@NotBlank ( message = "Không để trống email")
	@Pattern(regexp = "^[A-Za-z0-9_.]{6,32}@([a-zA-Z0-9]{2,12})(.[a-zA-Z]{2,12})+$", message = "email chưa đúng định dạng")
	public String email;
	
	@NotBlank( message = "Không để trống địa chỉ")
	@Pattern(regexp = "^\\d+[-]+\\d+[,]+[\\s[A-Za-z]\\d]+[,]+[\\s[A-Za-z]]+[,]+[\\s[A-Za-z]]+", message = "vi du: 23-32, An thoi 1, Ninh Kieu, Can Tho")
	public String address;
	

	public String emoji;

    
}