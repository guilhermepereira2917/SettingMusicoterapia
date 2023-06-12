package utils;

import entities.Paciente;
import entities.Profissional;

import java.util.List;

public class SQLUtils {

    public static String montarFiltroInPacientes(List<Paciente> pacientes) {
        if (pacientes == null || pacientes.isEmpty()) {
            return null;
        }

        StringBuilder filtroIn = new StringBuilder();
        filtroIn.append("(");
        
        for (int i = 0; i < pacientes.size() - 1; i++) {
            filtroIn.append(pacientes.get(i).getId().toString()).append(", ");
        }
        
        filtroIn.append(pacientes.get(pacientes.size() - 1).getId().toString()).append(")");
        
        return filtroIn.toString();
    }


    public static String montarFiltroInProfissionais(List<Profissional> profissionais) {
        if (profissionais == null || profissionais.isEmpty()) {
            return null;
        }

        StringBuilder filtroIn = new StringBuilder();
        filtroIn.append("(");

        for (int i = 0; i < profissionais.size() - 1; i++) {
            filtroIn.append(profissionais.get(i).getId().toString()).append(", ");
        }

        filtroIn.append(profissionais.get(profissionais.size() - 1).getId().toString()).append(")");

        return filtroIn.toString();
    }
}
