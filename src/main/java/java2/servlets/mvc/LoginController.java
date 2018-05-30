package java2.servlets.mvc;

import java2.businesslogic.login.LoginRequest;
import java2.businesslogic.login.LoginResponse;
import java2.businesslogic.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired private LoginService loginService;

    @RequestMapping(value = "userLogin", method = {RequestMethod.GET})
    public ModelAndView userLoginGet(HttpServletRequest request) {
        logger.info("userLogin GET called.");
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("userLogin", "model", null);
    }

    @RequestMapping(value = "userLogin", method = {RequestMethod.POST})
    public ModelAndView userLoginPost(HttpServletRequest request) {
        logger.info("userLogin POST called.");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LoginResponse response = loginService.login(new LoginRequest(login, password,'U'));
        if(response.isSuccess()) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", response.getUserId());
            session.setAttribute("role", response.getRole());
            session.setAttribute("login", login);
            return new ModelAndView("userMain", "model", response);
        } else {
            return new ModelAndView("userLogin", "model", response);
        }
    }

    @RequestMapping(value = "adminLogin", method = {RequestMethod.GET})
    public ModelAndView adminLoginGet(HttpServletRequest request) {
        logger.info("adminLogin GET called.");
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("adminLogin", "model", null);
    }

    @RequestMapping(value = "adminLogin", method = {RequestMethod.POST})
    public ModelAndView adminLoginPost(HttpServletRequest request) {
        logger.info("adminLogin POST called.");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LoginResponse response;
        response = loginService.login(new LoginRequest(login, password,'S'));
        if(response.isSuccess()) {
            HttpSession session = request.getSession( );
            session.setAttribute("userId", response.getUserId());
            session.setAttribute("role", response.getRole());
            session.setAttribute("login", login);
            return new ModelAndView("adminRegistration", "model", null);
        } else {
            response = loginService.login(new LoginRequest(login, password,'A'));
            if(response.isSuccess()) {
                HttpSession session = request.getSession( );
                session.setAttribute("userId", response.getUserId());
                session.setAttribute("role", response.getRole());
                session.setAttribute("login", login);
                return new ModelAndView("adminMain", "model", response);
            }
            return new ModelAndView("adminLogin", "model", response);
        }
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET})
    public ModelAndView logout(HttpServletRequest request) {
        logger.info("logout GET called.");
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        session.invalidate();
        if(role.equals("A")) {
            return new ModelAndView("adminLogin", "model", null);
        } else {
            return new ModelAndView("userLogin", "model", null);
        }

    }
}
