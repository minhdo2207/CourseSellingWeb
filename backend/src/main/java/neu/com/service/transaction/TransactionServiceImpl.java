package neu.com.service.transaction;

import com.naharoo.commons.mapstruct.MappingFacade;
import neu.com.configuration.exception.InvalidInputRequestException;
import neu.com.model.Transaction;
import neu.com.repository.TransactionRepository;
import neu.com.vo.request.TransactionRequestVO;
import neu.com.vo.response.course.TransactionResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MappingFacade mapper;

    @Override
    public Object getTransactionRevenue(Long courseType) {
        return null;
    }

    @Override
    public Object updateTransaction(TransactionRequestVO transactionRequestVO, Long transactionId) {
        //Check if question exitst
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (!transactionOptional.isPresent()) {
            throw new InvalidInputRequestException("msg_error_transaction_notfound");
        }
        Transaction transaction = transactionOptional.get();
        transaction.setStatus(transactionRequestVO.getStatus());
        transactionRepository.save(transaction);
        return mapper.map(transaction, TransactionResponseVO.class);
    }
}
