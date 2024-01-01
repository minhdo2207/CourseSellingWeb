package hust.globalict.com.vo.request;

import lombok.Data;

@Data
public class FindEnrollmentRequestVo {
    private String userName;
    private String courseName;
    private String startDate;
    private String endDate;
    private Long status;
}
