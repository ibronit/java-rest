# java-rest

Demo application which implements a user rest api. It contains an entity listener which listens to every user creation and puts a new message into the `user-creation` kafka queue. The consumer of the queue will send an email about the new user creation.

## Requirements to run the application

* docker https://docs.docker.com/install/
* docker-compose https://docs.docker.com/compose/install/

## Usage

### App starting

```sh
$ docker-compose up
```

### Useful services for local development

| Service | url |
| ------ | ------ |
| Kafdrop | http://localhost:9100 |
| Mailhog | http://localhost:8025 |


## Architecture

| File | Description |
| ------ | ------ |
| com.example.restdemo.user.User | User model |
| com.example.restdemo.user.UserRepository | Data rest for the user model via Spring Data REST |
| com.example.restdemo.user.UserCreationListener | Listener for user creation which publish a new message into the `user-creation` queue |
| com.example.restdemo.user.UserCreationKafkaListener | Consumer for the `user-creation` queue |
| com.example.restdemo.mail.MailService | Service which handles mail sending |
