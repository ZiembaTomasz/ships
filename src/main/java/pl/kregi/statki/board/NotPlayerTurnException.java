package pl.kregi.statki.board;

public class NotPlayerTurnException extends IllegalArgumentException {
    public NotPlayerTurnException(){
        super("Not Player turn");
    }
}
