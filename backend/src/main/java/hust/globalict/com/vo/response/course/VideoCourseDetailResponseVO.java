package hust.globalict.com.vo.response.course;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import hust.globalict.com.vo.response.ChapterResponseVO;

import java.util.List;

@Data
@SuperBuilder
public class VideoCourseDetailResponseVO extends CourseDetailResponseVO {
    //TODO add Chapters
    private List<ChapterResponseVO> chaptersVo;

}
