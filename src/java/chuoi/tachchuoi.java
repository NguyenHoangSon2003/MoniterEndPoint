package chuoi;

public class tachchuoi {

    public String extractValue(String input, String key) {
        if (input == null || key == null || input.isEmpty() || key.isEmpty()) {
            return "Không tìm thấy giá trị";
        }
        // Thêm dấu ":" vào key để tìm chính xác
        String searchKey = key + ": ";
        int startIndex = input.indexOf(searchKey);
        if (startIndex == -1) {
            return "Không tìm thấy giá trị"; // Key không tồn tại
        }

        startIndex += searchKey.length();
        int endIndex = input.indexOf("<br>", startIndex);
        if (endIndex == -1) {
            endIndex = input.length(); // Nếu không có "\n", lấy đến hết chuỗi
        }

        return input.substring(startIndex, endIndex).trim();
    }
    
    public String extractValueSpace(String input, String key) {
        if (input == null || key == null || input.isEmpty() || key.isEmpty()) {
            return "Không tìm thấy giá trị";
        }
//        String searchKey = key + ": ";
        int startIndex = input.indexOf(key);
        if (startIndex == -1) {
            return "Không tìm thấy giá trị"; // Key không tồn tại
        }

        startIndex += key.length();
        int endIndex = input.indexOf("G", startIndex);
        if (endIndex == -1) {
            endIndex = input.length(); // Nếu không có "\n", lấy đến hết chuỗi
        }

        return input.substring(startIndex, endIndex).trim();
    }
    
    //tach chuoi lệnh service --status-all
    public String extractValueGiaoThuc(String input, String serviceName) {
        // Tách từng dòng và kiểm tra trạng thái
        String[] lines = input.split("<br>");
        String status = "-";

        for (String line : lines) {
            line = line.trim();
            if (line.endsWith(serviceName)) {
                status = line.substring(1, 4).trim(); // Lấy ký hiệu trạng thái
                break;
            }
        }

        return status;
    }
}
