import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDetailLessonDetailFormComponent } from './course-detail-lesson-detail-form.component';

describe('CourseDetailLessonDetailFormComponent', () => {
  let component: CourseDetailLessonDetailFormComponent;
  let fixture: ComponentFixture<CourseDetailLessonDetailFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDetailLessonDetailFormComponent]
    });
    fixture = TestBed.createComponent(CourseDetailLessonDetailFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
