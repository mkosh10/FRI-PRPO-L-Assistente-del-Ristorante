import { Routes } from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {InfoPageComponent} from "./components/info-page/info-page.component";
import {NotFoundPageComponent} from "./components/not-found-page/not-found-page.component";

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
    path: 'NotFound',
    component: NotFoundPageComponent
  },
  {
    path: '**',
    component: NotFoundPageComponent
  },
];
