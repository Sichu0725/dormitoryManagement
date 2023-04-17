package com.gbsw.dormitory.management.dormitorymanagementserver.nfc;

import javax.smartcardio.*;

import java.nio.ByteBuffer;
import java.util.*;

public class NfcMain implements Runnable {
    private static final HttpRequest request = new HttpRequest();
    @Override
    public void run() {
        CardTerminal terminal;
        CardChannel channel;

        while (true) {
            // 터미널 초기화
            try {
                terminal = InitializeTerminal();

                if(IsCardPresent(terminal)) {                                   // 리더기 위에 카드(핸드폰)가 있을 경우
                    channel = GetCardAndOpenChannel(terminal);

                    String response = selectCardAID(channel);

                    // 값 받아오는 부분
//                    System.out.println(response);
                    try {
                        request.SendMsg(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                Thread.sleep(2000);

            } catch (CardException | InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    public CardTerminal InitializeTerminal() throws CardException {
        // Get terminal
        System.out.println("Searching for terminals...");
        CardTerminal terminal = null;
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();

        //Print list of terminals
        for(CardTerminal ter:terminals) {
            System.out.println("Found: "  + ter.getName());
            terminal = terminals.get(0);// We assume just one is connected
        }

        return terminal;
    }

    public boolean IsCardPresent(CardTerminal terminal) throws CardException {
        System.out.println("Waiting for card...");

        boolean isCard = false;

        while (!isCard) {
            isCard = terminal.waitForCardPresent(0);
            if(isCard)
                System.out.println("Card was found! :-)");
        }

        return true;
    }

    public CardChannel GetCardAndOpenChannel(CardTerminal terminal) throws CardException {
        Card card = terminal.connect("*");
        CardChannel channel = card.getBasicChannel();

        byte[] baReadUID = new byte[5];
        baReadUID = new byte[]{(byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00};

        // tag의 uuid를 얻은 후 출력 Log용
        System.out.println("UID : " + SendCommand(baReadUID, channel));

        return channel;
    }

    public String selectCardAID(CardChannel channel) {

        byte[] baSelectCardAID = new byte[11];
        baSelectCardAID = new byte[]{(byte) 0x00, (byte) 0xA4, (byte) 0x04, (byte) 0x00, (byte)0x05,(byte) 0xF2, (byte) 0x22, (byte) 0x22, (byte) 0x22, (byte) 0x22};

        return SendCommand(baSelectCardAID, channel);
    }

    public static String SendCommand(byte[] cmd, CardChannel channel) {
        StringBuilder response = new StringBuilder();
        byte[] baResp = new byte[258];
        ByteBuffer bufCmd = ByteBuffer.wrap(cmd);
        ByteBuffer bufResp = ByteBuffer.wrap(baResp);

        int output = 0;

        try {
            output = channel.transmit(bufCmd, bufResp);
        } catch(CardException ex){
//            ex.printStackTrace();
        }

        System.out.print("response : ");
        for (int i = 0; i < output; i++) {
            response.append(String.format("%02X", baResp[i]));
        }

        return response.toString();
    }
}