package neu.com.vo.enumData;


public enum CourseType {
    VIDEO_COURSE(1L),
    MEETING_COURSE(2L);

    private final Long value;

    CourseType(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
