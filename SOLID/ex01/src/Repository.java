import java.util.List;

public interface Repository {
    void save(StudentRecord r);
    int count();
    List<StudentRecord> all();
}