
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
	private ArrayList<Integer> pathlength;
	private ArrayList<Integer> pathlengthSorted;

	public ActivityList() {
		first = new ArrayList<Activity>();
		activities = new ArrayList<Activity>();
		paths = new ArrayList<String>();
		pathlength = new ArrayList<Integer>();		
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
			activities.add(newActivity);
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
		System.out.println(activities.size());
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

	public boolean changeDuration(String name, int duration) {
		boolean x = false;

		for(int i =0; i < activities.size(); i++) {
			if(activities.get(i).name.equals(name)) {
				activities.get(i).duration = duration;
				x = true;
			}

		}
		return x;

	}

	public boolean traverse(ArrayList<Activity> list, String path, int dur, int iterations) {
		int ndur = 0;
		for (int i = 0; i < list.size(); i++) {
			String npath = path + list.get(i).name + ": " + list.get(i).duration;
			int niterations = iterations + 1;
			ndur = dur + list.get(i).duration;
			System.out.println(activities.size());
			if (niterations > activities.size()) {
				System.out.println(false);
				return false;
			}
			if (list.get(i).successors.size() > 0) {
				//for (int j = 0; j < list.get(i).successors.size(); j++) {
				npath += " --> ";
				traverse(list.get(i).successors, npath, ndur, niterations);
				//}
			}
			else {
				paths.add(npath);
				pathlength.add(ndur);
			}
		}
		return true;
	}

	public String calculatePaths() {
		String path = "";
		int dur = 0;
		int iterations = 0;
		boolean successful;
		if (first.size() > 1)
			return "1 or more nodes not connected";
		for (int i = 0; i < first.size(); i++) {
			path = first.get(i).name + ": " + first.get(i).duration;
			dur += first.get(i).duration;
			iterations++;
			/*if (first.get(i).successors.size() <= 0)
				return "1 or more nodes not connected";*/
			path += " --> ";
			successful = traverse(first.get(i).successors, path, dur, iterations);
			if (!successful)
				return "Circular Path";
		}
		return "Success";
	}

	public String getPaths(boolean critical) {
		String pathList = "";
		pathlength = new ArrayList<Integer>();
		paths = new ArrayList<String>();
		String successful = calculatePaths();
		if (successful.equals("Success")) {
			if (pathlength.size() < 1)
				return "Circular Path";
			sort(pathlength);
			ArrayList<Integer> pathlengthcopy = new ArrayList<Integer>();
			for (int i = 0; i < pathlength.size(); i++) {
				pathlengthcopy.add(pathlength.get(i));
			}
			if (critical) {
				int clength = pathlengthcopy.indexOf(pathlengthSorted.get(paths.size()-1));
				for (int i = 0; i < paths.size(); i++) {
					int j = pathlengthcopy.indexOf(pathlengthSorted.get(paths.size()-1-i));
					if (j == clength) {
						pathlengthcopy.set(j, -1);
						System.out.println(pathlengthcopy);
						System.out.println(j);
						pathList = pathList + paths.get(j) + "\n" + "Critical Path Duration: " + pathlength.get(j) + "\n\n\n\n"; 
					}
					else {
						i = paths.size();
					}
				}
			}  
			else {
				for (int i = 0; i < paths.size(); i++) {
					int j = pathlengthcopy.indexOf(pathlengthSorted.get(paths.size()-1-i));
					pathlengthcopy.set(j, -1);
					System.out.println(pathlengthcopy);
					System.out.println(j);
					pathList = pathList + paths.get(j) + "\n" + "Duration: " + pathlength.get(j) + "\n\n\n\n"; 
				}
			}
			System.out.println(pathList);
			return pathList;
		}
		else {
			deleteLinkedList();
			return successful;
		}
	}

	public void sort(ArrayList<Integer> pathlength) {
		pathlengthSorted = new ArrayList<Integer>();
		for (int i = 0; i < pathlength.size(); i++) {
			pathlengthSorted.add(pathlength.get(i));
		}
		Collections.sort(pathlengthSorted);
	}

	public boolean deleteLinkedList() {
		first = new ArrayList<Activity>();
		activities = new ArrayList<Activity>();
		paths = new ArrayList<String>();
		pathlength = new ArrayList<Integer>();
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
	public boolean Circular(ArrayList<Activity> list){

		for(int i=0;i<list.size();i++){// return true, if there is a duplicate
			for(int j=i+1;j<list.size();j++){
				if(list.get(i).name.equals(list.get(j).name)){
					return true;
				}
			}
		}
		return false;

	}

	// This function creates a report file (.txt) with user specified name and will output a status text on whether file creation was successful
	public String createReport(String title, boolean critical) throws IOException {				

		String status;											// status will relay whether or not file creation was successful
		String fileName = title + ".txt";						// creates a string which will hold the whole file name

		boolean hasInvalidChar = false;							

		for (int i = 0; i < title.length() && hasInvalidChar == false; i++) {		// loops until invalid char found or until whole string is analyzed

			char c = title.charAt(i);
			if (c == '?' || c == '<' || c == '>' || c == '|' || c == '/') {

				hasInvalidChar = true;												// found an invalid character being used for file name

			}
		}

		if (hasInvalidChar == true) {												// runs only if no invalid characters were found

			status = "Could not create " + title + ".txt, there is/are a invalid character/s in file name \n";

		} else {

			File file = new File(fileName);						// creates text file with title given by var fileName and file object
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(file);				// this object (pw) is used to write to file
			// TO SEE IF FILE WAS CREATED, REFRESH PROJECT on package explorer and the 
			// new .txt file should show up

			pw.println("Report Title: " + fileName + "\n");
			Date date = new Date();
			pw.println("Created on: " + date.toString() + "\n");
			pw.print("Activities: ");

			List<String> activityNames = new ArrayList();									// this arrayList of string will hold the activity names

			for (int i = 0; i < activities.size(); i++) {									// loops through activities created and stores name in activityNames
				activityNames.add(activities.get(i).name);
			}

			Collections.sort(activityNames, String.CASE_INSENSITIVE_ORDER);							// orders the activity names alpha-numerically

			for (int i = 0; i < activities.size(); i++) {
				if (i == activities.size() - 1) {
					pw.print(activityNames.get(i));
				} else {
					pw.print(activityNames.get(i) + ", ");
				}

			}

			pw.println("\n");

			//----------------------------------------printing out paths---------------------------------------------------------------

			pw.println("Paths:");

			String reportPaths = getPaths(critical);
			pw.print(reportPaths);


			pw.close();																		// necessary for writing to file

			status = "Created: ";															// status string tell user creation successful
			status += title + ".txt\n";

		}

		return status;

	}


}
