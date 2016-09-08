package data;
import java.util.*;
public class InlineIdTime{
	private static  InlineIdTime login=null;
	private HashMap hashMap=null;
	private InlineIdTime(){
		hashMap=new HashMap();
	}
	public static InlineIdTime getInstance(){
		if(login==null){ 
			login=new InlineIdTime();
		}
		return login;
	}
	public synchronized  void update(String id,long from,long end){//更新用户在线状态
		long []bu={from,end};
		if(hashMap.get(id)==null){
			hashMap.put(id, bu);
		}
		else{
			long[] temp=(long[]) hashMap.get(id);
			temp[0]=from;
			temp[1]=end;
			//System.out.println(from+"\t"+end);
		}
	}
	public synchronized boolean isInline(String id){//在线返回true，否则返回false
		long []t=(long[])hashMap.get(id);
		if(hashMap.get(id)==null){//从未登录
			return false;
		}
		else{
			if(System.currentTimeMillis()-t[1]>(1000*8)){//已经离线 ，时间差超过10s
				return false;
			}
			else{//在线
				return true;
			}
		}
	}
	public synchronized HashMap<String, long[]> getLog(){//返回所有记录
		HashMap<String, long[]> m=new HashMap<String, long[]>();
		Set set=hashMap.keySet();
		Iterator iter=set.iterator();
		while(iter.hasNext()){
			String str=(String)iter.next();
			long[] lon=(long[])hashMap.get(str);
			long []t={lon[0],lon[1]};
			m.put(str,t);
		}
		return m;
	}
	public long[] getIdTime(String ID){
		long []r={0,0};
		long []t=null;
		t=(long[]) hashMap.get(ID);
		if(t!=null){
			r[0]=t[0];
			r[1]=t[1];
		}
		return r;
	}
}
