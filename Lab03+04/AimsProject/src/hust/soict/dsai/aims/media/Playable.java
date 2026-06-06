package hust.soict.dsai.aims.media;
import hust.soict.dsai.aims.exception.PlayerException; // Nhớ import

public interface Playable {
    public void play() throws PlayerException;
}