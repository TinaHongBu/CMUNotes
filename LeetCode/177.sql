# get the nth highest salary from the Employee table.

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
# How to set a variable in a function
DECLARE M INT;
SET M = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT DISTINCT Salary AS getNthHighestSalary
      FROM Employee
      ORDER BY Salary DESC
      LIMIT 1 OFFSET M
  );
END