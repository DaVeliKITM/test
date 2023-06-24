import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SkaiciuotuvasAtsijungimasJunitTests {

    @Before
    public void paleistiNarsykle() {
        SkaiciuotuvasMetodai.paleistiNarsykle(SkaiciuotuvasMetodai.URL_PRISIJUNGTI);
    }

    @Test
    public void atijungtiPrisijungusiVartotojaPozityvusTestas() {
        SkaiciuotuvasMetodai.prijungtiEsamaVartotoja(SkaiciuotuvasMetodai.PRISIJUNGIMO_VARDAS, SkaiciuotuvasMetodai.SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODIS, SkaiciuotuvasMetodai.MYGTUKAS_PRISIJUNGTI);
        String prisijungusioVartotojoUrl = SkaiciuotuvasMetodai.browser.getCurrentUrl();
        SkaiciuotuvasMetodai.atjungtiVartotoja(SkaiciuotuvasMetodai.PRISIJUNGIMO_REZULTATAS);
        String atsijungusioVartotojoUrl = SkaiciuotuvasMetodai.browser.getCurrentUrl();
        Assert.assertNotEquals("Vartotojui nepavyko atsijungti", atsijungusioVartotojoUrl, prisijungusioVartotojoUrl);
    }

    @After
    public void uzdarytiNarsykle() {
        SkaiciuotuvasMetodai.uzdarytiNarsykle();
    }
}
