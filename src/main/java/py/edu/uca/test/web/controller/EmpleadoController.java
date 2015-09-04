package py.edu.uca.test.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.service.EmpleadoService;
import py.edu.uca.test.web.dto.EmpleadoDTO;
import py.edu.uca.test.web.dto.SuccessResponseDTO;
import py.edu.uca.test.web.dto.UserDTO;

@Controller
public class EmpleadoController {

    protected static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private EmpleadoService empleadoService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/empleado/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable Long id) {
        ResponseEntity<EmpleadoDTO> retorno;
        EmpleadoDTO dto = empleadoService.findById(id);
        if (dto != null && dto.getId() != null) {
            retorno = new ResponseEntity<EmpleadoDTO>(dto, HttpStatus.OK);
        } else {
            retorno = new ResponseEntity<EmpleadoDTO>(dto, HttpStatus.NOT_FOUND);
        }
        return retorno;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/empleado")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<SuccessResponseDTO> saveUser(@RequestBody EmpleadoDTO empleadoDTO) {
        SuccessResponseDTO result = new SuccessResponseDTO();
        HttpStatus status = HttpStatus.CREATED;
        try {
            Empleado newEmpleado = empleadoService.saveEmpleado(empleadoDTO);
            if(newEmpleado != null && newEmpleado.getId() != null) {
                result.setMessage("Empleado creado con éxito");
            } else {
                result.setMessage("No se pudo crear el empleado");
                result.setSuccess(false);
            }
        } catch (RuntimeException e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<SuccessResponseDTO>(result, status);
    }

}
