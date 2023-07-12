package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hotel_capri");

    public static EntityManager create(){
        return FACTORY.createEntityManager();
    }

}
