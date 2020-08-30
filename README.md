/* *****************************************************************************
 *  Name: Mohammad Alqudah
 *  NetID: malqudah
 *  Precept: P05
 *
 *
 *
 *
 *
 *  Operating system: macOS
 *  Compiler: javac 11.0.2
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: No
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: No
 *
 *  Hours to complete assignment (optional): 6
 *
 **************************************************************************** */

Programming Assignment 1: Percolation



/* *****************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 **************************************************************************** */
as mentioned in precept, first job was to make a private helper method that
would convert from rows, columns (ie 2D sites or indices) to 1D indices. after
math trial and error i found the equation and created the method. i also
separated the index checks that were supposed to be done in the other methods
into its own private helper method to decrease the clutter. for the open method,
called indexcheck and then checked if the site at the given coordinates was open
if not, then open it and increment the opensite counter. in addition, i linked
the virtual top to all elements in the first row (when row is 0) and the bottom
to all elements in the last row (n - 1). then proceeded to use union to link the
open sites in the cardinal directions around the site in question to it,
checking through if statements to make sure none of these sites were out of
bounds (for ex, a negative row or column).
for the isopen method, i simply used the index check and then the conversion
helper method to check if the site in question was open; i used a variable
opened and set its value to true to differentiate between open and closed sites
in the 1D array (which made it a boolean array obviously). so, return the value
of the 1D array at the given site, which would either be true (open) or false
(closed).
for the isfull method i used the indexcheck and then used the find operation;
if the other pieces of code were successfull in linking the open sites
and the virtual top/bottom to their respective rows, then if the system
percolated the function find of the virtual bottom would return the same as
find of the virtual top, which is what occurred in the specific tests. true
for percolating, false for not percolating.
for the number of open sites, i simply returned the value incrementer i had
used in previous methods.
the respective tests were run in main to make sure the program worked (ie
printing if the object was full, if it percolated, etc).
to check whether the system percolates, i used the virtual top/ bottom method
that was mentioned in precept; after deciding the length of the 1 dimensional
array, which was n^2, the length of the weighted quick union object  needed to
account for the virtual top and bottom, so I made it to be n^2 plus 2. this
would make the top n^2 and the bottom n^2 + 1, separate from the 1d array
indices. i then linked the top through union to the first element in the 1d
array or all elements in the first row of the nxn grid,
and linked the bottom to the last element in it or the last row of the nxn grid.
after a series of union operations, to check if the system percolated i used the
find operation; if the system percolated then find of the bottom should yield
the same as the find of the top and vice versa.


/* *****************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for various values of n and T when implementing
 *  Percolation.java with QuickFindUF.java (not QuickUnionUF.java). Use a
 *  "doubling" hypothesis, where you successively increase either n or T by
 *  a constant multiplicative factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one. Do not include
 *  data points that take less than 0.25 seconds.
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 1.2

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
30          0.67                1.90448         3.53337
36          1.276               1.8605          3.40522
43          2.374               2.00295         3.80987
52          4.755               1.9489          3.65983
62          9.267               2.09042         4.04431
74          19.372

converges to about 3.65983

(keep n constant)
 n = 100
 multiplicative factor (for T) = 1.4

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
5           2.973               1.39825         0.996285
7           4.157               1.14241         0.395694
8           4.749               1.34028         0.870444
10          6.365               1.11123         0.31346
12          7.073               1.16584         0.45603
14          8.246               1.26364         0.695448
17          10.42               1.19875         0.538771
20          12.491              1.40941         1.01992
28          17.606              1.15211         0.420815
34          20.284

converges to about 0.695448
/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 * T^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */

QuickFindUF running time (in seconds) as a function of n and T:

    ~
       2.1*10^-7 * n^3.7 * T^0.7

       this is found by picking largest values of n and T respectively
       and setting F(n, T) = a * n^3.7 * T^0.7
       3.7 and 0.7 and the log ratio estimates for n and T respectively
       i picked a value, F(n) and set the equation equal to that and solved
       for a. similar for the next set of charts
       used 74 for n and 34 for t



/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T = 100, factor for n is 6/5

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
69          1.188               1.41162         1.89082
83          1.677               1.51401         2.27489
100         2.539               1.37338         1.74019
120         3.487               1.43619         1.98547
144         5.008               1.42133         1.92842
173         7.118               1.41486         1.90340
208         10.071              1.52845         2.32696
250         15.393              1.52316         2.30794
300         23.446

converges to about 2.30794

(keep n constant)
 n = 100, T factor is 6/5

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
86          2.021               1.19693         0.98595
103         2.419               1.19181         0.96244
124         2.883               1.17759         0.89660
149         3.395               1.21473         1.06692
179         4.124               1.19277         0.96685
215         4.919

converges to about 0.96685

WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~
       3.7*10^-7 * 300^2.3 * 215^0.9
       same method as previous part; used 300 for n and 215 for t and solved



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
program does not handle backwash



/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
Molly, and Lab TAs on Monday
Also the ideas mentioned in precept (Professor Ibrahim)

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
calculated the mean / stddev on my own instead of using the stdstats library;
caused tests to keep failing but eventually figured it out



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
fun assignment to do
