namespace PomodoroAPI.Domain
{
    public class PomodoroSession
    {
       public int SessionId { get; set; }
       public DateTime StartTime { get; set; }
       public DateTime EndTime { get; set; }
       public int TaskId { get; set; }
       public TaskItem TaskItem { get; set; }
       public PomodoroSession() { }

       public PomodoroSession(DateTime startTime, DateTime endTime)
        {
           StartTime = startTime;
           EndTime = endTime;
        }

    }
}
