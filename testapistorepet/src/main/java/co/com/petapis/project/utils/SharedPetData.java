package co.com.petapis.project.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedPetData {

    // Lista original de IDs válidos
    private static final List<Long> validPetIds = new ArrayList<>();

    // Lista usada para reiniciar cuando sea necesario
    private static List<Long> backupList = new ArrayList<>();

    // Agrega un nuevo ID válido
    public static void addValidPetId(Long id) {
        validPetIds.add(id);
        backupList.add(id); // Guardamos en el backup para posible reinicio
    }

    // Obtiene y remueve el primer ID disponible
    public static Long getAndRemoveFirstValidId() {
        if (validPetIds.isEmpty()) {
            throw new RuntimeException("No hay IDs válidos disponibles.");
        }
        return validPetIds.remove(0);
    }

    // Obtiene el primer ID sin removerlo
    public static Long peekFirstValidId() {
        if (validPetIds.isEmpty()) {
            throw new RuntimeException("No hay IDs válidos disponibles.");
        }
        return validPetIds.get(0);
    }

    // Reinicia la lista con los valores originales
    public static void resetValidPetIds() {
        validPetIds.clear();
        validPetIds.addAll(backupList);
    }

    // Verifica si hay IDs disponibles
    public static boolean hasValidIds() {
        return !validPetIds.isEmpty();
    }

    // Solo por si necesitas imprimirlos para debug
    public static List<Long> getCurrentIds() {
        return Collections.unmodifiableList(validPetIds);
    }
}
