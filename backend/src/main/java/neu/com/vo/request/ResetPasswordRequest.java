package neu.com.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank(message = "msg_reset_password_username_missing")
    private String account;

    @NotBlank(message = "msg_reset_password_old_pwd_missing")
    private String oldPassword;

    @NotBlank(message = "msg_reset_password_new_pwd_missing")
    private String newPassword;
}
