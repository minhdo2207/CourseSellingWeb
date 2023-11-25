import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { StudentComponent } from './component/student/student.component';
import { TeacherComponent } from './component/teacher/teacher.component';
import { CourseComponent } from './component/course/course.component';
import { ReportComponent } from './component/report/report.component';
import { CourseDetailComponent } from './component/course/course-detail/course-detail.component';
import { CourseDetailLessonComponent } from './component/course/course-detail/course-detail-lesson/course-detail-lesson.component';
import { CourseDetailLessonDetailComponent } from './component/course/course-detail/course-detail-lesson/course-detail-lesson-detail/course-detail-lesson-detail.component';
import { StudentDetailComponent } from './component/student/student-detail/student-detail.component';
import { TeacherDetailComponent } from './component/teacher/teacher-detail/teacher-detail.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'student/list', component: StudentComponent },
  { path: 'student/detail/:id', component: StudentDetailComponent },
  { path: 'teacher/list', component: TeacherComponent },
  { path: 'teacher/detail/:id', component: TeacherDetailComponent },
  { path: 'course/list', component: CourseComponent },
  { path: 'course/detail/:id', component: CourseDetailComponent },
  { path: 'course/detail/setup-lesson/:id', component: CourseDetailLessonComponent },
  { path: 'course/detail/setup-lesson/detail/:id', component: CourseDetailLessonDetailComponent },
  { path: 'report/list', component: ReportComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
