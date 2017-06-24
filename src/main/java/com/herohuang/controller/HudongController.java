package com.herohuang.controller;

import com.alibaba.fastjson.JSON;
import com.herohuang.model.Message;
import com.herohuang.model.Room;
import com.herohuang.model.Uploader;
import com.herohuang.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by Acheron 2016-10-18
 */
@Controller
public class HudongController {

    /**
     * Index of the chat page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String chat(Model model) {
        // Mock Rooms
        List<Room> rooms = new ArrayList<Room>();
        Room room1 = new Room("1", "ROOM1");
        Room room2 = new Room("2", "ROOM2");
        rooms.add(room1);
        rooms.add(room2);
        model.addAttribute("rooms", rooms);

        // Mock Users
        User user = new User("1", "张三");
        model.addAttribute("user", user);

        return "index";
    }


    /**
     * Get messages of the room
     *
     * @param roomId
     * @return
     */
    @RequestMapping(value = "history")
    @ResponseBody
    public List<Message> getHistoryMessage(String roomId) {
        List<Message> list = new ArrayList<Message>();
        Message m1 = new Message(roomId,"1","张三","Hello world",new Date());
        Message m2 = new Message(roomId,"2","李四","Nice to meet you",new Date());
        list.add(m1);
        list.add(m2);
        return list;
    }


    /**
     * Deprecated
     * 聊天信息图片上传
     *
     * @param request  请求
     * @param response 响应
     * @return 返回图片信息
     * @throws Exception 异常
     */
    @Deprecated
    @RequestMapping(value = "imageUp.do")
    @ResponseBody
    public String imageUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Uploader up = new Uploader(request);
        up.setSavePath("upload");
        String[] fileType = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        up.upload();
        String callback = request.getParameter("callback");
        String result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName() + "\", \"size\": " + up.getSize() + ", \"state\": \"" + up.getState() + "\", \"type\": \"" + up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";

        result = result.replaceAll("\\\\", "\\\\");

        return JSON.parse(result).toString();


    }


}
