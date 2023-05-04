public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Chat chat = new Chat();

        User mike = new User("Майк");
        mike.joinChat(chat);
        User fred = new User("Фред");
        fred.joinChat(chat);
        User jamie = new User("Джейми");
        jamie.joinChat(chat);

        mike.sendMessage("Привет");
        fred.sendMessage("Привет, а ты кто?");
        jamie.sendMessage("Майк? Привет?");
        mike.sendMessage("Ошибся чатом))");

        mike.leftChat();
        mike.sendMessage("А нет, не ошибся");

    }
}