package oslomet.testing;

//Domain of Rosa

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ReactiveAdapterRegistry;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.springframework.mock.web.MockHttpSession;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhetController {

    @Mock
    private Sikkerhet sjekk;

    @Mock
    private BankRepository repository;

    @Mock
    private MockHttpSession session;

    @Test
    public void testsjekkLoggInn() {
        Kunde kunde1 = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Asker", "22224444", "HeiHei");


        when(sjekk.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        String checkOk = sjekk.sjekkLoggInn("01010110523", "HeiHei");

        assertEquals(checkOk, "OK");


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
    @Test
    public void testloggetInn(){

        // arrange

        String personNummer = "12345678901";

        session.setAttribute("Innlogget", personNummer);

        when(sjekk.loggetInn()).thenReturn("Innlogget");



        String loggetInnCheck = sjekk.loggetInn();
        // act

        assertEquals("Innlogget", loggetInnCheck);

        // assert

    }

    @Test
    public void testLoggUt(){

        String personNummer = "12345678901";

        session.setAttribute("Innlogget", personNummer);

        sjekk.loggUt();


        assertNull(session.getAttribute("Innlogget"));




    }

}
