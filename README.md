# Calendaring Service
The Calendaring Service is an integration service that serves as a building block for Hack for LA projects that require calendar services (e.g. the ability to schedule meetings).

## Installation instructions
Make sure you have Postgres and a recent version of JDK and Maven installed on your computer. To get started:

1. Clone the repository to your machine
2. Sign up for a [Nylas](https://www.nylas.com/) account and get add your credentials to a `.env` file in the base of your directory.
3. From within the parent directory run `mvn spring-boot:run` in your terminal

That's it! Your calendaring microservice should be up and running. In order to test the endpoints you will need to use Postman or your preferred API testing tool.
