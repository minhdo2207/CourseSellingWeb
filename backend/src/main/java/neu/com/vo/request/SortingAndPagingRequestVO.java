package neu.com.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortingAndPagingRequestVO {

	private int page;

	private int size;

	private String sortKey;

	private String sortDir;
}
