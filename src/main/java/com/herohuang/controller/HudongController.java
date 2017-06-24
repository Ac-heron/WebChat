package com.herohuang.controller;

import com.alibaba.fastjson.JSON;
import com.herohuang.model.User;
import com.herohuang.model.Uploader;
import com.herohuang.model.Room;
import com.herohuang.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Room> keshiList = new ArrayList<Room>();
        Room room1 = new Room("123", "科室1");
        Room room2 = new Room("124", "科室2");
        keshiList.add(room1);
        keshiList.add(room2);
        model.addAttribute("keshiList", keshiList);

        User yishengxx = new User("123", "张三");
        model.addAttribute("yishengxx", yishengxx);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("yiyuanid", "1234");
        model.addAttribute("jcsjYiyuanxx", "");
        return "chat";
    }


    /**
     * Get messages of the room
     *
     * @param keshiid
     * @return
     */
    @RequestMapping(value = "liaotianxx.do")
    @ResponseBody
    public List<Message> liaotianxx(String keshiid) {
        List<Message> list = new ArrayList<Message>();
        Message x1 = new Message();
        x1.setKeshiid("123");
        x1.setXiaoxixx("哈哈只");
        Message x2 = new Message();
        x1.setKeshiid("124");
        x1.setXiaoxixx("哈哈只");
        list.add(x1);
        list.add(x2);
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
