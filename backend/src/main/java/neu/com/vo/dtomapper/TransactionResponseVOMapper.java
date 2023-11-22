package neu.com.vo.dtomapper;

import neu.com.model.Transaction;
import neu.com.vo.response.course.TransactionResponseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TransactionResponseVOMapper extends CommonMapper<Transaction, TransactionResponseVO> {
}

