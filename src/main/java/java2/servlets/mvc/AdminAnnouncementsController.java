package java2.servlets.mvc;

import java2.businesslogic.ValidationError;
import java2.businesslogic.announcementban.AnnouncementBanRequest;
import java2.businesslogic.announcementban.AnnouncementBanResponse;
import java2.businesslogic.announcementban.AnnouncementBanService;
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
public class AdminAnnouncementsController {
    private static Logger logger = Logger.getLogger(AdminAnnouncementsController.class.getName());

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    AnnouncementBanService announcementBanService;

    @RequestMapping(value = "adminAnnouncements", method = {RequestMethod.GET})
    public ModelAndView adminAnnouncementsGet(HttpServletRequest request) {
        logger.info("adminAnnouncements GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("A")) {
            return new ModelAndView("adminLogin", "model", null);
        }
        try {
            String login = request.getParameter("login");
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info(login + " " + id);
            AnnouncementBanResponse banResponse =
            announcementBanService.ban(new AnnouncementBanRequest(login, id));
            for(ValidationError error : banResponse.getErrors()) {
                logger.info(error.getField() + ":" + error.getErrorMessage());
            }
        } catch(Exception e) {
            logger.info("Announcement ban was not successful!");
        }
        List<Announcement> announcements =
                announcementRepository.getAllAnnouncements();
        return new ModelAndView("adminAnnouncements", "model", announcements);
    }
}
