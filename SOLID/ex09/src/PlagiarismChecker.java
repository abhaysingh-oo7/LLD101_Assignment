public class PlagiarismChecker implements PlagiarismService {
    @Override
    public int check(Submission s) {
        return (s.code.contains("class") ? 12 : 40);
    }
}



// public class PlagiarismChecker {
//     public int check(Submission s) {
//         // fake score: lower is "better", but pipeline adds it anyway (smell)
//         return (s.code.contains("class") ? 12 : 40);
//     }
// }
