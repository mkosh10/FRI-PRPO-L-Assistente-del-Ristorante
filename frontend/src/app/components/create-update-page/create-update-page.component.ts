import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../../../services/RestaurantService";
import {NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-create-update-page',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './create-update-page.component.html',
  styleUrl: './create-update-page.component.css'
})
export class CreateUpdatePageComponent implements OnInit{
  editForm!: FormGroup;
  type! : string
  id! : number
  popupMessage: string | null = null;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private restaurantService: RestaurantService,
              private fb: FormBuilder,) {
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = +params['id']
      this.type = params['type']
      this.initializeForm()
      this.getById(this.type, this.id)
    })
  }

  initializeForm(): void {
    if (this.type === 'reservations') {
      this.editForm = this.fb.group({
        customerName: [''],
        customerContactInfo: [''],
        numberOfGuests: [''],
        reservationDate: [''],
        tableAssigned: [''],
        specialRequests: [''],
      });
    } else if (this.type === 'menu') {
      this.editForm = this.fb.group({
        name: [''],
        price: [''],
        calories: [''],
        isAvailable: [false],
        isVegetarian: [false],
        isVegan: [false],
        isGlutenFree: [false],
        ingredients: [''],
        description: [''],
      });
    } else if (this.type === 'employees') {
      this.editForm = this.fb.group({
        firstName: [''],
        lastName: [''],
        email: [''],
        position: [''],
        hireDate: [''],
        salary: [''],
      });
    }
  }


  async getById(type: string, id : number){
    try {
      if(type ==='reservations'){
        this.editForm.patchValue(await this.restaurantService.getReservationById(id))
      } else if (type ==='employees'){
        this.editForm.patchValue(await this.restaurantService.getEmployeeById(id))
      } else if(type === 'menu'){
        this.editForm.patchValue(await this.restaurantService.getMenuItemById(id))

      } else {

        console.error("Invalid type", type, id)
        this.router.navigate(['/NotFound']);
      }
      console.log(this.editForm)
    } catch(error) {
      console.error("Error fetching data. Error Message : ", error)
      this.router.navigate(['/NotFound']);
    }
  }


  async saveChanges(){
    try {
      if(this.type ==='reservations'){
        await this.restaurantService.updateReservationById(this.id, this.editForm.value)
      } else if (this.type ==='employees'){
        await this.restaurantService.updateEmployeeInfoById(this.id, this.editForm.value)
      } else if(this.type === 'menu'){
        await this.restaurantService.updateMenuItemById(this.id, this.editForm.value)

      } else {
        console.error("Invalid type", this.type, this.id)
        this.router.navigate(['/NotFound']);
      }
      this.showPopupMessage("This item was successfully updated!")
    } catch(error) {
      console.error("Error updating data. Error Message : ", error)
      this.showPopupMessage("something went wrong. This item was not updated")
    }
  }

  showPopupMessage(message: string){
    this.popupMessage = message
    setTimeout(() => {
      this.popupMessage = null;
      this.router.navigate(['/']);
    }, 4000);
  }


}
