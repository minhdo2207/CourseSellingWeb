package neu.com.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRequestVO {
	@NotBlank(message = "msg_error_field_name_missing")
	private String fullName;

	@NotBlank(message = "msg_error_field_phone_missing")
	@Size(max = 11, min = 10, message = "msg_login_phone_number_too_long")
	private String phone;
}
