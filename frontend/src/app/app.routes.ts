import { Routes } from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {InfoPageComponent} from "./components/info-page/info-page.component";
import {NotFoundPageComponent} from "./components/not-found-page/not-found-page.component";
import {CreateUpdatePageComponent} from "./components/create-update-page/create-update-page.component";
import {CreateNewPageComponent} from "./components/create-new-page/create-new-page.component";

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
    path: 'edit/:type/:id',
    component: CreateUpdatePageComponent
  },
  {
    path: 'create/:type',
    component: CreateNewPageComponent
  },
  {
    path: '**',
    component: NotFoundPageComponent
  },
];
