package hust.globalict.com.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "msg_login_username_missing")
	private String username;

	@NotBlank(message = "msg_login_pwd_missing")
	private String password;

}
