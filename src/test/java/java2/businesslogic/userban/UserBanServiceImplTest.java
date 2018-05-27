package java2.businesslogic.userban;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserBanServiceImplTest {
    @Mock private UserBanValidator validator;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private UserBanService service = new UserBanServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        UserBanRequest request = new UserBanRequest("login");
        User user = Mockito.mock(User.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.of(user));
        UserBanResponse response = service.ban(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(userRepository).banByLogin(any(String.class));
    }

    @Test
    public void shouldReturnFail() {
        UserBanRequest request = new UserBanRequest(null);
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        UserBanResponse response = service.ban(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}