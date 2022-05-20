package edu.kalum.enrollment.core.beans;

import edu.kalum.enrollment.models.dao.IAspiranteDao;
import edu.kalum.enrollment.models.dao.ICarreraTecnicaDao;
import edu.kalum.enrollment.models.dao.IEnrollmentProcessDao;
import edu.kalum.enrollment.models.domain.Aspirante;
import edu.kalum.enrollment.models.domain.CarreraTecnica;
import edu.kalum.enrollment.models.entities.EnrollmentRequest;
import edu.kalum.enrollment.models.entities.EnrollmentResponse;
import edu.kalum.enrollment.models.entities.StatusEnrollmentProcess;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Stateless
@WebService(endpointInterface = "edu.kalum.enrollment.core.beans.IEnrollmentService")
@Provider
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class EnrollmentServiceImpl implements IEnrollmentService {
    //private Logger logger = LogManager.getRootLogger();
    @Inject
    private IEnrollmentProcessDao enrollmentProcessDao;
    @Inject
    private ICarreraTecnicaDao carreraTecnicaDao;
    @Inject
    private IAspiranteDao aspiranteDao;

    @Override
    public EnrollmentResponse enrollmentProcess(EnrollmentRequest request) {
        //logger.debug("Iniciando proceso de inscripción");
        EnrollmentResponse enrollmentResponse = null;
        //logger.debug("Iniciando el proceso de busqueda de la carrera técnica con el id" + request.getCarreraId());
        CarreraTecnica carreraTecnica = carreraTecnicaDao.findById(request.getCarreraId());
        if(carreraTecnica != null){
            //logger.debug("Iniciando proceso de busqueda del aspirante con número de expediente " + request.getNoExpediente());
            Aspirante aspirante = aspiranteDao.findById(request.getNoExpediente());
            if(aspirante != null){
                //logger.info("Iniciando el proceso de inscripción");
                StatusEnrollmentProcess respuesta = null;
                try {
                    respuesta = enrollmentProcessDao.executeEnrollmentProcess(request);
                } catch(Exception e){
                    enrollmentResponse = new EnrollmentResponse(503, "Error ".concat(e.getCause().getMessage()));
                    //logger.error("Ocurrio un error al momento de llamar al procedimiento almacenado {" + e.getMessage() + "}");
                }
                if(respuesta != null){
                    //logger.info("Se finalizo el proceso de inscripción de forma exitosa");
                    enrollmentResponse = new EnrollmentResponse(201,respuesta.getStatus());
                }
            } else {
                //logger.warn("No se encontraron registros del aspirante con el número de expediente " + request.getNoExpediente());
                enrollmentResponse = new EnrollmentResponse(400,"El aspirante con el expediente " + request.getNoExpediente() + " no existe");
            }
        } else {
            //logger.warn("No se encontraron registros de la carrera técnica con id " + request.getCarreraId());
            enrollmentResponse = new EnrollmentResponse(400,"La carrera con el id " + request.getCarreraId() + " no existe");
        }
        return enrollmentResponse;
    }
}
