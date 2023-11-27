package neu.com.controller.coursemanagement;

import jakarta.validation.Valid;
import neu.com.service.transaction.TransactionService;
import neu.com.utils.Constants;
import neu.com.vo.request.TransactionCreateRequestVO;
import neu.com.vo.request.TransactionRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    //TODO: ADD transaction
    @PostMapping("/student/{userID}/course/{courseId}")
    public Object createTransaction(@Valid @RequestBody TransactionCreateRequestVO transactionRequestVO, @PathVariable("userID") Long userID, @PathVariable("courseId") Long courseId) throws ParseException {
        return transactionService.createTransaction(transactionRequestVO, userID, courseId);
    }

    //TODO: DELETE transaction
    @DeleteMapping("/{transactionId}")
    public Object deleteTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }
}
