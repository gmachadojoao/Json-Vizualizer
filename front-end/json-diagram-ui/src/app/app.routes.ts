import { Routes } from '@angular/router';
import { DiagramComponent } from './diagram.component';

export const routes: Routes = [
  { path: '', component: DiagramComponent },
  { path: '**', redirectTo: '' }
];
