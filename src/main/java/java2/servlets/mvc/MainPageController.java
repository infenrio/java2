package java2.servlets.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class MainPageController {
    private static Logger logger = Logger.getLogger(MainPageController.class.getName());

    @RequestMapping(value = "adminMain", method = {RequestMethod.GET})
    public ModelAndView adminMainGet(HttpServletRequest request) {
        logger.info("adminMain GET called.");
        HttpSession session = request.getSession();
        String role = "";
        try {
            role = session.getAttribute("role").toString();
        } catch (Exception e) {
            logger.info(e.toString());
        }
        if("A".equals(role)) {
            return new ModelAndView("adminMain", "model", null);
        } else {
            return new ModelAndView("adminLogin", "model", null);
        }
    }

    @RequestMapping(value = "userMain", method = {RequestMethod.GET})
    public ModelAndView userMainGet(HttpServletRequest request) {
        logger.info("userMain GET called.");
        HttpSession session = request.getSession();
        String role = "";
        try {
            role = session.getAttribute("role").toString();
        } catch (Exception e) {
            logger.info(e.toString());
        }
        if("U".equals(role)) {
            return new ModelAndView("userMain", "model", null);
        } else {
            return new ModelAndView("userLogin", "model", null);
        }
    }
}
