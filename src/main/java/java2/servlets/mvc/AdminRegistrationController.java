package java2.servlets.mvc;

import java2.businesslogic.registration.RegistrationRequest;
import java2.businesslogic.registration.RegistrationResponse;
import java2.businesslogic.registration.adminregistration.AdminRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class AdminRegistrationController {
    private static Logger logger = Logger.getLogger(UserRegistrationController.class.getName());

    @Autowired
    private AdminRegistrationService adminRegistrationService;

    @RequestMapping(value = "adminRegistration", method = {RequestMethod.GET})
    public ModelAndView adminRegistrationGet(HttpServletRequest request) {
        logger.info("adminRegistration GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("S")) {
            return new ModelAndView("adminLogin", "model", null);
        }
        return new ModelAndView("adminRegistration", "model", null);
    }

    @RequestMapping(value = "adminRegistration", method = {RequestMethod.POST})
    public ModelAndView adminRegistrationPost(HttpServletRequest request) {
        logger.info("adminRegistration POST called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("S")) {
            return new ModelAndView("adminLogin", "model", null);
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        RegistrationRequest registrationRequest = new RegistrationRequest(login, password, name, email);
        RegistrationResponse response = adminRegistrationService.register(registrationRequest);

        return new ModelAndView("adminRegistration", "model", response);
    }
}
