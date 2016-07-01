Project: TicketService

Steps to run the program
1.Go to Project folder
2. run "mvn clean install"
3. "target" folder will be generated.
4. go inside "target" folder, you can find a "*.jar" file.
5. run "java -jar filname.jar"



Properties File
Location:ticket-system-2016-06-30\ticket-system\src\main\resources\com\system\util
Description
1. Ticket prices can be changed in this file.
2. Number of rows for each level can be changed.
3. Number of seats in each row can be changed.
4. Expiry time to unlock ticket can be changed.

Note: 
1. If already reserved ticket is trying to lock again, the system throws exception 
"Few/All selected seats were  locked/already reserved"
2. 


Scenario:1 
 1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 
1
Balcony Details
Level ||  Level Name   || Price  ||  Rows   || Seats in Row
1       ORCHESTRA        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
2       MAIN        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
3       BALCONY1        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
4       BALCONY2        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################


Scenario 2
Selecting and Reserving tickets
1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 
2
Select Balcony Level : 
3
Enter selected seat numbers (separated by comma) : 
1,2
Do you want to hold the seats (press 'Y' for confirmation / any other key for changing the option)
y
Locked selected seats
 1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 
3
Confirm selected tickets by entering following details :
Enter email address : 
test@test.com
Enter userName : 
test
Confirmed, TicketId :cf239b30-fde9-4594-8b44-93d324543e7a
 1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 
4
Enter your emailId (user for booking) :
test@test.com
Ticket-Id :cf239b30-fde9-4594-8b44-93d324543e7a
Level Name :BALCONY1 Selected seats :1,2,
 1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 

Scenario 3:
Expiring a ticket
Select Options (1,2,3,4,5): 
2
Select Balcony Level : 
4
Enter selected seat numbers (separated by comma) : 
1
Do you want to hold the seats (press 'Y' for confirmation / any other key for changing the option)
y
Locked selected seats
 1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 
1
Balcony Details
Level ||  Level Name   || Price  ||  Rows   || Seats in Row
1       ORCHESTRA        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
2       MAIN        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
3       BALCONY1        100.0         2       5
Available seats : 
0     3  4  5  
1 6  7  8  9  10  
##################
4       BALCONY2        100.0         2       5
Available seats : 
0   2  3  4  5   // holded the ticket#1
1 6  7  8  9  10  
##################
 1: ShowDetails 
 2: Select Seats 
 3: Confirm Seats 
 4: User tickets 
 5: Exit 

Select Options (1,2,3,4,5): 
wait for 1 min since in properties file unlock time is 1 min
Select Options (1,2,3,4,5): 
1
Balcony Details
Level ||  Level Name   || Price  ||  Rows   || Seats in Row
1       ORCHESTRA        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
2       MAIN        100.0         2       5
Available seats : 
0 1  2  3  4  5  
1 6  7  8  9  10  
##################
3       BALCONY1        100.0         2       5
Available seats : 
0     3  4  5  
1 6  7  8  9  10  
##################
4       BALCONY2        100.0         2       5
Available seats : 
0 1  2  3  4  5  // ticket is unlocked
1 6  7  8  9  10  