package utils;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

    public static Integer calculaIdade(Date data) {
        if (data == null) {
            return null;
        }

        Date dataAtual = new Date();

        Calendar calendarioDataAtual = Calendar.getInstance();
        calendarioDataAtual.setTime(dataAtual);

        Calendar calendarioDataNascimento = Calendar.getInstance();
        calendarioDataNascimento.setTime(data);

        int idade = calendarioDataAtual.get(Calendar.YEAR) - calendarioDataNascimento.get(Calendar.YEAR);
        return idade;
    }
}
