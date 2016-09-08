package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Message {
	/*
	 * id1,[id:message,id:message]
	 * */
	private HashMap hashMap=new HashMap();
	private static Message message=new Message();;
	private Message(){}
	public static Message getInstance(){		
		return message;
	}
	//获得
	public  synchronized void set (String senderId,String receiverId,String message){
		Map<String,String> map=new HashMap<String,String>();
		String[]str={senderId,message};
		map.put(senderId, message);
		if(hashMap.get(receiverId)==null){//尚没有receicverId的消息记录
			hashMap.put(receiverId, new HashMap());//准备为receiverId添加消息记录
		}		
		Map receiver=(Map)hashMap.get(receiverId);
		if(receiver.get(senderId)==null){//receiverId的消息记录中尚且没有senderId发送的消息
			receiver.put(senderId, new ArrayList());//准备存放sender发送的消息
		}
		@SuppressWarnings("unchecked")
		List<String> list=((List<String>)receiver.get(senderId));
		//System.out.println("list.size()="+list.size());
		list.add(message);
			
	}
	public  synchronized List get (String receiverId,String senderId){//返回的senderId向receiverId发送的所有消息
		Map receiverMap=(Map)hashMap.get(receiverId);
		Map senderMap=null;
		List result=new ArrayList(100);
		List temp=new ArrayList(100);
		if((receiverMap=(Map)hashMap.get(receiverId))!=null){
			if((temp=(List)receiverMap.get(senderId))!=null){
				/*Iterator iter=temp.iterator();
				while(iter.hasNext()){
					result.add(iter.next());
				}*/
				result=temp;
				receiverMap.remove(senderId);
				if(receiverMap.size()==0){
					hashMap.remove(receiverId);
				}
			}
		}
		return result;
	}
	public synchronized Map checkNewMessageNum(String receiverId){
		Map receiverMap=(Map)hashMap.get(receiverId);
		Map result=new HashMap();
		if((receiverMap=(Map)hashMap.get(receiverId))!=null){
			 Set x=receiverMap.keySet();
			 Iterator iter=x.iterator();
			 while(iter.hasNext()){
				 String key=(String) iter.next();
				 int n=0;
				 List list=(List)receiverMap.get(key);
				 if(list!=null){
					 n=list.size();
				 }
				 result.put(key, n);
			 }
		}
		return result;
	}
}
