import {Component, OnInit} from '@angular/core';
import {NgFor, NgIf} from "@angular/common";
import {CardComponent} from "../card/card.component";
import {Employee} from "../../models/Employee";
import {Reservation} from "../../models/Reservation";
import {MenuItem} from "../../models/MenuItem";
import {Router} from "@angular/router";
import {RestaurantService} from "../../../services/RestaurantService";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    CardComponent,
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent implements OnInit{

 ngOnInit(){
   console.log("MAIN PAGE INITIALIZED")

   if(this.selectedListName === 'Reservations'){
     this.fetchAllReservations()
   } else if(this.selectedListName === 'Employees'){
     this.fetchAllEmployees()
   } else if(this.selectedListName === 'MenuItems'){
     this.fetchAllMenuItems()
   }
 }

 constructor(private router: Router, private restaurantService: RestaurantService) {
 }
  selectedListName : string = "Reservations"


  reservations : Reservation[] = []
  employees: Employee[] = []
  menuItems: MenuItem[] = []


  setSelectedList(listName : string) : void{
    this.selectedListName = listName
    console.log(this.selectedListName)
    if(this.selectedListName === 'Reservations'){
      this.fetchAllReservations()
    } else if(this.selectedListName === 'Employees'){
      this.fetchAllEmployees()
    } else if(this.selectedListName === 'MenuItems'){
      this.fetchAllMenuItems()
    }
  }

  viewClickedObject(object : Reservation| MenuItem |  Employee ){
    console.log("objektot za gledanje" + object)
    if((object as Reservation).id != null && this.selectedListName === 'Reservations'){
      this.router.navigate(['/details', 'reservations', (object as Reservation).id]);
    } else if ((object as Employee).id != null && this.selectedListName === 'Employees'){
      this.router.navigate(['/details', 'employees', (object as Employee).id]);

    } else if((object as MenuItem).id != null && this.selectedListName === 'MenuItems'){
      this.router.navigate(['/details', 'menu', (object as MenuItem).id]);
    } else {
      console.log(object)
    }
  }



  async fetchAllReservations(): Promise<void>{
    try {
      this.reservations = await this.restaurantService.getReservations()
    } catch (error){
      console.error("GetAllReservations() function", error)
    }
  }

  async fetchAllEmployees(): Promise<void>{
    try {
      this.employees = await this.restaurantService.getEmployees()
    } catch (error){
      console.error("GetAllReservations() function", error)
    }
  }

  async fetchAllMenuItems(): Promise<void>{
    try {
      this.menuItems = await this.restaurantService.getMenuItems()
    } catch (error){
      console.error("GetAllReservations() function", error)
    }
  }
}
