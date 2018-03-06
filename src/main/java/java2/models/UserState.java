package java2.models;

public enum UserState {
    ACTIVE("User is active"),
    BANNED("User is banned");

    private String stateText;

    UserState(String stateText) {
        this.stateText = stateText;
    }

    public String getStateText() {
        return stateText;
    }
}
