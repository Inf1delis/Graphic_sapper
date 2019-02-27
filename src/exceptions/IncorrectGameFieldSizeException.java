package exceptions;

public class IncorrectGameFieldSizeException extends RuntimeException  {
    public IncorrectGameFieldSizeException() {
        super("Некорректные размеры поля!");
    }
}
