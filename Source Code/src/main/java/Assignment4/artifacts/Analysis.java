package Assignment4.artifacts;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Analysis {
	private HashMap<Integer, Result> results;

	public Analysis() {
		this.results = new HashMap<Integer, Result>();
	}

	public void add(int episode, List<Double> rewardSequence, int steps, long milliseconds) {
		Result result = new Result(0, steps, milliseconds);
		rewardSequence.forEach(new Consumer<Double>() {

			@Override
			public void accept(Double t) {
				result.reward += t;
			}
		});

		this.results.put(episode, result);
	}
	
	public void print() {
		double totalReward = 0.0;
		int totalSteps = 0;
		long totalMilliseconds = 0;
		int minSteps = Integer.MAX_VALUE;
		double maxReward = -100;
		
		for (Integer episodeIndex : this.results.keySet()) {
			Result result = this.results.get(episodeIndex);
			
			totalReward += result.reward;
			totalSteps += result.steps;
			totalMilliseconds += result.milliseconds;
			
			if (result.steps < minSteps) {
				minSteps = result.steps;
			}

			if (result.reward > maxReward) {
				maxReward = result.reward;
			}
			
			//System.out.println(episodeIndex + ", " + result.steps + ", " + result.reward + ", " + result.milliseconds);
		}

		System.out.print("episodes_Q_S=\\\n[");
		for (Integer episodeIndex : this.results.keySet()) {
			System.out.print(episodeIndex + ",");
		}
		System.out.println("]");
		System.out.print("steps_Q_S=\\\n[");
		for (Integer episodeIndex : this.results.keySet()) {
			Result result = this.results.get(episodeIndex);
			System.out.print(result.steps + ",");
		}
		System.out.println("]");
		System.out.print("reward_Q_S=\\\n[");
		for (Integer episodeIndex : this.results.keySet()) {
			Result result = this.results.get(episodeIndex);
			System.out.print(result.reward + ",");
		}
		System.out.println("]");
		System.out.print("time_Q_S=\\\n[");
		for (Integer episodeIndex : this.results.keySet()) {
			Result result = this.results.get(episodeIndex);
			System.out.print(result.milliseconds + ",");
		}
		System.out.println("]");
		
		System.out.println("\nAverage Reward: " + totalReward / this.results.size());
		System.out.println("Max Reward: " + maxReward);
		System.out.println("Average Number of Steps: " + totalSteps / this.results.size());
		System.out.println("Minimum Number of Steps: " + minSteps);
		System.out.println("Average Time (in milliseconds): " + totalMilliseconds / this.results.size());
	}

	public HashMap<Integer, Result> getResults() {
		return this.results;
	}

	public class Result {
		public double reward;
		public int steps;
		public long milliseconds;

		public Result(double reward, int steps, long milliseconds) {
			this.reward = reward;
			this.steps = steps;
			this.milliseconds = milliseconds;
		}
	}

}
