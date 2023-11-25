import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherDetailFormComponent } from './teacher-detail-form.component';

describe('TeacherDetailFormComponent', () => {
  let component: TeacherDetailFormComponent;
  let fixture: ComponentFixture<TeacherDetailFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeacherDetailFormComponent]
    });
    fixture = TestBed.createComponent(TeacherDetailFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
