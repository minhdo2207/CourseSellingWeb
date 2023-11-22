package neu.com.vo.response.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserDetailResponseVO {

    private Long userId;

    private String userEmail;

    private String userName;

    private String userPhone;

    private Date userDob;

    private String userAddress;

    private Integer userGender;

    private String userImage;

}
