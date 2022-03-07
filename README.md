## Step-by-step guide on how to send SMS from an application via an Android phone

### 🌀 Step 1 - Download [GSM Modem app](https://play.google.com/store/apps/details?id=com.gsmmodem) from Google Play

![GSM Modem](src/main/resources/img/google_play2.jpeg)



### 🌀 Step 2 - Download and install [GSM Helper Tool](https://play.google.com/store/apps/details?id=com.gsmmodem) from GitHub

#### You can find more information about this tool [here](https://github.com/sadiqodho/GSM-Helper-Tool#readme).

![GSM Helper Tool download](src/main/resources/img/helper_tool_download.jpeg)


### 🌀 Step 3 - Create a user on GSM Modem application:

#### Open the application and go to Users.


![GSM Modem start page](src/main/resources/img/app_start.jpeg)

#### Press on the plus button and add a user.
#### The username and password entered here will be used in our application to log in to the server provided by the GSM Modem application.
![GSM Modem users](src/main/resources/img/add_user.jpeg)


### 🌀 Step 4 - Go back to the main page and start the server
![GSM Modem start server](src/main/resources/img/server_on.jpeg)


### 🌀 Step 5 - Make sure you have the Helper Tool application running in the background
> Now you can send requests from your application to the server provided by the GSM Modem application on your phone.


### 🌀 Step 6 - Send request from the application

```
public String sendSMSViaPhone(String username, String password, String address, String port, String phone, String message) throws IOException {
        String messageEncoded = URLEncoder.encode(message, "UTF-8");

        return WebClient
                .builder()
                .baseUrl(address + ":" + port)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/SendSMS")
                        .queryParam("username", username)
                        .queryParam("password", password)
                        .queryParam("phone", phone)
                        .queryParam("message", messageEncoded)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
```

---


## Response :

### ❄ Message sent successfully

![Positive response](src/main/resources/img/response_200.png)

Note: If the Helper Tool is not running in the background you will still receive a positive response, but the message will not be delivered.

### ❄ Username or password
![Incorrect username or password](src/main/resources/img/username_or_password_error.png)

Make sure you entered the correct credentials from `Step 3`.

### ❄ Internal Server error

![Server error](src/main/resources/img/error.png)

Make sure you start the SMS server from GSM Modem application.


---

## Extra information:

* On `Settings` page, you can set the server port, the receive SMS URL and the number of SMS your phone is allowed to send per minute.
###
![GSM Modem start server](src/main/resources/img/customize.jpeg)
#
* On `JSON Sample` page, you find an example on how the send SMS request should look like.
###
![GSM Modem request example](src/main/resources/img/request_example.jpeg)
