package py.edu.uca.test.web.controller;

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
import py.edu.uca.test.web.dto.SuccessResponseDTO;
import py.edu.uca.test.web.dto.UserDTO;
import py.edu.uca.test.web.dto.UserProfileDTO;
import py.edu.uca.test.web.session.UserCurrentInfo;

@Api(value = "userProfile", description = "Contiene informacion del usuario autenticado.")
@Controller
public class UserController {

	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retorna información del perfil del usuario")
	@RequestMapping(method = RequestMethod.GET, value = "/api/users/{userId}/profile")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<UserProfileDTO> userProfile(@PathVariable Long userId) {
		UserEntity user = userService.findById(userId);
		UserProfileDTO dto = UserConverter.buildFullUserProfileDTO(user);
		if (dto != null && dto.getUserId() != null) {
			return new ResponseEntity<UserProfileDTO>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserProfileDTO>(new UserProfileDTO(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Deshabilita el usuario solicitado, impidiendo que pueda seguir utilizando la aplicación.")
	@RequestMapping(method = RequestMethod.POST, value = "/api/user/{userIdTodisable}/disable")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<SuccessResponseDTO> disableUser(@ModelAttribute UserCurrentInfo userCurrentInfo,
			@PathVariable Long userIdTodisable) {

		SuccessResponseDTO result = new SuccessResponseDTO();
		HttpStatus status = HttpStatus.OK;

		try {
			userService.disableUser(userCurrentInfo.getUserId(), userIdTodisable, UserStateEnum.SUSPENDED_BY_ADMIN);
		} catch (RuntimeException e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<SuccessResponseDTO>(result, status);

	}

	@ApiOperation(value = "Modifica el password del usuario autenticado. Debe proveerse del password vigente y del nuevo password (debe ser diferente al vigente)")
	@RequestMapping(method = RequestMethod.POST, value = "/api/user/password")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<SuccessResponseDTO> password(@ModelAttribute UserCurrentInfo userCurrentInfo,
			@RequestBody CredentialsDTO credentials) {

		SuccessResponseDTO result = new SuccessResponseDTO();
		HttpStatus status = HttpStatus.OK;

		UserEntity user = userService.findById(userCurrentInfo.getUserId());
		if (!credentials.getPassword().equals(user.getPassword())) {
			result.setMessage("El password actual es incorrecto");
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
			return new ResponseEntity<SuccessResponseDTO>(result, status);
		}
		if (credentials.getNewPassword().equals(user.getPassword())) {
			result.setMessage("El nuevo password debe ser distinto al actual");
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
			return new ResponseEntity<SuccessResponseDTO>(result, status);
		}
		user.setPassword(credentials.getNewPassword());
		userService.saveUser(user);
		result.setMessage("Password guardado con éxito");
		return new ResponseEntity<SuccessResponseDTO>(result, status);

	}

	@ApiOperation(value = "Actualiza los datos el usuario.")
	@RequestMapping(method = RequestMethod.PUT, value = "/api/admin/users/{userId}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<SuccessResponseDTO> update(@PathVariable Long userId,
			@RequestBody UserDTO userDTO) {
		SuccessResponseDTO result = new SuccessResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			userService.updateUser(userId, userDTO);
			result.setMessage("Usuario actualizado con éxito");
		} catch (RuntimeException e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<SuccessResponseDTO>(result, status);
	}

	@ApiOperation(value = "Actualiza los datos el usuario.")
	@RequestMapping(method = RequestMethod.PUT, value = "/api/users/{userId}/profile")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<SuccessResponseDTO> account(@PathVariable Long userId,
			@RequestBody UserProfileDTO userProfileDTO) {
		SuccessResponseDTO result = new SuccessResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			userService.updateUser(userId, userProfileDTO);
			result.setMessage("Usuario actualizado con éxito");
		} catch (RuntimeException e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<SuccessResponseDTO>(result, status);
	}

	@ApiOperation(value = "Crea un nuevo usuario para el sistema. Si el usuario se encuentra en el estado: WAITING_FOR_APPROVAL, se notifica al administrador para que complete el registro.")
	@RequestMapping(method = RequestMethod.POST, value = "/api/admin/users")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<SuccessResponseDTO> saveUser(@ModelAttribute UserCurrentInfo userCurrentInfo,
			@RequestBody UserDTO userDTO) {
		SuccessResponseDTO result = new SuccessResponseDTO();
		HttpStatus status = HttpStatus.CREATED;
		try {
			UserEntity newUser = userService.saveUser(userCurrentInfo.getUserId(), userDTO);
			result.setMessage("Usuario creado con éxito");
		} catch (RuntimeException e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<SuccessResponseDTO>(result, status);
	}

	@ApiOperation(value = "Elimina un usuario existente. Se verifica que el usuario a eliminar exista y sea distinto del que realiza la operación.")
	@RequestMapping(method = RequestMethod.DELETE, value = "/api/admin/users/{userToDelete}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseEntity<SuccessResponseDTO> deleteUser(@PathVariable Long userToDelete,
			@ModelAttribute UserCurrentInfo userCurrentInfo) {
		SuccessResponseDTO result = new SuccessResponseDTO();
		HttpStatus status = HttpStatus.OK;
		try {
			userService.deleteUser(userCurrentInfo.getUserId(), userToDelete);
			result.setMessage("Usuario eliminado con éxito");
		} catch (RuntimeException e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<SuccessResponseDTO>(result, status);
	}

}
