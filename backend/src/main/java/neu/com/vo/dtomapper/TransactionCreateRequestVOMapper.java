package neu.com.vo.dtomapper;

import neu.com.model.Transaction;
import neu.com.vo.request.TransactionCreateRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TransactionCreateRequestVOMapper extends CommonMapper<TransactionCreateRequestVO, Transaction> {
}

