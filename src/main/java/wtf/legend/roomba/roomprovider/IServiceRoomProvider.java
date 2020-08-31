package wtf.legend.roomba.roomprovider;

import net.dv8tion.jda.api.entities.Message;

import java.util.List;
import java.util.Set;

public interface IServiceRoomProvider {

    /**
     * Anyone looking at this who wants to create a room provider and wondering
     * "wHeRe Do I gEt OpTiOnS"
     * It's your job to implement that yourself...
     *
     * ... have fun (if you want an example look at @wtf.legend.roomba.roomprovider.providers.JstrisRoomProvider)
     */
    String createRoom();
    String getProviderName();
    Set<String> getProviderAliases();

}
