package com.github.jaytobi.thorntail.jpa.controller;


import com.github.jaytobi.thorntail.jpa.entities.Animal;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("/animals")
public class AnimalController {
    private static Logger log = Logger.getLogger(SimpleRestController.class);  //use this - because slf4j may cause problems on Java 9 + ?

    @PersistenceContext(name = "MyPU")
    private EntityManager entityManager;  //TODO must be moved to another layer - just for simple prototype

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> getAnimal() {
        Animal animal = new Animal();
        animal.setAge(13);
        animal.setName("Frog");

        log.warnf("reading animals from database");
        entityManager.persist(animal);
        List<Animal> animals = (List<Animal>) entityManager.createNativeQuery("SELECT * FROM Animal;").getResultList();
        return animals;
    }
}
