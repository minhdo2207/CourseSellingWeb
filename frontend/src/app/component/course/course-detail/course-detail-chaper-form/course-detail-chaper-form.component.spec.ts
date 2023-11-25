import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDetailChaperFormComponent } from './course-detail-chaper-form.component';

describe('CourseDetailChaperFormComponent', () => {
  let component: CourseDetailChaperFormComponent;
  let fixture: ComponentFixture<CourseDetailChaperFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CourseDetailChaperFormComponent]
    });
    fixture = TestBed.createComponent(CourseDetailChaperFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
