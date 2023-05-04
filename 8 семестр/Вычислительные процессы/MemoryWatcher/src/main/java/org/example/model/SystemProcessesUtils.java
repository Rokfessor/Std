package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SystemProcessesUtils {
    //Метод получения списка всех процессов в системе
    public static Map<Integer, String> getAvailableProcesses() throws IOException {
        //Вызываем команду для получения информации о процессах в системе
        ProcessBuilder pb = new ProcessBuilder("ps", "-A");
        Map<Integer, String> result = new HashMap<>();
        //Обработка результата
        try (BufferedReader br = new BufferedReader(new InputStreamReader(pb.start().getInputStream()))) { //Построчно парсим результат команды
            String line;
            br.readLine(); //Пропускаем строку с заголовком
            while ((line = br.readLine()) != null) {
                String[] proc = line.trim().replaceAll("( )+", " ").split(" ");
                if (proc[3] == null)
                    continue;
                result.put(Integer.valueOf(proc[0]), proc[3]);
            }
        }

        return result;
    }

    //Метод получения используемой процессом памяти
    public static List<MemoryBlock> getProcessInfo(int pid, String startAddress, String endAddress) throws IOException {
        //Собираем строчку для фильтрации по адресу
        String addressRange =
                (startAddress != null ? startAddress : "") +
                ',' +
                (endAddress != null ? endAddress : "");

        //Вызываем команду для получения информации о использованной памяти
        ProcessBuilder pb = new ProcessBuilder("pmap", "-A" , addressRange, "-q" ,"-x", String.valueOf(pid));
        List<MemoryBlock> result = new ArrayList<>();

        //Обработка результата
        try (BufferedReader br = new BufferedReader(new InputStreamReader(pb.start().getInputStream()))) {
            String line;
            br.readLine(); //Пропускаем строку с заголовками
            while ((line = br.readLine()) != null) { //Построчно парсим результат команды
                String[] proc = line.trim().replaceAll("( )+", " ").split(" ");
                String mapping = proc.length > 6 ? "[" + proc[6] + "]" : proc[5];
                result.add(new MemoryBlock(
                        proc[0],
                        Long.parseLong(proc[1]),
                        Long.parseLong(proc[2]),
                        Long.parseLong(proc[3]),
                        proc[4],
                        mapping
                ));
            }
        }

        return result;
    }
}
