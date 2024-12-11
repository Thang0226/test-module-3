create database book_library;

use book_library;

create table Book (
	id int primary key auto_increment,
    name varchar(100),
    author varchar(50),
    description text,
    count int
);

create table Student (
	id int primary key auto_increment,
    full_name varchar(50),
    class_name varchar(50)
);

create table Call_card (
	id int primary key auto_increment,
	book_id int not null,
    student_id int not null,
    state boolean,
    borrow_date date,
    return_date date
);

-- Insert records into Book table
INSERT INTO Book (name, author, description, count) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 'A story about the American dream and its pitfalls.', 5),
('1984', 'George Orwell', 'A dystopian novel set in a totalitarian regime.', 3),
('To Kill a Mockingbird', 'Harper Lee', 'A novel about racial injustice in the Deep South.', 4),
('Moby Dick', 'Herman Melville', 'A tale of obsession, revenge, and the sea.', 2),
('The Catcher in the Rye', 'J.D. Salinger', 'A young man’s struggle with the world around him.', 6);
INSERT INTO Book (name, author, description, count) VALUES
('Book about something', 'J.D. Salinger', 'A young man’s struggle with the world', 0);

-- Insert records into Student table
INSERT INTO Student (full_name, class_name) VALUES
('Alice Johnson', 'Grade 10'),
('Bob Smith', 'Grade 11'),
('Charlie Brown', 'Grade 12'),
('Diana Prince', 'Grade 10'),
('Eve Green', 'Grade 11');

-- Insert records into Call_card table
INSERT INTO Call_card (book_id, student_id, state, borrow_date, return_date) VALUES
(1, 1, TRUE, '2024-12-01', '2024-12-15'),
(2, 2, TRUE, '2024-12-05', '2024-12-20'),
(3, 3, FALSE, '2024-12-01', '2024-12-10'),
(4, 4, TRUE, '2024-12-08', '2024-12-22'),
(5, 5, FALSE, '2024-12-02', '2024-12-10');






delimiter $$
drop procedure if exists list_books $$
create procedure list_books()
begin
	select * from Book;
end $$
delimiter ;

delimiter $$
drop procedure if exists find_book $$
create procedure find_book(in in_id int)
begin
	select * from Book
    where id = in_id;
end $$
delimiter ;

delimiter $$
drop procedure if exists list_students $$
create procedure list_students()
begin
	select * from Student;
end $$
delimiter ;














