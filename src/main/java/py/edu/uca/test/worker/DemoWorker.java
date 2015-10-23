package py.edu.uca.test.worker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.service.EmpleadoService;

@Component("demoWorker")
public class DemoWorker {

    @Autowired
    private EmpleadoService  empleadoService;
    
    protected static final Logger logger = LoggerFactory.getLogger(DemoWorker.class);
    private static final AtomicLong counter = new AtomicLong(0);

    /**
     * Cada 5 segundos, se procesa la cola de compositions que se encuentren en
     * estado {@link CompositionScrapperStatusEnum#PENDING} <br/>
     * Una vez invocado el rest service para actualización se marca como
     * "RELOADED".
     */
    @Scheduled(fixedDelay = 5000)
    public void processCompositionsFromQueue() {
        Date now = new Date();
        long increment = counter.incrementAndGet();
//        logger.info("[" + increment + "] estoy en el worker, fecha: " + now);
//        System.out.println("[" + increment + "] estoy en el worker, fecha: " + now);
        long countEmpleados = empleadoService.count();
        List<Empleado> todos = empleadoService.findAll();
        for(Empleado empleado: todos) {
            logger.info("Empleado:" + empleado.getNombre());
        }
//        logger.info("Cantidad de empleados: " + countEmpleados);
    }

}
