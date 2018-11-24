package com.github.jaytobi.thorntail.jpa.controller;

import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalDateTime;

@RequestScoped
@Path("/simple")
public class SimpleRestController {
    private static Logger log = Logger.getLogger(SimpleRestController.class);  //use this - because slf4j may cause problems on Java 9 + ?


    @GET
    @Path("/")
    public String hello() {
        LocalDateTime now = LocalDateTime.now();
        log.warnf("now is %s", now);
        return now.toString();
    }
}
