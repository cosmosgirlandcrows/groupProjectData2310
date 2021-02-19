package oslomet.testing;

//Domain of Rosa

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhet {

    @Mock
    private Sikkerhet sjekk;

    @Mock
    private BankRepository repository;

    @Test
    public void testsjekkLoggInn() {
        Kunde kunde1 = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Asker", "22224444", "HeiHei");


        when(sjekk.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        String checkOk = sjekk.sjekkLoggInn("01010110523", "HeiHei");

        assertEquals(checkOk, "OK");


    }

    //How do I mock a https session?
    @Test
    public void testloggUt(){

        // arrange

        // act

        // assert


    }

    @Test
    public void testloggInnAdmin(){

        // arrange

        // act
        when(sjekk.loggInnAdmin("Admin","Admin")).thenReturn("Logget inn");
        when(sjekk.loggInnAdmin("testFail", "testFail")).thenReturn("Ikke logget inn");

        String loginCheck = sjekk.loggInnAdmin("Admin", "Admin");

        String loginfailCheck = sjekk.loggInnAdmin("testFail", "testFail");
        // assert
        assertEquals("Logget inn", loginCheck);
        assertEquals("Ikke logget inn", loginfailCheck);

    }

    //idk how to test things that dont return strings, this one returns a session getattribute change so idk how to test for that
    @Test
    public void testloggetInn(){

        // arrange

        // act

        // assert

    }
}
