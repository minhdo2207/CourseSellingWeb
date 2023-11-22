package neu.com.vo.request.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCourseRequestVo {
	private String name;

	private Long type;
}
	