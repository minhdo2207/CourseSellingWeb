import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDetailLessonFormComponent } from './course-detail-lesson-form.component';

describe('CourseDetailLessonFormComponent', () => {
  let component: CourseDetailLessonFormComponent;
  let fixture: ComponentFixture<CourseDetailLessonFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDetailLessonFormComponent]
    });
    fixture = TestBed.createComponent(CourseDetailLessonFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
