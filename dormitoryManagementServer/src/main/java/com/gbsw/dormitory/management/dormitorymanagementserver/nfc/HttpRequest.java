package com.gbsw.dormitory.management.dormitorymanagementserver.nfc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    // AwesomeRequestSendPropertyProgramInstallerInistalizerRestControllerBaseMaximTwitterInstagramJavaSpringBootSsipDuckAnimeGoodClass
    // 어썸리퀘스트센드프로퍼티프로그램인스톨러이니셜라이저레스트컨트롤러베이스맥심트위터인스타그램자바스프링부트씹덕애니미굿클래스
    //제 57회 전국기능경기대회 사이버보안부문 금메달 윤서준씨가 원하신 클래스명입니다.

    private static final String SERVER_URL = "http://localhost:8080";


    public void SendMsg(String uuid) throws Exception {

        //todo 갤럭시의 경우 uuid : FAFAFF000001 ( FAFAFF가 인식용 뒤 숫자가 idx )


        HttpURLConnection conn = null;
        URL url = new URL(SERVER_URL + "/api/v1/user/nfcTag?uuid="+uuid);
        conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");


        conn.setDoOutput(true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(bw.toString());
        bw.flush();
        bw.close();


        //서버에서 보낸 응답 데이터 수신
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String returnMsg = reader.readLine();


        JSONObject jObj = new JSONObject(returnMsg);

        System.out.println("jObj = " + jObj);

        int a = (int) jObj.get("size");
        JSONArray list = (JSONArray) jObj.get("data");

        for (int i = 0; i < list.length(); i++) {
            JSONObject data = (JSONObject) list.get(i);
            System.out.println(data);
        }

    }

}