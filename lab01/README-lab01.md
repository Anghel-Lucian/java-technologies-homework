### Lab 01

##### Homework
The application searches through a dictionary of 4439 english words generated with [aspell](http://app.aspell.net/create) which it uses to find permutations of the given character sequence.

The algorithm for finding permutations looks through all of the rows of the dictionary. For each row, it checks if the characters in word are contained inside the given character sequence, and if the length of the word is equal to the parameter `size`. If both conditions are true, the current word is added to the response as a string.

#### Bonus
The application logs the HTTP method, the IP address of the client, the client language and the parameters of the request, along with the response for each response into a `log.txt` file.
