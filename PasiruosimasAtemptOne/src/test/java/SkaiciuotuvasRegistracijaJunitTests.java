import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SkaiciuotuvasRegistracijaJunitTests {

    @Before
    public void paleistiNarsykle() {
        SkaiciuotuvasMetodai.paleistiNarsykle(SkaiciuotuvasMetodai.URL_REGISTRUOTIS);
    }

    @Test
    public void priregistruotiNaujaVartotojaPozityvusTestas() {
        SkaiciuotuvasMetodai.priregistruotiVartotoja(SkaiciuotuvasMetodai.REGISTRAVIMO_VARDAS, SkaiciuotuvasMetodai.SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODZIO_PATVIRTINIMAS, SkaiciuotuvasMetodai.MYGTUKAS_SUKURTI);
        String actualPrisijungusioVartotojoRezultatas = SkaiciuotuvasMetodai.gautiPrisijungusioVartotojoVarda();
        String expectedPrisijungusioVartotojoRezultatas = "Logout, " + SkaiciuotuvasMetodai.REGISTRAVIMO_VARDAS;
        Assert.assertEquals("Vartotojo priregistravimas nepavyko", actualPrisijungusioVartotojoRezultatas, expectedPrisijungusioVartotojoRezultatas);
    }

    @Test
    public void priregistruotiNaujaVartotojaNegatyvusTestas() {
        SkaiciuotuvasMetodai.priregistruotiVartotoja(SkaiciuotuvasMetodai.PRISIJUNGIMO_VARDAS, SkaiciuotuvasMetodai.SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODZIO_PATVIRTINIMAS, SkaiciuotuvasMetodai.MYGTUKAS_SUKURTI);
        String actualNepavykusiosRegistracijosRezultatas = SkaiciuotuvasMetodai.gautiNesekmingosRegistracijosZinute();
        String expectedNepavykusiosRegistracijosRezultatas = "Toks vartotojo vardas jau egzistuoja";
        Assert.assertEquals("Pavyko priregistruoti jau egzistuojanti vartotoja", actualNepavykusiosRegistracijosRezultatas, expectedNepavykusiosRegistracijosRezultatas);
    }

    @After
    public void uzdarytiNarsykle() {
        SkaiciuotuvasMetodai.uzdarytiNarsykle();
    }
}
