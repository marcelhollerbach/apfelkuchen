package bananas;

public abstract class Job implements Runnable {
   private JobListener l;

   public Job(JobListener l) {
      this.l = l;
   }
   protected void isDone(Object result) {
      l.idDone(result);
   }
}
