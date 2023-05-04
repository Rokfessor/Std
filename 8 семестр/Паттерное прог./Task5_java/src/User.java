public class User implements Observer {
    final private String name;
    private Chat chat = null;

    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println("<<< Пользователь " + name + " получил сообщение: \"" + message + "\"");
    }

    public void sendMessage(String message) {
        if (chat == null) {
            System.out.println("!!! Пользователь не состоит в чате");
            return;
        }

        System.out.println(">>> Пользователь " + name + " отправил сообщение");
        chat.notifyObservers(message, this);
    }

    public void joinChat(Chat chat) {
        this.chat = chat;
        chat.addObserver(this);
        System.out.println("=== Пользователь " + name + " зашел в чат");
    }

    public void leftChat() {
        chat.removeObserver(this);
        chat = null;
        System.out.println("=== Пользователь " + name + " вышел из чата");
    }
}
