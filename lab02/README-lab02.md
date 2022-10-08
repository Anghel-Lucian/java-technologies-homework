### Lab 02

##### Homework
The application has two pages: input and result. Inside of input, a form can be used by the user to add new cars. Inside of result, a table with all of the cars added in the application will be rendered.

The object-oriented model of the application is represented of the `Car` class, which represents the car entity the user can add in the application, and the business logic portion is represented by the `CarBean` class which takes care of writing the cars to a file and reading the cars from the file, to be displayed inside of `result.jsp`.

The navigation is taken care of inside the `CarServlet` class. The only navigation that takes place is a redirection from the `input.jsp` file to the `result.jsp` file after the user inserts a new car in the app.

There are two filters in the application:
* `DecoratorFilter`, which decorates the response by changing some parameter values if those values pass an equality criteria;
* `LoggerFilter`, which logs the request's parameters in the console.

##### Bonus
The application also has a `CategoryListener` listener component, which initializes a context attribute with a value read from the `web.xml` file.


