package java2.database;

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
}
