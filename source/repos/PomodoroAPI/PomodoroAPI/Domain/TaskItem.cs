namespace PomodoroAPI.Domain
{
    public class TaskItem
    {
        public int TaskId { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }

        public bool IsCompleted { get; set; }
       
        // Relación con el usuario
        public int UserId { get; set; }
        public User User { get; set; }

        // Relación con sesiones Pomodoro
        public ICollection<PomodoroSession> PomodoroSessions { get; set; }
        public DateTime CreatedAt { get; set; }
        public DateTime UpdatedAt { get; set; }
        public DateTime DeletedAt { get; set;}
    }

}
