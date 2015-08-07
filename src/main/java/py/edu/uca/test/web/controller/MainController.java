package py.edu.uca.test.web.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sodep on 21/07/14.
 */


@Api(value = "main", description = "Raiz de la aplicación. Actualmente redirecciona a la documentación de la API")
@Controller("/")
public class MainController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @ApiOperation(value = "Realiza el rediccionamiento a '/public/api-doc/index.html', el cual despliega la documentación de la API ")
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public void defaultApp(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        String appUrl = "/public/api-doc/index.html";
        redirectStrategy.sendRedirect(request, response, appUrl);
    }


}
