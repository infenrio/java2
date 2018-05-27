package java2.businesslogic.registration.adminregistration;

import java2.businesslogic.ValidationError;
import java2.businesslogic.registration.RegistrationRequest;
import java2.businesslogic.registration.RegistrationResponse;
import java2.businesslogic.registration.RegistrationValidator;
import java2.businesslogic.registration.userregistration.UserRegistrationService;
import java2.businesslogic.registration.userregistration.UserRegistrationServiceImpl;
import java2.database.UserRepository;
import java2.database.UserStateRepository;
import java2.domain.User;
import java2.domain.UserState;
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
public class AdminRegistrationServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock private UserStateRepository userStateRepository;
    @Mock private RegistrationValidator validator;

    @InjectMocks
    private UserRegistrationService service = new UserRegistrationServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        RegistrationRequest request = new RegistrationRequest("login", "password", "name", "email");
        UserState userState = Mockito.mock(UserState.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        Mockito.when(userStateRepository.findById("ACTIVE")).
                thenReturn(Optional.of(userState));
        RegistrationResponse response = service.register(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(userRepository).save(any(User.class));
    }

    @Test
    public void shouldReturnFail() {
        RegistrationRequest request = new RegistrationRequest(null, "password", "name", "email");
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        RegistrationResponse response = service.register(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
        Mockito.verifyZeroInteractions(userRepository);
    }
}