import { Routes } from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {InfoPageComponent} from "./components/info-page/info-page.component";

export const routes: Routes = [
  {
    path: '',
    component: MainPageComponent,
    pathMatch: 'full'
  },
  {
    path: 'details/:type/:id',
    component: InfoPageComponent
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full',
  },
];
