INSERT INTO Employee (firstName, lastName, email, position, hireDate, salary, updatedAt) VALUES ('Alice', 'Johnson', 'alice.johnson@restaurant.com', 'Chef', '2020-04-15', 55000.00, NOW());

INSERT INTO Employee (firstName, lastName, email, position, hireDate, salary, updatedAt) VALUES ('Robert', 'Miller', 'robert.miller@restaurant.com', 'Waiter', '2021-06-20', 35000.00, NOW());

INSERT INTO Employee (firstName, lastName, email, position, hireDate, salary, updatedAt) VALUES ('Sophia', 'Williams', 'sophia.williams@restaurant.com', 'Restaurant Manager', '2019-10-05', 75000.00, NOW());


INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree,  ingredients, calories) VALUES ('Cheeseburger', 'A classic cheeseburger with cheddar cheese, lettuce, and tomato', 10.99, 'Main Course', true, false, false, false, 'Beef Patty, Cheddar Cheese, Lettuce, Tomato, Pickles, Burger Bun', 500);

INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree,  ingredients, calories) VALUES ('Margherita Pizza', 'A traditional pizza with fresh tomatoes, mozzarella, and basil', 12.99, 'Main Course', true, false, false, true, 'Tomato Sauce, Mozzarella, Basil, Olive Oil', 600);

INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree,  ingredients, calories) VALUES ('Chicken Caesar Salad', 'Crisp romaine lettuce, grilled chicken, croutons, and Caesar dressing', 11.49, 'Salad', true, false, false, false,  'Romaine Lettuce, Grilled Chicken, Croutons, Caesar Dressing', 400);

INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree, ingredients, calories) VALUES ('Pasta Carbonara', 'Pasta in a creamy sauce with bacon and parmesan', 13.50, 'Main Course', true, false, false, false,  'Spaghetti, Bacon, Eggs, Parmesan, Cream', 750);

INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree,  ingredients, calories) VALUES ('BBQ Ribs', 'Tender ribs smothered in a smoky BBQ sauce', 15.99, 'Main Course', true, false, false, true, 'Pork Ribs, BBQ Sauce', 800);

INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree,  ingredients, calories) VALUES ('Fish Tacos', 'Grilled fish tacos with cabbage slaw and a lime crema', 11.99, 'Main Course', true, false, false, true,  'Grilled Fish, Tortillas, Cabbage, Lime, Crema', 450);

INSERT INTO MenuItem (name, description, price, category, isAvailable, isVegetarian, isVegan, isGlutenFree,  ingredients, calories) VALUES ('Vegan Buddha Bowl', 'A wholesome vegan bowl with quinoa, roasted veggies, and tahini dressing', 14.00, 'Salad', true, true, true, true,  'Quinoa, Roasted Vegetables, Tahini Dressing, Avocado', 350);




INSERT INTO Reservation (customerName, customerContactInfo, numberOfGuests, reservationDate, specialRequests, tableAssigned, arrivalTime, discountCode, createdAt) VALUES ('John Doe', 'john.doe@example.com', 4, '2024-11-30 17:00:00', 'Vegetarian options required', 'Table 5', '2024-11-30 19:00:00', 'DISCOUNT10', NOW());

INSERT INTO Reservation (customerName, customerContactInfo, numberOfGuests, reservationDate, specialRequests, tableAssigned, arrivalTime, discountCode, createdAt) VALUES ('Emma Watson', 'emma.watson@example.com', 2, '2024-12-01 17:00:00', 'Quiet corner table', 'Table 12', '2024-12-01 18:30:00', 'HOLIDAY20', NOW());

INSERT INTO Reservation (customerName, customerContactInfo, numberOfGuests, reservationDate, specialRequests, tableAssigned, arrivalTime, discountCode, createdAt) VALUES ('Michael Scott', 'michael.scott@dundermifflin.com', 6, '2024-12-03 17:00:00', 'Birthday cake needed', 'Table 8', '2024-12-03 20:00:00', NULL, NOW());
