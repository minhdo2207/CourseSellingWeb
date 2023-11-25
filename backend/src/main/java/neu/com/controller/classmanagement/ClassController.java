package neu.com.controller.classmanagement;

import jakarta.validation.Valid;
import neu.com.service.classservice.ClassService;
import neu.com.utils.Constants;
import neu.com.vo.request.course.ClassRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_VERSION + "/admin/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/{classId}")
    public Object getClass(@PathVariable("classId") Long classId) {
        return classService.getClass(classId);
    }
    @PutMapping("/{classId}")
    public Object updateClass(@Valid @RequestBody ClassRequestVO classRequestVO, @PathVariable("classId") Long classId) {
        return classService.updateClass(classRequestVO, classId);

    }

    @DeleteMapping("/{classId}")
    public Object deleteClass(@PathVariable("classId") Long classId) {
        return classService.deleteClass(classId);
    }

    @PostMapping("/course/{courseId}")
    public Object addClass(@Valid @RequestBody ClassRequestVO classRequestVO, @PathVariable("courseId") Long courseId) {
        return classService.addClass(classRequestVO, courseId);
    }
}
