package exceptions;

public class GameOverException extends RuntimeException {
    public GameOverException() {
        super("Game Over! Хочешь сыграть еще? +/-");
    }
}
