package portfolio.oop;

/** Demonstrates an abstract base class and an overriding subclass. */
public class AbstractClassDemo {
    public static void main(String[] args) {
        LessonTask task = new CodingPracticeTask("Implement binary search");
        task.printSummary();
        task.complete();
    }

    private abstract static class LessonTask {
        private final String title;

        protected LessonTask(String title) {
            this.title = title;
        }

        public void printSummary() {
            System.out.println("Task: " + title);
        }

        public abstract void complete();
    }

    private static class CodingPracticeTask extends LessonTask {
        private CodingPracticeTask(String title) {
            super(title);
        }

        @Override
        public void complete() {
            System.out.println("Completed by writing, testing, and refactoring code.");
        }
    }
}
