# Data Models

## History Before DBMS - File System

File System + Metadata -> Database

## Conceptual Model - Entity Relationship Model (ERM)

### ERM

- Entities
  - Strong
  - Weak: existence-dependent
- Relationships
  - Cardinalities
    - Recursive
      - 1-1
      - 1-M
      - M-M
    - Binary
      - 1-1
      - 1-M
      - M-M
    - N-ary
  - Parcitipation
    - Optional
    - Mandatory
- Attributes (of Entities/Relationships)
  - Simple
  - Single Value
  - Multi Value
  - Composite
  - Derived

### ERD Steps

- Entity & Relationships
- Cardinalities
- Attributes
- Primary Keys

## ERM -> Extended ERM

- Specification
- Generalization
- Categorization
- Aggregation	

## Extended ERM -> Relational Model 

- Entities -> Tables
- Relationships -> Foreign Keys
- Attributes -> Columns

### Concepts

- Relations = Tables
- Attributes = Fields = Columns
- Domain 
- Tuples = Records = Rows
- Cardinality = #Tuples = #Rows
- Degree = #Attributes = #Columns

### Relational Keys

- Super key: uniqueness
- Candidate key: uniqueness + irreducibility
- Primary key
- Foreign key

### Relations VS. Relationships

Relations: A set of tuples (Each row in a table is a tuple)

-  One table

Relationships: Links between columns

- N tables
- Foreign Key