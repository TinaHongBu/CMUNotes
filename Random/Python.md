# Random Python Notes

- Iterate through all possible pairs in a list

  ``` python
  # All possible pairs
  pair = [(x, y) for x in data for y in data] 
  # excluding duplicates
  pair = [(x, y) for x in data for y in data if x != y]
  # Unique pairs when order is irrelavant
  pair = [foo(data[x], data[y]) for x in range(len(data)) for y in range(x+1,len(data))]
  ```
