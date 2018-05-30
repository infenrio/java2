package java2.servlets.mvc;

import java2.businesslogic.ValidationError;
import java2.businesslogic.userban.UserBanRequest;
import java2.businesslogic.userban.UserBanResponse;
import java2.businesslogic.userban.UserBanService;
import java2.database.UserRepository;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class AdminUsersController {
    private static Logger logger = Logger.getLogger(AdminUsersController.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBanService userBanService;

    @RequestMapping(value = "adminUsers", method = {RequestMethod.GET})
    public ModelAndView adminUsersGet(HttpServletRequest request) {
        logger.info("adminUsers GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("A")) {
            return new ModelAndView("adminLogin", "model", null);
        }
        try {
            String login = request.getParameter("login");
            UserBanResponse banResponse =
                    userBanService.ban(new UserBanRequest(login));
            for(ValidationError error : banResponse.getErrors()) {
                logger.info(error.getField() + ":" + error.getErrorMessage());
            }
        } catch(Exception e) {
            logger.info("User ban was not successful!");
        }
        List<User> users =
                userRepository.getAllUsers();
        return new ModelAndView("adminUsers", "model", users);
    }
}
