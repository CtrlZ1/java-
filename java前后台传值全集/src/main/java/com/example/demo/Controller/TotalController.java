package com.example.demo.Controller;
import com.example.demo.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TotalController {
    //可以把实体类，实体类数组，list，map，字符串，等等转化为json形式
    public static String toJson1(Object result)
    {
        if(result!=null){
            JSONArray obj=JSONArray.fromObject(result);
            return(obj.toString());
        }
        return null;
    }
    public static String toJson2(Object result)
    {
        if(result!=null){
            JSONObject obj=JSONObject.fromObject(result);
            return obj.toString();
        }
        return null;
    }
    @ModelAttribute
    public void model(Model model){
        User user=new User("xiaoming","123456");
        //传对象
        model.addAttribute("user",user);
        //传字符串
        model.addAttribute("string","后台传递过来的字符串");
        //传map
        Map<String,User> map=new HashMap<String, User>();
        map.put("user1",user);
        model.addAttribute("map",map);
        //传递列表
        List<User> list=new ArrayList<User>();
        list.add(user);
        list.add(user);
        model.addAttribute("list",list);
        //对象转json
        model.addAttribute("json_user",toJson2(user));
        //字符串转json
        model.addAttribute("json_string",toJson1("['json is easy']"));//一定要放进[]里
        //list转json
        model.addAttribute("json_list",toJson1(list));
        //map转json
        model.addAttribute("json_map",toJson2(map));

    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    //接收前台向后台传值
    @RequestMapping("getdata")
    @ResponseBody
    public Object getdata(@RequestParam String ObjectUser){
        //System.out.println(1);
        JSONObject userObject=JSONObject.fromObject(ObjectUser);
        //System.out.println(1);
        User user=(User)userObject.toBean(userObject,User.class);
        System.out.println(user.getUsername()+user.getPassword());
        return "success";
    }
    @RequestMapping("getdata1")
    @ResponseBody
    public Object getdata1(@RequestParam String ObjectUser){
        JSONArray  userObject=JSONArray .fromObject(ObjectUser);
        for(int i=0;i<userObject.size();i++)
        {
            User user=(User)userObject.getJSONObject(i).toBean(userObject.getJSONObject(i),User.class);
            System.out.println(user.getUsername()+user.getPassword());
        }
        return "success";
    }
}
