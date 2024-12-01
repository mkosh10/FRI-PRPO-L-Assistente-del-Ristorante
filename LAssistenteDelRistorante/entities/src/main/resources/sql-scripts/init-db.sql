INSERT INTO Employee (firstName, lastName, email, position, hireDate, salary, updatedAt) VALUES ('Alice', 'Johnson', 'alice.johnson@restaurant.com', 'Chef', '2020-04-15', 55000.00, NOW());

INSERT INTO Employee (firstName, lastName, email, position, hireDate, salary, updatedAt) VALUES ('Robert', 'Miller', 'robert.miller@restaurant.com', 'Waiter', '2021-06-20', 35000.00, NOW());

INSERT INTO Employee (firstName, lastName, email, position, hireDate, salary, updatedAt) VALUES ('Sophia', 'Williams', 'sophia.williams@restaurant.com', 'Restaurant Manager', '2019-10-05', 75000.00, NOW());

--
-- INSERT INTO MenuItem (name, description, price, category, available, createdAt, updatedAt) VALUES ('Margherita Pizza', 'Classic Italian pizza with tomatoes, mozzarella, and basil', 8.99, 'Main Course', true, NOW(), NOW());
--
-- INSERT INTO MenuItem (name, description, price, category, available, createdAt, updatedAt) VALUES ('Caesar Salad', 'Crispy romaine lettuce, Caesar dressing, croutons, and parmesan cheese', 6.50, 'Appetizer', true, NOW(), NOW());
--
-- INSERT INTO MenuItem (name, description, price, category, available, createdAt, updatedAt) VALUES ('Grilled Salmon', 'Fresh salmon fillet served with steamed vegetables and lemon butter sauce', 14.99, 'Main Course', true, NOW(), NOW());
--
-- INSERT INTO MenuItem (name, description, price, category, available, createdAt, updatedAt) VALUES ('Chocolate Lava Cake', 'Warm chocolate cake with a gooey center served with vanilla ice cream', 5.50, 'Dessert', true, NOW(), NOW());
--
-- INSERT INTO MenuItem (name, description, price, category, available, createdAt, updatedAt) VALUES ('Garlic Bread', 'Toasted bread with garlic butter and herbs', 3.99, 'Side Dish', true, NOW(), NOW());



INSERT INTO Reservation (customerName, customerContactInfo, numberOfGuests, reservationDate, specialRequests, tableAssigned, arrivalTime, discountCode, createdAt) VALUES ('John Doe', 'john.doe@example.com', 4, '2024-11-30', 'Vegetarian options required', 'Table 5', '2024-11-30 19:00:00', 'DISCOUNT10', NOW());

INSERT INTO Reservation (customerName, customerContactInfo, numberOfGuests, reservationDate, specialRequests, tableAssigned, arrivalTime, discountCode, createdAt) VALUES ('Emma Watson', 'emma.watson@example.com', 2, '2024-12-01', 'Quiet corner table', 'Table 12', '2024-12-01 18:30:00', 'HOLIDAY20', NOW());

INSERT INTO Reservation (customerName, customerContactInfo, numberOfGuests, reservationDate, specialRequests, tableAssigned, arrivalTime, discountCode, createdAt) VALUES ('Michael Scott', 'michael.scott@dundermifflin.com', 6, '2024-12-03', 'Birthday cake needed', 'Table 8', '2024-12-03 20:00:00', NULL, NOW());
