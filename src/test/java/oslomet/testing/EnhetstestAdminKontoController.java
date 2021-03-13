package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestBody;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    // Ida og Sofia: Vi tror denne er riktig

    @Test
    public void testAdminKontoController_hentAlle_loggetInn() {
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentAlleKonti()).thenReturn(konti);

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        assertEquals(konti, resultat);
}
    @Test
    public void hentAlle_IkkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        assertNull(resultat);
    }

    @Test
    public void registrerKonto_loggetInn() {

        Konto enKonto = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);

        Mockito.when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.registrerKonto(enKonto)).thenReturn("OK");

        String resultat = adminKontoController.registrerKonto(enKonto);

        assertEquals("OK", resultat);
    }

    @Test
    public void registrerKonto_ikkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.registrerKonto(null);

        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void endreKonto_loggetInn() {

        Konto enKonto = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);


        Mockito.when(sjekk.loggetInn()).thenReturn("01010110522");

        Mockito.when(repository.endreKonto(enKonto)).thenReturn("OK");

        String resultat = adminKontoController.endreKonto(enKonto);

        assertEquals("OK", resultat);


    }


    @Test
    public void endreKonto_ikkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.endreKonto(null);

        assertEquals("Ikke innlogget", resultat);

    }



    @Test
    public void slettKonto_loggetInn() {

        Konto enKonto = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);

        Mockito.when(sjekk.loggetInn()).thenReturn("105010123456");

        Mockito.when(repository.slettKonto(anyString())).thenReturn("OK");

        String resultat = adminKontoController.slettKonto("12345678901");

        assertEquals("OK", resultat);

    }


    @Test
    public void slettKonto_ikkeLoggetInn() {
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.slettKonto(null);

        assertEquals("Ikke innlogget", resultat);

    }

}
