package py.edu.uca.test.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import py.edu.uca.test.service.EmpleadoService;
import py.edu.uca.test.web.dto.EmpleadoDTO;

@Controller
public class EmpleadoController {

	protected static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

	@Autowired
	private EmpleadoService empleadoService;

	@RequestMapping(method = RequestMethod.GET, value = "/api/empleado/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable Long id) {
	    EmpleadoDTO dto = empleadoService.findById(id);
		ResponseEntity<EmpleadoDTO> retorno = new ResponseEntity<EmpleadoDTO>(dto, HttpStatus.OK);
		return retorno;

	}

}
