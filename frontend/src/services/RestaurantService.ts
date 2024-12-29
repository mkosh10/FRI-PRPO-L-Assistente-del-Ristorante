import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {firstValueFrom, Observable} from 'rxjs';
import {Reservation} from "../app/models/Reservation";
import {Employee} from "../app/models/Employee";
import {MenuItem} from "../app/models/MenuItem";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  private apiUrl = 'http://localhost:8080/v1';

  constructor(private http: HttpClient) {}

  // GET ALL
  getReservations(): Promise<Reservation[]> {
    return firstValueFrom(this.http.get<Reservation[]>(`${this.apiUrl}/reservations`));
  }

  getEmployees(): Promise<Employee[]> {
    return firstValueFrom(this.http.get<Employee[]>(`${this.apiUrl}/employees`));
  }

  getMenuItems(): Promise<MenuItem[]> {
    return firstValueFrom(this.http.get<MenuItem[]>(`${this.apiUrl}/menu`));
  }

  // GET BY ID
  getReservationById(id: number): Promise<Reservation> {
    return firstValueFrom(this.http.get<Reservation>(`${this.apiUrl}/reservations/${id}`));
  }
  getEmployeeById(id: number): Promise<Employee> {
    return firstValueFrom(this.http.get<Employee>(`${this.apiUrl}/employees/${id}`));
  }
  getMenuItemById(id: number): Promise<MenuItem> {
    return firstValueFrom(this.http.get<MenuItem>(`${this.apiUrl}/menu/${id}`));
  }

  // PUT CALLS
  updateReservationById(id: number, reservation : Reservation): Promise<any> {
    return firstValueFrom(this.http.put<any>(`${this.apiUrl}/reservations/${id}`, reservation));
  }
  updateEmployeeInfoById(id: number, employee : Employee): Promise<any> {
    return firstValueFrom(this.http.put<any>(`${this.apiUrl}/employees/${id}`, employee));
  }
  updateMenuItemById(id: number, menuItem : MenuItem): Promise<any> {
    return firstValueFrom(this.http.put<any>(`${this.apiUrl}/menu/${id}`, menuItem));
  }

  // POST CALLS

  addNewReservation(reservation : Reservation): Promise<any> {
    return firstValueFrom(this.http.put<any>(`${this.apiUrl}/reservations`, reservation));
  }
  addNewEmployee(employee : Employee): Promise<any> {
    return firstValueFrom(this.http.put<any>(`${this.apiUrl}/employees`, employee));
  }
  addNewMenuItem(menuItem : MenuItem): Promise<any> {
    return firstValueFrom(this.http.put<any>(`${this.apiUrl}/menu`, menuItem));
  }

  //DELETE

  // TODO: Observable<ANY> dodaj boljsi response
  deleteReservationById(id: number): Promise<any> {
    return firstValueFrom(this.http.delete<any>(`${this.apiUrl}/reservations/${id}`));
  }
  deleteEmployeeId(id: number): Promise<any> {
    return firstValueFrom(this.http.delete<any>(`${this.apiUrl}/employees/${id}`));
  }
  deleteMenuItemById(id: number): Promise<any> {
    return firstValueFrom(this.http.delete<any>(`${this.apiUrl}/menu/${id}`));
  }


}
