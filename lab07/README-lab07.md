### Lab 07

#### Homework

There are three types of users: author, reviewer, admin, each with a page. 

Users can submit documents, to which they can attach additional authors, and also see them in their dashboard.

The admin can set the timeframe for both registration and document submission.

#### Bonus

* `@Inject` was used to inject repository and other types of classes. `@Transactional` was used primarily inside `SettingsRepository` for updating the timeframes;
* `@Produces` is used for creating identifiers for entities and injected in multiple classes;
* `@Interceptor` is used for decoupling logging. The interceptor is `LoggerInterceptor` which logs document submission requests into a log file, it intercepts the `create` method from `DocumentRepository`;
* `@Decorator` is used in `AdminDecorator` and `LogInDecorator` which modify the `logIn` method inside `LogInDecorator` and the `updateRegistrationDates` and `updateSubmissionDate` methods inside `AdminBean`;
* `@Observes` is used inside `UserRepository` and intercepts when a document is uploaded. This results in user entities getting updated whenever a document is uploaded that they are an author of;
* Multiple Bean Validation annotations where used in entities to ensure correct data format.
