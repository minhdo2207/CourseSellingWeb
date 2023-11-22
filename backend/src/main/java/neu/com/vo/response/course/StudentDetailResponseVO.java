package neu.com.vo.response.course;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class StudentDetailResponseVO extends UserDetailResponseVO {
    private List<TransactionResponseVO> transactionResponseVOS;
}
