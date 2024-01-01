package hust.globalict.com.vo.request.course;

import lombok.Data;

@Data
public class UserCreateRequestVO {
    private String userName;

    private String userEmail;

    private String userPhone;

    private String userAddress;
}
