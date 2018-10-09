import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ActivityList {
		
	private class Activity{
		public String name;
		public int duration;
		public ArrayList<Activity> predecessors;
	}

	private ArrayList<Activity> first;
	private ArrayList<Activity> activities;

	public ActivityList() {
		first = null;
	}

	public ArrayList<Activity> getFirst() { // returns ArrayList of first nodes
		return first;
	}

	// Adds an element to the front of the linked list.
	public void addFirst(String name, int duration)   
	{
		//create a new node
		Activity newActivity = new Activity();
		newActivity.name = name;
		newActivity.duration = duration;
		newActivity.predecessors = null;
		//add new node to first ArrayList
		first.add(newActivity);
		activities.add((newActivity);
	}

	public void add(String name, int duration, String pred) {
		//create a new node
		Activity newActivity = getActivity(name);
		if (newActivity==null) {
			newActivity = new Activity();
		}
		newActivity.name = name;
		newActivity.duration = duration;
		String p[] = pred.split(", ");
		for (int i = 0; i < p.length; i+=2) {
			Activity prevAct = getActivity(p[i]);
			if (prevAct != null)
				newActivity.predecessors.add(prevAct);
			else {
				prevAct = new Activity();
				prevAct.name = p[i];
			}
		}
		activities.add(newActivity);
	}

	public Activity getActivity(String name) {
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).name.equals(name))
				return activities.get(i);
		}
		return null;
		/*Activity found = traverse(list, name);
		if (found != null)
			return found;
		else {
			for (int i = 0; i < list.size(); i++) {
				getActivity(list.get(i).successors, name);
			}
		}
		return null;*/
	}

	public Activity traverse(ArrayList<Activity> list, String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).name.equals(name))
				return list.get(i);
		}
		return null;
	}

	//public String getPath() {

	//}


}
