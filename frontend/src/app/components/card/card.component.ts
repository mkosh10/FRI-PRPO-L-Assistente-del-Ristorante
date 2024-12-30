import {Component, Input} from '@angular/core';
import {Employee} from "../../models/Employee";
import {Reservation} from "../../models/Reservation";
import {MenuItem} from "../../models/MenuItem";
import {NgFor, NgIf} from "@angular/common";


@Component({
  selector: 'app-card',
  standalone: true,
  imports: [    NgIf,
    NgFor],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {

  @Input() cardData!: Employee | Reservation | MenuItem;
  @Input() selectedListName!: string ;
  asEmployee(): Employee | null {
    return this.cardData as Employee;
  }
  asReservation(): Reservation | null {
    return this.cardData as Reservation;
  }

  asMenuItem(): MenuItem | null {
    return this.cardData as MenuItem;
  }

}
