package vn.ghost.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ghost.jobhunter.service.EmailService;
import vn.ghost.jobhunter.service.SubscriberService;
import vn.ghost.jobhunter.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private final EmailService emailService;
      private final SubscriberService subscriberService;

    public EmailController(EmailService emailService,SubscriberService subscriberService) {
        this.emailService = emailService;
        this.subscriberService=subscriberService;
    }

    @GetMapping("/email")
    @ApiMessage("Send simple email")
    public String sendSimpleEmail() {
        // this.emailService.sendSimpleEmail();
        this.subscriberService.sendSubscribersEmailJobs();
        return "ok";
    }
}
