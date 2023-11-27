package neu.com.service.transaction;

import neu.com.vo.request.TransactionCreateRequestVO;
import neu.com.vo.request.TransactionRequestVO;

import java.text.ParseException;

public interface TransactionService
{
    Object getTransactionRevenue(Long courseType);

    Object updateTransaction(TransactionRequestVO transactionRequestVO, Long transactionId);

    Object createTransaction(TransactionCreateRequestVO transactionRequestVO,Long userID, Long courseId) throws ParseException;

    Object deleteTransaction(Long transactionId);
}
