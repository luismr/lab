Tests for Wallethub
===================

# Modules

## tests-java

### Palindromes

#### Problem:

Write an efficient algorithm to check if a string is a palindrome. A string is a palindrome if the string matches the reverse of string.

Example:   

* 1221 is a palindrome but not 1121.

#### Notes:

Algorithm implemented on StringUtils class. Tests can be performed by StringUtilsTest unit case.

### Complementary Arrays

#### Problem:

Write an efficient algorithm to find K-complementary pairs in a given array of integers.  Given Array A, pair (i, j) is K-complementary  if K = A[i] + A[j];

#### Notes:

Algorithm implemented on ArrayUtils class. Tests can be performed by ArrayUtilsTest unit case.

### Most Frequent Phrases

#### Problem:

Given a large file that does not fit in memory (say 10GB), find the top 100000 most frequent phrases. The file has 50 phrases per line separated by a pipe (|).  Assume that the phrases do not contain pipe.

Example:

A line may look like: Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing      ….

The above line has 5 phrases in visible region.

#### Notes:

Algorithm implemented on ReadPhrase class. Tests can be performed by ReadPhraseTest unit case.

This class was tested using a sample file phrases.txt and run fast for small files. We generated a large file bases on Lord of Rings book with several copies inside it self generating a huge file (9.1 GB). It runs in arround 30 minutes and I designed some solutions and different approaches bases on split source file in smaller chunks and multi-threading the phrase count ans sorting. To grand integrity on smaller trunks (if split by size a line or phrase can be splitted too), I think in something like split by lines (\n).

The current version implemented grant whole processing in a singleton instance to processes on file per time.

## tests-mysql 

### Rank Votes

#### Problem:

Write a query to rank order the following table in MySQL by votes, display the rank as one of the columns.

	CREATE TABLE votes ( name CHAR(10), votes INT ); 
	INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);

#### Notes:

Just run

	SELECT *
	  FROM votes
	 ORDER BY votes DESC, name ASC
	 
Output should be

	MariaDB [wallethub]> SELECT *
	    ->   FROM votes
	    ->  ORDER BY votes DESC, name ASC;
	+-------+-------+
	| name  | votes |
	+-------+-------+
	| Green |    50 |
	| Black |    40 |
	| Brown |    20 |
	| White |    20 |
	| Jones |    15 |
	| Smith |    10 |
	+-------+-------+
	6 rows in set (0.00 sec)

### Capitalize First Letter of Word

#### Problem:

Write a function to capitalize the first letter of a word in a given string;

Example: 

	initcap(UNITED states Of AmERIca ) = United States Of America

#### Notes:

Create this function

	DELIMITER ||  
	
	CREATE FUNCTION initcap( phrase VARCHAR(255) ) RETURNS VARCHAR(255) 
	BEGIN  
	  DECLARE letter CHAR(1);  
	  DECLARE capitalized VARCHAR(255);  
	  DECLARE i INT DEFAULT 1;  
	  DECLARE found INT DEFAULT 1;  
	  DECLARE exceptions CHAR(17) DEFAULT ' ()[]{},.-_!@;:?/';  
	  
	  SET capitalized = LCASE( phrase );  
	  
		WHILE i < LENGTH( phrase ) DO  
			BEGIN  
			SET letter = SUBSTRING( capitalized, i, 1 );  
	
			IF LOCATE( letter, exceptions ) > 0 THEN
				BEGIN
				SET found = 1;  
				END;
			ELSEIF found = 1 THEN  
				BEGIN  
				IF letter >= 'a' AND letter <= 'z' THEN  
					BEGIN  
					SET capitalized = CONCAT(LEFT(capitalized,i-1),UCASE(letter),SUBSTRING(capitalized,i+1));  
					SET found = 0;  
					END;  
				ELSEIF letter >= '0' AND letter <= '9' THEN  
					BEGIN
					SET found = 0;  
	                END;
				END IF;  
				END;  
			END IF;  
	        
			SET i = i+1;  
			END;  
		END WHILE;  
	  
		RETURN capitalized;  
	END ||  
	
	DELIMITER ; 

Just run

	SELECT initcap("UNITED states Of AmERIca")
	  FROM dual

Output should be 

	MariaDB [wallethub]> SELECT initcap("UNITED states Of AmERIca")
	    ->   FROM dual;
	+-------------------------------------+
	| initcap("UNITED states Of AmERIca") |
	+-------------------------------------+
	| United States Of America            |
	+-------------------------------------+
	1 row in set (0.00 sec)
	
	MariaDB [wallethub]> 

### Split Lines

#### Problem:

Write a procedure in MySQL to split a column into rows using a delimiter. 

	CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) ); 
	INSERT INTO sometbl VALUES(1, 'Smith'),(2, 'Julio|Jones|Falcons'),(3, 'White|Snow'),(4, 'Paint|It|Red'),(5, 'Green|Lantern'),(6, 'Brown|bag');

