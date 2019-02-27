package exceptions;

public class IncorrectCordinatesException extends RuntimeException {
    public IncorrectCordinatesException() {
        super("Неверно введенные координаты!");
    }
}
