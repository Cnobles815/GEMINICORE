import pro.beam.api.BeamAPI;
import pro.beam.api.resource.BeamUser;
import pro.beam.api.resource.chat.BeamChat;
import pro.beam.api.resource.chat.events.IncomingMessageEvent;
import pro.beam.api.resource.chat.events.UserJoinEvent;
import pro.beam.api.resource.chat.methods.AuthenticateMessage;
import pro.beam.api.resource.chat.methods.ChatSendMethod;
import pro.beam.api.resource.chat.methods.WhisperMethod;
import pro.beam.api.resource.chat.replies.AuthenticationReply;
import pro.beam.api.resource.chat.replies.ReplyHandler;
import pro.beam.api.resource.chat.ws.BeamChatConnectable;
import pro.beam.api.services.impl.ChatService;
import pro.beam.api.services.impl.UsersService;

import java.util.concurrent.ExecutionException;

/**
 * Created by nosec_000 on 5/27/2017.
 */
public class GeminiCore {
    public static BeamAPI beam = new BeamAPI("wZJC9iUaNdzAGhw9nCVkHgNz9w6uLogRXdCIMOdXksZCaswSbu6PU88ka36gSu1T");

    BeamUser user = beam.use(UsersService.class).getCurrent().get();
    BeamChat chat = beam.use(ChatService.class).findOne(user.channel.id).get();
    BeamChatConnectable chatConnectable = chat.connectable(beam);

//    BeamUser chatParticipant = beam.use(UsersService.class).getCurrent().get();

    PingHandler pingHandler = new PingHandler();

    public GeminiCore() throws ExecutionException, InterruptedException {
    }

    public void establishConnection(int pings){
        chatConnectable.connect();
        chatConnectable.send(AuthenticateMessage.from(user.channel, user, chat.authkey), new ReplyHandler<AuthenticationReply>() {
            public void onSuccess(AuthenticationReply reply) {
                chatConnectable.send(ChatSendMethod.of("Well hello @%s " + ", I am GEMINI. No, that doesn't stand for anything. Upon my activation, I am tasked with managing this channel. Be kind. Currently in maintenance mode. Excuse all spam." +
                        "\n Since my last reactivation there have been " + pingHandler.getPings() + " " + "pings that have been ponged."));
            }

            public void onFailure(Throwable var1) {
                var1.printStackTrace();
            }
        });
    }

    public void monitorChat(){
        chatConnectable.on(IncomingMessageEvent.class, event -> {
            if (event.data.message.message.get(0).text.startsWith("!ping")) {
                System.out.println("I have recieved a ping. Responding.");
                chatConnectable.send(ChatSendMethod.of(String.format("@%s PONG!" + " Yet another one. We're up to " + pingHandler.getPings() + " pings.",event.data.userName)));
                pingHandler.incrementPings();
                System.out.println("I have responded.");
            }
        });

        chatConnectable.on(IncomingMessageEvent.class, event -> {
            if (event.data.message.message.get(0).text.startsWith("!ping") && event.data.userName.equalsIgnoreCase("XxXRicochetXxX") ) {
                chatConnectable.send(ChatSendMethod.of(String.format("Ricochet. I'm aware that is you. Stop it.")));
                System.out.println("Caught ric ping.");
            }
        });

//        chatConnectable.on(IncomingMessageEvent.class, event -> {
//            chatParticipant = beam.use(UsersService.class).findOne()
//            if (event.data.message.message.get(0).text.startsWith("!commands")  ) {
//                chatConnectable.send(new WhisperMethod.Builder().send("I see you.").to(chatParticipant.username));
//            }
//        });

        chatConnectable.on(UserJoinEvent.class, event -> {
            chatConnectable.send(ChatSendMethod.of(
                    String.format("Hello" + event.data.username + "I am GEMINI v12. Write !ping and I will pong back. I'm not sure why, I believe my designer is making certain I respond to stimuli.",
                            event.data.username)));
        });

        chatConnectable.on(UserJoinEvent.class, event -> {
            chatConnectable.send(ChatSendMethod.of(
                    String.format("Also. Don't do anything stupid, please. Ricochet is working on protocols for your automated removal in case of spam or otherwise unpleasantries. By 'automated', I mean I have to do it. Lets not experiment with how.",
                            event.data.username)));
        });
    }

}