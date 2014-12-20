package bananas;

import bananas.parser.Side;

public abstract class Job implements Runnable {
   private JobListener l;

   public Job(JobListener l) {
      this.l = l;
   }

   protected void isDone(Side result) {
      l.idDone(result);
   }
}
