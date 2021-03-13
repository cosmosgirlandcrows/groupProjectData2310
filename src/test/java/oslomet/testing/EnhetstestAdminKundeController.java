package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void testAdminKontoController_hentAlle_loggetInn() {
        List<Kunde> kunde = new ArrayList<>();
        Kunde kunde1 = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Asker", "22224444", "HeiHei");
        Kunde kunde2 = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Asker", "22224444", "HeiHei");
        kunde.add(kunde1);
        kunde.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentAlleKunder()).thenReturn(kunde);

        List<Kunde> resultat = adminKundeController.hentAlle();

        assertEquals(kunde, resultat);
    }

    @Test
    public void hentAlle_IkkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Kunde> resultat = adminKundeController.hentAlle();

        assertNull(resultat);
    }

    @Test
    public void lagreKunde_loggetInn() {

        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        Mockito.when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.registrerKunde(enKunde)).thenReturn("OK");

        String resultat = adminKundeController.lagreKunde(enKunde);

        assertEquals("OK", resultat);
    }

    @Test
    public void lagreKunde_ikkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.lagreKunde(null);

        assertEquals("Ikke logget inn", resultat);
        }

    @Test
    public void endre_loggetInn() {
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        Mockito.when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.endreKundeInfo(enKunde)).thenReturn("OK");

        String resultat = adminKundeController.endre(enKunde);

        assertEquals("OK", resultat);
    }

    @Test
    public void endre_ikkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.endre(null);

        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void slett_loggetInn() {

        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        Mockito.when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.slettKunde(anyString())).thenReturn("OK");

        String resultat = adminKundeController.slett("01010110523");

        assertEquals("OK", resultat);
    }

    @Test
    public void slett_ikkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.slett("01010110523");

        assertEquals("Ikke logget inn", resultat);
    }
}


