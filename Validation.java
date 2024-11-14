public class Validation {
    // struct class thing for storing valid and invalidation reason cause i dont
    // know how to return a tuple in java
    boolean valid;
    String invalidationReason;

    Validation(boolean valid, String invalidationReason) {
        this.valid = valid;
        this.invalidationReason = invalidationReason;
    }
}
