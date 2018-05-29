package java2.servlets.mvc;

import java2.businesslogic.registration.RegistrationRequest;
import java2.businesslogic.registration.RegistrationResponse;
import java2.businesslogic.registration.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class UserRegistrationController {
    private static Logger logger = Logger.getLogger(UserRegistrationController.class.getName());

    @Autowired private UserRegistrationService userRegistrationService;

    @RequestMapping(value = "userRegistration", method = {RequestMethod.GET})
    public ModelAndView userRegistrationGet(HttpServletRequest request) {
        logger.info("userRegistration GET called.");
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("userRegistration", "model", null);
    }

    @RequestMapping(value = "userRegistration", method = {RequestMethod.POST})
    public ModelAndView userRegistrationPost(HttpServletRequest request) {
        logger.info("userRegistration POST called.");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        RegistrationRequest registrationRequest = new RegistrationRequest(login, password, name, email);
        RegistrationResponse response = userRegistrationService.register(registrationRequest);

        return new ModelAndView("userRegistration", "model", response);
    }
}
