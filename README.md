# GreyChain_Assignment

-> Problem Statement
There is a scenario where thousands of loans are flowing into one store, assume any way of transmission of Loans. We need to create a one loan store, which store the loans in the following order

Write a Java Program with all the JUNIT test cases. TDD approach will be preferred. 
Framework: Spring boot, build tool : maven or gradle
REST APIs need to be exposed like below:
Get All Loans (/loans)
Add loan (/loans/add)
get loan by loan id (/loans/{loanId})
get loan by customer id (/loans/{customerId)
get loans by lenderId (/loans/{lenderId})
get aggregate loans by Lender (aggregate remaining amount, Interest and Penalty)(/loans/aggregate/lender)
get aggregate loans by customer id  (aggregate remaining amount, Interest and Penalty)(/loans/aggregate/customer)
get the aggregate loans by interest  (aggregate remaining amount, Interest and Penalty)(/loans/aggregate/interest)
do the exception handling as well.

There are couple of requirement/validation
The payment date can’t be greater than the Due Date. If its greater we have to reject the Loan and thrown the exception
We need to write an aggregation on the remaining amount, Interest and Penalty Group by Lender, Group by Interest and Group by Customer ID.
If the Loan crosses the due date, it should write an alert in the log message.
