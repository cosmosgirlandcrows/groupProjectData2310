package oslomet.testing;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    // Ida og Sofia: Vi tror denne er riktig
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

    // Ida og Sofia: Vi tror denne er riktig
    @Test
    public void hentAlle_IkkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Kunde> resultat = adminKundeController.hentAlle();

        assertNull(resultat);
    }

    @Test
    public void lagre_loggetInn() {
        // arrange

        // act

        // assert

    }

    @Test
    public void lagre_ikkeLoggetInn() {
        // arrange

        // act

        // assert

    }

    @Test
    public void endre_loggetInn() {
        // arrange

        // act

        // assert

    }


    @Test
    public void endre_ikkeLoggetInn() {
        // arrange

        // act

        // assert
    }

    /*
     @GetMapping("/slett")
    public String slett(String personnummer) {
        String p = sjekk.loggetInn();
        if (p !=null) {
            return repository.slettKunde(personnummer);
        }
        return "Ikke logget inn";
    }
     */

    // Vi prøvde denne, men denne er også feil:(
    @Test
    public void slett_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        Mockito.when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.slettKunde(anyString())).thenReturn("OK");

        // act
        String resultat = adminKundeController.slett("01010110523");

        // assert
        assertThrows();
    }



  /*  @Test
    public void slett_loggetInn() {
        // arrange
        List<> slett = new ArrayList<>();
        Konto slett1 = new Konto();
        Konto slett2 = new Konto();

        slett.add(slett1);
        slett.add(slett2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.slettKunde(anyString())).thenReturn("01010110523");

        List<Konto> resultat = adminKundeController.slett("105010123456");


        // act

        // assert

    } */

    @Test
    public void slett_ikkeLoggetInn() {
        // arrange

        // act

        // assert


    }

}
