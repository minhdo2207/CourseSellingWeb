package neu.com.vo.request;

import lombok.Data;

@Data
public class TransactionCreateRequestVO {

    private String transactionDate;

    private Long transactionValue;

    private Long status;

}
