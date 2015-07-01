package nachhh.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  Banana Test
 * @author nachhh
 *
 */
public class BananaDesk {
	
	private static Map<String, List<String>> directDestTable = new HashMap<String, List<String>>();
	
	public static void main(String[] args) {
		//load example data to table
		List<String> seattleList = new ArrayList<String>();
		seattleList.add("Florida");seattleList.add("LA");
		directDestTable.put("Seattle", seattleList);
		
		List<String> LAList = new ArrayList<String>();
		LAList.add("Maine");LAList.add("Florida");
		directDestTable.put("LA", LAList);
		
		List<String> FloridaList = new ArrayList<String>();
		FloridaList.add("Seattle");
		directDestTable.put("Florida", FloridaList);
		
		List<String> MaineList = new ArrayList<String>();
		directDestTable.put("Maine", MaineList);
		
		List<String> r = new ArrayList<>();
		Set<String> bl = new HashSet<>();
		List<List<String>> routes = find_routes("Seattle", "Florida", r, bl);
		
		//print results
		routes.forEach( route -> {
			System.out.print("[");
			for(String p:route)
				System.out.print(p + " -> ");
			System.out.print("]");
			System.out.print("");
		});
	}
	
	/**
	 * 
	 * @param origin : the city of origin for the route
	 * @param dest : the destination city for the route
	 * @param r : a route that led to origin
	 * @param blackList : contains cities that don't lead to dest
	 * (this is a dynamic programming enhancements to speed up running time
	 * significantly)
	 * @return a list of routes from origin to dest
	 */
	public static List<List<String>> find_routes(String origin, String dest, List<String> r, Set<String> blackList) {
		ArrayList<List<String>> res = new ArrayList<List<String>>();
		if(r.contains(origin) || blackList.contains(origin)) //cicle or dead route
			return res;	
		List<String> r2 = new ArrayList<>();
		r2.addAll(r);
		r2.add(origin);
		if(origin.equals(dest)) {//we ended in dest -> we found a route
			res.add(r2);
			return res;
		}
		for(String city: directDestTable.get(origin)) {
			List<List<String>> routes = find_routes(city, dest, r2, blackList);
			if(routes.size() == 0)//this city does not lead to anywhere. add to bl
				blackList.add(city);
			else 
				res.addAll(routes);
		}
		return res;
	} 
}
