package main.models;

public class Message<T> {
    private final T message;
    private boolean successful = true;

    public Message(boolean successful) {
        this.successful = successful;
        this.message = null;
    }

    public Message(boolean successful, T message) {
        this.message = message;
        this.successful = successful;
    }
    /*
    Без аннотации компилятор выдаст предупреждение о том,
    что локальная переменная s не используется. С аннотацией
    компилятор игнорирует это предупреждение для локального модуля
     */
    @SuppressWarnings("unused")
    public T getMessage() {
        return message;
    }

    @SuppressWarnings("unused")
    public boolean isSuccessful() {
        return successful;
    }
}
