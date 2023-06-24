import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;

public class SkaiciuotuvasPrisijungimasJunitTests {

    @Before
    public void paleistiNarsykle() {
        SkaiciuotuvasMetodai.paleistiNarsykle(SkaiciuotuvasMetodai.URL_PRISIJUNGTI);
    }

    @Test
    public void prijungtiEsamaVartotojaPozityvusTestas() {
        SkaiciuotuvasMetodai.prijungtiEsamaVartotoja(SkaiciuotuvasMetodai.PRISIJUNGIMO_VARDAS, SkaiciuotuvasMetodai.SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODIS, SkaiciuotuvasMetodai.MYGTUKAS_PRISIJUNGTI);
        String actualPrisijungusioVartotojoRezultatas = SkaiciuotuvasMetodai.gautiPrisijungusioVartotojoVarda();
        String expectedPrisijungusioVartotojoRezultatas = "Logout, Diva";
        Assert.assertEquals("Vartotojo prisijungimas nepavyko", actualPrisijungusioVartotojoRezultatas, expectedPrisijungusioVartotojoRezultatas);
    }

    @Test
    public void prijungtiEsamaVartotojaNegatyvusTestas() {
        SkaiciuotuvasMetodai.prijungtiEsamaVartotoja(SkaiciuotuvasMetodai.PRISIJUNGIMO_VARDAS, SkaiciuotuvasMetodai.NETEISINGAS_SLAPTAZODIS, SkaiciuotuvasMetodai.LAUKELIS_SLAPTAZODIS, SkaiciuotuvasMetodai.MYGTUKAS_PRISIJUNGTI);
        String actualNepavykurioPrisijungimoRezultatas = SkaiciuotuvasMetodai.gautiNesekmingoPrisijungimoZinute();
        String expectedNepavykurioPrisijungimoRezultatas = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        Assert.assertEquals("Vartotojas prisijunge sekmingau su neteisingu slaptazodziu", actualNepavykurioPrisijungimoRezultatas, expectedNepavykurioPrisijungimoRezultatas);
    }

    @After
    public void uzdarytiNarsykle() {
        SkaiciuotuvasMetodai.uzdarytiNarsykle();
    }
}
