package neu.com.service.transaction;

import neu.com.vo.request.TransactionRequestVO;

public interface TransactionService
{
    Object getTransactionRevenue(Long courseType);

    Object updateTransaction(TransactionRequestVO transactionRequestVO, Long transactionId);
}
