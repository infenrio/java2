package java2.servlets.mvc;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
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
public class UserAnnouncementsController {
    private static Logger logger = Logger.getLogger(UserAnnouncementsController.class.getName());

    @Autowired
    private AnnouncementRepository announcementRepository;

    @RequestMapping(value = "userAnnouncements", method = {RequestMethod.GET})
    public ModelAndView userAnnouncementsGet(HttpServletRequest request) {
        logger.info("userAnnouncements GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }
        String login = session.getAttribute("login").toString();
        List<Announcement> announcements =
                announcementRepository.findByLogin(login);
        return new ModelAndView("userAnnouncements", "model", announcements);
    }
}
