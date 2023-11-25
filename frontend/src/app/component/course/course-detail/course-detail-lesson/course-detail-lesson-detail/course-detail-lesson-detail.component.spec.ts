import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDetailLessonDetailComponent } from './course-detail-lesson-detail.component';

describe('CourseDetailLessonDetailComponent', () => {
  let component: CourseDetailLessonDetailComponent;
  let fixture: ComponentFixture<CourseDetailLessonDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDetailLessonDetailComponent]
    });
    fixture = TestBed.createComponent(CourseDetailLessonDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
