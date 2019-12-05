package Pathfinding;

import java.util.ArrayList;

public class Path {

	private ArrayList<Step> steps = new ArrayList<>();

	public Path() {}

	public Step getStep() {

		return steps.get(0);
	}

	public void addStep(int x, int y) {

		Step newStep = new Step(x,y);
		steps.add(newStep);
	}

	public void prependStep(int x, int y) {

		Step newStep = new Step(x, y);
		steps.add(0, newStep);
	}
}
