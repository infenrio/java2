package java2.servlets.mvc;

import java2.database.AnnouncementCategoryRepository;
import java2.domain.AnnouncementCategory;
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
public class AnnouncementCategoryController {
    private static Logger logger = Logger.getLogger(AnnouncementCategoryController.class.getName());

    @Autowired private AnnouncementCategoryRepository announcementCategoryRepository;

    @RequestMapping(value = "announcementCategories", method = {RequestMethod.GET})
    public ModelAndView announcementCategoriesGet(HttpServletRequest request) {
        logger.info("announcementCategories GET called.");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        List<AnnouncementCategory> categories =
                announcementCategoryRepository.getCategoriesByParentCategoryId(categoryId);
        return new ModelAndView("announcementCategories", "model", categories);
    }
}
