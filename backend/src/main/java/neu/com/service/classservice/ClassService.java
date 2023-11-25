package neu.com.service.classservice;

import neu.com.vo.request.course.ClassRequestVO;

public interface ClassService {
    Object updateClass(ClassRequestVO classRequestVO, Long classId);

    Object deleteClass(Long classId);

    Object addClass(ClassRequestVO classRequestVO, Long courseId);

    Object getClass(Long classId);
}
