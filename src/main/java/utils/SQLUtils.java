package utils;

import entities.Paciente;
import java.util.List;

public class SQLUtils {

    public static String montarFiltroInPacientes(List<Paciente> lista) {
        if (lista == null || lista.isEmpty()) {
            return null;
        }

        String filtroIn = "(";
        
        for (int i = 0; i < lista.size() - 1; i++) {
            filtroIn += lista.get(i).getId().toString() + ", ";                    
        }
        
        filtroIn += lista.get(lista.size() - 1).getId().toString() + ")";
        
        return filtroIn;
    }

}
