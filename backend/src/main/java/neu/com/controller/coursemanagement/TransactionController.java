package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.transaction.TransactionService;
import neu.com.utils.Constants;
import neu.com.vo.request.TransactionRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    /**
     * Update transaction
     */

    @PutMapping("/{transactionId}")
    public Object updateTransaction(@Valid @RequestBody TransactionRequestVO transactionRequestVO, @PathVariable("transactionId") Long transactionId) {
        return transactionService.updateTransaction(transactionRequestVO, transactionId);
    }
}
