package neu.com.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseVO {

	private String token;

	private String refreshToken;

	private Long id;

	private String username;

	private String fullName;

	private String email;

	private String phone;

	private Boolean isFirstLogin;

	private Boolean isInternal;

	private List<String> roles;

	private List<String> permissions;
}
