![dxcvape-logo](https://user-images.githubusercontent.com/52403567/185983183-df1c7a53-4281-41a8-a288-9bac0eb37b68.png) 
## ShoeShy - Shoe store website

A shoe store website made with Spring Boot, payment integration with Paypal Sandbox, there is 3 roles: USER, ADMIN, and STAFF. USER can only visit homepage while STAFF and ADMIN can visit both homepage and admin page. User can sign in and sign up with normal account or with OAuth2 account (GG,FB), edit profile, see orders history, reset password, and checkout with or without voucher, search & sort products. ADMIN and STAFF can visit admin page, view Dashboard, CRUD User, Product, Order, Category & SubCategory, Voucher, and have authorizing privilege, export data to excel, pdf, csv, upload excel, pdf, csv to db (to be added), payment with NEAR (to be added)

Stack used:
  *	Stack:
    -	Frontend: AngularJS, Thymeleaf, MDBootstrap 5, JQuery
    -	Backend: Spring Boot, Spring Data JPA, Spring Security, OAuth2
    -	Database: SQL Server 2019 (Somee.com)
  *	Software:
    -	Visual Studio Code + Spring Boot extension
    -	[Adoptium Temurin JDK 17](https://adoptium.net/)

## Installation
1. Clone this repo, create database by running the shoeshy_db.sql script inside db folder
2. Config application.properties according to your credentials
  ```
    # for SQLServer Database connection
    spring.datasource.url=jdbc:sqlserver://localhost:1433;database=<your database>
    spring.datasource.username=<your username>
    spring.datasource.password=<your password>
    spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
    spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
    spring.jpa.show-sql=false
    spring.jpa.hibernate.ddl-auto = none
  ```
  - Create a app password for your gmail in order to send email in this webapp
  ```
    # for send mail contact
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=<your gmail username>
    spring.mail.password=<your gmail app password>
    spring.mail.properties.mail.smpt.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
  ```
  - Create Google, Facebook OAuth app and put your client-id, secret key to here, if you don't know how pls check this [link](https://www.codejava.net/frameworks/spring-boot/social-login-with-google-and-facebook-examples)
  ```
    # Google
    spring.security.oauth2.client.registration.google.client-id=<your google client id>
    spring.security.oauth2.client.registration.google.client-secret=<your google client secret>
    spring.security.oauth2.client.registration.google.scope=email,profile

    # Facebook
    spring.security.oauth2.client.registration.facebook.client-id=<your facebook client id>
    spring.security.oauth2.client.registration.facebook.client-secret=<your facebook client secret>
    spring.security.oauth2.client.registration.facebook.scope=email,public_profile
  ```
  - Create a Paypal Sandbox account and fill your business account id and secret here (tips: when checkout use your sandbox personal account email and password)
  ```
    paypal.mode = sandbox
    paypal.client.id=<your sandbox business account id>
    paypal.client.secret=<your sandbox business account secret>
  ```
  - Subscribe to [apilayer.com's Exchange Rate Data API](https://apilayer.com/marketplace/exchangerates_data-api) and fill your api key here
  ```
    apilayer.apikey=<your api key>
  ```

## Visuals - Demo

- User homepage


- Admin homepage




## Support
If things are unclear, PM me at [balisongian@gmail.com](mailto:balisongian@gmail.com)


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.


## Authors and acknowledgment
Authors of this project: [Jackytank](https://github.com/jackytank)

## License
[MIT](https://choosealicense.com/licenses/mit/)
