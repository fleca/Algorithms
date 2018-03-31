# Algorithms
Princeton Algorithms Part 1.
This repository contains java codes wrote during the Coursera - Algorithms Part 1 (Princeton) course.
They can be used as help to other students and any kind of suggestions are welcome!

Week 1 assignment: Percolation
The first week taught about quick-find and quick-union (original + improvements) algorithms and the assigment was to write a code that model systems as an n-by-b grid of sites and if there is a full site in the bottom row, then the system percolates.
The algorithm was made to randomly open 1 site at the time and when the system percolated for the first time the code would register the percentage of open sites (comparing to total sites), then we had to make the code run thousands of times in different n-by-n grids in order to prove that by opening sites randomly, the average percentage of open sites to percolade is 59.3%. More info at: https://introcs.cs.princeton.edu/java/24percolation/
