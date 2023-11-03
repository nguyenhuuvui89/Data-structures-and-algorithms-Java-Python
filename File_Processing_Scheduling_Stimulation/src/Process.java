public class Process {
    private int processId;
    private int arrivalTime;
    private int duration;
    private int priority;
    private int remainingTime;
    private int waitTime;

    public Process(int processId, int priority, int duration, int arrivalTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.priority = priority;
        this.waitTime = 0;
        this.remainingTime = duration;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void incrementWaitTime() {
        waitTime++;
    }


    @Override
    public String toString() {
        return "Process{" +
                "processId=" + processId +
                ", arrivalTime=" + arrivalTime +
                ", duration=" + duration +
                ", priority=" + priority +
                '}';
    }
}
