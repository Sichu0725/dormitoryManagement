package com.gbsw.hs.kr.dormitory_management_mobile;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class CardService extends HostApduService {
    private static final String TAG = "CardService";

    private static final String SAMPLE_LOYALTY_CARD_AID = "F222222222";
    private static final String SELECT_APDU_HEADER = "00A40400";
    //todo str -> hex -> byte array 로 해야할 듯?
    private static final String USER_ID = "000001";
    private static final byte[] SELECT_OK_SW = HexStringToByteArray("galaxy" + USER_ID);
    private static final byte[] UNKNOWN_CMD_SW = HexStringToByteArray("0000");
    private static final byte[] SELECT_APDU = BuildSelectApdu(SAMPLE_LOYALTY_CARD_AID);

    private Messenger _handler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // 서비스를 앱 종료시까지 계속 실행상태로
        return START_STICKY;
    }

    // 외부로부터의 APDU명령을 받았을때 호출
    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {

        if (Arrays.equals(SELECT_APDU, commandApdu)) {
            Log.i(TAG, "Application selected");

            // NFC 찍혔을때

            Toast.makeText(this, "NFC tag!", Toast.LENGTH_SHORT).show();

            return SELECT_OK_SW;
        } else {
            return UNKNOWN_CMD_SW;
        }
    }

    // 연결을 잃었을때 호출됨
    @Override
    public void onDeactivated(int reason) {
        Log.i(TAG, "Deactivated: " + reason);
    }

    public static byte[] BuildSelectApdu(String aid) {
        // Format: [CLASS | INSTRUCTION | PARAMETER 1 | PARAMETER 2 | LENGTH | DATA]
        return HexStringToByteArray(SELECT_APDU_HEADER + String.format("%02X",
                aid.length() / 2) + aid);
    }

    public static String byteArrayToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static byte[] HexStringToByteArray(String hexString) throws IllegalArgumentException {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    | Character.digit(hexString.charAt(i + 1), 16));
        }

        System.out.println(1212 + " " + hexString + ", " +Arrays.toString(data) + ", " + byteArrayToHex(data));

        return data;
    }

    public static String StrToHex(String s) throws IllegalArgumentException {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            result.append(String.format("%02X ", (int) s.charAt(i)));
        }

        return result.toString();
    }
}