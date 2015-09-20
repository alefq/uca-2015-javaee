package py.edu.uca.test.service;

import java.util.List;

import py.edu.uca.test.domain.UserEntity;
import py.edu.uca.test.domain.enums.UserStateEnum;
import py.edu.uca.test.web.dto.UserDTO;
import py.edu.uca.test.web.dto.UserProfileDTO;

/**
 * Servicios para el manejo de Usuarios.
 * <p/>
 * Created by sodep on 17/01/14.
 */
public interface UserService {

    /**
     * Buscar usuario por identificador. El usuario tiene una relación a la
     * tabla/entity persona, este es un ejemplo de uso de annotations para
     * modelar las relaciones en JPA
     *
     * @param id
     *            Identificador del usuario.
     * @return Retorna un objeto del tipo
     *         {@link py.edu.uca.test.domain.UserEntity}.
     */
    UserEntity findById(Long id);

    /**
     * Buscar usuario por email.
     *
     * @param email
     *            Email del usuario.
     * @return Retorna un objeto del tipo
     *         {@link py.edu.uca.test.domain.UserEntity}.
     */
    UserEntity findByMail(String email);

    /**
     * Obtiene todos los usuarios del sistema.
     *
     * @return Una lista de {@link py.edu.uca.test.domain.UserEntity}. En caso
     *         de no existir ninguno entonces retorna una lista vacia.
     */
    List<UserEntity> findAll();

    /**
     * Guarda un usuario nuevo.
     *
     * @param user
     *            Usuario con los atributos cargados.
     * @return El usuario guardado.
     */
    UserEntity saveUser(UserEntity user);

    /**
     * Inhabilita un usuario.
     *
     * @param adminUserId
     *            Identificador del usuario que realiza la acción.
     * @param userIdToDisable
     *            Identificador del usuario a ser inhabilitado.
     * @param state
     *            Estado al que pasa el usuario.
     */
    void disableUser(Long adminUserId, Long userIdToDisable, UserStateEnum state);

    /**
     * Persiste un usuario existente con la información encapsulada en
     * {@link UserProfileDTO userProfileDTO}.
     *
     * @param userId
     *            Identificador del usuario.
     * @param userProfileDTO
     *            Información actualizada del usuario.
     */
    public void updateUser(Long userId, UserProfileDTO userProfileDTO);

    public void updateUser(Long userId, UserDTO userDTO);

    /**
     * Crea un nuevo usuario con la información encapsulada en {@link UserDTO}.
     *
     * @param currentUserId
     *            Identificador del usuario que realiza la acción.
     * @param userDTO
     *            Información del nuevo usuario.
     * @return Una instancia del nuevo usuario que persistió.
     */
    public UserEntity saveUser(Long currentUserId, UserDTO userDTO);

    /**
     * Elimina un usuario.
     *
     * @param userId
     *            Identificador del usuario que realiza la acción.
     * @param userToDelete
     *            Identificador del usuario a ser eliminado.
     */
    public void deleteUser(Long userId, Long userToDelete);

    /**
     * Valida si el <code>email</code> es único en el sistema.
     *
     * @param email
     *            Dirección de email a ser validada.
     * @return {@link java.lang.Boolean#TRUE} si es único y
     *         {@link java.lang.Boolean#FALSE} si no lo es.
     */
    public Boolean validateUniqueEmail(String email);

    /**
     * Verifica si el usuario se encuentra activo.
     *
     * @param userId
     *            Identificador del usuario.
     * @return {@link java.lang.Boolean#TRUE} si lo esta y
     *         {@link java.lang.Boolean#FALSE} si no lo esta.
     */
    public boolean isUserActive(Long userId);

    /**
     * Valida si el usuario se encuentra suspendido.
     *
     * @param userId
     *            identificador del ususario.
     * @return {@link java.lang.Boolean#TRUE} si lo esta y
     *         {@link java.lang.Boolean#FALSE} si no lo esta.
     */
    boolean isUserSuspended(Long userId);

}
