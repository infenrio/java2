package java2.views;

import org.springframework.stereotype.Component;

@Component
public class ProgramExitView implements View {
    @Override
    public void execute() {
        System.out.println("See you later!");
        System.exit(0);
    }
}
