public class Validation {
    final boolean valid;
    final String invalidationReason;

    Validation(boolean valid, String invalidationReason) {
        this.valid = valid;
        this.invalidationReason = invalidationReason;
    }
}
