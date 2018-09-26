package org.concatel.backEndExam3.repositories;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.log4j.Logger;
import org.concatel.backEndExam3.exceptions.EmptyNameException;
import org.concatel.backEndExam3.exceptions.EmptyPlanetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RebelDAO {

    @Autowired
    Environment env;
    private String a;
    private String b;
    private Path rebelsLog;
    private final static Logger logger = Logger.getLogger(RebelDAO.class);

    public RebelDAO() {
        this.rebelsLog = Paths.get("C:/Users/agonzalez/Desktop/rebelsLocations.log");
    }

    public void save(String name, String planet) throws Exception {
        if (name.trim().isEmpty()) {
            throw new EmptyNameException("The rebel's name can't be empty.");
        }
        if (planet.trim().isEmpty()) {
            throw new EmptyPlanetException("The rebel's location can't be empty.");
        }
        String date = new Date().toString();
        FileWriter fw = new FileWriter(rebelsLog.toFile(), Boolean.TRUE);
        fw.write(String.format("Rebel %s on %s at %s\n", name, planet, date));
        fw.close();
        logger.info(String.format("Logged rebel %s on %s at %s", name, planet, date));
    }
}
