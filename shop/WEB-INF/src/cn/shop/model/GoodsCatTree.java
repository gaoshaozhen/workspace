package cn.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.shop.base.MutilTree;

public class GoodsCatTree
{
    MutilTree catTree;
    public GoodsCatTree(List<Map<String, Object>> list)
    {
        catTree = new MutilTree();
        Map<String,Object> root = new HashMap<String, Object>();        
        root.put("cat_id", 0);
        catTree.setNode(root);
        
          
    }

    public void setCattree(List<Map<String, Object>> list, MutilTree node)
    {       
        Map<String, Object> no = (Map<String, Object>)node.getNode();
        Integer catId = (Integer)no.get("cat_id");
        List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
        for(Map<String, Object> temp : list)
        {           
            if(catId.compareTo((Integer)temp.get("parent_id")) == 0)
            {
                MutilTree n = new MutilTree();
                node.setNode(n);
                children.add(temp);
                list.remove(temp);
            }            
        }             
        node.setChildren(children);        
    }
    
    public List<?> getChildren()
    {
        return null;
    }

    public Map<String, Object> getNode(int id)
    {
        return null;
    }
}
