package com.controller;

import com.service.SMSService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Tag(description = "SMS API", name = "SMS Services")
@Controller
public class SMSController {

    @Autowired
    private final SMSService smsService;

    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/sendSMS")
    @ResponseBody
    @Operation(summary = "Send SMS via phone",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Returns the response received from the SMS server.", content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"phone\": \"0712345678\", " + "\"message\": \"Message has been sent\"" + "\"status\": \"200\"}"))),
                    @ApiResponse(responseCode = "422", description = "The username or password sent to the SMS server is incorrect.", content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Username or Password is incorrect!\", " + "\"status\": \"422\"}"))),
                    @ApiResponse(responseCode = "500", description = "SMS server is closed.", content = @Content(mediaType = "application/json"))
            })

    public String sendSMSViaPhone(@RequestParam(name = "username") String username,
                                  @RequestParam(name = "password") String password,
                                  @RequestParam(name = "address", defaultValue = "http://192.168.0.104") String address,
                                  @RequestParam(name = "port") String port,
                                  @RequestParam(name = "recipientNumber") String phone,
                                  @RequestParam(name = "message") String message) throws IOException {

        return smsService.sendSMSViaPhone(username, password, address, port, phone, message);
    }
}

