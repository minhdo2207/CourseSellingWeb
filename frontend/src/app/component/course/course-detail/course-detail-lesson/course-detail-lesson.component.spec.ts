import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDetailLessonComponent } from './course-detail-lesson.component';

describe('CourseDetailLessonComponent', () => {
  let component: CourseDetailLessonComponent;
  let fixture: ComponentFixture<CourseDetailLessonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDetailLessonComponent]
    });
    fixture = TestBed.createComponent(CourseDetailLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
