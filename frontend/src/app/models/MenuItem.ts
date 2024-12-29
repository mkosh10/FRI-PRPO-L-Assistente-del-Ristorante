export class MenuItem {
  menuItemId?: number;
  name?: string ;
  description?: string;
  price?: number;
  category?: string;
  isAvailable?: boolean;
  isVegetarian?: boolean;
  isVegan?: boolean;
  isGlutenFree?: boolean;
  ingredients?: string = "";
  calories?: number;
}