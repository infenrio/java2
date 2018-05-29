package java2.servlets.mvc;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class AnnouncementListController {
    private static Logger logger = Logger.getLogger(AnnouncementListController.class.getName());

    @Autowired private AnnouncementRepository announcementRepository;

    @RequestMapping(value = "announcementList", method = {RequestMethod.GET})
    public ModelAndView userLoginGet(HttpServletRequest request) {
        logger.info("announcementList GET called.");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        List<Announcement> announcements =
                announcementRepository.findByCategory(categoryId);
        return new ModelAndView("announcementList", "model", announcements);
    }
}
