-- Angela To (a4to)
-- A10657395

/* Create the db
-- PART A 
CREATE TABLE Sailor (
    sname TEXT PRIMARY KEY NOT NULL,
    rating INT NOT NULL  
);
CREATE TABLE Boat (
    bname TEXT PRIMARY KEY NOT NULL,
    color TEXT NOT NULL,
    rating INT NOT NULL     
);
CREATE TABLE Reservation(
    sname TEXT NOT NULL,
    bname TEXT NOT NULL,
    day TEXT NOT NULL,
    start INT NOT NULL,
    finish INT NOT NULL CHECK (finish > start),
    FOREIGN KEY(bname) REFERENCES Boat(bname),
    FOREIGN KEY(sname) REFERENCES Sailor(sname),
    PRIMARY KEY(bname, day, start, finish)
);
*/

-- PART B Select from all
SELECT * FROM Sailor;
SELECT * FROM Boat;
SELECT * FROM Reservation;


-- PART C.1 List all the pairs of sailors qualified to sail
SELECT S.sname AS Sailor, B.bname AS Boat
FROM Sailor S, Boat B 
WHERE S.rating >= B.rating;


-- PART C.2 List for each sailor the boats they are qualified
-- to sail
SELECT S.sname AS Sailor, COUNT(B.bname) AS Boats 
FROM Sailor S, Boat B 
WHERE S.rating >= B.rating
GROUP BY S.sname
UNION
SELECT S.sname AS Sailor, 0 AS Boats
FROM Sailor S
WHERE S.sname NOT IN (
    SELECT S.sname
    FROM Sailor S, Boat B 
    WHERE S.rating >= B.rating);


-- PART C.3.i List the sailors with the lowest rating using MIN
SELECT S.sname 
FROM Sailor S
WHERE S.rating = (
    SELECT MIN(S.rating) 
    FROM Sailor S);


-- PART C.3.ii List the sailors w/o the lowest rating using MIN
SELECT S.sname 
FROM Sailor S
WHERE S.rating NOT IN (
    -- Sailors with the higher ratings
    SELECT S1.rating 
    FROM Sailor S1, Sailor S2
    WHERE S1.rating > S2.rating);


-- PART C.4 List the sailors who have at least one reservation and
-- reserved only red boats. (Bob, Rusty)
SELECT DISTINCT R.sname 
FROM Reservation R
WHERE R.sname NOT IN (
	SELECT R.sname 
	FROM Reservation R, Boat B
	WHERE R.bname = B.bname AND B.color <> 'red');


-- PART C.5 Select the sailors who reserved no red boats (Horatio, Brutus)
SELECT S.sname 
FROM Sailor S
WHERE S.sname NOT IN (
    SELECT R.sname
    FROM Reservation R
    WHERE R.bname IN (
        SELECT B.bname
        FROM Boat B
        WHERE B.color = 'red'));

		
-- PART C.6.i Select the sailors who have reserved every red boats 
-- using NOT IN (Andy, Rusty)
SELECT S.sname from Sailor S
WHERE 1 NOT IN 
	(SELECT 1 FROM Boat B
    WHERE B.color = 'red'
    AND 1 NOT IN 
		(SELECT 1 from Reservation R
        WHERE R.bname = B.bname
        AND R.sname = S.sname));


-- PART C.6.ii Select the sailors who have reserved every red boats 
-- using NOT EXISTS (Andy, Rusty)
SELECT S.sname 
FROM Sailor S 
WHERE NOT EXISTS
	(SELECT * 
	FROM Boat B
	WHERE B.color = 'red' AND NOT EXISTS
		(SELECT * 
		FROM Reservation R
		WHERE R.bname = B.bname AND R.sname = S.sname));


-- PART C.6.iIi Select the sailors who have reserved every red boats 
-- using COUNT
SELECT S.sname 
FROM Sailor S, Boat B, Reservation R
WHERE S.sname = R.sname AND R.bname = B.bname AND B.color = 'red'
GROUP BY S.sname
HAVING COUNT(*) >= 
	(SELECT COUNT(*) 
	FROM Boat B 
	WHERE B.color='red'); 

	
-- PART C.7 For each reserved boat list the average rating of sailors
-- having reserved that boat
CREATE VIEW Reservation2 AS
SELECT DISTINCT R.sname, R.bname
FROM Reservation R;

SELECT B.bname, AVG(S.rating) AS Ratings
FROM Sailor S, Reservation2 R, Boat B
WHERE S.sname = R.sname 
AND R.bname = B.bname
GROUP BY B.bname;


-- PART D Create a table that shows reservation conflicts
-- -|bname day sname1 start1 finish1 sname2 start2 finish2
CREATE TABLE Conflicts AS 
	SELECT R1.bname AS bname, R1.day AS day,
		   R1.sname AS sname1, R1.start AS start1, R1.finish AS finish1, 
	       R2.sname AS sname2, R2.start AS start2, R2.finish AS finish2
	FROM Reservation R1, Reservation R2
	WHERE R1.sname <> R2.sname
	AND R1.day = R2.day 
	AND R1.bname = R2.bname
	AND R2.start < R1.finish;


-- PART E.1 Change all red boats to blue and all blue boats to red
UPDATE Boat 
SET color = 'green'
WHERE color = 'red';

UPDATE Boat 
SET color = 'red'
WHERE color = 'blue';

UPDATE Boat 
SET color = 'blue'
WHERE color = 'green';


-- PART E.2 Delete those sailors who are not qualified to sail
-- ANY boat together with their reservation
DELETE FROM Reservation
WHERE sname IN
	(SELECT DISTINCT S.sname 
	FROM Sailor S, Boat B 
	WHERE S.rating < 
		(SELECT MIN(B.rating) 
		FROM Boat B));

DELETE FROM Sailor
WHERE sname IN
	(SELECT DISTINCT S.sname 
	FROM Sailor S, Boat B 
	WHERE S.rating < 
		(SELECT MIN(B.rating) 
		FROM Boat B));


-- PART F List all tables
SELECT * FROM Sailor;
SELECT * FROM Boat;
SELECT * FROM Reservation;
