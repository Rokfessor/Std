package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
    public static boolean alreadyExecuted() {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", "ps axu | grep Fifo/Server/build/resources/main:/home/maxim/.gradle");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(pb.start().getInputStream()))) {
            String r = br.readLine();
            return r != null && !r.contains(ProcessHandle.current().pid() + "");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
