import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {CardComponent} from "./components/card/card.component";
import {CommonModule} from "@angular/common";
import {InfoPageComponent} from "./components/info-page/info-page.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MainPageComponent, CardComponent, CommonModule, InfoPageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
