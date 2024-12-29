import {Component, Input, OnInit} from '@angular/core';
import {CardComponent} from "../card/card.component";
import {NgForOf, NgIf} from "@angular/common";
import {Employee} from "../../models/Employee";
import {Reservation} from "../../models/Reservation";
import {MenuItem} from "../../models/MenuItem";
import {RestaurantService} from "../../../services/RestaurantService"
import {ActivatedRoute} from "@angular/router";
import {routes} from "../../app.routes";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

@Component({
  selector: 'app-info-page',
  standalone: true,
  imports: [
    CardComponent,
    NgForOf,
    NgIf
  ],
  templateUrl: './info-page.component.html',
  styleUrl: './info-page.component.css'
})
export class InfoPageComponent implements OnInit{

  @Input() infoData!: Employee | Reservation | MenuItem;

  id!: number;
  type!: string;
  data!:  Employee | Reservation | MenuItem;
  constructor(private route: ActivatedRoute, private restaurantService: RestaurantService) {
  }

  ngOnInit(){
    this.route.params.subscribe(params => {
      this.id = +params['id']
      this.type = params['type']
      this.getById(this.type, this.id).then(r => console.log("reposneeee e", r))

    })
  }

  async getById(type: string, id : number){
    try {
      if(type ==='reservations'){
        this.data = await this.restaurantService.getReservationById(id)
      } else if (type ==='employees'){
        this.data = await  this.restaurantService.getEmployeeById(id)
      } else if(type === 'menu'){
        this.data = await  this.restaurantService.getMenuItemById(id)

      } else {

        console.error("Invalid type", type, id)
      }
      console.log(this.data)
    } catch(error) {
      console.error("Error fetching data. Error Message : ", error)
    }
  }
  asEmployee(): Employee | null {
    return this.infoData as Employee;
  }
  asReservation(): Reservation | null {
    return this.infoData as Reservation;
  }

  asMenuItem(): MenuItem | null {
    return this.infoData as MenuItem;
  }




}
