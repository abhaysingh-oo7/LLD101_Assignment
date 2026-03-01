public class PaymentGateway implements PaymentService {
    public String charge(String studentId, double amount) {
        return "TXN-9001";
    }
}


// public class PaymentGateway {
//     public String charge(String studentId, double amount) {
//         // fake deterministic txn
//         return "TXN-9001";
//     }
// }
