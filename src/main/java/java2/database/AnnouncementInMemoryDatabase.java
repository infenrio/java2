package java2.database;

import java2.models.Announcement;
import java2.models.AnnouncementState;
import java2.models.User;
import java2.models.UserState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnnouncementInMemoryDatabase implements AnnouncementDatabase {
    private List<Announcement> announcements = new ArrayList<>();

    @Override
    public void add(Announcement announcement) {
        announcements.add(announcement);
    }

    @Override
    public List<Announcement> findByUser(User user) {
        return announcements.stream()
                .filter(a -> a.getCreator().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Announcement> findByTitle(String title) {
        return announcements.stream()
                .filter(a -> a.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        List<Announcement> allAnnouncements = new ArrayList<>();
        allAnnouncements.addAll(announcements);
        return allAnnouncements;
    }

    @Override
    public List<Announcement> getValidAnnouncements() {
        return announcements.stream()
                .filter(a -> !a.getState().equals(AnnouncementState.BANNED))
                .filter(a -> !a.getCreator().getState().equals(UserState.BANNED))
                .collect(Collectors.toList());
    }

    @Override
    public void banAnnouncement(Announcement announcement) {
        announcement.ban();
    }
}
