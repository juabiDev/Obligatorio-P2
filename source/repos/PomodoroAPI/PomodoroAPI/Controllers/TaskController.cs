using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PomodoroAPI.Context;
using PomodoroAPI.Domain;

[Route("api/[controller]")]
[ApiController]
public class TaskItemsController : ControllerBase
{
    private readonly PomodoroContext _context;

    public TaskItemsController(PomodoroContext context)
    {
        _context = context;
    }

    // GET: api/TaskItems
    [HttpGet]
    public async Task<IActionResult> GetTaskItems()
    {
        var tasks = await _context.TaskItems.ToListAsync();
        return Ok(tasks);
    }

    // GET: api/TaskItems/5
    
    [HttpGet("{id}")]
    public async Task<IActionResult> GetTaskItem([FromRoute] int id)
    {
        var task = await _context.TaskItems.FindAsync(id);
        if (task == null)
        {
            return NotFound();
        }
        return Ok(task);
    }

    // POST: api/TaskItems

    [HttpPost]
    public async Task<IActionResult> CreateTaskItem([FromBody] TaskItem task)
    {
        await _context.TaskItems.AddAsync(task);
        await _context.SaveChangesAsync();
        return CreatedAtAction(nameof(GetTaskItems), new { id = task.TaskId }, task);
    }

    // PUT: api/TaskItems/5
    [HttpPut("{id}")]
    public async Task<IActionResult> UpdateTaskItem([FromRoute] int id, [FromBody] TaskItem task)
    {
        if (id != task.TaskId)
        {
            return BadRequest();
        }
        _context.Entry(task).State = EntityState.Modified;
        await _context.SaveChangesAsync();
        return NoContent();
    }

    // DELETE: api/TaskItems/5
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteTaskItem([FromRoute] int id)
    {
        var task = await _context.TaskItems.FindAsync(id);
        if (task == null)
        {
            return NotFound();
        }
        _context.TaskItems.Remove(task);
        await _context.SaveChangesAsync();
        return NoContent();
    }

}
