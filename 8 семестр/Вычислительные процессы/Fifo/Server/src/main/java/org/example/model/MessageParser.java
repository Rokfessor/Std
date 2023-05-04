package org.example.model;

public class MessageParser {
    public static Message parse(String message) {
        String[] s = message.split("]");
        String info = s[0];

        if (!info.startsWith("["))
            throw new IllegalArgumentException("Wrong message text: " + message);

        Message result = new Message();
        String[] t = info.substring(1).split("\\.");
        result.type = MessageType.valueOf(t[0]);
        result.clientName = t[1];


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length; ++i)
            sb.append(s[i]);

        result.text = sb.toString();

        return result;
    }
}
