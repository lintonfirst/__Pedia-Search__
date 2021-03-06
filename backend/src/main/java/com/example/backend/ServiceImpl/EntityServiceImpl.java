package com.example.backend.ServiceImpl;

import com.example.backend.Dao.EntityDao;
import com.example.backend.Entity.Entity;
import com.example.backend.Service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    private EntityDao entityDao;

    @Override
    public Map<String,Object> findByKeyword(String keyword){
        Entity target = entityDao.findByName(keyword);

        Map<String,Object> map = new HashMap<String, Object>();

        List<Map<String,Object>> linkedList = new LinkedList<>();

        if(target!=null)
        {
            map.put("title",target.getName());
            for(Entity entity:target.getEntitySet())
            {
                Map<String,Object> itemMap = new HashMap<>();
                itemMap.put("title",entity.getName());
                itemMap.put("weight",entity.getWeight());
                linkedList.add(itemMap);
            }
        }
        else
        {
            map.put("title",keyword);
        }
        map.put("linked_words",linkedList);

        return map;
    }
}
