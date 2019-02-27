package exceptions;

public class WinException extends RuntimeException {
    public WinException () {
        super("Ты победил! Поздравляю!\nХочешь попробовать снова? +/-");
    }
}
