package neu.com.vo.request.course;

import lombok.Data;

@Data
public class FindUserRequestVo {
    private String userName;
    private Long roleId;
}
