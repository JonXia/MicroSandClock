package music;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
//播放音频类
public class Music extends Thread{
    Player player;
    InputStream input;
    //构造
    public Music(InputStream input) {
        this.input = input;
    }
    @Override
    public synchronized void run() {
        // TODO Auto-generated method stub
        try {
            play();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //播放方法
    public void play() throws FileNotFoundException, JavaLayerException {

            BufferedInputStream buffer = new BufferedInputStream(input);
            player = new Player(buffer);
            player.play();
    }
}