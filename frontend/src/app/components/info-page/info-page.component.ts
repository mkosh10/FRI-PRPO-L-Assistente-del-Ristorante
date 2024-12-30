import {Component, Input, OnInit} from '@angular/core';
import {CardComponent} from "../card/card.component";
import {NgForOf, NgIf} from "@angular/common";
import {Employee} from "../../models/Employee";
import {Reservation} from "../../models/Reservation";
import {MenuItem} from "../../models/MenuItem";
import {RestaurantService} from "../../../services/RestaurantService"
import {ActivatedRoute, Router} from "@angular/router";
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

  id!: number;
  type!: string;
  infoData!:  Employee | Reservation | MenuItem;
  popupMessage: string | null = null;
  constructor(private route: ActivatedRoute, private router: Router, private restaurantService: RestaurantService) {
  }

  ngOnInit(){
    this.route.params.subscribe(params => {
      this.id = +params['id']
      this.type = params['type']
      this.getById(this.type, this.id)
    })
  }

  async getById(type: string, id : number){
    try {
      if(type ==='reservations'){
        this.infoData = await this.restaurantService.getReservationById(id)
      } else if (type ==='employees'){
        this.infoData = await  this.restaurantService.getEmployeeById(id)
      } else if(type === 'menu'){
        this.infoData = await  this.restaurantService.getMenuItemById(id)

      } else {

        console.error("Invalid type", type, id)
        this.router.navigate(['/NotFound']);
      }
      console.log(this.infoData)
      console.log(this.asReservation()?.id)
    } catch(error) {
      console.error("Error fetching data. Error Message : ", error)
        this.router.navigate(['/NotFound']);
    }
  }

  deleteThisData() {
    try {
      console.log("delete this data called")
      console.log(this.infoData)
      if(this.type ==='employees' && this.asEmployee()?.id != null) {
        this.restaurantService.deleteEmployeeId(this.asEmployee()?.id!!).then(res => {
          this.showPopupMessage("Item deleted successfully!")
        })

      } else if(this.type ==='reservations' && this.asReservation()?.id != null) {
        this.restaurantService.deleteReservationById(this.asReservation()?.id!!).then(res => {
          this.showPopupMessage("Item deleted successfully!")
        })
      } else if(this.type ==='menu' && this.asMenuItem()?.id != null) {
        this.restaurantService.deleteMenuItemById(this.asMenuItem()?.id!!).then(res => {
          this.showPopupMessage("Item deleted successfully!")
        })
      } else {
        console.log("nothing happened")
      }
    } catch(error) {
      this.showPopupMessage("Something went wrong")
      console.error("Delete details error:", error)
    }
  }

  showPopupMessage(message: string){
    this.popupMessage = message
    setTimeout(() => {
      this.popupMessage = null;
      this.router.navigate(['/']);
    }, 3000);
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
