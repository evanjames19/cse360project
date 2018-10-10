import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ActivityList {

	private class Activity{
		public String name;
		public int duration;
		public ArrayList<Activity> predecessors;
		public ArrayList<Activity> successors;
	}

	private ArrayList<Activity> first;
	private ArrayList<Activity> activities;
	private ArrayList<String> paths;

	public ActivityList() {
		first = new ArrayList<Activity>();
		activities = new ArrayList<Activity>();
		paths = new ArrayList<String>();
	}

	public ArrayList<Activity> getFirst() { // returns ArrayList of first nodes
		return first;
	}

	// Adds an element to the front of the linked list.
	public void addFirst(String name, int duration)   
	{
		//create a new node
		Activity newActivity = getActivity(name);
		if (newActivity==null) { //if activity does not already exist
			newActivity = new Activity();
			activities.add(newActivity);
			newActivity.predecessors = new ArrayList<Activity>();
			newActivity.successors = new ArrayList<Activity>();
		}
		newActivity.name = name;
		newActivity.duration = duration;
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).predecessors.contains(newActivity)
					&& !newActivity.successors.contains(activities.get(i)))
				newActivity.successors.add(activities.get(i));
		}
		//add new node to first ArrayList
		first.add(newActivity);
	}

	public void add(String name, int duration, String pred) {
		//create a new node
		Activity newActivity = getActivity(name);
		if (newActivity==null) { //if activity does not already exist
			newActivity = new Activity();
			newActivity.predecessors = new ArrayList<Activity>();
			newActivity.successors = new ArrayList<Activity>();
		}
		newActivity.name = name;
		newActivity.duration = duration;
		String p[] = pred.split(", "); //get list of predecessors
		for (int i = 0; i < p.length; i++) { //for every predecessor
			Activity prevAct = getActivity(p[i]);
			if (prevAct != null) {
				prevAct.successors.add(newActivity);
				newActivity.predecessors.add(prevAct);
			}
			else if (!p[i].equals("")){
				Activity prev = new Activity();
				prev.name = p[i];
				prev.predecessors = new ArrayList<Activity>();
				prev.successors = new ArrayList<Activity>();
				prev.successors.add(newActivity);
				newActivity.predecessors.add(prev);
				activities.add(prev);
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

	public void traverse(ArrayList<Activity> list, String path) {
		for (int i = 0; i < list.size(); i++) {
			String npath = path + "\n" + list.get(i).name + ": " + list.get(i).duration + "; ";
			if (list.get(i).successors.size()>0)
				traverse(list.get(i).successors, npath);
			else {
				paths.add(npath);
			}
		}
	}

	public void calculatePaths() {
		String path = "";
		for (int i = 0; i < first.size(); i++) {
			path = first.get(i).name + ": " + first.get(i).duration + "; ";
			traverse(first.get(i).successors, path);		
		}
	}

	public String getPaths() {
		String pathList = "";
		calculatePaths();
		for (int i = 0; i < paths.size(); i++) {
			pathList = pathList + paths.get(i) + "\n\n\n\n"; 
		}
		System.out.println(pathList);
		return pathList;
	}

	public boolean deleteLinkedList() {
		first = new ArrayList<Activity>();
		activities = new ArrayList<Activity>();
		paths = new ArrayList<String>();
		return (first.size() == 0 && activities.size() == 0);
	}

	public void printAll() {
		for (int i = 0; i < activities.size(); i++) {
			System.out.println(activities.get(i).name + " " + activities.get(i).duration);
			if (activities.get(i).predecessors!=null) {
				System.out.println("Pred:");
				for (int j = 0; j < activities.get(i).predecessors.size(); j++) {
					System.out.println(activities.get(i).predecessors.get(j).name + " " + activities.get(i).predecessors.get(j).duration);
				}
			}
			if (activities.get(i).successors!=null) {
				System.out.println("Succ:");
				for (int j = 0; j < activities.get(i).successors.size(); j++) {
					System.out.println(activities.get(i).successors.get(j).name + " " + activities.get(i).successors.get(j).duration);
				}
			}
			System.out.println("\n");
		}

		System.out.println("\n\n\n\n\n\n");
	}


}
