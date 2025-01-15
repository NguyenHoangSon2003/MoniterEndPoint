package jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class thongtintrave {

    public String thongtintrave(String username, String password, String host, String command) {
        //Khai báo đối tượng Jsch
        JSch jsch = new JSch();
        Session session = null;
        ChannelExec channel = null;
        try {
            //Thiết Lập Phiên SSH:
            //Tại đây, một phiên SSH mới được tạo bằng thông tin đăng nhập đã cung cấp. 
            //Cấu hình StrictHostKeyChecking được đặt là "no" để bỏ qua việc kiểm tra khóa máy chủ. 
            //Phiên được thiết lập bằng phương thức connect().
            session = jsch.getSession(username, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            //Một kênh "exec" được mở để thực thi lệnh trên máy chủ từ xa. Lệnh được đặt bằng setCommand(), 
            //và đầu ra được ghi vào một ByteArrayOutputStream.
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            InputStream inputStream = channel.getInputStream();
                channel.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder responseString = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    responseString.append(line).append("<br>");
                }
            return responseString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
