package View;
 
import java.io.File;
 
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

 
public class Sound extends Thread {
 
                   private String filename;
                   Player player;
 
                   public Sound(String mp3Filename) {
                      this.filename = mp3Filename;
                   }
 
                   public void run() {
                           
                      try {
                          File f = new File ( filename );
                          MediaLocator locator = new MediaLocator ( f.toURI().toURL());
                          player = Manager.createPlayer ( locator );
                          player.addControllerListener(new ControllerListener() {
                            public void controllerUpdate(ControllerEvent event) {
                               if (event instanceof EndOfMediaEvent) {
                                  player.setMediaTime(Player.RESET);
                                  player.realize();
                                  player.start();
                               }
                            }
                         });
                         player.realize();
                         player.start();
                      } catch (Exception e) {
                         e.printStackTrace();
                      }
                   }
 
        }