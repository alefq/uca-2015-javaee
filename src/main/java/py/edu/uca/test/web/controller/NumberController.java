package py.edu.uca.test.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import py.edu.uca.test.converter.UserConverter;
import py.edu.uca.test.domain.UserEntity;
import py.edu.uca.test.domain.enums.UserStateEnum;
import py.edu.uca.test.service.UserService;
import py.edu.uca.test.web.dto.CredentialsDTO;
import py.edu.uca.test.web.dto.NumberDTO;
import py.edu.uca.test.web.dto.SuccessResponseDTO;
import py.edu.uca.test.web.dto.UserDTO;
import py.edu.uca.test.web.dto.UserProfileDTO;
import py.edu.uca.test.web.session.UserCurrentInfo;

@Controller
public class NumberController {

	protected static final Logger logger = LoggerFactory.getLogger(NumberController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/api/check/{number}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<NumberDTO> isPar(@PathVariable Integer number) {
		
		NumberDTO dto = new NumberDTO();
		if(number %2 == 0) {
			dto.setFecha(new Date());
			dto.setValue(number);
			dto.setPar(true);
		}
		ResponseEntity<NumberDTO> retorno = new ResponseEntity<NumberDTO>(dto, HttpStatus.OK);
		return retorno;

	}

}
