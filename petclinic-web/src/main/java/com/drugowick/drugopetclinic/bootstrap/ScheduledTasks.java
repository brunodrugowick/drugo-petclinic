package com.drugowick.drugopetclinic.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final DataLoader dataLoader;

    public ScheduledTasks(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Value("${petclinic.devmode:#{'0'}}")
    private String devMode;

    @Value("${petclinic.devmode.password:#{'xibanga'}}")
    private String devPass;

    @Value("${petclinic.maintenance.schedule:#{'24'}}")
    private String maintenance_schedule;

    @Scheduled(fixedDelayString = "${petclinic.maintenance.schedule:#{'86400000'}}",
            initialDelayString = "${petclinic.maintenance.schedule:#{'86400000'}}")
    public void scheduledMaintenance() {
        if (devMode.equals("1") && devPass.equals("aiowas")) {
            log.info("DEVMODE: Sample data will be created (Scheduled Maintenance).");
            dataLoader.loadData();
        }
    }
}
