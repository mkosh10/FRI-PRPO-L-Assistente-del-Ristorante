import { Component } from '@angular/core';
import {NgFor, NgIf} from "@angular/common";
import {CardComponent} from "../card/card.component";
import {Employee} from "../../models/Employee";
import {Reservation} from "../../models/Reservation";
import {MenuItem} from "../../models/MenuItem";

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
export class MainPageComponent {

  selectedListName : string = "Reservations"
  reservations : Reservation[]  = [
    {
      reservationId: 1,
      customerName: "John",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: "Vegetarian meal"
    },
    {
      reservationId: 2,
      customerName: "Jane",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: "Window seat",
    },
    {
      reservationId: 3,
      customerName: "Jane",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: undefined,
    },
    {
      reservationId: 4,
      customerName: "Jane",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: "Window seat",
    },
    {
      reservationId: 5,
      customerName: "Jane",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: "Window seat"
    },
    {
      reservationId: 6,
      customerName: "Jane",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: "Window seat",
    },
    {
      reservationId: 7,
      customerName: "Hello",
      customerContactInfo: "john@Doe.com",
      numberOfGuests: 2,
      reservationDate: "2024-12-25",
      tableAssigned: 5,
      specialRequests: "Window seat",
    }
  ];

  employees: Employee[] = [
    {
      employeeId: 1,
      firstName: "Alice",
      lastName: "Johnson",
      email: "alice.johnson@restaurant.com",
      position: "Chef",
      hireDate: "2020-04-15",
      salary: 55000,
    },
    {
      employeeId: 2,
      firstName: "Robert",
      lastName: "Miller",
      email: "robert.miller@restaurant.com",
      position: "Waiter",
      hireDate: "2021-06-20",
      salary: 35000,
    },
    {
      employeeId: 3,
      firstName: "Emily",
      lastName: "Davis",
      email: "emily.davis@restaurant.com",
      position: "Manager",
      hireDate: "2018-09-10",
      salary: 75000,
    },
    {
      employeeId: 4,
      firstName: "Michael",
      lastName: "Brown",
      email: "michael.brown@restaurant.com",
      position: "Bartender",
      hireDate: "2019-03-25",
      salary: 40000,
    },
    {
      employeeId: 5,
      firstName: "Sophia",
      lastName: "Wilson",
      email: "sophia.wilson@restaurant.com",
      position: "Sous Chef",
      hireDate: "2022-01-10",
      salary: 50000,
    },
    {
      employeeId: 6,
      firstName: "James",
      lastName: "Anderson",
      email: "james.anderson@restaurant.com",
      position: "Dishwasher",
      hireDate: "2023-05-15",
      salary: 25000,
    },
    {
      employeeId: 7,
      firstName: "Olivia",
      lastName: "Martinez",
      email: "olivia.martinez@restaurant.com",
      position: "Hostess",
      hireDate: "2020-11-05",
      salary: 30000,
    },
  ];

  menuItems: MenuItem[] = [
    {
      menuItemId: 1,
      name: "Cheeseburger",
      description: "A classic cheeseburger with cheddar cheese, lettuce, and tomato.",
      price: 10.99,
      category: "Main Course",
      isAvailable: true,
      isVegetarian: false,
      isVegan: false,
      isGlutenFree: false,
      ingredients: "Beef Patty, Cheddar Cheese, Lettuce, Tomato, Pickles, Burger Bun",
      calories: 500,
    },
    {
      menuItemId: 2,
      name: "Margherita Pizza",
      description: "A traditional pizza with fresh tomatoes, mozzarella, and basil.",
      price: 12.99,
      category: "Main Course",
      isAvailable: true,
      isVegetarian: true,
      isVegan: false,
      isGlutenFree: true,
      ingredients: "Tomato Sauce, Mozzarella, Basil, Olive Oil",
      calories: 600,
    },
    {
      menuItemId: 3,
      name: "Caesar Salad",
      description: "Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan cheese.",
      price: 8.99,
      category: "Appetizer",
      isAvailable: true,
      isVegetarian: true,
      isVegan: false,
      isGlutenFree: false,
      ingredients: "Romaine Lettuce, Caesar Dressing, Croutons, Parmesan Cheese",
      calories: 350,
    },
    {
      menuItemId: 4,
      name: "Grilled Salmon",
      description: "Freshly grilled salmon served with a side of vegetables.",
      price: 15.99,
      category: "Main Course",
      isAvailable: true,
      isVegetarian: false,
      isVegan: false,
      isGlutenFree: true,
      ingredients: "Salmon Fillet, Lemon, Olive Oil, Mixed Vegetables",
      calories: 400,
    },
    {
      menuItemId: 5,
      name: "Chocolate Cake",
      description: "Rich chocolate cake with a gooey center and whipped cream topping.",
      price: 6.99,
      category: "Dessert",
      isAvailable: true,
      isVegetarian: true,
      isVegan: false,
      isGlutenFree: false,
      ingredients: "Flour, Cocoa Powder, Sugar, Eggs, Butter, Whipped Cream",
      calories: 450,
    },
    {
      menuItemId: 6,
      name: "Vegan Buddha Bowl",
      description:
        "A healthy mix of quinoa, roasted vegetables, avocado slices, and tahini dressing.",
      price: 11.99,
      category: "Main Course",
      isAvailable: true,
      isVegetarian: true,
      isVegan: true,
      isGlutenFree: true,
      ingredients:
        "Quinoa, Sweet Potatoes, Broccoli, Avocado, Chickpeas, Tahini Dressing",
      calories: 300,
    },
    {
      menuItemId: 7,
      name: "French Fries",
      description:
        "Golden crispy French fries served with a side of ketchup or mayonnaise.",
      price: 4.99,
      category: "Side Dish",
      isAvailable: true,
      isVegetarian: true,
      isVegan: true,
      isGlutenFree: true,
      ingredients:
        "Potatoes, Vegetable Oil, Salt (Optional Ketchup or Mayonnaise)",
      calories: 250,
    },
  ];

  setSelectedList(listName : string) : void{
    this.selectedListName = listName
    console.log(this.selectedListName)
  }

  viewClickedObject(object : Reservation| MenuItem |  Employee ){
    console.log("testtt", object)
  }
}
