# Expeditors_test
The repository contains the solution for the Expeditors test I received from Alliyah DaVault on Oct-12-2022. Please see the task I was given at the very end of this README file.

# How to build
1. Prerequisites:
	-  Java ver 11;
	-  maven ver 3.6.3;
2. The source code is located at the github at https://github.com/olegfomin/Expeditors_test 
3. First you'll have to bring the code on your computer with the command as follows: ** git clone git@github.com:olegfomin/Expeditors_test.git **
4. The ** cd ** into the root directory of the project that is supposed to be a folder called ** Expeditors_test **
5. To build the project please issue the command as follows: ** mvn clean install shade:shade ** The last part of the command **shade:shade** is 
responsible for merging all external dependencies into the executable file that is likely to be called: ** ./target/Expeditors_test-1.0-SNAPSHOT.jar **

# How to run
 * The arguments must be followed with the double minus sign like that "--".
 * --input_file - it must be followed by space or spaces and then it should contain 
      the path to the input CSV file where the input values will be read from (default value is "<jarfileRoot>/test.csv" filled with the original task data given to me on Oct-12-2022). 
      The software purposefully skips all the strings which have '#' at the very beginning so that some explanations can be put. The
      header of the file will look like: #"FirstName","LastName","Street Address","City","State","Age". 
      This header will not participate in the calculations so if you do not like it go ahead and delete it 
 * --output_file - it must be followed by space or spaces and then it should contain 
 *                 the path to the output file where the resulting .CSV file will be put. By default it will print everything into stdout (terminal)
             
# The original task
##Exercise Summary:
This Developer Design and Development exercise is used in the evaluation process for potential new hire candidates.  Please approach this exercise as you would approach a design and development project at work and include unit tests.  Any documentation or explanations about your approach and assumptions are helpful.  Please send a link to your code repository when you are complete. 

##Requirements:  
Write a standalone executable or script using the language of your preference (Java is the primary dev language at Expeditors).  Given the provided input data, print the expected output to the console or write to a text file.

Input data:
"Dave","Smith","123 main st.","seattle","wa","43"  
"Alice","Smith","123 Main St.","Seattle","WA","45"  
"Bob","Williams","234 2nd Ave.","Tacoma","WA","26"  
"Carol","Johnson","234 2nd Ave","Seattle","WA","67"  
"Eve","Smith","234 2nd Ave.","Tacoma","WA","25"  
"Frank","Jones","234 2nd Ave.","Tacoma","FL","23"  
"George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"  
"Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"  
"Ian","Smith","123 main st ","Seattle","Wa","18"  
"Jane","Smith","123 Main St.","Seattle","WA","13"  

##Expected output:   
Each household and number of occupants, followed by:  
Each First Name, Last Name, Address and Age sorted by Last Name then First Name where the occupant(s) is older than 18
                                                          
 
