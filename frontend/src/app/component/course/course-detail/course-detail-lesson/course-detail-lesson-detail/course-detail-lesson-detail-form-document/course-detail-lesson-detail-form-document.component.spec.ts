import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDetailLessonDetailFormDocumentComponent } from './course-detail-lesson-detail-form-document.component';

describe('CourseDetailLessonDetailFormDocumentComponent', () => {
  let component: CourseDetailLessonDetailFormDocumentComponent;
  let fixture: ComponentFixture<CourseDetailLessonDetailFormDocumentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDetailLessonDetailFormDocumentComponent]
    });
    fixture = TestBed.createComponent(CourseDetailLessonDetailFormDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
