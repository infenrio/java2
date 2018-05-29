package java2.servlets.mvc;

import java2.businesslogic.ValidationError;
import java2.businesslogic.login.LoginRequest;
import java2.businesslogic.login.LoginResponse;
import java2.businesslogic.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class HelloWorldController {

    private static Logger logger = Logger.getLogger(HelloWorldController.class.getName());

    @Autowired private LoginService loginService;

//    @RequestMapping(value = "hello/{id}", method = {RequestMethod.GET})
//    public ModelAndView processGet(HttpServletRequest request,
//                                   @PathVariable Long id) {
//
//        logger.info("ID = " + id);
//
//        String productTitle = request.getParameter("title");
//        String productDescription = request.getParameter("description");
//
//        addProductService.addProduct(productTitle, productDescription);
//
//        return new ModelAndView("helloWorld", "model", "Hello from MVC! + id");
//    }

    @RequestMapping(value = "hello", method = {RequestMethod.GET})
    public ModelAndView process(HttpServletRequest request) {

        String productTitle = request.getParameter("title");
        String productDescription = request.getParameter("description");

//        addProductService.addProduct(productTitle, productDescription);

        return new ModelAndView("helloWorld2", "model", "Hello from MVC2!");
    }

//    @RequestMapping(value = "userLogin", method = {RequestMethod.GET})
    public ModelAndView processGet(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LoginResponse response = loginService.login(new LoginRequest(login, password,'U'));

        return new ModelAndView("userLogin", "model", response);
    }

//    @RequestMapping(value = "userLogin", method = {RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String htmlModel = "";
        LoginResponse response = loginService.login(new LoginRequest(login, password,'U'));
        if(response.isSuccess()) {
//            return new ModelAndView("helloWorld2", "model",
//                    "Logged in with id: " + response.getUserId());

            return new ModelAndView("userLogin", "model",
                    response);
        }
        for (ValidationError error: response.getErrors()) {
            htmlModel += "<div>" + error.getField() + " - " + error.getErrorMessage() + "</div>";
        }

        return new ModelAndView("userLogin", "model", response);
    }
}
