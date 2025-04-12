package zad1;

class StringTask implements Runnable {
    enum TaskState {
        CREATED,
        RUNNING,
        ABORTED,
        READY
    }

    private final String givenLetter;
    private final int anInt;
    private TaskState state;
    private volatile String name;

    public StringTask(String givenLetter, int i) {
        this.givenLetter = givenLetter;
        this.anInt = i;
        state = TaskState.CREATED;
        name = "";
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        state = TaskState.RUNNING;
        for (int i = 0; i < anInt; i++) {
            name += givenLetter;
        }
        state = TaskState.READY;
    }

    public String getResult() {
        return name;
    }

    public String getTxt() {
        return (givenLetter.toUpperCase());
    }

    public TaskState getState() {
        return state;
    }

    public void abort() {
        Thread.currentThread().interrupt();
        state = TaskState.ABORTED;
    }

    public boolean isDone() {
        if (state == TaskState.ABORTED || state == TaskState.READY) {
            return true;
        } else {
            return false;
        }
    }
}