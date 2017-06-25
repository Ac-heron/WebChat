package com.herohuang.socket;


import com.alibaba.fastjson.JSONObject;
import com.herohuang.model.Message;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

/**
 * 医生互动服务器
 *
 * @author xionghuang
 * @date 2016-10-18
 */
//@ServerEndpoint(value = "/hudongsocket",configurator = SpringConfigurator.class)
@ServerEndpoint(value = "/hudongsocket")
public class HudongEndpoint {

    private Logger logger = Logger.getLogger("endpoint-logger");

    //与客户端的会话链接
    private Session session;

    //实例线程安全的集合
    private static CopyOnWriteArraySet<HudongEndpoint> hudongSet = new CopyOnWriteArraySet<HudongEndpoint>();

    //记录连接数
    private static int onlineCount = 0;

    //连接成功
    @OnOpen
    public void open(Session session) {
        this.session = session;
        hudongSet.add(this);
        addOnlineCount();
    }


    //接收消息
    @OnMessage
    public void getMessage(String message, Session session) {
        JSONObject jsonObject = JSONObject.parseObject(message);

        logger.info("Start insert message....");
        Message m = new Message();
        m.setRoomId(jsonObject.getString("roomId"));
        m.setUserId(jsonObject.getString("userId"));
        m.setUserName(jsonObject.getString("userName"));
        m.setSendDate(new Date());
        m.setContent(jsonObject.getString("content"));
        logger.info("End insert message....");

        jsonObject.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        for (Session openSession : session.getOpenSessions()) {
            jsonObject.put("isSelf", openSession.equals(session));
            openSession.getAsyncRemote().sendText(jsonObject.toString());
        }
    }

    //关闭连接
    @OnClose
    public void close() {
        hudongSet.remove(this);
        subOnlineCount();
    }

    //错误时方法
    @OnError
    public void error(Throwable t) {
        t.printStackTrace();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        HudongEndpoint.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        HudongEndpoint.onlineCount--;
    }

}