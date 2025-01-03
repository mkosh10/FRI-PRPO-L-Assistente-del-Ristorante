import {Component, OnInit} from '@angular/core';
import {NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../../../services/RestaurantService";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

@Component({
  selector: 'app-create-new-page',
  standalone: true,
    imports: [
        NgIf,
        ReactiveFormsModule
    ],
  templateUrl: './create-new-page.component.html',
  styleUrl: './create-new-page.component.css'
})
export class CreateNewPageComponent implements OnInit{
  editForm!: FormGroup;
  type! : string
  popupMessage: string | null = null;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private restaurantService: RestaurantService,
              private fb: FormBuilder,) {
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.type = params['type']
      this.initializeForm()
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
        arrivalTime: ['']
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



  async createNewItem(){
    try {
      console.log("ova se prakja kako request", this.editForm.value)
      if(this.type ==='reservations'){
        if(this.editForm.value.arrivalTime){
          console.log("vlegov tuka")
          this.editForm.value.arrivalTime = this.editForm.value.arrivalTime.replace('T', ' ')
        }
        await this.restaurantService.addNewReservation(this.editForm.value)
      } else if (this.type ==='employees'){
        await this.restaurantService.addNewEmployee(this.editForm.value)
      } else if(this.type === 'menu'){
        await this.restaurantService.addNewMenuItem(this.editForm.value)
      } else {
        console.error("Invalid type", this.type)
        this.router.navigate(['/NotFound']);
      }
      this.showPopupMessage("Created!")
      console.log("passing this thing",this.editForm.value)
    } catch(error) {
      console.error("Error updating data. Error Message : ", error)
      this.showPopupMessage("Something went wrong. This item was not created ")
    }
  }

  showPopupMessage(message: string){
    this.popupMessage = message
    setTimeout(() => {
      this.popupMessage = null;
      if(!message.includes('wrong')){
        this.router.navigate(['/']);
      }
    }, 3000);
  }
}
