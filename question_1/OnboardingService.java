import java.util.*;

public class OnboardingService {
    // private final FakeDb db;

    // public OnboardingService(FakeDb db) {
        // this.db = db;
    // }
    private final Repository repo;

    public OnboardingService(Repository repo){
        this.repo = repo;
    }

    private final RawParser parser = new RawParser();
    private final RawValidator validator = new RawValidator(Set.of("CSE", "AI", "SWE"));
    private final StudentPrinter printer = new StudentPrinter();
    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.

    public void registerFromRawInput(String raw) {
        // System.out.println("INPUT: " + raw);
        printer.printInput(raw);

        // Map<String,String> kv = new LinkedHashMap<>();

        // String[] parts = raw.split(";");
        // for (String p : parts) {
        // String[] t = p.split("=", 2);
        // if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        // }
        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        // validation inline, printing inline
        // List<String> errors = new ArrayList<>();
        // if (name.isBlank()) errors.add("name is required");
        // if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        // if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit))
        // errors.add("phone is invalid");
        // if (!(program.equals("CSE") || program.equals("AI") ||
        // program.equals("SWE"))) errors.add("program is invalid");

        // if (!errors.isEmpty()) {
        // System.out.println("ERROR: cannot register");
        // for (String e : errors) System.out.println("- " + e);
        // return;
        // }

        List<String> errors = validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            // System.out.println("ERROR: cannot register");
            // for (String e : errors)
            //     System.out.println("- " + e);
            // return;
            printer.printErrors(errors);
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repo.save(rec);

        // System.out.println("OK: created student " + id);
        // System.out.println("Saved. Total students: " + db.count());
        // System.out.println("CONFIRMATION:");
        // System.out.println(rec);
        printer.printSuccess(rec, repo.count());
    }
}
