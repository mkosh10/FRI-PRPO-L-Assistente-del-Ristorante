import { Component } from '@angular/core';
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-not-found-page',
  standalone: true,
    imports: [
        NgIf
    ],
  templateUrl: './not-found-page.component.html',
  styleUrl: './not-found-page.component.css'
})
export class NotFoundPageComponent {

}
