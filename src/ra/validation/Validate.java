package ra.validation;

public class Validate {
    public static boolean isValidProductId(String productId) {
        // Kiểm tra độ dài của productId
        if (productId.length() != 5) {
            return false;
        }

        // Kiểm tra ký tự đầu tiên là 'P' và 4 ký tự tiếp theo là số
        if (productId.charAt(0) != 'P') {
            return false;
        }
        for (int i = 1; i < productId.length(); i++) {
            if (!Character.isDigit(productId.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
