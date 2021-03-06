package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
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
public class EnhetstestBankController {


    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;


    @Test
    public void testBankController_hentTransaksjoner_loggetInn() {

        //We initially start with making two transactions to attach to our mock dummy

        Transaksjon t1 = new Transaksjon(113, "test", 4.5, "2013-03-21", "Hei :)", "test", "accountnr2");
        Transaksjon t2 = new Transaksjon(113, "test", 4.5, "2013-03-25", "Hei :)", "test", "accountnr2");

        //We need a list to be able to attach them to the account as all transactions are in a list!
        List<Transaksjon> transList = new ArrayList<>();


        transList.add(t1);
        transList.add(t2);

        //this is an account, a mock account that has the transactions in the list attached to it. Cool!

        Konto k1 = new Konto("a", "b", 600.00, "type", "nok", transList);

        //We check for a logged in account. If we don't do this, it won't work because we'd be dealing with a null situation
        when(sjekk.loggetInn()).thenReturn("01010110523");


        //I'm not 100% sure what this does. needs to be linked to the thing you're testing, and the dummy object you made to see if its valid I guess?

        //Why?
        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(k1);

        //Here we make a Konto object for the result of the hentTransaksjoner in the Controller. Why does a method called
        //"getTransactions" want a Konto object to return instead of a list of transactions? lmao who knows
        Konto resultat = bankController.hentTransaksjoner("01010110523", "2000-01-01", "2020-01-01");

        //k1 doesn't contain actual transactions, we're just checking if the structure is the same I think
        assertEquals(k1.getTransaksjoner(), resultat.getTransaksjoner());

    }

    @Test
    public void testBankController_hentTransaksjoner_ikkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        Konto resultat = bankController.hentTransaksjoner("01010110523", "2000-01-01", "2020-01-01");

        assertNull(resultat);

    }



    @Test
    public void hentKonti_LoggetInn() {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    //Floyd
    @Test
    public void hentSaldi_loggetInn() {
        // arrange
        List<Konto> saldi = new ArrayList<>();

        Konto saldo1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto saldo2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        saldi.add(saldo1);
        saldi.add(saldo2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi(anyString())).thenReturn(saldi);

        // act
        List<Konto> resultat = bankController.hentSaldi();


        // assert

        assertEquals(saldi, resultat);



    }

    //Floyd
    @Test
    public void hentSaldi_ikkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert

        assertNull(resultat);

    }


    //Rosa
    @Test
    public void registrerBetaling_loggetInn() {


        // arrange

        //   public Transaksjon(int txID, String fraTilKontonummer, double belop, String dato, String melding, String avventer, String kontonummer)
        Transaksjon testTransacc = new Transaksjon(123,"thisacc", 13.37, "2017-05-29","test", "1","thatacc");
        // act

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerBetaling(testTransacc)).thenReturn("OK");

        String resultat = bankController.registrerBetaling(testTransacc);

        // assert
        assertEquals("OK", resultat);

    }

    //Rosa
    @Test
    public void registrerBetaling_ikkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        Transaksjon testTransacc = new Transaksjon(123,"thisacc", 13.37, "2017-05-29","test", "1","thatacc");

        String resultat = repository.registrerBetaling(testTransacc);

        // assert
        assertNull(resultat);
    }


// Her er koden til Ida og Sofia

    //Ida og sofia
    @Test
    public void hentBetalinger_loggetInn() {
        List<Transaksjon> betaling = new ArrayList<>();
        Transaksjon betaling1 = new Transaksjon(113, "test", 4.5, "2013-03-21", "Hei :)", "test", "accountnr2");
        Transaksjon betaling2 = new Transaksjon(113, "test", 4.5, "2013-03-21", "Hei :)", "test", "accountnr2");

        betaling.add(betaling1);
        betaling.add(betaling2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentBetalinger(anyString())).thenReturn(betaling);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertEquals(betaling, resultat);
    }

    //Ida og sofia
    @Test
    public void hentBetalinger_ikkeLoggetInn() {
        when(sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertNull(resultat);
    }


    //Ida og sofia
    @Test
    public void utforBetaling_loggetInn() {
        List<Transaksjon> betaling = new ArrayList<>();
        Transaksjon betaling1 = new Transaksjon(113, "test", 4.5, "2013-03-21", "Hei", "test", "accountnr2");
        Transaksjon betaling2 = new Transaksjon(113, "test", 4.5, "2013-03-21", "Hei", "test", "accountnr2");


        betaling.add(betaling1);
        betaling.add(betaling2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentBetalinger(anyString())).thenReturn(betaling);

        when(repository.utforBetaling(anyInt())).thenReturn("OK");

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertEquals(betaling, resultat);
    }


    //Ida og sofia
    @Test
    public void utforBetaling_ikkeLoggetInn() {
        when(sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.utforBetaling(113);

        assertNull(resultat);
    }


    //DONE!!

    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        Mockito.when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    //DONE!!


    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }


    //Rosa
    @Test
    public void endre_loggetInn() {
        // arrange

        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");


        // act
        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKundeInfo(enKunde)).thenReturn("OK");

        String result = bankController.endre(enKunde);

        // assert

        assertEquals("OK", result);

    }


    //Rosa
    @Test
    public void endre_ikkeLoggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = bankController.endre(enKunde);

        // assert
        assertNull(resultat);

    }
}





