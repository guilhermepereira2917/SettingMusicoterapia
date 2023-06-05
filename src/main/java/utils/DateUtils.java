package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

    private static SimpleDateFormat dataSimpleFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat horarioSimpleFormat = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat dataJPQLSimpleFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    public static String getDataFormatada(Date data) {
        return dataSimpleFormat.format(data);
    }

    public static String getHorarioFormatado(Date horario) {
        return horarioSimpleFormat.format(horario);
    }

    public static String getDataFormatadaJPQL(Date data) {
        return String.format("{d '%s'}", dataJPQLSimpleFormat.format(data));
    }

}