For (2), example rows would look like >> “3, white”, “3, Snow” …

#### Notes:

Create this procedure

	DROP PROCEDURE IF EXISTS getNames;
	
	DELIMITER ||  
	
	CREATE PROCEDURE getNames(IN p_id INT) 
	BEGIN  
		DECLARE v_finished INTEGER DEFAULT 0;
		DECLARE v_found INT DEFAULT 0;  
		DECLARE v_name VARCHAR(50);
	    DECLARE v_keep INT DEFAULT 1;
		DECLARE cursor_names CURSOR FOR 
			SELECT NAME 
	          FROM sometbl
			 WHERE id = p_id;
	    DECLARE CONTINUE HANDLER 
			FOR NOT FOUND SET v_finished = 1;
	        
		DROP TEMPORARY TABLE IF EXISTS sometbl_tmp;
	    CREATE TEMPORARY TABLE sometbl_tmp (ID INT, NAME VARCHAR(50));    
	    
		OPEN cursor_names;
	    
	    process_names: LOOP
			FETCH cursor_names INTO v_name;
	    
			if v_finished = 1 THEN
				LEAVE process_names;
			END IF;
	    
			WHILE v_keep = 1 DO  
				BEGIN  
	            
	            SET v_found = INSTR(v_name, '|');
	
				IF v_found = 0 THEN
					BEGIN
					
	                INSERT INTO sometbl_tmp
						 VALUES (p_id, v_name);
	                     
					SET v_keep = 0;
	                
	                END;
				ELSE 
					BEGIN
	                
	                INSERT INTO sometbl_tmp
						 VALUES (p_id, SUBSTRING(v_name, 1, v_found - 1));
	                
	                SET v_name = SUBSTRING(v_name, v_found + 1);
	                
	                END;
				END IF;
				END;  
			END WHILE;  
	    END LOOP;
	    
	    SELECT *
	      FROM sometbl_tmp
		 WHERE id = p_id;
	      
		DROP TEMPORARY TABLE IF EXISTS sometbl_tmp;
	    
	END ||  
	
	DELIMITER ; 

Just run

	CALL getNames(3);

Output must be

	MariaDB [wallethub]> CALL getNames(3);
	+------+-------+
	| ID   | NAME  |
	+------+-------+
	|    3 | White |
	|    3 | Snow  |
	+------+-------+
	2 rows in set (0.00 sec)
	
	Query OK, 0 rows affected (0.00 sec)
	
	MariaDB [wallethub]> 
	

### BUGS SUMARISING

#### Pronblem:

I have a table for bugs from a bug tracking software; let’s call the table “bugs”. The table has four columns (id, open_date, close_date, severity). On any given day a bug is open if the open_date is on or before that day and close_date is after that day. For example, a bug is open on “2012-01-01”, if it’s created on or before “2012-01-01” and closed on or after “2012-01-02”. I want a SQL to show number of bugs open for a range of dates.

#### Notes:

Create table if it not exists

	DROP TABLE bugs;

	CREATE TABLE bugs (id INT, open_date DATE, close_date DATE, severity VARCHAR(10));
	INSERT INTO bugs
		VALUES 	(1, '2015-07-30', '2015-08-02', 'low'),
			(1, '2015-07-31', '2015-08-02', 'low'),
            		(1, '2015-08-01', '2015-08-02', 'moderate'),
            		(1, '2015-08-01', '2015-08-03', 'high'),
            		(1, '2015-08-01', '2015-08-05', 'normal');

Just run

	SET @dateStart 	= '2015-07-01';            
	SET @dateEnd 	= '2015-08-02';
	
	SELECT open_date, count(1) as bugs_opened
	  FROM bugs
	 WHERE open_date >= @dateStart
	   AND open_date <= @dateEnd
	   AND close_date <= @dateEnd
	 GROUP BY open_date;
	 
Output should be

	MariaDB [wallethub]> SET @dateStart = '2015-07-01';            
	Query OK, 0 rows affected (0.00 sec)
	
	MariaDB [wallethub]> SET @dateEnd = '2015-08-02';
	Query OK, 0 rows affected (0.00 sec)
	
	MariaDB [wallethub]> 
	MariaDB [wallethub]> SELECT open_date, count(1) as bugs_opened
	    ->   FROM bugs
	    ->  WHERE open_date >= @dateStart
	    ->    AND open_date <= @dateEnd
	    ->    AND close_date <= @dateEnd
	    ->  GROUP BY open_date;
	+------------+-------------+
	| open_date  | bugs_opened |
	+------------+-------------+
	| 2015-07-30 |           1 |
	| 2015-07-31 |           1 |
	| 2015-08-01 |           1 |
	+------------+-------------+
	3 rows in set (0.00 sec)
	
	MariaDB [wallethub]> 

# Running

## Requirements

* Java 1.6 or newer
* Maven 3.0 or newer
* JAVA\_HOME, MVN\_HOME and PATH properly working

## Executing

	$ mvn test
 
