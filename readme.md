**Objective**:
Evaluate the candidate's Java proficiency using GitHub.

**Duration**:
Please, spend no more than 1 hour on your solution.

**Task Overview**:
We want to deterministically create groups from a set of data, based on a 64-bit (long) hash of an identifier. The groups correspond to a portion of the total possible set of hashed identifiers.
For example, the groups could be defined as follows:
* "A", [0.0, 0.1) // includes 0.0, excludes 0.1; this would be 10% of identifiers
* "B", [0.1, 0.4) // includes 0.1, excludes 0.4; this would be 30% of identifiers
* "C", [0.4, 0.65)
* "D", [0.65, 1.0)

Write a function in Java that reliably maps the hashed identifier (64-bit long) into specific groups based on the defined ranges.
```java
/**
  * @param hashedIdentifier A 64-bit hash (e.g., from MurmurHash3 or MD5)
  * @return The group name ("A", "B", "C", or "D"), or null if no group matches.
  */
  public String toGroup(long hashedIdentifier) {
    // Your logic here
  }
```
**Deliverables**:
* GitHub Repository should contain the following: The URL of the GitHub repository containing your solution with a README file (please make sure the profile is public and can be viewed without providing additional access).
* The README should contain the task and a technical explanation of why it has been resolved in the way you implemented.
  
**Evaluation Criteria**:
1. Overall Structure and Organization of the GitHub Repository. (25%)
2. Code quality, readability, and efficiency. (75%)


**Implementation details**.
The main assumption that all element in range has equal probability.
The whole initial range is [0..1] and anything like this [0.0, 0.1] it is sub-range with (0.1-0.0)*100% = 10% of all possible values.
**For example**:
1. Initial range it's all possible numbers from -100 to 99 (200 elements).
2. In that case group "A" - [0.0, 0.1) means any number N, where -100<=N<-80 (200*(0.1-0.0)=20 first element ~10%) 
2. In case of group "B" - [0.1, 0.4) it means any number N, where -80<=N<-20 (200*(0.4-0.1)=60 next element ~30%)
3. We can think about any group like a some subset of possible values with offset from begin to the end of initial set.
```
   Line with all possible values
   |----|------------|----|--...-|
-100 A -80    B    -20    0      99
```
> **Notes**:
> 
> The order of the groups aren't matter.
> 
> Group's ranges are not verified for the intersection.
> 
> In case of group intersection result can be incorrect
> 
> As far as groups amount is pretty small, linear time complexity for group detection isn't a problem (O(n) complexity, has no any premature optimisation)
> 
> Later toGroup can be optimized with binary search with O(Lon(n)) time complexity. For this we need to sort list with Groups or use TreeSet.
