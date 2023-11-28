import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentFormClassComponent } from './student-form-class.component';

describe('StudentFormClassComponent', () => {
  let component: StudentFormClassComponent;
  let fixture: ComponentFixture<StudentFormClassComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentFormClassComponent]
    });
    fixture = TestBed.createComponent(StudentFormClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
