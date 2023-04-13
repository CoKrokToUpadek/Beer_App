//package com.cokroktoupadek.beersandmealsbackend.scheduler;
//
//import com.cokroktoupadek.beersandmealsbackend.facade.AdminFacade;
//import com.cokroktoupadek.beersandmealsbackend.service.email.SimpleEmailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalTime;
//
//@Component
//@RequiredArgsConstructor
//public class UpdateBeersAndMealsDbScheduler {
//
//    private AdminFacade adminFacade;
//
//    private SimpleEmailService emailService;
//
//    @Autowired
//    public UpdateBeersAndMealsDbScheduler(AdminFacade adminFacade, SimpleEmailService emailService) {
//        this.adminFacade = adminFacade;
//        this.emailService = emailService;
//    }
//    //worked with fixed delay, so I assume it will work with cron
//    @Scheduled(cron = "0 0 10 * * *")
//    void updateDbs() {
//        String msg;
//        try {
//            adminFacade.updateMealDbFacade();
//            adminFacade.updateBeerDbFacade();
//            msg = "Db updated successfully at:" + LocalTime.now();
//            emailService.send(msg);
//        } catch (Exception e) {
//            msg = "Something when wrong when updating db at:" + LocalTime.now()+"\n"+"Error msg:"+e.getMessage();
//            emailService.send(msg);
//        }
//    }
//}
