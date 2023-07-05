package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

    private static SimpleDateFormat diaDaSemanaSimpleFormat = new SimpleDateFormat("EEEE");

    private static SimpleDateFormat dataSimpleFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat horarioSimpleFormat = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat dataJPQLSimpleFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat horarioJPQLSimpleFormat = new SimpleDateFormat("HH:mm:ss");

    public static Date getDataAtual() {
        return new Date();
    }

    public static boolean isDataMenorQueDataAtual(Date data) {
        Date dataAtual = getDataAtual();

        return dataAtual.after(data);
    }

    public static boolean isDataIgualDataAtual(Date data) {
        Date dataAtual = getDataAtual();

        return dataAtual.compareTo(data) == 0;
    }

    public static Integer calculaIdade(Date dataNascimento) {
        if (dataNascimento == null) {
            return null;
        }

        Date dataAtual = getDataAtual();

        Calendar calendarioDataAtual = Calendar.getInstance();
        calendarioDataAtual.setTime(dataAtual);

        Calendar calendarioDataNascimento = Calendar.getInstance();
        calendarioDataNascimento.setTime(dataNascimento);

        if (calendarioDataAtual.compareTo(calendarioDataNascimento) <= 0) {
            return 0;
        }

        int idade = calendarioDataAtual.get(Calendar.YEAR) - calendarioDataNascimento.get(Calendar.YEAR);

        if (calendarioDataAtual.get(Calendar.MONTH) == calendarioDataNascimento.get(Calendar.MONTH)) {
            if (calendarioDataAtual.get(Calendar.DAY_OF_MONTH) < calendarioDataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade = idade - 1;
            }
        }

        return idade;
    }

    public static String getDescricaoDiaDaSemana(Date data) {
        if (data == null) {
            return null;
        }

        return diaDaSemanaSimpleFormat.format(data);
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

    public static String getHoraFormatadaJPQL(Date horario) {
        return String.format("{t '%s'}", horarioJPQLSimpleFormat.format(horario));
    }

}
