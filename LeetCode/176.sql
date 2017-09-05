## Use offset
SELECT DISTINCT
    Salary AS SecondHighestSalary
FROM
    Employee
ORDER BY Salary DESC
LIMIT 1 OFFSET 1

## where not in to exclude the max
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee 
WHERE Salary NOT IN 
(SELECT Max(Salary) 
 FROM Employee);
