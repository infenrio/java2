package java2.models;

public enum AnnouncementState {
    ACTIVE("Announcement is active"),
    ARCHIVED("Announcement is archived"),
    BANNED("Announcement is banned");

    private String stateText;

    AnnouncementState(String stateText) {
        this.stateText = stateText;
    }

    public String getStateText() {
        return stateText;
    }
}
