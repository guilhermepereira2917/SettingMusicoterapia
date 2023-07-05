package utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilsTest {

    @Test
    public void deveRetornarNullParaDataDeNascimentoNull() {
        Date dataNascimento = null;
        Integer idade = DateUtils.calculaIdade(dataNascimento);

        Assert.assertNull(idade);
    }

    @Test
    public void deveRetornarZeroParaDataDeNascimentoMaiorQueDataAtual() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(new Date());
        dataNascimento.add(Calendar.DAY_OF_WEEK, 1);

        Integer idade = DateUtils.calculaIdade(dataNascimento.getTime());
        Assert.assertEquals(Integer.valueOf(0), idade);
    }

    @Test
    public void deveRetornarDiferencaAnosParaAniversarioCompleto() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(new Date());
        dataNascimento.add(Calendar.YEAR, -10);

        Integer idade = DateUtils.calculaIdade(dataNascimento.getTime());
        Assert.assertEquals(Integer.valueOf(10), idade);
    }

    @Test
    public void deveRetornarDiferencaAnosMenosUmParaAniversarioIncompleto() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(new Date());
        dataNascimento.add(Calendar.YEAR, -10);
        dataNascimento.add(Calendar.DAY_OF_WEEK, 1);

        Integer idade = DateUtils.calculaIdade(dataNascimento.getTime());
        Assert.assertEquals(Integer.valueOf(9), idade);
    }

    @Test
    public void deveRetornarDiferencaAnosMaisUmParaAniversarioIncompletoMaisUmDia() {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(new Date());
        dataNascimento.add(Calendar.YEAR, -10);
        dataNascimento.add(Calendar.DAY_OF_WEEK, -1);

        Integer idade = DateUtils.calculaIdade(dataNascimento.getTime());
        Assert.assertEquals(Integer.valueOf(10), idade);
    }
    
}
